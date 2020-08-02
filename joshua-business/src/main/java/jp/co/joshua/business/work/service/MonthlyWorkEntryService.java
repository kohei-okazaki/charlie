package jp.co.joshua.business.work.service;

import java.time.LocalDate;
import java.util.List;

import jp.co.joshua.business.work.dto.DailyWorkEntryDataDto;
import jp.co.joshua.common.exception.AppException;

/**
 * 当月勤怠登録画面サービスインタフェース
 *
 * @version 1.0.0
 */
public interface MonthlyWorkEntryService {

    LocalDate getTargetDate(String year, String month) throws AppException;

    List<Integer> getYearList(LocalDate targetDate);

    List<Integer> getMonthList();

    void executeEntry(LocalDate targetDate, Integer seqWorkUserMngMtId,
            List<DailyWorkEntryDataDto> dtoList);
}
