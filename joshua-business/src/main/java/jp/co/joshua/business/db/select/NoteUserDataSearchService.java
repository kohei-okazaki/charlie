package jp.co.joshua.business.db.select;

import java.util.List;

import org.springframework.data.domain.Pageable;

import jp.co.joshua.common.db.entity.NoteUserData;

/**
 * メモユーザ情報検索サービスインターフェース
 *
 * @version 1.0.0
 */
public interface NoteUserDataSearchService {

    List<NoteUserData> selectBySeqLoginIdAndLikeTitle(Integer seqLoginId, String title,
            Pageable pageable);

    long countBySeqLoginIdAndLikeTitle(Integer seqLoginId, String title);

}
