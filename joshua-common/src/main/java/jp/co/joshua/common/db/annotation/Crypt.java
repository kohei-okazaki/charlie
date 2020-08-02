package jp.co.joshua.common.db.annotation;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * 暗号化されたフィールドであることを示すマーカーアノテーション
 *
 * @version 1.0.0
 */
@Documented
@Retention(RUNTIME)
@Target(FIELD)
public @interface Crypt {
    // マーカーインターフェース
}
