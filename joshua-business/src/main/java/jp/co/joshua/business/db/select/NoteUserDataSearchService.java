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

    List<NoteUserData> selectBySeqLoginId(Integer seqLoginId, Pageable pageable);

    long countBySeqLoginId(Integer seqLoginId);

}
