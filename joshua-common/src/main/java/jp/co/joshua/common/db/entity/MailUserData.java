package jp.co.joshua.common.db.entity;

import org.seasar.doma.Entity;
import org.seasar.doma.GeneratedValue;
import org.seasar.doma.GenerationType;
import org.seasar.doma.Id;
import org.seasar.doma.jdbc.entity.NamingType;

import jp.co.joshua.common.db.annotation.Crypt;
import jp.co.joshua.common.log.annotation.Mask;

/**
 * メールユーザ情報 Entity
 *
 * @version 1.0.0
 */
@Entity(naming = NamingType.SNAKE_UPPER_CASE)
public class MailUserData extends BaseEntity {

    /** メールユーザID */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer seqMailUserId;
    /** ログインID */
    private Integer seqLoginId;
    /** メールアドレス */
    @Crypt
    @Mask
    private String mailAddress;

    public void setSeqMailUserId(Integer seqMailUserId) {
        this.seqMailUserId = seqMailUserId;
    }

    public Integer getSeqMailUserId() {
        return seqMailUserId;
    }

    public void setSeqLoginId(Integer seqLoginId) {
        this.seqLoginId = seqLoginId;
    }

    public Integer getSeqLoginId() {
        return seqLoginId;
    }

    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }

    public String getMailAddress() {
        return mailAddress;
    }

}