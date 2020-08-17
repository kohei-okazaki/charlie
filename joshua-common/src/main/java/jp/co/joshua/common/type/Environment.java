package jp.co.joshua.common.type;

/**
 * 環境種別の列挙<br>
 * <ul>
 * <li>local環境</li>
 * <li>ec2環境</li>
 * </ul>
 *
 * @version 1.0.0
 */
public enum Environment implements BaseEnum {

    /** local環境 */
    LOCAL("local"),
    /** EC2環境 */
    EC2("ec2");

    private String value;

    private Environment(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return this.value;
    }

    public static Environment of(String value) {
        return BaseEnum.of(Environment.class, value);
    }

}
