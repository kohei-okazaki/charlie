package jp.co.joshua.business.work.service;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.joshua.business.db.create.DailyWorkEntryDataCreateService;
import jp.co.joshua.business.db.select.DailyWorkEntryDataSearchService;
import jp.co.joshua.business.db.update.DailyWorkEntryDataUpdateService;
import jp.co.joshua.business.work.WorkAuthStatus;
import jp.co.joshua.business.work.dto.DailyWorkEntryDataDto;
import jp.co.joshua.common.db.entity.DailyWorkEntryData;
import jp.co.joshua.common.util.DateUtil;
import jp.co.joshua.common.util.DateUtil.DateFormatType;
import jp.co.joshua.common.util.StringUtil;

/**
 * 当月勤怠登録画面サービス実装クラス
 *
 * @version 1.0.0
 */
@Service
public class MonthlyWorkEntryServiceImpl implements MonthlyWorkEntryService {

    /** 日別勤怠登録情報検索サービス */
    @Autowired
    private DailyWorkEntryDataSearchService dailyWorkEntryDataSearchService;
    /** 日別勤怠登録情報登録サービス */
    @Autowired
    private DailyWorkEntryDataCreateService dailyWorkEntryDataCreateService;
    /** 日別勤怠登録情報更新サービス */
    @Autowired
    private DailyWorkEntryDataUpdateService dailyWorkEntryDataUpdateService;

    @Override
    public LocalDate getTargetDate(String year, String month) {

        String targetYear = StringUtil.isEmpty(year)
                ? DateUtil.toString(DateUtil.getSysDate(), DateFormatType.YYYY)
                : year;

        String targetMonth = StringUtil.isEmpty(month)
                ? DateUtil.toString(DateUtil.getSysDate(), DateFormatType.MM)
                : month;

        return LocalDate.of(Integer.parseInt(targetYear),
                Integer.parseInt(targetMonth), 1);
    }

    @Override
    public List<Integer> getYearList(LocalDate targetDate) {
        int min = targetDate.minusYears(1).getYear();
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            list.add(min + i);
        }
        return list;
    }

    @Override
    public List<Integer> getMonthList() {
        return Stream.of(Month.class.getEnumConstants()).map(Month::getValue)
                .collect(Collectors.toList());
    }

    @Override
    public void executeEntry(LocalDate targetDate, Integer seqWorkUserMtId,
            List<DailyWorkEntryDataDto> dtoList) {

        // 既に登録された日別勤怠登録情報を検索
        List<DailyWorkEntryData> dailyWorkEntryDataList = dailyWorkEntryDataSearchService
                .getDailyWorkEntryDataList(targetDate, seqWorkUserMtId);

        for (DailyWorkEntryDataDto dailyWorkEntryDataDto : dtoList) {
            boolean isInsert = true;

            LocalDate date = DateUtil.toLocalDate(dailyWorkEntryDataDto.getBegin());
            for (DailyWorkEntryData entity : dailyWorkEntryDataList) {

                if (DateUtil.toLocalDate(entity.getBegin()).equals(date)) {
                    // 更新処理
                    entity.setBegin(dailyWorkEntryDataDto.getBegin());
                    entity.setEnd(dailyWorkEntryDataDto.getEnd());
                    entity.setStatus(WorkAuthStatus.STILL.getValue());
                    dailyWorkEntryDataUpdateService.update(entity);
                    isInsert = false;
                    break;
                }
            }

            if (isInsert) {
                // 登録処理
                DailyWorkEntryData entity = new DailyWorkEntryData();
                entity.setSeqWorkUserMtId(seqWorkUserMtId);
                entity.setBegin(dailyWorkEntryDataDto.getBegin());
                entity.setEnd(dailyWorkEntryDataDto.getEnd());
                entity.setStatus(WorkAuthStatus.STILL.getValue());
                dailyWorkEntryDataCreateService.create(entity);
            }
        }
    }

}
