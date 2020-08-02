package jp.co.joshua.business.db.create;

import jp.co.joshua.common.db.entity.LoginUserData;

/**
 * ログインユーザ情報作成サービスインターフェース
 *
 * @version 1.0.0
 */
public interface LoginUserDataCreateService {

    /**
     * ログインユーザ情報を登録する
     *
     * @param entity
     *            ログインユーザ情報
     */
    void create(LoginUserData entity);

}
