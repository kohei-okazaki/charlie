package jp.co.joshua.tools.source;

/**
 * 自動生成JavaソースのImportクラス
 *
 * @version 1.0.0
 */
public class Import {

    /** クラス型 */
    private Class<?> clazz;

    /**
     * コンストラクタ
     *
     * @param clazz
     *            クラス型
     */
    public Import(Class<?> clazz) {
        this.clazz = clazz;
    }

    /**
     * コンストラクタ
     *
     * @param field
     *            フィールド情報
     */
    public Import(Field field) {
        this.clazz = field.getClassType();
    }

    @Override
    public String toString() {
        return "import " + clazz.getName() + ";";
    }

}
