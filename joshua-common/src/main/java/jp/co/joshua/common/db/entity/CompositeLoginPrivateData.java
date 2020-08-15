package jp.co.joshua.common.db.entity;

import org.seasar.doma.Entity;
import org.seasar.doma.jdbc.entity.NamingType;

import jp.co.joshua.common.db.type.AppAuth;

/**
 * ログインユーザ情報 + 個別ユーザ情報結合Entity<br>
 * <ul>
 * <li>selectLoginDataJoinPrivate.sql</li>
 * </ul>
 *
 * @version 1.0.0
 */
@Entity(naming = NamingType.SNAKE_UPPER_CASE)
public class CompositeLoginPrivateData extends BaseEntity {

    /** ログインID */
    private Integer seqLoginId;
    /** ユーザ名 */
    private String userName;
    /** アプリ権限 */
    private AppAuth appAuth;

    public Integer getSeqLoginId() {
        return seqLoginId;
    }

    public void setSeqLoginId(Integer seqLoginId) {
        this.seqLoginId = seqLoginId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public AppAuth getAppAuth() {
        return appAuth;
    }

    public void setAppAuth(AppAuth appAuth) {
        this.appAuth = appAuth;
    }

}
