package jp.co.joshua.business.db.create;

import jp.co.joshua.common.db.entity.WorkUserHistMt;

/**
 * 勤怠ユーザ履歴マスタ作成サービスインターフェース
 *
 * @version 1.0.0
 */
public interface WorkUserHistMtCreateService {

    void create(WorkUserHistMt entity);

}
