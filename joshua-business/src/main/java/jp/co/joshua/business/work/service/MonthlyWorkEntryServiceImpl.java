package jp.co.joshua.business.work.service;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

}
