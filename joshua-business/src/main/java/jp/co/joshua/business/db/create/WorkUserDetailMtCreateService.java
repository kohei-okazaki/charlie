package jp.co.joshua.business.db.create;

import jp.co.joshua.common.db.entity.WorkUserDetailMt;

/**
 * 勤怠ユーザ詳細マスタ作成サービスインターフェース
 *
 * @version 1.0.0
 */
public interface WorkUserDetailMtCreateService {

    /**
     * 勤怠ユーザ詳細マスタを登録する
     *
     * @param entity
     *            勤怠ユーザ詳細マスタ
     */
    void create(WorkUserDetailMt entity);

}
