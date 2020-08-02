package jp.co.joshua.tools.excel;

import java.util.ArrayList;
import java.util.List;

import jp.co.joshua.tools.excel.type.CellPositionType;

/**
 * 自動生成ツールのRowクラス
 *
 * @version 1.0.0
 */
public class Row {

    /** セルリスト */
    private List<Cell> cellList = new ArrayList<>();

    public void addCell(Cell cell) {
        this.cellList.add(cell);
    }

    public List<Cell> getCellList() {
        return cellList;
    }

    public Cell getCell(CellPositionType type) {
        return this.cellList.get(type.getPosition());
    }
}
