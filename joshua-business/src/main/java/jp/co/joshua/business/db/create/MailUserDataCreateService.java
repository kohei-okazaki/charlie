package jp.co.joshua.business.db.create;

import jp.co.joshua.common.db.entity.MailUserData;

/**
 * メールユーザ情報作成サービスインターフェース
 *
 * @version 1.0.0
 */
public interface MailUserDataCreateService {

    void create(MailUserData entity);

}
