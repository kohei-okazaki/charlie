package jp.co.joshua.business.db.select;

import java.util.List;

import org.springframework.data.domain.Pageable;

import jp.co.joshua.common.db.entity.WorkUserCompositeMt;

/**
 * 勤怠ユーザマスタ検索サービスインターフェース
 *
 * @version 1.0.0
 */
public interface WorkUserMtSearchService {

    long count();

    List<WorkUserCompositeMt> selectCompositeRegularMt();

    List<WorkUserCompositeMt> selectCompositeRegularMt(Pageable pageable);

    WorkUserCompositeMt selectByLoginIdAndMaxWorkUserMtId(Integer seqLoginId);

}
