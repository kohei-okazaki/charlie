package jp.co.joshua.tools.excel;

import java.util.ArrayList;
import java.util.List;

/**
 * 自動生成ツールのExcelクラス
 *
 * @version 1.0.0
 */
public class Excel {

    /** シートリスト */
    private List<Sheet> sheetList = new ArrayList<>();
    /** アクティブなシート */
    private Sheet currentSheet;

    public void addSheet(Sheet sheet) {
        this.sheetList.add(sheet);
    }

    public void activeSheet(String sheetName) {
        this.currentSheet = getSheet(sheetName);
    }

    public List<Row> getRowList() {
        return this.currentSheet.getRowList();
    }

    private Sheet getSheet(String sheetName) {
        return this.sheetList.stream()
                .filter(e -> e.getName().equals(sheetName))
                .findFirst()
                .orElse(null);
    }
}
