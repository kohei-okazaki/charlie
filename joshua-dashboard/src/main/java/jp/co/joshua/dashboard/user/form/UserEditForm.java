package jp.co.joshua.dashboard.user.form;

import javax.validation.constraints.NotBlank;

import jp.co.joshua.common.log.annotation.Mask;

/**
 * ユーザ情報設定変更画面Form
 *
 * @version 1.0.0
 */
public class UserEditForm {

    /** パスワード変更フラグ */
    @NotBlank
    private String passwordEditFlag;
    /** パスワード */
    @Mask
    private String password;
    /** メールアドレス */
    @Mask
    @NotBlank
    private String mailAddress;
    /** ユーザ名 */
    private String userName;

    public String getPasswordEditFlag() {
        return passwordEditFlag;
    }

    public void setPasswordEditFlag(String passwordEditFlag) {
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}
