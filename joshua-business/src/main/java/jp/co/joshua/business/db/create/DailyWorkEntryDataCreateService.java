package jp.co.joshua.business.db.create;

import jp.co.joshua.common.db.entity.DailyWorkEntryData;

/**
 * 日別勤怠登録情報登録サービスインターフェース
 *
 * @version 1.0.0
 */
public interface DailyWorkEntryDataCreateService {

    void create(DailyWorkEntryData entity);

}
