package jp.co.joshua.business.db.select;

import java.util.List;

import org.seasar.doma.jdbc.SelectOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import jp.co.joshua.common.db.dao.WorkUserHistMtDao;
import jp.co.joshua.common.db.entity.CompositeWorkHistMt;
import jp.co.joshua.common.db.util.DomaUtil;

/**
 * 勤怠ユーザ履歴マスタ検索サービス実装クラス
 *
 * @version 1.0.0
 */
@Service
public class WorkUserHistMtSearchServiceImpl implements WorkUserHistMtSearchService {

    @Autowired
    private WorkUserHistMtDao dao;

    @Override
    public long count() {
        return dao.count();
    }

    @Override
    public List<CompositeWorkHistMt> selectAllJoinRegularMt(Pageable pageable) {
        SelectOptions options = DomaUtil.createSelectOptions(pageable, false);
        return dao.selectAllJoinRegularMt(options);
    }

}
