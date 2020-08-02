package jp.co.joshua.common.type;

/**
 * アプリで共通で使う"0"と"1"を扱う列挙
 *
 * @version 1.0.0
 */
public enum CommonFlag implements BaseEnum {

    /** true */
    TRUE("1"),
    /** false */
    FALSE("0");

    /** 値 */
    private String value;

    private CommonFlag(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return this.value;
    }

    public static CommonFlag of(String value) {
        return BaseEnum.of(CommonFlag.class, value);
    }
}
