package jp.co.joshua.tools.gen;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

import jp.co.joshua.common.util.FileUtil.FileExtension;
import jp.co.joshua.common.util.FileUtil.FileSeparator;
import jp.co.joshua.common.util.StringUtil;
import jp.co.joshua.tools.excel.DmlExcelReader;
import jp.co.joshua.tools.excel.ExcelReader;
import jp.co.joshua.tools.excel.Row;

/**
 * DML自動生成クラス
 *
 * @version 1.0.0
 */
public class DmlGenerator extends BaseGenerator {

    @Override
    protected ExcelReader getExcelReader() throws Exception {
        return new DmlExcelReader();
    }

    @Override
    List<GenerateFile> generateImpl() throws Exception {

        List<GenerateFile> list = new ArrayList<>();

        for (String table : prop.getDmlTableList()) {
            excel.activeSheet(table);

            StringJoiner sj = new StringJoiner(StringUtil.NEW_LINE);

            List<Row> rowList = excel.getRowList();
            for (int i = 0; i < rowList.size(); i++) {

                if (i == 0) {
                    // 1行の場合
                    continue;
                }

                StringBuilder sb = new StringBuilder();
                sb.append("INSERT INTO " + table + " (");
                // ヘッダ部の作成
                String header = getOneLine(rowList.get(0), false);
                sb.append(header);
                sb.append(") VALUES (");
                // レコード部の場合
                Row row = rowList.get(i);
                String data = getOneLine(row, true);
                sb.append(data);
                sb.append(");");

                sj.add(sb.toString());
            }

            GenerateFile genFile = new GenerateFile();
            genFile.setFileName(table + FileExtension.SQL.getValue());
            genFile.setData(sj.toString());
            genFile.setOutputPath(prop.getBaseDir() + FileSeparator.SYSTEM.getValue()
                    + GenerateType.DML.getPath());

            list.add(genFile);
        }

        return list;
    }

    private String getOneLine(Row row, boolean isDataRecord) {
        StringJoiner sj = new StringJoiner(StringUtil.COMMA);
        String enclosedChar = isDataRecord ? "'" : "";
        row.getCellList().stream().forEach(e -> {
            sj.add(enclosedChar + e.getValue() + enclosedChar);
        });
        return sj.toString();
    }

}
