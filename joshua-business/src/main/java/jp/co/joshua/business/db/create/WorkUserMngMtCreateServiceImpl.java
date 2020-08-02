package jp.co.joshua.business.db.create;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.joshua.common.db.dao.WorkUserMngMtDao;
import jp.co.joshua.common.db.entity.WorkUserMngMt;

/**
 * 勤怠ユーザ管理マスタ作成サービス実装クラス
 * 
 * @version 1.0.0
 */
@Service
public class WorkUserMngMtCreateServiceImpl implements WorkUserMngMtCreateService {

    @Autowired
    private WorkUserMngMtDao dao;

    @Override
    public void create(WorkUserMngMt entity) {
        dao.insert(entity);
    }

}
