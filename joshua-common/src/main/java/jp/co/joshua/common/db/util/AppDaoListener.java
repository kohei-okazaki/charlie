package jp.co.joshua.common.db.util;

import org.seasar.doma.jdbc.entity.EntityListener;
import org.seasar.doma.jdbc.entity.PreInsertContext;
import org.seasar.doma.jdbc.entity.PreUpdateContext;

import jp.co.joshua.common.db.entity.BaseEntity;
import jp.co.joshua.common.util.DateUtil;

/**
 * Daoのリスナークラス
 *
 * @param <T>
 *            Entity
 * @version 1.0.0
 */
public class AppDaoListener<T extends BaseEntity> implements EntityListener<T> {

    /**
     * デフォルトコンストラクタ
     */
    public AppDaoListener() {
    }

    @Override
    public void preInsert(T entity, PreInsertContext<T> context) {
        // バージョン情報の設定
        entity.setVersion(Integer.valueOf(1));
        // 登録日時の設定
        entity.setRegDate(DateUtil.getSysDate());
        // 更新日時の設定
        entity.setUpdateDate(DateUtil.getSysDate());
    }

    @Override
    public void preUpdate(T entity, PreUpdateContext<T> context) {
        // 更新日時の設定
        entity.setUpdateDate(DateUtil.getSysDate());
    }
}
