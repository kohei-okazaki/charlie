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
}
