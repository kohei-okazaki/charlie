package jp.co.joshua.business.db.update;

import jp.co.joshua.common.db.entity.MailUserData;

/**
 * メールユーザ情報更新サービスインターフェース
 *
 * @version 1.0.0
 */
public interface MailUserDataUpdateService {

    /**
     * メールユーザ情報を更新する
     *
     * @param entity
     *            メールユーザ情報
     */
    void update(MailUserData entity);

}
