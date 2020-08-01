package jp.co.joshua.tools.source.type;

import jp.co.joshua.common.type.BaseEnum;

/**
 * ClassType
 *
 * @version 1.0.0
 */
public enum ClassType implements BaseEnum {

    /** クラス */
    CLASS("class"),
    /** インターフェース */
    INTERFACE("interface"),
    /** アノテーション */
    ANNOTATION("@interface"),
    /** 列挙 */
    ENUM("enum");

    /** 値 */
    private String value;

    private ClassType(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return this.value;
    }

    public static ClassType of(String value) {
        return BaseEnum.of(ClassType.class, value);
    }

}
