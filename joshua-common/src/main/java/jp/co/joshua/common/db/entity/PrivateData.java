package jp.co.joshua.common.db.entity;

import jp.co.joshua.common.db.entity.BaseEntity;
import org.seasar.doma.Entity;
import org.seasar.doma.jdbc.entity.NamingType;
import org.seasar.doma.Id;
import org.seasar.doma.GeneratedValue;
import org.seasar.doma.GenerationType;
import java.lang.Integer;
import jp.co.joshua.common.log.annotation.Mask;
import jp.co.joshua.common.db.annotation.Crypt;
import java.lang.String;

/**
 * 個別ユーザ情報 Entity
 *
 * @version 1.0.0
 */
@Entity(naming = NamingType.SNAKE_UPPER_CASE)
public class PrivateData extends BaseEntity {

    /** ユーザ個別情報ID */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer seqPrivateDataId;
    /** ログインID */
    private Integer seqLoginId;
    /** メールアドレス */
    @Mask
    @Crypt
    private String mailAddress;
    /** ユーザ名 */
    private String userName;

    public void setSeqPrivateDataId(Integer seqPrivateDataId) {
        this.seqPrivateDataId = seqPrivateDataId;
    }

    public Integer getSeqPrivateDataId() {
        return seqPrivateDataId;
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

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

}