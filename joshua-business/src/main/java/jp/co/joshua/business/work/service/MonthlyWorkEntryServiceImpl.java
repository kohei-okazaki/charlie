package jp.co.joshua.business.work.service;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

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

}
