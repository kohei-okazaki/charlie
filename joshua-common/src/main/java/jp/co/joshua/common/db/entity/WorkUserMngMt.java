package jp.co.joshua.common.db.entity;

import org.seasar.doma.Entity;
import org.seasar.doma.GeneratedValue;
import org.seasar.doma.GenerationType;
import org.seasar.doma.Id;
import org.seasar.doma.jdbc.entity.NamingType;

/**
 * 勤怠ユーザ管理マスタ Entity
 *
 * @version 1.0.0
 */
@Entity(naming = NamingType.SNAKE_UPPER_CASE)
public class WorkUserMngMt extends BaseEntity {

    /** 勤怠ユーザ管理マスタID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer seqWorkUserMngMtId;
    /** ログインID */
    private Integer seqLoginId;
    /** 勤怠ユーザ詳細マスタID */
    private Integer seqWorkUserDetailMtId;

    public void setSeqWorkUserMngMtId(Integer seqWorkUserMngMtId) {
        this.seqWorkUserMngMtId = seqWorkUserMngMtId;
    }

    public Integer getSeqWorkUserMngMtId() {
        return seqWorkUserMngMtId;
    }

    public void setSeqLoginId(Integer seqLoginId) {
        this.seqLoginId = seqLoginId;
    }

    public Integer getSeqLoginId() {
        return seqLoginId;
    }

    public void setSeqWorkUserDetailMtId(Integer seqWorkUserDetailMtId) {
        this.seqWorkUserDetailMtId = seqWorkUserDetailMtId;
    }

    public Integer getSeqWorkUserDetailMtId() {
        return seqWorkUserDetailMtId;
    }

}