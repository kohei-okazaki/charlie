package jp.co.joshua.common.db.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.seasar.doma.Entity;
import org.seasar.doma.jdbc.entity.NamingType;

import jp.co.joshua.common.db.type.BusinessFlg;
import jp.co.joshua.common.db.type.Weekday;
import jp.co.joshua.common.db.type.WorkAuthStatus;

/**
 * 日別勤怠登録情報 + 営業日マスタ結合Entity<br>
 * <ul>
 * <li>selectDailyMtAndCalendarMtByDate.sql</li>
 * </ul>
 *
 * @version 1.0.0
 */
@Entity(naming = NamingType.SNAKE_UPPER_CASE)
public class CompositeDailyWorkEntryData extends BaseEntity {

    /** 日別勤怠登録情報ID */
    private Integer seqDailyWorkEntryDataId;
    /** 勤怠ユーザ管理マスタID */
    private Integer seqWorkUserMngMtId;
    /** 始業時間 */
    private LocalDateTime begin;
    /** 終業時間 */
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
    private WorkAuthStatus workAuthStatus;
    /** 備考 */
    private String note;
    /** 日付 */
    private LocalDate date;
    /** 曜日 */
    private Weekday weekday;
    /** 営業日フラグ */
    private BusinessFlg businessFlg;

    public Integer getSeqDailyWorkEntryDataId() {
        return seqDailyWorkEntryDataId;
    }

    public void setSeqDailyWorkEntryDataId(Integer seqDailyWorkEntryDataId) {
        this.seqDailyWorkEntryDataId = seqDailyWorkEntryDataId;
    }

    public Integer getSeqWorkUserMngMtId() {
        return seqWorkUserMngMtId;
    }

    public void setSeqWorkUserMngMtId(Integer seqWorkUserMngMtId) {
        this.seqWorkUserMngMtId = seqWorkUserMngMtId;
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

    public WorkAuthStatus getWorkAuthStatus() {
        return workAuthStatus;
    }

    public void setWorkAuthStatus(WorkAuthStatus workAuthStatus) {
        this.workAuthStatus = workAuthStatus;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Weekday getWeekday() {
        return weekday;
    }

    public void setWeekday(Weekday weekday) {
        this.weekday = weekday;
    }

    public BusinessFlg getBusinessFlg() {
        return businessFlg;
    }

    public void setBusinessFlg(BusinessFlg businessFlg) {
        this.businessFlg = businessFlg;
    }

}
