package jp.co.joshua.business.work.service;

import java.time.LocalDate;
import java.util.List;

import jp.co.joshua.business.work.dto.DailyWorkEntryDataDto;

/**
 * 当月勤怠登録画面サービスインタフェース
 *
 * @version 1.0.0
 */
public interface MonthlyWorkEntryService {

    void executeEntry(LocalDate targetDate, Integer seqWorkUserMngMtId,
            List<DailyWorkEntryDataDto> dtoList);
}
