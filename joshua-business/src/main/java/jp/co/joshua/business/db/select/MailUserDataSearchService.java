package jp.co.joshua.business.db.select;

import jp.co.joshua.common.db.entity.MailUserData;

/**
 * メールユーザ情報検索サービスインターフェース
 *
 * @version 1.0.0
 */
public interface MailUserDataSearchService {

    MailUserData selectBySeqLoginId(Integer seqLoginId);

}
