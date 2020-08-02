package jp.co.joshua.business.work;

import jp.co.joshua.common.type.BaseEnum;

/**
 * 勤怠承認ステータス
 *
 * @version 1.0.0
 */
public enum WorkAuthStatus implements BaseEnum {

    /** 未承認 */
    STILL("10"),
    /** 承認済 */
    DONE("20");

    /** 値 */
    private String value;

    private WorkAuthStatus(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return this.value;
    }

    public static WorkAuthStatus of(String value) {
        return BaseEnum.of(WorkAuthStatus.class, value);
    }

}
