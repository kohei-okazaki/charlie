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
    public List<CompositeDailyWorkEntryData> selectMonthList(LocalDate date,
            Integer seqWorkUserMngMtId) {

        return dao
                .selectDailyMtAndCalendarMtByDate(
                        DateUtil.toString(date, DateFormatType.YYYYMM_NOSEP),
                        seqWorkUserMngMtId);
    }

    @Override
    public List<DailyWorkEntryData> selectDailyWorkEntryDataList(LocalDate date,
            Integer seqWorkUserMngMtId) {

        return dao
                .selectDailyMtListByDate(
                        DateUtil.toString(date, DateFormatType.YYYYMM_NOSEP),
                        seqWorkUserMngMtId);
    }

}
