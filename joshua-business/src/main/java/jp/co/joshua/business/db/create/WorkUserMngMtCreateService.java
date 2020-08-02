package jp.co.joshua.business.db.create;

import jp.co.joshua.common.db.entity.WorkUserMngMt;

/**
 * 勤怠ユーザ管理マスタ作成サービスインターフェース
 *
 * @version 1.0.0
 */
public interface WorkUserMngMtCreateService {

    /**
     * 勤怠ユーザ管理マスタを登録する
     *
     * @param entity
     *            勤怠ユーザ管理マスタ
     */
    void create(WorkUserMngMt entity);

}
