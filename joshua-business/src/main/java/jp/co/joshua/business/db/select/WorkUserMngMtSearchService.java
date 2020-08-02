package jp.co.joshua.business.db.select;

import java.util.List;

import org.springframework.data.domain.Pageable;

import jp.co.joshua.common.db.entity.CompositeWorkUserMt;
import jp.co.joshua.common.db.entity.WorkUserMngMt;

/**
 * 勤怠ユーザ管理マスタ検索サービスインタフェース
 * 
 * @version 1.0.0
 */
public interface WorkUserMngMtSearchService {

    long count();

    List<CompositeWorkUserMt> selectCompositeRegularMt(Pageable userMtPageable);

    WorkUserMngMt selectBySeqLoginId(Integer seqLoginId);

    CompositeWorkUserMt selectActiveRegularMt(Integer seqLoginId);

}
