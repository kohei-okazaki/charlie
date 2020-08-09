package jp.co.joshua.tools.gen;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

import org.seasar.doma.Domain;

import jp.co.joshua.common.type.BaseEnum;
import jp.co.joshua.common.util.FileUtil.FileExtension;
import jp.co.joshua.common.util.FileUtil.FileSeparator;
import jp.co.joshua.common.util.StringUtil;
import jp.co.joshua.tools.excel.Cell;
import jp.co.joshua.tools.excel.EnumExcelReader;
import jp.co.joshua.tools.excel.ExcelReader;
import jp.co.joshua.tools.excel.Row;
import jp.co.joshua.tools.source.Constructor;
import jp.co.joshua.tools.source.Field;
import jp.co.joshua.tools.source.Getter;
import jp.co.joshua.tools.source.Import;
import jp.co.joshua.tools.source.JavaSource;
import jp.co.joshua.tools.source.Method;
import jp.co.joshua.tools.source.Signature;
import jp.co.joshua.tools.source.type.AccessType;
import jp.co.joshua.tools.source.type.ClassType;
import jp.co.joshua.tools.util.ToolUtil;

/**
 * Domaで使用するEnumを自動生成するクラス
 *
 * @version 1.0.0
 */
public class EnumGenerator extends BaseGenerator {

    @Override
    protected ExcelReader getExcelReader() throws Exception {
        return new EnumExcelReader();
    }

    @Override
    List<GenerateFile> generateImpl() throws Exception {

        List<GenerateFile> list = new ArrayList<>();

        for (String enumSheetName : prop.getEnumSheetList()) {
            excel.activeSheet(enumSheetName);

            LOG.debug("Enum名:" + enumSheetName);

            JavaSource source = new JavaSource();

            Row packageRow = excel.getRowList().get(0);
            Cell packageCell = packageRow.getCellList().get(1);
            String packageName = packageCell.getValue();

            Row classRow = excel.getRowList().get(1);
            Cell classCell = classRow.getCellList().get(1);
            String className = classCell.getValue();

            Row classCommentRow = excel.getRowList().get(2);
            Cell classCommentCell = classCommentRow.getCellList().get(1);
            String classCommentName = classCommentCell.getValue();

            setCommonInfo(source, packageName, className, classCommentName);

            // Enumの設定
            for (Row row : excel.getRowList().subList(3, excel.getRowList().size())) {
                String comment = row.getCellList().get(0).getValue();
                String label = row.getCellList().get(1).getValue();
                String value = row.getCellList().get(2).getValue();
                source.addEnum(new jp.co.joshua.tools.source.Enum(label, value, comment));
            }

            // fieldの設定
            Field field = new Field("value", "値", String.class);
            source.addField(field);

            // Constructorの設定
            Signature signature = new Signature().addArgs(String.class, "value");
            Constructor constructor = new Constructor(AccessType.PRIVATE, enumSheetName,
                    signature);
            source.addConstructor(constructor);

            // getterの設定
            Getter getter = new Getter(field);
            getter.setOverride(true);
            source.addMethod(getter);

            // ofメソッドの設定
            Method ofMethod = new Method(null, AccessType.PUBLIC) {

                @Override
                public String getMethodName() {
                    return "of";
                }

                @Override
                public String toString() {

                    final String INDENT = "     ";

                    StringJoiner body = new StringJoiner(StringUtil.NEW_LINE);
                    body.add(INDENT + this.accessType.getValue()
                            + (isStatic ? " static" : "") + " " + enumSheetName + " "
                            + getMethodName() + "(String value) {");
                    body.add(INDENT + INDENT + "return BaseEnum.of(" + enumSheetName
                            + ".class, value);");
                    body.add(INDENT + "}");
                    return body.toString();
                }
            };
            ofMethod.setStatic(true);
            source.addMethod(ofMethod);

            GenerateFile generateFile = new GenerateFile();
            generateFile.setFileName(enumSheetName + FileExtension.JAVA.getValue());
            generateFile.setData(ToolUtil.toStrJavaSource(source, prop));
            generateFile.setOutputPath(prop.getBaseDir() + FileSeparator.SYSTEM.getValue()
                    + GenerateType.ENUM.getPath());
            list.add(generateFile);
        }

        return list;
    }

    /**
     * 共通情報を設定する
     *
     * @param source
     *            JavaSource
     * @param packageName
     * @param className
     * @param classComment
     */
    private void setCommonInfo(JavaSource source, String packageName, String className,
            String classComment) {

        // パッケージ
        source.setPackage(
                new jp.co.joshua.tools.source.Package(packageName));
        // ソースのクラスJavaDoc
        source.setClassJavaDoc(classComment);
        // ソースのクラス名
        source.setClassName(className);
        // ソースのクラスタイプ
        source.setClassType(ClassType.ENUM);
        // ソースのアクセス修飾子
        source.setAccessType(AccessType.PUBLIC);
        // 親Interface
        source.addImplInterface(BaseEnum.class);
        // 親InterfaceのImport
        source.addImport(new Import(BaseEnum.class));
        // クラスに付与するアノテーション
        source.addClassAnnotation(Domain.class,
                "(valueType = String.class, factoryMethod = \"of\")");
        // org.seasar.doma.DomainのImport
        source.addImport(new Import(Domain.class));
    }

}
