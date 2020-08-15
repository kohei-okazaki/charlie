package jp.co.joshua.dashboard.work.form;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import jp.co.joshua.common.db.type.BusinessFlg;

/**
 * 1日あたりの勤怠データ
 *
 * @version 1.0.0
 */
public class DailyEntryForm {

    /** 日別勤怠登録情報ID */
    private Integer seqDailyWorkEntryDataId;
    /** 日にち */
    private String date;
    /** 営業日フラグ */
    private BusinessFlg businessFlg;
    /** 始業時間(時) */
    @Min(value = 0)
    @Max(value = 23)
    private Integer workBeginHour;
    /** 始業時間(分) */
    @Min(value = 0)
    @Max(value = 59)
    private Integer workBeginMinute;
    /** 終業時間(時) */
    @Min(value = 0)
    @Max(value = 23)
    private Integer workEndHour;
    /** 終業時間(分) */
    @Min(value = 0)
    @Max(value = 59)
    private Integer workEndMinute;
    /** 作業時間(時) */
    @Min(value = 0)
    @Max(value = 23)
    private Integer actualTimeHour;
    /** 作業時間(分) */
    @Min(value = 0)
    @Max(value = 59)
    private Integer actualTimeMinute;
    /** 休日出勤作業時間(時) */
    @Min(value = 0)
    @Max(value = 23)
    private Integer holidayWorkTimeHour;
    /** 休日出勤作業時間(分) */
    @Min(value = 0)
    @Max(value = 59)
    private Integer holidayWorkTimeMinute;

    public Integer getSeqDailyWorkEntryDataId() {
        return seqDailyWorkEntryDataId;
    }

    public void setSeqDailyWorkEntryDataId(Integer seqDailyWorkEntryDataId) {
        this.seqDailyWorkEntryDataId = seqDailyWorkEntryDataId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public BusinessFlg getBusinessFlg() {
        return businessFlg;
    }

    public void setBusinessFlg(BusinessFlg businessFlg) {
        this.businessFlg = businessFlg;
    }

    public Integer getWorkBeginHour() {
        return workBeginHour;
    }

    public void setWorkBeginHour(Integer workBeginHour) {
        this.workBeginHour = workBeginHour;
    }

    public Integer getWorkBeginMinute() {
        return workBeginMinute;
    }

    public void setWorkBeginMinute(Integer workBeginMinute) {
        this.workBeginMinute = workBeginMinute;
    }

    public Integer getWorkEndHour() {
        return workEndHour;
    }

    public void setWorkEndHour(Integer workEndHour) {
        this.workEndHour = workEndHour;
    }

    public Integer getWorkEndMinute() {
        return workEndMinute;
    }

    public void setWorkEndMinute(Integer workEndMinute) {
        this.workEndMinute = workEndMinute;
    }

    public Integer getActualTimeHour() {
        return actualTimeHour;
    }

    public void setActualTimeHour(Integer actualTimeHour) {
        this.actualTimeHour = actualTimeHour;
    }

    public Integer getActualTimeMinute() {
        return actualTimeMinute;
    }

    public void setActualTimeMinute(Integer actualTimeMinute) {
        this.actualTimeMinute = actualTimeMinute;
    }

    public Integer getHolidayWorkTimeHour() {
        return holidayWorkTimeHour;
    }

    public void setHolidayWorkTimeHour(Integer holidayWorkTimeHour) {
        this.holidayWorkTimeHour = holidayWorkTimeHour;
    }

    public Integer getHolidayWorkTimeMinute() {
        return holidayWorkTimeMinute;
    }

    public void setHolidayWorkTimeMinute(Integer holidayWorkTimeMinute) {
        this.holidayWorkTimeMinute = holidayWorkTimeMinute;
    }

}
