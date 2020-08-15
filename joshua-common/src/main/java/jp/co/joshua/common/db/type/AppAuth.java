package jp.co.joshua.common.db.type;

import jp.co.joshua.common.type.BaseEnum;
import org.seasar.doma.Domain;

/**
 * アプリの権限の列挙型
 *
 * @version 1.0.0
 */
@Domain(valueType = String.class, factoryMethod = "of")
public enum AppAuth implements BaseEnum {

    /** 00:管理者権限 */
    ADMIN("00"),
    /** 01:経理権限 */
    ACCOUNTING("01"),
    /** 02:一般権限 */
    COMMON("02");

    /** 値 */
    private String value;

    private AppAuth(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }

     public static AppAuth of(String value) {
          return BaseEnum.of(AppAuth.class, value);
     }

}