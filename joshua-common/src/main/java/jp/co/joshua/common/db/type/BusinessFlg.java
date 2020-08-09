package jp.co.joshua.common.db.type;

import jp.co.joshua.common.type.BaseEnum;
import org.seasar.doma.Domain;

/**
 * 営業日フラグの列挙型
 *
 * @version 1.0.0
 */
@Domain(valueType = String.class, factoryMethod = "of")
public enum BusinessFlg implements BaseEnum {

    /** 1:営業日 */
    ON("1"),
    /** 0:非営業日 */
    OFF("0");

    /** 値 */
    private String value;

    private BusinessFlg(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }

     public static BusinessFlg of(String value) {
          return BaseEnum.of(BusinessFlg.class, value);
     }

}