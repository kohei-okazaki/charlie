package jp.co.joshua.business.db.create;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.joshua.common.db.dao.WorkUserHistMtDao;
import jp.co.joshua.common.db.entity.WorkUserHistMt;

/**
 * 勤怠ユーザ履歴マスタ作成サービス実装クラス
 *
 * @version 1.0.0
 */
@Service
public class WorkUserHistMtCreateServiceImpl implements WorkUserHistMtCreateService {

    @Autowired
    private WorkUserHistMtDao dao;

    @Override
    public void create(WorkUserHistMt entity) {
        dao.insert(entity);
    }

}
