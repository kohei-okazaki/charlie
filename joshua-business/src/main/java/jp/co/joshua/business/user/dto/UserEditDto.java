package jp.co.joshua.business.user.dto;

import jp.co.joshua.common.log.annotation.Mask;

/**
 * ユーザ情報設定変更画面DTO
 *
 * @version 1.0.0
 */
public class UserEditDto {

    /** ログインID */
    private Integer seqLoginId;
    /** パスワード変更フラグ */
    private Boolean passwordEditFlag;
    /** パスワード */
    @Mask
    private String password;
    /** メールアドレス */
    @Mask
    private String mailAddress;

    public Integer getSeqLoginId() {
        return seqLoginId;
    }

    public void setSeqLoginId(Integer seqLoginId) {
        this.seqLoginId = seqLoginId;
    }

    public Boolean getPasswordEditFlag() {
        return passwordEditFlag;
    }

    public void setPasswordEditFlag(Boolean passwordEditFlag) {
        this.passwordEditFlag = passwordEditFlag;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMailAddress() {
        return mailAddress;
    }

    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }

}
