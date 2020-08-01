package jp.co.joshua.common.db.entity;

import org.seasar.doma.Entity;
import org.seasar.doma.GeneratedValue;
import org.seasar.doma.GenerationType;
import org.seasar.doma.Id;
import org.seasar.doma.jdbc.entity.NamingType;

/**
 * 勤怠ユーザマスタ Entity
 *
 * @version 1.0.0
 */
@Entity(naming = NamingType.SNAKE_UPPER_CASE)
public class WorkUserMt extends BaseEntity {

    /** 勤怠ユーザマスタID */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer seqWorkUserMtId;
    /** ログインID */
    private Integer seqLoginId;
    /** 定時情報マスタID */
    private Integer seqRegularWorkMtId;

    public void setSeqWorkUserMtId(Integer seqWorkUserMtId) {
        this.seqWorkUserMtId = seqWorkUserMtId;
    }

    public Integer getSeqWorkUserMtId() {
        return seqWorkUserMtId;
    }

    public void setSeqLoginId(Integer seqLoginId) {
        this.seqLoginId = seqLoginId;
    }

    public Integer getSeqLoginId() {
        return seqLoginId;
    }

    public void setSeqRegularWorkMtId(Integer seqRegularWorkMtId) {
        this.seqRegularWorkMtId = seqRegularWorkMtId;
    }

    public Integer getSeqRegularWorkMtId() {
        return seqRegularWorkMtId;
    }

}