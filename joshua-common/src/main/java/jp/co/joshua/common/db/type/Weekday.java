package jp.co.joshua.common.db.type;

import jp.co.joshua.common.type.BaseEnum;
import org.seasar.doma.Domain;

/**
 * 曜日の列挙型
 *
 * @version 1.0.0
 */
@Domain(valueType = String.class, factoryMethod = "of")
public enum Weekday implements BaseEnum {

    /** 1:日曜日 */
    SUNDAY("1"),
    /** 2:月曜日 */
    MONDAY("2"),
    /** 3:火曜日 */
    TUESDAY("3"),
    /** 4:水曜日 */
    WEDNESDAY("4"),
    /** 5:木曜日 */
    THURSDAY("5"),
    /** 6:金曜日 */
    FRIDAY("6"),
    /** 7:土曜日 */
    SATURDAY("7");

    /** 値 */
    private String value;

    private Weekday(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }

     public static Weekday of(String value) {
          return BaseEnum.of(Weekday.class, value);
     }

}