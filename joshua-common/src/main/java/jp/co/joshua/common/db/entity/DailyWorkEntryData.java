package jp.co.joshua.common.db.entity;

import java.time.LocalDate;

import org.seasar.doma.Entity;
import org.seasar.doma.GeneratedValue;
import org.seasar.doma.GenerationType;
import org.seasar.doma.Id;
import org.seasar.doma.jdbc.entity.NamingType;

/**
 * 日別勤怠登録情報 Entity
 *
 * @version 1.0.0
 */
@Entity(naming = NamingType.SNAKE_UPPER_CASE)
public class DailyWorkEntryData extends BaseEntity {

    /** 日別勤怠登録情報ID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer seqDailyWorkEntryDataId;
    /** 勤怠ユーザマスタID */
    private Integer seqWorkUserMtId;
    /** 勤怠日 */
    private LocalDate workDate;
    /** 始業時間(時) */
    private Integer beginHour;
    /** 始業時間(分) */
    private Integer beginMinute;
    /** 終業時間(時) */
    private Integer endHour;
    /** 終業時間(分) */
    private Integer endMinute;
    /** 承認ステータス */
    private String status;
    /** 備考 */
    private String note;

    public void setSeqDailyWorkEntryDataId(Integer seqDailyWorkEntryDataId) {
        this.seqDailyWorkEntryDataId = seqDailyWorkEntryDataId;
    }

    public Integer getSeqDailyWorkEntryDataId() {
        return seqDailyWorkEntryDataId;
    }

    public void setSeqWorkUserMtId(Integer seqWorkUserMtId) {
        this.seqWorkUserMtId = seqWorkUserMtId;
    }

    public Integer getSeqWorkUserMtId() {
        return seqWorkUserMtId;
    }

    public void setWorkDate(LocalDate workDate) {
        this.workDate = workDate;
    }

    public LocalDate getWorkDate() {
        return workDate;
    }

    public void setBeginHour(Integer beginHour) {
        this.beginHour = beginHour;
    }

    public Integer getBeginHour() {
        return beginHour;
    }

    public void setBeginMinute(Integer beginMinute) {
        this.beginMinute = beginMinute;
    }

    public Integer getBeginMinute() {
        return beginMinute;
    }

    public void setEndHour(Integer endHour) {
        this.endHour = endHour;
    }

    public Integer getEndHour() {
        return endHour;
    }

    public void setEndMinute(Integer endMinute) {
        this.endMinute = endMinute;
    }

    public Integer getEndMinute() {
        return endMinute;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getNote() {
        return note;
    }

}