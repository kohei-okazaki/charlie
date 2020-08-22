package jp.co.joshua.common.type;

/**
 * 暗号化アルゴリズムの列挙<br>
 * 適宜新しいアルゴリズムが追加された場合、本クラスに追加する
 *
 * @version 1.0.0
 */
public enum Algorithm implements BaseEnum {

    /** AES */
    AES("AES"),
    /** SHA-256 */
    SHA_256("SHA-256"),
    /** SHA-512 */
    SHA_512("SHA-512");

    private String value;

    private Algorithm(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return this.value;
    }

    public static Algorithm of(String value) {
        return BaseEnum.of(Algorithm.class, value);
    }

}
