package jp.co.joshua.business.db.select;

import java.util.List;

import org.springframework.data.domain.Pageable;

import jp.co.joshua.common.db.entity.CompositeWorkHistMt;

/**
 * 勤怠ユーザ履歴マスタ検索サービスインターフェース
 *
 * @version 1.0.0
 */
public interface WorkUserHistMtSearchService {

    long count();

    List<CompositeWorkHistMt> selectAllJoinRegularMt(Pageable userMtPageable);

}
