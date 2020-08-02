package jp.co.joshua.business.db.update;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.joshua.common.db.dao.WorkUserMngMtDao;
import jp.co.joshua.common.db.entity.WorkUserMngMt;

/**
 * 勤怠ユーザ管理マスタ更新サービス実装クラス
 * 
 * @version 1.0.0
 */
@Service
public class WorkUserMngMtUpdateServiceImpl implements WorkUserMngMtUpdateService {

    @Autowired
    private WorkUserMngMtDao dao;

    @Override
    public void update(WorkUserMngMt entity) {
        dao.update(entity);
    }

}
