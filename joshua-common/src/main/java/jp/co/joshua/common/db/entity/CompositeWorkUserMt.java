package jp.co.joshua.common.db.entity;

import org.seasar.doma.Entity;
import org.seasar.doma.jdbc.entity.NamingType;

/**
 * 勤怠ユーザマスタ + 定時情報マスタ結合Entity
 *
 * @version 1.0.0
 */
@Entity(naming = NamingType.SNAKE_UPPER_CASE)
public class CompositeWorkUserMt extends BaseEntity {

    /** 勤怠ユーザマスタID */
    private Integer seqWorkUserMtId;
    /** ログインID */
    private Integer seqLoginId;
    /** 定時情報マスタID */
    private Integer seqRegularWorkMtId;
    /** 始業時間(時) */
    private Integer beginHour;
    /** 始業時間(分) */
    private Integer beginMinute;
    /** 終業時間(時) */
    private Integer endHour;
    /** 終業時間(分) */
    private Integer endMinute;

    public Integer getSeqWorkUserMtId() {
        return seqWorkUserMtId;
    }

    public void setSeqWorkUserMtId(Integer seqWorkUserMtId) {
        this.seqWorkUserMtId = seqWorkUserMtId;
    }

    public Integer getSeqLoginId() {
        return seqLoginId;
    }

    public void setSeqLoginId(Integer seqLoginId) {
        this.seqLoginId = seqLoginId;
    }

    public Integer getSeqRegularWorkMtId() {
        return seqRegularWorkMtId;
    }

    public void setSeqRegularWorkMtId(Integer seqRegularWorkMtId) {
        this.seqRegularWorkMtId = seqRegularWorkMtId;
    }

    public Integer getBeginHour() {
        return beginHour;
    }

    public void setBeginHour(Integer beginHour) {
        this.beginHour = beginHour;
    }

    public Integer getBeginMinute() {
        return beginMinute;
    }

    public void setBeginMinute(Integer beginMinute) {
        this.beginMinute = beginMinute;
    }

    public Integer getEndHour() {
        return endHour;
    }

    public void setEndHour(Integer endHour) {
        this.endHour = endHour;
    }

    public Integer getEndMinute() {
        return endMinute;
    }

    public void setEndMinute(Integer endMinute) {
        this.endMinute = endMinute;
    }

}
