package jp.co.joshua.business.work.service;

import java.time.LocalDate;

/**
 * 当月勤怠登録画面サービスインタフェース
 *
 * @version 1.0.0
 */
public interface MonthlyWorkEntryService {

    LocalDate getTargetDate(String year, String month);

}
