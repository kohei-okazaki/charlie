package jp.co.joshua.common.db.util;

import jp.co.joshua.common.db.entity.BaseEntity;

/**
 * Entity暗号化/復号インターフェース
 *
 * @version 1.0.0
 */
public interface EntityCrypter {

    /**
     * 指定されたEntityクラスの暗号化を行う
     *
     * @param entity
     *            基底Entityクラス
     */
    void encrypt(BaseEntity entity);

    /**
     * 指定されたEntityクラスの復号を行う
     *
     * @param entity
     *            基底Entityクラス
     */
    void decrypt(BaseEntity entity);

}
