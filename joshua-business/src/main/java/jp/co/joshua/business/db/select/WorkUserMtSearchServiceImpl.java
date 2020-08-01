package jp.co.joshua.business.db.select;

import java.util.List;

import org.seasar.doma.jdbc.SelectOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import jp.co.joshua.common.db.dao.WorkUserMtDao;
import jp.co.joshua.common.db.entity.CompositeWorkUserMt;
import jp.co.joshua.common.db.entity.WorkUserMt;
import jp.co.joshua.common.db.util.DomaUtil;

/**
 * 勤怠ユーザマスタ検索サービス実装クラス
 *
 * @version 1.0.0
 */
@Service
public class WorkUserMtSearchServiceImpl implements WorkUserMtSearchService {

    @Autowired
    private WorkUserMtDao dao;

    @Override
    public long count() {
        return dao.count();
    }

    @Override
    public List<CompositeWorkUserMt> selectCompositeRegularMt() {
        return dao.selectCompositeRegularMt();
    }

    @Override
    public List<CompositeWorkUserMt> selectCompositeRegularMt(Pageable pageable) {
        SelectOptions option = DomaUtil.createSelectOptions(pageable, false);
        return dao.selectCompositeRegularMt(option);
    }

    @Override
    public CompositeWorkUserMt selectByLoginIdAndMaxWorkUserMtId(Integer seqLoginId) {
        return dao.selectByLoginIdAndMaxWorkUserMtId(seqLoginId);
    }

    @Override
    public WorkUserMt selectActiveBySeqLoginId(Integer seqLoginId) {
        return dao.selectActiveBySeqLoginId(seqLoginId);
    }

}
