package jp.co.joshua.common.db.entity;

import org.seasar.doma.Entity;
import org.seasar.doma.jdbc.entity.NamingType;

/**
 * 日別勤怠登録情報 + 営業日マスタ + 勤怠ユーザ管理マスタ結合Entity
 * <ul>
 * <li>selectStatusList.sql</li>
 * </ul>
 *
 * @version 1.0.0
 */
@Entity(naming = NamingType.SNAKE_UPPER_CASE)
public class CompositeDailyWorkAuthStatusData extends BaseEntity {

    /** ログインID */
    private Integer seqLoginId;
    /** 勤怠ユーザ管理マスタID */
    private Integer seqWorkUserMngMtId;
    /** 対象年月の未承認件数 */
    private Integer nonAuthCount;
    /** 対象年月の承認済件数 */
    private Integer authCount;
    /** 対象年月の営業日件数 */
    private Integer businessDayCount;

    public Integer getSeqLoginId() {
        return seqLoginId;
    }

    public void setSeqLoginId(Integer seqLoginId) {
        this.seqLoginId = seqLoginId;
    }

    public Integer getSeqWorkUserMngMtId() {
        return seqWorkUserMngMtId;
    }

    public void setSeqWorkUserMngMtId(Integer seqWorkUserMngMtId) {
        this.seqWorkUserMngMtId = seqWorkUserMngMtId;
    }

    public Integer getNonAuthCount() {
        return nonAuthCount;
    }

    public void setNonAuthCount(Integer nonAuthCount) {
        this.nonAuthCount = nonAuthCount;
    }

    public Integer getAuthCount() {
        return authCount;
    }

    public void setAuthCount(Integer authCount) {
        this.authCount = authCount;
    }

    public Integer getBusinessDayCount() {
        return businessDayCount;
    }

    public void setBusinessDayCount(Integer businessDayCount) {
        this.businessDayCount = businessDayCount;
    }

}
