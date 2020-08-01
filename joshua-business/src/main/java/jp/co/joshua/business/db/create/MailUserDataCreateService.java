package jp.co.joshua.business.db.create;

import jp.co.joshua.common.db.entity.MailUserData;

/**
 * メールユーザ情報サービスインターフェース
 *
 * @version 1.0.0
 */
public interface MailUserDataCreateService {

    /**
     * メールユーザ情報を登録する
     *
     * @param mailUserData
     *            メールユーザ情報
     */
    void create(MailUserData mailUserData);
}
