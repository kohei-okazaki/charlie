package jp.co.joshua.business.db.select;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.joshua.common.db.dao.DailyWorkEntryDataDao;
import jp.co.joshua.common.db.entity.BusinessCalendarMt;
import jp.co.joshua.common.db.entity.DailyWorkEntryData;
import jp.co.joshua.common.util.CollectionUtil;

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
    @Autowired
    private BusinessCalendarMtSearchService businessCalendarMtSerachService;

    @Override
    public List<DailyWorkEntryData> getMonthList(Integer seqWorkUserMtId,
            LocalDate targetDate) {

        // 処理対象年月より、営業日マスタリストを検索
        List<BusinessCalendarMt> businessCalendarMtList = businessCalendarMtSerachService
                .selectByMonth(targetDate);

        LocalDate begin = CollectionUtil.getFirst(businessCalendarMtList).getDate();
        LocalDate end = CollectionUtil.getLast(businessCalendarMtList).getDate();

        // TODO SQLを改良する
        // DATE_FORMAT(BEGIN, '%Y%m') = /* begin */200810
        List<DailyWorkEntryData> dailyWorkEntryDataList = dao
                .selectBySeqWorkUserMtIdAndBetweenBegin(seqWorkUserMtId, begin, end);

        // TODO
        // dailyWorkEntryDataListとbusinessCalendarMtListをマージする
        return null;
    }

}
