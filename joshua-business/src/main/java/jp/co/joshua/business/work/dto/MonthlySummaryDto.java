package jp.co.joshua.business.work.dto;

import java.math.BigDecimal;

/**
 * 月次集計勤怠情報Dto
 *
 * @version 1.0.0
 */
public class MonthlySummaryDto {

    /** 作業時間 */
    private BigDecimal actualTime;
    /** 残業時間 */
    private BigDecimal overTime;
    /** 深夜残業時間 */
    private BigDecimal lateOverTime;
    /** 休日出勤作業時間 */
    private BigDecimal holidayWorkTime;

    public BigDecimal getActualTime() {
        return actualTime;
    }

    public void setActualTime(BigDecimal actualTime) {
        this.actualTime = actualTime;
    }

    public BigDecimal getOverTime() {
        return overTime;
    }

    public void setOverTime(BigDecimal overTime) {
        this.overTime = overTime;
    }

    public BigDecimal getLateOverTime() {
        return lateOverTime;
    }

    public void setLateOverTime(BigDecimal lateOverTime) {
        this.lateOverTime = lateOverTime;
    }

    public BigDecimal getHolidayWorkTime() {
        return holidayWorkTime;
    }

    public void setHolidayWorkTime(BigDecimal holidayWorkTime) {
        this.holidayWorkTime = holidayWorkTime;
    }

}
