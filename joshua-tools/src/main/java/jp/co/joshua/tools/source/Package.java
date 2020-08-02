package jp.co.joshua.tools.source;

/**
 * 自動生成JavaソースのPackageクラス
 *
 * @version 1.0.0
 */
public class Package {

    /** 値 */
    private String value;

    public Package(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        String prefix = "package ";
        String suffix = ";";
        return prefix + value + suffix;
    }

}
