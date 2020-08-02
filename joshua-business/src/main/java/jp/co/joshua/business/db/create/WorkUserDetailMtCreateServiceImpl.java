package jp.co.joshua.business.db.create;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.joshua.common.db.dao.WorkUserDetailMtDao;
import jp.co.joshua.common.db.entity.WorkUserDetailMt;

/**
 * 勤怠ユーザ詳細マスタ作成サービス実装クラス
 * 
 * @version 1.0.0
 */
@Service
public class WorkUserDetailMtCreateServiceImpl implements WorkUserDetailMtCreateService {

    @Autowired
    private WorkUserDetailMtDao dao;

    @Override
    public void create(WorkUserDetailMt entity) {
        dao.insert(entity);
    }

}
