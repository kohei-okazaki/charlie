package jp.co.joshua.business.db.create;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.joshua.common.db.dao.WorkUserMtDao;
import jp.co.joshua.common.db.entity.WorkUserMt;

/**
 * 勤怠ユーザマスタ作成サービス実装クラス
 *
 * @version 1.0.0
 */
@Service
public class WorkUserMtCreateServiceImpl implements WorkUserMtCreateService {

    @Autowired
    private WorkUserMtDao dao;

    @Override
    public void create(WorkUserMt workUserMt) {
        dao.insert(workUserMt);
    }

}
