package jp.co.joshua.business.db.update;

import jp.co.joshua.common.db.entity.DailyWorkEntryData;

/**
 * 日別勤怠登録情報更新サービスインターフェース
 *
 * @version 1.0.0
 */
public interface DailyWorkEntryDataUpdateService {

    void update(DailyWorkEntryData entity);

}
