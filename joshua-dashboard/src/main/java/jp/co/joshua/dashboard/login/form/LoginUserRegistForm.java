package jp.co.joshua.dashboard.login.form;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import jp.co.joshua.common.log.annotation.Mask;

/**
 * ログインユーザ登録画面Form
 *
 * @version 1.0.0
 */
public class LoginUserRegistForm {

    /** パスワード */
    @Mask
    @NotBlank
    @Length(min = 3, max = 15)
    private String password;
    /** 確認用パスワード */
    @Mask
    @NotBlank
    @Length(min = 3, max = 15)
    private String confirmPassword;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

}
