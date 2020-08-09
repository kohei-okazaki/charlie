package jp.co.joshua.tools.source;

import java.util.Map.Entry;
import java.util.StringJoiner;

import jp.co.joshua.common.util.StringUtil;
import jp.co.joshua.tools.source.type.AccessType;

/**
 * 自動生成Javaソースのコンストラクタクラス
 *
 * @version 1.0.0
 */
public class Constructor {

    /** アクセスタイプ */
    private AccessType accessType;
    /** クラス名 */
    private String className;
    /** シグネチャ */
    private Signature signature;

    /**
     * コンストラクタ
     *
     * @param accessType
     *            アクセスタイプ
     * @param className
     *            クラス名
     * @param signature
     *            シグネチャ
     */
    public Constructor(AccessType accessType, String className, Signature signature) {
        this.accessType = accessType;
        this.className = className;
        this.signature = signature;
    }

    @Override
    public String toString() {
        final String INDENT = "    ";
        StringJoiner body = new StringJoiner(StringUtil.NEW_LINE);
        body.add(INDENT + accessType.getValue() + " " + className + "("
                + signature.toString() + ") {");
        for (Entry<Class<?>, String> entry : signature.getArgsMap().entrySet()) {
            body.add(INDENT + INDENT + "this." + entry.getValue() + " = "
                    + entry.getValue() + ";");
        }
        body.add(INDENT + "}");

        return body.toString();
    }

}
