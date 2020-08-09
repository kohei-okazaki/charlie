package jp.co.joshua.business.db.select;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.joshua.common.db.dao.WorkUserMngMtDao;
import jp.co.joshua.common.db.entity.CompositeWorkUserMt;
import jp.co.joshua.common.db.entity.WorkUserMngMt;

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
    public WorkUserMngMt selectBySeqLoginId(Integer seqLoginId) {
        return dao.selectBySeqLoginId(seqLoginId);
    }

    @Override
    public CompositeWorkUserMt selectActiveRegularMt(Integer seqLoginId) {
        return dao.selectActiveRegularMt(seqLoginId);
    }

}
