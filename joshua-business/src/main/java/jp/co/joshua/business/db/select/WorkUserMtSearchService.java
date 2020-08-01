package jp.co.joshua.business.db.select;

import java.util.List;

import org.springframework.data.domain.Pageable;

import jp.co.joshua.common.db.entity.CompositeWorkUserMt;
import jp.co.joshua.common.db.entity.WorkUserMt;

/**
 * 勤怠ユーザマスタ検索サービスインターフェース
 *
 * @version 1.0.0
 */
public interface WorkUserMtSearchService {

    long count();

    List<CompositeWorkUserMt> selectCompositeRegularMt();

    List<CompositeWorkUserMt> selectCompositeRegularMt(Pageable pageable);

    CompositeWorkUserMt selectByLoginIdAndMaxWorkUserMtId(Integer seqLoginId);

    WorkUserMt selectActiveBySeqLoginId(Integer seqLoginId);

}
