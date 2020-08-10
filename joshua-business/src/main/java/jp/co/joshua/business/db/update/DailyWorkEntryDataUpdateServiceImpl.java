package jp.co.joshua.business.db.update;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.joshua.common.db.dao.DailyWorkEntryDataDao;
import jp.co.joshua.common.db.entity.DailyWorkEntryData;
import jp.co.joshua.common.db.type.WorkAuthStatus;

/**
 * 日別勤怠登録情報更新サービス実装クラス
 *
 * @version 1.0.0
 */
@Service
public class DailyWorkEntryDataUpdateServiceImpl
        implements DailyWorkEntryDataUpdateService {

    @Autowired
    private DailyWorkEntryDataDao dao;

    @Override
    public void update(DailyWorkEntryData entity) {
        dao.update(entity);
    }

    @Override
    public void updateAuthDone(DailyWorkEntryData entity) {
        entity.setWorkAuthStatus(WorkAuthStatus.DONE);
        update(entity);
    }

    @Override
    public void updateAuthReject(DailyWorkEntryData entity) {
        entity.setWorkAuthStatus(WorkAuthStatus.STILL);
        update(entity);
    }

}
