package jp.co.joshua.dashboard.work.form;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

/**
 * 定時時刻登録Form
 *
 * @version 1.0.0
 */
public class RegularEntryForm {

    /** 始業時刻(時) */
    @NotBlank
    @Min(value = 0)
    @Max(value = 23)
    private String beginHour;
    /** 始業時刻(分) */
    @NotBlank
    @Min(value = 0)
    @Max(value = 59)
    private String beginMinute;
    /** 終業時刻(時) */
    @NotBlank
    @Min(value = 0)
    @Max(value = 23)
    private String endHour;
    /** 終業時刻(分) */
    @NotBlank
    @Min(value = 0)
    @Max(value = 59)
    private String endMinute;

    public String getBeginHour() {
        return beginHour;
    }

    public void setBeginHour(String beginHour) {
        this.beginHour = beginHour;
    }

    public String getBeginMinute() {
        return beginMinute;
    }

    public void setBeginMinute(String beginMinute) {
        this.beginMinute = beginMinute;
    }

    public String getEndHour() {
        return endHour;
    }

    public void setEndHour(String endHour) {
        this.endHour = endHour;
    }

    public String getEndMinute() {
        return endMinute;
    }

    public void setEndMinute(String endMinute) {
        this.endMinute = endMinute;
    }

}
