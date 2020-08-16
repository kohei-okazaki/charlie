package jp.co.joshua.business.work.component;

import java.math.BigDecimal;
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
import jp.co.joshua.business.work.dto.MonthlySummaryDto;
import jp.co.joshua.common.db.entity.CompositeDailyWorkEntryData;
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

    /** 勤怠ユーザ管理マスタ検索サービス */
    @Autowired
    private WorkUserMngMtSearchService mngSearchService;

    /**
     * ログインIDより適用中の定時情報マスタを返す
     *
     * @param seqLoginId
     *            ログインID
     * @return CompositeWorkUserMt
     * @throws AppException
     */
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

    /**
     * ログインIDより勤怠ユーザ管理マスタを返す
     *
     * @param seqLoginId
     *            ログインID
     * @return WorkUserMngMt
     */
    public WorkUserMngMt getWorkUserMngMtBySeqLoginId(Integer seqLoginId) {
        return mngSearchService.selectBySeqLoginId(seqLoginId);
    }

    /**
     * 指定した年と月から対象年月を返す
     *
     * @param year
     *            年
     * @param month
     *            月
     * @return 対象年月
     * @throws AppException
     *             アプリ例外
     */
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

    /**
     * 対象年月より年のリストを返す
     *
     * @param targetDate
     *            対象年月
     * @return 年のリスト
     */
    public List<Integer> getYearList(LocalDate targetDate) {
        int min = targetDate.minusYears(1).getYear();
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            list.add(min + i);
        }
        return list;
    }

    /**
     * 対象年月より月のリストを返す
     *
     * @return 月のリスト
     */
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
     * @see #getWorkTime(LocalTime, LocalTime)
     */
    public LocalTime getWorkTime(LocalDateTime begin, LocalDateTime end) {
        return getWorkTime(begin.toLocalTime(), end.toLocalTime());
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
    public LocalTime getWorkTime(LocalTime begin, LocalTime end) {
        long diff = DateUtil.diffLocalTimeByMinute(begin, end);
        return LocalTime.of((int) diff / 60, (int) diff % 60);
    }

    /**
     * 検査対象時間targetがwhenより未来かどうかを返す<br>
     * target > when
     *
     * @param target
     *            検査対象時間
     * @param when
     *            比較時間
     * @return 未来の場合True、それ以外の場合False
     */
    public boolean isFuture(LocalTime target, LocalTime when) {
        return target.isAfter(when);
    }

    /**
     * 日別勤怠登録情報 + 営業日マスタ結合Entityリストより、月次集計勤怠情報Dtoを返す
     *
     * @param entityList
     *            日別勤怠登録情報 + 営業日マスタ結合Entityのリスト
     * @return 月次集計勤怠情報Dto
     */
    public MonthlySummaryDto getMonthlySummaryDto(
            List<CompositeDailyWorkEntryData> entityList) {

        BigDecimal actualTime = BigDecimal.ZERO;
        BigDecimal overTime = BigDecimal.ZERO;
        BigDecimal lateOverTime = BigDecimal.ZERO;
        BigDecimal holidayWorkTime = BigDecimal.ZERO;

        for (CompositeDailyWorkEntryData entity : entityList) {
            if (entity.getActualTime() != null) {
                actualTime = actualTime
                        .add(DateUtil.toBigDecimal(entity.getActualTime()));
            }
            if (entity.getOverTime() != null) {
                overTime = overTime
                        .add(DateUtil.toBigDecimal(entity.getOverTime()));
            }
            if (entity.getLateOverTime() != null) {
                lateOverTime = lateOverTime
                        .add(DateUtil.toBigDecimal(entity.getLateOverTime()));
            }
            if (entity.getHolidayWorkTime() != null) {
                holidayWorkTime = holidayWorkTime
                        .add(DateUtil.toBigDecimal(entity.getHolidayWorkTime()));
            }
        }

        MonthlySummaryDto dto = new MonthlySummaryDto();
        dto.setActualTime(actualTime);
        dto.setOverTime(overTime);
        dto.setLateOverTime(lateOverTime);
        dto.setHolidayWorkTime(holidayWorkTime);
        return dto;
    }

}
