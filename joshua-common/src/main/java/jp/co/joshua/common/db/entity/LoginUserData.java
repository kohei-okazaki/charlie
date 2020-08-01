package jp.co.joshua.common.db.entity;

import java.time.LocalDate;

import org.seasar.doma.Entity;
import org.seasar.doma.GeneratedValue;
import org.seasar.doma.GenerationType;
import org.seasar.doma.Id;
import org.seasar.doma.jdbc.entity.NamingType;

/**
 * ログインユーザ情報 Entity
 *
 * @version 1.0.0
 */
@Entity(naming = NamingType.SNAKE_UPPER_CASE)
public class LoginUserData extends BaseEntity {

    /** ログインID */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer seqLoginId;
    /** パスワード */
    private String password;
    /** パスワード有効期限 */
    private LocalDate passwordExpire;

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

}