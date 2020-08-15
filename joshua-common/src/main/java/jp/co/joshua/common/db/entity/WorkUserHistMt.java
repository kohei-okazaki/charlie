package jp.co.joshua.common.db.entity;

import jp.co.joshua.common.db.entity.BaseEntity;
import org.seasar.doma.Entity;
import org.seasar.doma.jdbc.entity.NamingType;
import org.seasar.doma.Id;
import org.seasar.doma.GeneratedValue;
import org.seasar.doma.GenerationType;
import java.lang.Integer;

/**
 * 勤怠ユーザ履歴マスタ Entity
 *
 * @version 1.0.0
 */
@Entity(naming = NamingType.SNAKE_UPPER_CASE)
public class WorkUserHistMt extends BaseEntity {

    /** 勤怠ユーザ履歴マスタID */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer seqWorkUserHistMtId;
    /** 勤怠ユーザ管理マスタID */
    private Integer seqWorkUserMngMtId;
    /** 勤怠ユーザ詳細マスタID */
    private Integer seqWorkUserDetailMtId;
    /** ログインID */
    private Integer seqLoginId;
    /** 定時情報マスタID */
    private Integer seqRegularWorkMtId;

    public void setSeqWorkUserHistMtId(Integer seqWorkUserHistMtId) {
        this.seqWorkUserHistMtId = seqWorkUserHistMtId;
    }

    public Integer getSeqWorkUserHistMtId() {
        return seqWorkUserHistMtId;
    }

    public void setSeqWorkUserMngMtId(Integer seqWorkUserMngMtId) {
        this.seqWorkUserMngMtId = seqWorkUserMngMtId;
    }

    public Integer getSeqWorkUserMngMtId() {
        return seqWorkUserMngMtId;
    }

    public void setSeqWorkUserDetailMtId(Integer seqWorkUserDetailMtId) {
        this.seqWorkUserDetailMtId = seqWorkUserDetailMtId;
    }

    public Integer getSeqWorkUserDetailMtId() {
        return seqWorkUserDetailMtId;
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