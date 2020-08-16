package jp.co.joshua.common.util;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

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

    @Test
    public void testSyosu() {
        {
            LocalTime beginTime = LocalTime.of(10, 00);
            LocalTime endTime = LocalTime.of(11, 00);
            long diff = DateUtil.diffLocalTimeByMinute(beginTime, endTime);

            LocalTime actualTime = LocalTime.of((int) diff / 60, (int) diff % 60);

            System.out.println(actualTime);
            System.out.println(actualTime.getHour() + "."
                    + (actualTime.getMinute() * 100 / 60));
        }

        {
            LocalTime beginTime = LocalTime.of(10, 00);
            LocalTime endTime = LocalTime.of(10, 45);
            long diff = DateUtil.diffLocalTimeByMinute(beginTime, endTime);

            LocalTime actualTime = LocalTime.of((int) diff / 60, (int) diff % 60);
            System.out.println(actualTime);
            System.out.println(actualTime.getHour() + "."
                    + (actualTime.getMinute() * 100 / 60));
        }
        {
            LocalTime beginTime = LocalTime.of(10, 00);
            LocalTime endTime = LocalTime.of(11, 45);
            long diff = DateUtil.diffLocalTimeByMinute(beginTime, endTime);

            LocalTime actualTime = LocalTime.of((int) diff / 60, (int) diff % 60);
            System.out.println(actualTime);
            System.out.println(actualTime.getHour() + "."
                    + (actualTime.getMinute() * 100 / 60));
        }
        {
            LocalTime beginTime = LocalTime.of(10, 00);
            LocalTime endTime = LocalTime.of(18, 15);
            long diff = DateUtil.diffLocalTimeByMinute(beginTime, endTime);

            LocalTime actualTime = LocalTime.of((int) diff / 60, (int) diff % 60);
            System.out.println(actualTime);
            System.out.println(actualTime.getHour() + "."
                    + (actualTime.getMinute() * 100 / 60));
        }

        {

            List<BigDecimal> list = new ArrayList<>();
            {
                LocalTime beginTime = LocalTime.of(10, 00);
                LocalTime endTime = LocalTime.of(10, 15);
                long diff = DateUtil.diffLocalTimeByMinute(beginTime, endTime);
                LocalTime actualTime = LocalTime.of((int) diff / 60, (int) diff % 60);
                System.out.println(actualTime);
                BigDecimal b = new BigDecimal(actualTime.getHour() + "."
                        + (actualTime.getMinute() * 100 / 60));
                list.add(b);
            }
            {
                LocalTime beginTime = LocalTime.of(10, 00);
                LocalTime endTime = LocalTime.of(10, 15);
                long diff = DateUtil.diffLocalTimeByMinute(beginTime, endTime);
                LocalTime actualTime = LocalTime.of((int) diff / 60, (int) diff % 60);
                System.out.println(actualTime);
                BigDecimal b = new BigDecimal(actualTime.getHour() + "."
                        + (actualTime.getMinute() * 100 / 60));
                list.add(b);
            }
            {
                LocalTime beginTime = LocalTime.of(10, 00);
                LocalTime endTime = LocalTime.of(10, 15);
                long diff = DateUtil.diffLocalTimeByMinute(beginTime, endTime);
                LocalTime actualTime = LocalTime.of((int) diff / 60, (int) diff % 60);
                System.out.println(actualTime);
                BigDecimal b = new BigDecimal(actualTime.getHour() + "."
                        + (actualTime.getMinute() * 100 / 60));
                list.add(b);
            }
            {
                LocalTime beginTime = LocalTime.of(10, 00);
                LocalTime endTime = LocalTime.of(10, 15);
                long diff = DateUtil.diffLocalTimeByMinute(beginTime, endTime);
                LocalTime actualTime = LocalTime.of((int) diff / 60, (int) diff % 60);
                System.out.println(actualTime);
                BigDecimal b = new BigDecimal(actualTime.getHour() + "."
                        + (actualTime.getMinute() * 100 / 60));
                list.add(b);
            }
            BigDecimal result = list.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
            System.out.println(result);
        }
    }
}
