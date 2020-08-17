package jp.co.joshua.common.web.view;

import jp.co.joshua.common.type.BaseEnum;

/**
 * アプリの画面View定義の列挙<br>
 * 新しくControllerクラスのメソッドの戻り値の文字列が追加された場合、追加する
 *
 * @version 1.0.0
 */
public enum AppView implements BaseEnum {

    /** ログインView:/login/index */
    LOGIN_VIEW("/login/index", "/login"),
    /** TOPView:/common/top */
    TOP_VIEW("/common/top", "/top"),
    /** エラー画面のView */
    APP_ERROR_VIEW("/common/error", "/error"),
    /** ログインユーザ登録View:/login/regist */
    LOGIN_REGIST_VIEW("/login/regist", "/login/regist"),
    /** ログインユーザ登録確認View:/login/registconfirm */
    LOGIN_REGIST_CONFIRM_VIEW("/login/registconfirm", "/login/registconfirm"),
    /** ログインユーザ登録完了View:/login/registprocess */
    LOGIN_REGIST_PROCESS_VIEW("/login/registprocess", "/login/registprocess"),
    /** ログインユーザ設定変更View:/user/edit */
    USER_EDIT_VIEW("/user/edit", "/user/edit"),
    /** ログインユーザ設定変更確認View:/user/editconfirm */
    USER_EDIT_CONFIRM_VIEW("/user/editconfirm", "/user/editconfirm"),
    /** ログインユーザ設定変更完了View:/user/editprocess */
    USER_EDIT_PROCESS_VIEW("/user/editprocess", "editprocess"),
    /** ユーザ一覧画面View:/user/list */
    USER_LIST_VIEW("/user/list", "/user/list"),
    /** ユーザ権限付与画面View:/user/role */
    USER_ROLE_VIEW("/user/role", "/user/role"),
    /** 当月勤怠登録View:/work/month/entry */
    WORK_MONTH_ENTRY_VIEW("/work/monthentry", "/work/month/entry"),
    /** 定時情報登録画面View:/work/regularentry */
    WORK_REGULAR_ENTRY_VIEW("/work/regularentry", "/work/regular/entry"),
    /** 定時情報更新画面View:/work/regularedit */
    WORK_REGULAR_EDIT_VIEW("/work/regularedit", "regularedit"),
    /** ユーザ定時情報登録画面View:/work/userregularentry */
    WORK_USER_REGULAR_ENTRY_VIEW("/work/userregularentry", "/work/userregular/entry"),
    /** 勤怠承認画面-ユーザ一覧View:/work/authuserlist */
    WORK_AUTH_USER_LIST("/work/authuserlist", "/work/auth/userlist"),
    /** 勤怠承認画面-月別勤怠一覧View:/work/authmonthly */
    WORK_AUTH_MONTHLY("/work/authmonthly", "/work/auth/monthly"),
    /** メモ一覧画面View:/note/list */
    NOTE_LIST_VIEW("/note/list", "/note/list");

    /** パス */
    private String value;
    /** リダイレクトパス */
    private String redirectPath;

    private AppView(String value, String redirectPath) {
        this.value = value;
        this.redirectPath = redirectPath;
    }

    @Override
    public String getValue() {
        return this.value;
    }

    public String toRedirect() {
        return "redirect:" + this.redirectPath;
    }

}
