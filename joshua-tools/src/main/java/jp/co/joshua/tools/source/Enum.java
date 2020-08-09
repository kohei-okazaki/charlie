package jp.co.joshua.tools.source;

import java.util.StringJoiner;

import jp.co.joshua.common.util.StringUtil;

/**
 * 自動生成JavaソースのEnumクラス
 *
 * @version 1.0.0
 */
public class Enum {

    /** 定義値 */
    private String label;
    /** 物理値 */
    private String value;
    /** コメント */
    private String comment;

    /**
     * コンストラクタ
     *
     * @param label
     *            定義値
     * @param value
     *            物理値
     * @param comment
     *            コメント
     */
    public Enum(String label, String value, String comment) {
        this.label = label;
        this.value = value;
        this.comment = comment;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {

        final String INDENT = "    ";

        /* JavaDoc作成 */
        String javadocPrefix = "/**";
        String javadocSuffix = "*/";
        StringJoiner javadocBody = new StringJoiner(StringUtil.SPACE);
        javadocBody.add(javadocPrefix);
        javadocBody.add(value + ":" + comment);
        javadocBody.add(javadocSuffix);
        String javadoc = INDENT + javadocBody.toString();

        /* Enum作成 */
        String enumBody = INDENT + label + "(\"" + value + "\")";

        return javadoc + StringUtil.NEW_LINE + enumBody;
    }

}
