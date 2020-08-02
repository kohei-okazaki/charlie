package jp.co.joshua.tools.db;

/**
 * 自動生成ExcelのColumn情報
 *
 * @version 1.0.0
 */
public class Column {

    /** カラム名 */
    private String name;
    /** コメント */
    private String comment;
    /** カラム定義 */
    private String type;
    /** プライマリー */
    private boolean isPrimary;
    /** シーケンス */
    private boolean isSequence;
    /** 暗号化 */
    private boolean isCrypt;
    /** NotNull制約 */
    private boolean isNotNull;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isPrimary() {
        return isPrimary;
    }

    public void setPrimary(boolean isPrimary) {
        this.isPrimary = isPrimary;
    }

    public boolean isSequence() {
        return isSequence;
    }

    public void setSequence(boolean isSequence) {
        this.isSequence = isSequence;
    }

    public boolean isCrypt() {
        return isCrypt;
    }

    public void setCrypt(boolean isCrypt) {
        this.isCrypt = isCrypt;
    }

    public boolean isNotNull() {
        return isNotNull;
    }

    public void setNotNull(boolean isNotNull) {
        this.isNotNull = isNotNull;
    }

}
