package jp.co.joshua.business.db.select;

import java.time.LocalDate;
import java.util.List;

import jp.co.joshua.common.db.entity.CompositeDailyWorkEntryData;
import jp.co.joshua.common.db.entity.DailyWorkEntryData;

/**
 * 日別勤怠登録情報検索サービスインターフェース
 *
 * @version 1.0.0
 */
public interface DailyWorkEntryDataSearchService {

    List<CompositeDailyWorkEntryData> selectMonthList(LocalDate date,
            Integer seqWorkUserMngMtId);

    List<DailyWorkEntryData> selectDailyWorkEntryDataList(LocalDate date,
            Integer seqWorkUserMngMtId);
}
