package jp.co.joshua.business.note.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import jp.co.joshua.business.db.create.NoteUserDataCreateService;
import jp.co.joshua.business.db.select.NoteUserDataSearchService;
import jp.co.joshua.business.db.update.NoteUserDataUpdateService;
import jp.co.joshua.business.note.dto.NoteDto;
import jp.co.joshua.common.aws.AwsS3Wrapper;
import jp.co.joshua.common.db.entity.NoteUserData;
import jp.co.joshua.common.exception.AppException;
import jp.co.joshua.common.util.DateUtil;
import jp.co.joshua.common.util.DateUtil.DateFormatType;
import jp.co.joshua.common.util.FileUtil.FileExtension;
import jp.co.joshua.common.web.auth.login.SecurityContextWrapper;

/**
 * メモのサービス実装クラス
 *
 * @version 1.0.0
 */
@Service
public class NoteServiceImpl implements NoteService {

    /** {@linkplain SecurityContextWrapper} */
    @Autowired
    private SecurityContextWrapper securityWrapper;
    /** {@linkplain ModelMapper} */
    @Autowired
    private ModelMapper modelMapper;
    /** {@linkplain AwsS3Wrapper} */
    @Autowired
    private AwsS3Wrapper s3Wrapper;
    /** メモユーザ情報検索サービス */
    @Autowired
    private NoteUserDataSearchService noteUserDataSearchService;
    /** メモユーザ情報作成サービス */
    @Autowired
    private NoteUserDataCreateService noteUserDataCreateService;
    /** メモユーザ情報更新サービス */
    @Autowired
    private NoteUserDataUpdateService noteUserDataUpdateService;

    @Override
    public List<NoteDto> getNoteDtoList(String title, Pageable pageable)
            throws AppException {

        Integer seqLoginId = securityWrapper.getLoginAuthDto().get().getSeqLoginId();
        List<NoteUserData> noteList = noteUserDataSearchService
                .selectBySeqLoginIdAndLikeTitle(seqLoginId, title, pageable);

        List<NoteDto> noteDtoList = new ArrayList<>();
        for (NoteUserData note : noteList) {
            NoteDto dto = toNoteDto(note, false);
            noteDtoList.add(dto);
        }

        return noteDtoList;
    }

    @Override
    public void entryNote(NoteDto noteDto) throws AppException {

        String s3Key = getS3Key();
        s3Wrapper.putFile(s3Key, noteDto.getDetail());

        NoteUserData note = new NoteUserData();
        note.setSeqLoginId(securityWrapper.getLoginAuthDto().get().getSeqLoginId());
        note.setTitle(noteDto.getTitle());
        note.setS3Key(s3Key);

        noteUserDataCreateService.create(note);
    }

    @Override
    public NoteDto getNote(Integer seqNoteUserDataId) throws AppException {
        NoteUserData note = noteUserDataSearchService.selectById(seqNoteUserDataId);
        if (note == null) {
            return null;
        }
        return toNoteDto(note, true);
    }

    @Override
    public void editNote(NoteDto dto) throws AppException {

        String s3Key = getS3Key();
        s3Wrapper.putFile(s3Key, dto.getDetail());

        NoteUserData entity = noteUserDataSearchService
                .selectById(dto.getSeqNoteUserDataId());

        // 更新前のS3キーを退避
        String befS3Key = entity.getS3Key();

        entity.setS3Key(s3Key);
        entity.setTitle(dto.getTitle());

        noteUserDataUpdateService.update(entity);

        s3Wrapper.deleteS3Object(befS3Key);
    }

    /**
     * S3キーを返す
     *
     * @return S3キー
     */
    private String getS3Key() {
        return new StringJoiner("/")
                .add("note")
                .add(securityWrapper.getLoginAuthDto().get().getSeqLoginId().toString())
                .add(DateUtil.toString(DateUtil.getSysDate(),
                        DateFormatType.YYYYMMDDHHMMSS_NOSEP)
                        + FileExtension.TEXT.getValue())
                .toString();
    }

    /**
     * {@linkplain NoteDto}に変換する
     *
     * @param note
     *            メモユーザ情報
     * @param isDisp
     *            詳細を全表示する場合True、それ以外False
     * @return NoteDto
     * @throws AppException
     *             S3からファイル取得に失敗した場合
     */
    private NoteDto toNoteDto(NoteUserData note, boolean isDisp) throws AppException {

        try (InputStream is = s3Wrapper.getS3ObjectByKey(note.getS3Key());
                Reader r = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(r);) {

            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

            NoteDto noteDto = modelMapper.map(note, NoteDto.class);
            String detail = sb.toString();

            if (!isDisp && detail.length() > 30) {
                // 全表示しない設定、かつ内容が30文字以上の場合
                noteDto.setDetail(detail.substring(0, 30) + "...");
            } else {
                noteDto.setDetail(detail);
            }
            return noteDto;

        } catch (IOException e) {
            throw new AppException(e);
        }
    }

}
