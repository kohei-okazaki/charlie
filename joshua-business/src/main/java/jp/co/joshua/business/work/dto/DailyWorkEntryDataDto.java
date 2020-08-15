package jp.co.joshua.business.work.dto;

import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * 日別勤怠登録情報のDtoクラス
 *
 * @version 1.0.0
 */
public class DailyWorkEntryDataDto {

    /** 日別勤怠登録情報ID */
    private Integer seqDailyWorkEntryDataId;
    /** 日にち */
    private int day;
    /** 曜日 */
    private String weekDay;
    /** 営業日フラグ */
    private String businessFlg;
    /** 始業日時 */
    private LocalDateTime begin;
    /** 終業日時 */
    private LocalDateTime end;
    /** 作業時間 */
    private LocalTime actualTime;
    /** 残業時間 */
    private LocalTime overTime;
    /** 深夜残業時間 */
    private LocalTime lateOverTime;
    /** 休日出勤作業時間 */
    private LocalTime holidayWorkTime;
    /** 承認ステータス */
    private String status;

    public Integer getSeqDailyWorkEntryDataId() {
        return seqDailyWorkEntryDataId;
    }

    public void setSeqDailyWorkEntryDataId(Integer seqDailyWorkEntryDataId) {
        this.seqDailyWorkEntryDataId = seqDailyWorkEntryDataId;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public String getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(String weekDay) {
        this.weekDay = weekDay;
    }

    public String getBusinessFlg() {
        return businessFlg;
    }

    public void setBusinessFlg(String businessFlg) {
        this.businessFlg = businessFlg;
    }

    public LocalDateTime getBegin() {
        return begin;
    }

    public void setBegin(LocalDateTime begin) {
        this.begin = begin;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    public LocalTime getActualTime() {
        return actualTime;
    }

    public void setActualTime(LocalTime actualTime) {
        this.actualTime = actualTime;
    }

    public LocalTime getOverTime() {
        return overTime;
    }

    public void setOverTime(LocalTime overTime) {
        this.overTime = overTime;
    }

    public LocalTime getLateOverTime() {
        return lateOverTime;
    }

    public void setLateOverTime(LocalTime lateOverTime) {
        this.lateOverTime = lateOverTime;
    }

    public LocalTime getHolidayWorkTime() {
        return holidayWorkTime;
    }

    public void setHolidayWorkTime(LocalTime holidayWorkTime) {
        this.holidayWorkTime = holidayWorkTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
