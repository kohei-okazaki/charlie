package jp.co.joshua.business.db.select;

import java.util.List;

import org.seasar.doma.jdbc.SelectOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import jp.co.joshua.common.db.dao.WorkUserMngMtDao;
import jp.co.joshua.common.db.entity.CompositeWorkUserMt;
import jp.co.joshua.common.db.entity.WorkUserMngMt;
import jp.co.joshua.common.db.util.DomaUtil;

/**
 * 勤怠ユーザ管理マスタ検索サービス実装クラス
 * 
 * @version 1.0.0
 */
@Service
public class WorkUserMngMtSearchServiceImpl implements WorkUserMngMtSearchService {

    @Autowired
    private WorkUserMngMtDao dao;

    @Override
    public long count() {
        return dao.count();
    }

    @Override
    public List<CompositeWorkUserMt> selectCompositeRegularMt(Pageable pageable) {
        SelectOptions option = DomaUtil.createSelectOptions(pageable, false);
        return dao.selectCompositeRegularMt(option);
    }

    @Override
    public WorkUserMngMt selectBySeqLoginId(Integer seqLoginId) {
        return dao.selectBySeqLoginId(seqLoginId);
    }

    @Override
    public CompositeWorkUserMt selectActiveRegularMt(Integer seqLoginId) {
        return dao.selectActiveRegularMt(seqLoginId);
    }

}
