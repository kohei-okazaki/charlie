package jp.co.joshua.business.note.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import jp.co.joshua.business.db.select.NoteUserDataSearchService;
import jp.co.joshua.business.note.dto.NoteDto;
import jp.co.joshua.common.aws.AwsS3Wrapper;
import jp.co.joshua.common.db.entity.NoteUserData;
import jp.co.joshua.common.exception.AppException;
import jp.co.joshua.common.web.auth.login.SecurityContextWrapper;

/**
 * メモのサービス実装クラス
 *
 * @version 1.0.0
 */
@Service
public class NoteServiceImpl implements NoteService {

    /** 認証情報ラッパー */
    @Autowired
    private SecurityContextWrapper securityWrapper;
    /** {@linkplain ModelMapper} */
    @Autowired
    private ModelMapper modelMapper;
    /** メモユーザ情報検索サービス */
    @Autowired
    private NoteUserDataSearchService noteUserDataSearchService;
    /** {@linkplain AwsS3Wrapper} */
    private AwsS3Wrapper s3Wrapper;

    @Override
    public List<NoteDto> getNoteDtoList(String title, Pageable pageable)
            throws AppException {

        Integer seqLoginId = securityWrapper.getLoginAuthDto().get().getSeqLoginId();
        List<NoteUserData> noteList = noteUserDataSearchService
                .selectBySeqLoginIdAndLikeTitle(
                        seqLoginId, title, pageable);
        List<NoteDto> noteDtoList = new ArrayList<>();
        for (NoteUserData note : noteList) {
            try (InputStream is = s3Wrapper.getS3ObjectByKey(note.getS3Key());
                    Reader r = new InputStreamReader(is);
                    BufferedReader br = new BufferedReader(r);) {

                StringBuilder sb = new StringBuilder();
                String line = null;
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }

                NoteDto noteDto = modelMapper.map(note, NoteDto.class);
                noteDto.setDetail(sb.toString());
                noteDtoList.add(noteDto);

            } catch (IOException e) {
                throw new AppException(e);
            }
        }

        return noteDtoList;
    }

}
