package jp.co.joshua.tools.excel;

import java.util.ArrayList;
import java.util.List;

/**
 * 自動生成ツールのSheetクラス
 *
 * @version 1.0.0
 */
public class Sheet {

    /** 名前 */
    private String name;
    /** 行リスト */
    private List<Row> rowList = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Row> getRowList() {
        return rowList;
    }

    public void addRow(Row row) {
        this.rowList.add(row);
    }

}
