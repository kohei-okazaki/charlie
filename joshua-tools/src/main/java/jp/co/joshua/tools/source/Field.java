package jp.co.joshua.tools.source;

import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

import jp.co.joshua.common.util.StringUtil;
import jp.co.joshua.tools.source.type.AccessType;

/**
 * 自動生成JavaソースのFieldクラス
 *
 * @version 1.0.0
 */
public class Field {

    /** フィールド名 */
    private String name;
    /** コメント */
    private String comment;
    /** 型 */
    private Class<?> classType;
    /** アクセスタイプ */
    private AccessType accessType;
    /** アノテーションMap */
    private Map<Class<?>, String> annotationMap;

    /**
     * コンストラクタ
     *
     * @param name
     *            フィールド名
     * @param comment
     *            コメント
     * @param classType
     *            型
     */
    public Field(String name, String comment, Class<?> classType) {
        this(name, comment, classType, AccessType.PRIVATE, new HashMap<>());
    }

    /**
     * コンストラクタ
     *
     * @param name
     *            フィールド名
     * @param comment
     *            コメント
     * @param classType
     *            型
     * @param annotationMap
     *            AnnotationMap
     */
    public Field(String name, String comment, Class<?> classType,
            Map<Class<?>, String> annotationMap) {
        this(name, comment, classType, AccessType.PRIVATE, annotationMap);
    }

    /**
     * コンストラクタ
     *
     * @param name
     *            フィールド名
     * @param comment
     *            コメント
     * @param classType
     *            型
     * @param accessType
     *            アクセスタイプ
     * @param annotationMap
     *            AnnotationMap
     */
    public Field(String name, String comment, Class<?> classType,
            AccessType accessType,
            Map<Class<?>, String> annotationMap) {
        this.name = name;
        this.comment = comment;
        this.classType = classType;
        this.accessType = accessType;
        this.annotationMap = annotationMap;
    }

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

    public Class<?> getClassType() {
        return classType;
    }

    public void setClassType(Class<?> classType) {
        this.classType = classType;
    }

    @Override
    public String toString() {

        final String TAB = "    ";

        /* JavaDoc作成 */
        String javadocPrefix = "/**";
        String javadocSuffix = "*/";
        StringJoiner javadocBody = new StringJoiner(StringUtil.SPACE);
        javadocBody.add(javadocPrefix);
        javadocBody.add(comment);
        javadocBody.add(javadocSuffix);
        String javadoc = TAB + javadocBody.toString();

        /* annotation作成 */
        StringJoiner annotationBody = new StringJoiner(StringUtil.NEW_LINE);
        annotationMap.entrySet().stream().forEach(entry -> {
            if (StringUtil.isEmpty(entry.getValue())) {
                annotationBody.add(TAB + "@" + entry.getKey().getSimpleName());
            } else {
                annotationBody.add(TAB + "@" + entry.getKey().getSimpleName()
                        + "(" + entry.getValue() + ")");
            }
        });

        /* field作成 */
        StringJoiner fieldBody = new StringJoiner(StringUtil.SPACE);
        fieldBody.add(accessType.getValue());
        fieldBody.add(classType.getSimpleName());
        fieldBody.add(name);
        String field = TAB + fieldBody.toString() + ";";

        if (StringUtil.isEmpty(annotationBody.toString())) {
            return javadoc + StringUtil.NEW_LINE + field;
        }
        return javadoc + StringUtil.NEW_LINE + annotationBody
                + StringUtil.NEW_LINE + field;
    }
}
