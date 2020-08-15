package jp.co.joshua.common.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;

import jp.co.joshua.common.util.DateUtil.DateFormatType;

public class DateUtilTest {

    @Test
    public void testToString() {

        LocalDate ld = LocalDate.now();
        System.out.println(DateUtil.toString(ld, DateFormatType.YYYYMMDD));

        LocalDateTime ldt = LocalDateTime.now();
        System.out.println(DateUtil.toString(ldt, DateFormatType.YYYYMMDDHHMMSS));

        LocalTime lt = LocalTime.now();
        System.out.println(DateUtil.toString(lt, DateFormatType.HHMM));

    }

    @Test
    public void testDiff() {
        LocalTime beginTime = LocalTime.of(10, 00);
        LocalTime endTime = LocalTime.of(18, 30);
        long diff = DateUtil.diffLocalTimeByMinute(beginTime, endTime);
        System.out.println(diff);

        LocalTime actualTime = LocalTime.of((int) diff / 60, (int) diff % 60);
        System.out.println(actualTime);
    }
}
