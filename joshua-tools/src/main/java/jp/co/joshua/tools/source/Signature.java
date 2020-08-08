package jp.co.joshua.tools.source;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.StringJoiner;

/**
 * 自動生成JavaソースのSignatureクラス
 *
 * @version 1.0.0
 */
public class Signature {

    /** 引数を持つMap (クラス型→引数名) */
    private Map<Class<?>, String> argsMap = new LinkedHashMap<>();

    /**
     * 指定されたクラス型と引数名を追加する
     *
     * @param clazz
     *            クラス型
     * @param name
     *            引数名
     * @return Signature
     */
    public Signature addArgs(Class<?> clazz, String name) {
        argsMap.put(clazz, name);
        return this;
    }

    /**
     * argsMapを返す
     *
     * @return argsMap
     */
    public Map<Class<?>, String> getArgsMap() {
        return argsMap;
    }

    @Override
    public String toString() {

        if (argsMap.isEmpty()) {
            return "";
        }

        StringJoiner body = new StringJoiner(" ");
        argsMap.entrySet().forEach(
                e -> body.add(e.getKey().getSimpleName() + " " + e.getValue()));

        return body.toString();
    }

}
