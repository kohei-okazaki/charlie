package jp.co.joshua.business.db.update;

import jp.co.joshua.common.db.entity.LoginUserData;

/**
 * ログインユーザ情報更新サービスインターフェース
 *
 * @version 1.0.0
 */
public interface LoginUserDataUpdateService {

    void update(LoginUserData entity);

}
