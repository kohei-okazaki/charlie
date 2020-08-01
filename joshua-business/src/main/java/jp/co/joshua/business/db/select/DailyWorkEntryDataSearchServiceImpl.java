package jp.co.joshua.business.db.select;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.joshua.common.db.dao.DailyWorkEntryDataDao;
import jp.co.joshua.common.db.entity.CompositeDailyWorkEntryData;
import jp.co.joshua.common.db.entity.DailyWorkEntryData;
import jp.co.joshua.common.util.DateUtil;
import jp.co.joshua.common.util.DateUtil.DateFormatType;

/**
 * 日別勤怠登録情報検索サービス実装クラス
 *
 * @version 1.0.0
 */
@Service
public class DailyWorkEntryDataSearchServiceImpl
        implements DailyWorkEntryDataSearchService {

    @Autowired
    private DailyWorkEntryDataDao dao;

    @Override
    public List<CompositeDailyWorkEntryData> getMonthList(LocalDate targetDate,
            Integer seqWorkUserMtId) {

        List<CompositeDailyWorkEntryData> dailyWorkEntryDataList = dao
                .selectDailyMtAndCalendarMtByDate(
                        DateUtil.toString(targetDate, DateFormatType.YYYYMM_NOSEP),
                        seqWorkUserMtId);
        System.out.println(dailyWorkEntryDataList.size());
        return dailyWorkEntryDataList;
    }

    @Override
    public List<DailyWorkEntryData> getDailyWorkEntryDataList(LocalDate targetDate,
            Integer seqWorkUserMtId) {

        return dao
                .selectDailyMtListByDate(
                        DateUtil.toString(targetDate, DateFormatType.YYYYMM_NOSEP),
                        seqWorkUserMtId);
    }

}
