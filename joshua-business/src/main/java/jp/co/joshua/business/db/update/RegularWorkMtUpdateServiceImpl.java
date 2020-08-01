package jp.co.joshua.business.db.update;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.joshua.common.db.dao.RegularWorkMtDao;
import jp.co.joshua.common.db.entity.RegularWorkMt;

/**
 * 定時情報マスタ更新サービス実装クラス
 *
 * @version 1.0.0
 */
@Service
public class RegularWorkMtUpdateServiceImpl implements RegularWorkMtUpdateService {

    /** 定時情報マスタDao */
    @Autowired
    private RegularWorkMtDao dao;

    @Override
    public void update(RegularWorkMt regularWorkMt) {
        dao.update(regularWorkMt);
    }

}
