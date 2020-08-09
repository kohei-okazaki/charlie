package jp.co.joshua.common.db.type;

import jp.co.joshua.common.type.BaseEnum;
import org.seasar.doma.Domain;

/**
 * 勤怠承認状態の列挙型
 *
 * @version 1.0.0
 */
@Domain(valueType = String.class, factoryMethod = "of")
public enum WorkAuthStatus implements BaseEnum {

    /** 10:未承認 */
    STILL("10"),
    /** 20:承認済 */
    DONE("20");

    /** 値 */
    private String value;

    private WorkAuthStatus(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }

     public static WorkAuthStatus of(String value) {
          return BaseEnum.of(WorkAuthStatus.class, value);
     }

}