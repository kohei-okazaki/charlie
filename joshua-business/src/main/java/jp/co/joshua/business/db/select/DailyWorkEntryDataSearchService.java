package jp.co.joshua.business.db.select;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Pageable;

import jp.co.joshua.common.db.entity.CompositeDailyWorkAuthStatusData;
import jp.co.joshua.common.db.entity.CompositeDailyWorkEntryData;
import jp.co.joshua.common.db.entity.CompositeWorkAuthTargetData;
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

    List<CompositeDailyWorkAuthStatusData> selectStatusList(LocalDate date,
            Pageable pageable);

    List<CompositeWorkAuthTargetData> selectAuthTargetDataList(Integer seqLoginId,
            LocalDate date);

    DailyWorkEntryData selectById(Integer seqDailyWorkEntryDataId);

}
