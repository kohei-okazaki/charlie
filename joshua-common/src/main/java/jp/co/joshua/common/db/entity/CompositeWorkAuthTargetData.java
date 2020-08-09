package jp.co.joshua.common.db.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.seasar.doma.Entity;
import org.seasar.doma.jdbc.entity.NamingType;

import jp.co.joshua.common.db.type.BusinessFlg;
import jp.co.joshua.common.db.type.Weekday;
import jp.co.joshua.common.db.type.WorkAuthStatus;

/**
 * 日別勤怠登録情報を承認する情報を保持するEntity<br>
 * TODO このクラスを使うSQLを追加すること
 * <ul>
 * <li>selectAuthTargetDataList.sql</li>
 * </ul>
 *
 * @version 1.0.0
 */
@Entity(naming = NamingType.SNAKE_UPPER_CASE)
public class CompositeWorkAuthTargetData extends BaseEntity {

    /** 日別勤怠登録情報ID */
    private Integer seqDailyWorkEntryDataId;
    /** 勤怠ユーザ管理マスタID */
    private Integer seqWorkUserMngMtId;
    /** 始業時間 */
    private LocalDateTime begin;
    /** 終業時間 */
    private LocalDateTime end;
    /** 承認ステータス */
    private WorkAuthStatus workAuthStatus;
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

    public WorkAuthStatus getWorkAuthStatus() {
        return workAuthStatus;
    }

    public void setWorkAuthStatus(WorkAuthStatus workAuthStatus) {
        this.workAuthStatus = workAuthStatus;
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
