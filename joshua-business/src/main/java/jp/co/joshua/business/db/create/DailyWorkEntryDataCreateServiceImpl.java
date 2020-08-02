package jp.co.joshua.business.db.create;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.joshua.common.db.dao.DailyWorkEntryDataDao;
import jp.co.joshua.common.db.entity.DailyWorkEntryData;

/**
 * 日別勤怠登録情報登録サービス実装クラス
 *
 * @version 1.0.0
 */
@Service
public class DailyWorkEntryDataCreateServiceImpl
        implements DailyWorkEntryDataCreateService {

    @Autowired
    private DailyWorkEntryDataDao dao;

    @Override
    public void create(DailyWorkEntryData entity) {
        dao.insert(entity);
    }

}
