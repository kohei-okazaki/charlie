package jp.co.joshua.dashboard.user.form;

import jp.co.joshua.common.db.type.AppAuth;

/**
 * ユーザ権限画面Form
 *
 * @version 1.0.0
 */
public class UserRoleForm {

    /** ログインID */
    private Integer seqLoginId;
    /** アプリ権限 */
    private AppAuth appAuth;

    public Integer getSeqLoginId() {
        return seqLoginId;
    }

    public void setSeqLoginId(Integer seqLoginId) {
        this.seqLoginId = seqLoginId;
    }

    public AppAuth getAppAuth() {
        return appAuth;
    }

    public void setAppAuth(AppAuth appAuth) {
        this.appAuth = appAuth;
    }

}
