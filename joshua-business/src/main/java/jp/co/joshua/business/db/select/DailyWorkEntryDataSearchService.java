package jp.co.joshua.business.db.select;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Pageable;

import jp.co.joshua.common.db.entity.CompositeDailyWorkAuthData;
import jp.co.joshua.common.db.entity.CompositeDailyWorkEntryData;
import jp.co.joshua.common.db.entity.DailyWorkEntryData;

/**
 * 日別勤怠登録情報検索サービスインターフェース
 *
 * @version 1.0.0
 */
public interface DailyWorkEntryDataSearchService {

    List<CompositeDailyWorkEntryData> selectDailyMtAndCalendarMtByDate(LocalDate date,
            Integer seqWorkUserMngMtId);

    List<DailyWorkEntryData> selectDailyMtListByDate(LocalDate date,
            Integer seqWorkUserMngMtId);

    List<CompositeDailyWorkAuthData> selectStatusList(LocalDate date, Pageable pageable);

}
