package jp.co.joshua.common.db.util;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.joshua.common.db.entity.BaseEntity;
import jp.co.joshua.common.util.CollectionUtil;

/**
 * 復号処理をおこなうFunction<br>
 * 暗号化カラムに対し、本クラスが復号処理を行う
 *
 * @param <T>
 *            Entity
 * @version 1.0.0
 */
@Component
public class DecryptFunction<T extends BaseEntity> implements Function<Stream<T>, T> {

    /** Entity暗号化/復号処理 */
    @Autowired
    private EntityCrypter entityCrypter;

    @Override
    public T apply(Stream<T> t) {

        List<T> list = t.map(e -> {
            entityCrypter.decrypt(e);
            return e;
        }).collect(Collectors.toList());

        return CollectionUtil.isEmpty(list) ? null : list.get(0);
    }

}
