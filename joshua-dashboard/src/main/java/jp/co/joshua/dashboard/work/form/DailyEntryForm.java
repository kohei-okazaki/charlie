package jp.co.joshua.dashboard.work.form;

/**
 * 1日あたりの勤怠データ
 *
 * @version 1.0.0
 */
public class DailyEntryForm {

    /** 日にち */
    private Integer day;
    /** 始業時間(時) */
    private Integer workBeginHour;
    /** 始業時間(分) */
    private Integer workBeginMinute;
    /** 終業時間(時) */
    private Integer workEndHour;
    /** 終業時間(分) */
    private Integer workEndMinute;

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
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

}
