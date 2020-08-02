package jp.co.joshua.common.db.entity;

import org.seasar.doma.Entity;
import org.seasar.doma.GeneratedValue;
import org.seasar.doma.GenerationType;
import org.seasar.doma.Id;
import org.seasar.doma.jdbc.entity.NamingType;

/**
 * 勤怠ユーザ詳細マスタ Entity
 *
 * @version 1.0.0
 */
@Entity(naming = NamingType.SNAKE_UPPER_CASE)
public class WorkUserDetailMt extends BaseEntity {

    /** 勤怠ユーザ詳細マスタID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer seqWorkUserDetailMtId;
    /** 定時情報マスタID */
    private Integer seqRegularWorkMtId;

    public void setSeqWorkUserDetailMtId(Integer seqWorkUserDetailMtId) {
        this.seqWorkUserDetailMtId = seqWorkUserDetailMtId;
    }

    public Integer getSeqWorkUserDetailMtId() {
        return seqWorkUserDetailMtId;
    }

    public void setSeqRegularWorkMtId(Integer seqRegularWorkMtId) {
        this.seqRegularWorkMtId = seqRegularWorkMtId;
    }

    public Integer getSeqRegularWorkMtId() {
        return seqRegularWorkMtId;
    }

}