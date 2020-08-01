package jp.co.joshua.dashboard.login.form;

import javax.validation.constraints.NotEmpty;

/**
 * ログインFormクラス
 *
 * @version 1.0.0
 */
public class LoginForm {

    /** ID */
    @NotEmpty
    private String id;
    /** パスワード */
    @NotEmpty
    private String password;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
