package jp.co.joshua.business.work.component;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.joshua.business.db.select.WorkUserMngMtSearchService;
import jp.co.joshua.common.db.entity.CompositeWorkUserMt;
import jp.co.joshua.common.db.entity.WorkUserMngMt;
import jp.co.joshua.common.exception.AppException;
import jp.co.joshua.common.util.DateUtil;
import jp.co.joshua.common.util.DateUtil.DateFormatType;
import jp.co.joshua.common.util.StringUtil;

/**
 * 勤怠関連Componentクラス<br>
 * IDからDBを取得や勤怠アプリで共通して使うメソッドを管理するクラス
 *
 * @version 1.0.0
 */
@Component
public class WorkEntryComponent {

    @Autowired
    private WorkUserMngMtSearchService mngSearchService;

    public CompositeWorkUserMt getActiveRegularMtBySeqLoginId(Integer seqLoginId)
            throws AppException {

        CompositeWorkUserMt regularMt = mngSearchService
                .selectActiveRegularMt(seqLoginId);
        if (regularMt == null) {
            throw new AppException(
                    "このユーザに勤怠ユーザ管理マスタ、勤怠ユーザ詳細マスタが設定されていません seqLoginId="
                            + seqLoginId);
        }
        return regularMt;
    }

    public WorkUserMngMt getActiveWorkUserMtBySeqLoginId(Integer seqLoginId) {
        return mngSearchService.selectBySeqLoginId(seqLoginId);
    }

    public LocalDate getTargetDate(String year, String month) throws AppException {

        try {
            String targetYear = StringUtil.isEmpty(year)
                    ? DateUtil.toString(DateUtil.getSysDate(), DateFormatType.YYYY)
                    : year;

            String targetMonth = StringUtil.isEmpty(month)
                    ? DateUtil.toString(DateUtil.getSysDate(), DateFormatType.MM)
                    : month;

            return LocalDate.of(Integer.parseInt(targetYear),
                    Integer.parseInt(targetMonth), 1);

        } catch (@SuppressWarnings("unused") NumberFormatException e) {
            // 指定年月が数字以外の場合、システム日付を返す
            return LocalDate.of(
                    Integer.parseInt(DateUtil.toString(DateUtil.getSysDate(),
                            DateFormatType.YYYY)),
                    Integer.parseInt(
                            DateUtil.toString(DateUtil.getSysDate(), DateFormatType.MM)),
                    1);
        } catch (DateTimeException e) {
            throw new AppException("指定された日付が無効です. year=" + year + ",month=" + month, e);
        }
    }

    public List<Integer> getYearList(LocalDate targetDate) {
        int min = targetDate.minusYears(1).getYear();
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            list.add(min + i);
        }
        return list;
    }

    public List<Integer> getMonthList() {
        return Stream.of(Month.class.getEnumConstants()).map(Month::getValue)
                .collect(Collectors.toList());
    }

    /**
     * 指定した開始時間と終了時間から実作業時間を返す
     *
     * @param begin
     *            開始時間
     * @param end
     *            終了時間
     * @return 実作業時間
     */
    public LocalTime getWorkTime(LocalDateTime begin, LocalDateTime end) {
        long diff = DateUtil.diffLocalTimeByMinute(begin.toLocalTime(),
                end.toLocalTime());
        return LocalTime.of((int) diff / 60, (int) diff % 60);
    }

}
