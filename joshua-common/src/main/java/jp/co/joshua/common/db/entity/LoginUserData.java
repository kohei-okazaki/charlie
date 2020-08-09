package jp.co.joshua.common.db.entity;

import jp.co.joshua.common.db.entity.BaseEntity;
import org.seasar.doma.Entity;
import org.seasar.doma.jdbc.entity.NamingType;
import org.seasar.doma.Id;
import org.seasar.doma.GeneratedValue;
import org.seasar.doma.GenerationType;
import java.lang.Integer;
import java.lang.String;
import java.time.LocalDate;
import jp.co.joshua.common.db.type.AppAuth;

/**
 * ログインユーザ情報 Entity
 *
 * @version 1.0.0
 */
@Entity(naming = NamingType.SNAKE_UPPER_CASE)
public class LoginUserData extends BaseEntity {

    /** ログインID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer seqLoginId;
    /** パスワード */
    private String password;
    /** パスワード有効期限 */
    private LocalDate passwordExpire;
    /** アプリの権限 */
    private AppAuth appAuth;

    public void setSeqLoginId(Integer seqLoginId) {
        this.seqLoginId = seqLoginId;
    }

    public Integer getSeqLoginId() {
        return seqLoginId;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPasswordExpire(LocalDate passwordExpire) {
        this.passwordExpire = passwordExpire;
    }

    public LocalDate getPasswordExpire() {
        return passwordExpire;
    }

    public void setAppAuth(AppAuth appAuth) {
        this.appAuth = appAuth;
    }

    public AppAuth getAppAuth() {
        return appAuth;
    }

}