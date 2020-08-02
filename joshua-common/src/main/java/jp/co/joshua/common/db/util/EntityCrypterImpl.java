package jp.co.joshua.common.db.util;

import java.lang.reflect.Field;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import jp.co.joshua.common.db.annotation.Crypt;
import jp.co.joshua.common.db.entity.BaseEntity;
import jp.co.joshua.common.log.Logger;
import jp.co.joshua.common.log.LoggerFactory;
import jp.co.joshua.common.util.BeanUtil;
import jp.co.joshua.common.util.BeanUtil.AccessorType;

/**
 * Entityの暗号/復号処理クラス
 *
 * @version 1.0.0
 */
@Component
public class EntityCrypterImpl implements EntityCrypter {

    /** LOG */
    private static final Logger LOG = LoggerFactory.getLogger(EntityCrypterImpl.class);
    /** Crypter */
    @Autowired
    @Qualifier("aesCrypter")
    private Crypter crypter;

    @Override
    public void encrypt(BaseEntity entity) {

        try {

            for (Field field : BeanUtil.getFieldList(entity.getClass())) {
                if (!field.isAnnotationPresent(Crypt.class)) {
                    // 暗号化カラム出ない場合、次のフィールドへ
                    continue;
                }

                // 値を取得
                String value = (String) BeanUtil.getAccessor(field.getName(),
                        entity.getClass(), AccessorType.GETTER).invoke(entity);

                // 暗号化
                value = crypter.encrypt(value);

                // 値を設定
                BeanUtil.getAccessor(field.getName(), entity.getClass(),
                        AccessorType.SETTER).invoke(entity, value);
            }

        } catch (Exception e) {
            LOG.error("暗号化に失敗しました", e);
        }

    }

    @Override
    public void decrypt(BaseEntity entity) {

        try {
            for (Field field : entity.getClass().getDeclaredFields()) {
                if (!field.isAnnotationPresent(Crypt.class)) {
                    continue;
                }

                // 値を取得
                String value = (String) BeanUtil.getAccessor(field.getName(),
                        entity.getClass(), AccessorType.GETTER).invoke(entity);

                // 復号
                value = crypter.decrypt(value);

                // 値を設定
                BeanUtil.getAccessor(field.getName(), entity.getClass(),
                        AccessorType.SETTER).invoke(entity, value);
            }

        } catch (Exception e) {
            LOG.error("復号に失敗しました", e);
        }

    }

}
