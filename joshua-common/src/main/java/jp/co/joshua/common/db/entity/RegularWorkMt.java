package jp.co.joshua.common.db.entity;

import org.seasar.doma.Entity;
import org.seasar.doma.GeneratedValue;
import org.seasar.doma.GenerationType;
import org.seasar.doma.Id;
import org.seasar.doma.jdbc.entity.NamingType;

/**
 * 定時情報マスタ Entity
 *
 * @version 1.0.0
 */
@Entity(naming = NamingType.SNAKE_UPPER_CASE)
public class RegularWorkMt extends BaseEntity {

    /** 定時情報マスタID */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer seqRegularWorkMtId;
    /** 始業時間(時) */
    private Integer beginHour;
    /** 始業時間(分) */
    private Integer beginMinute;
    /** 終業時間(時) */
    private Integer endHour;
    /** 終業時間(分) */
    private Integer endMinute;

    public void setSeqRegularWorkMtId(Integer seqRegularWorkMtId) {
        this.seqRegularWorkMtId = seqRegularWorkMtId;
    }

    public Integer getSeqRegularWorkMtId() {
        return seqRegularWorkMtId;
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

}