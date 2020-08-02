package jp.co.joshua.tools.db;

import java.util.ArrayList;
import java.util.List;

/**
 * 自動生成ExcelのTable情報
 *
 * @version 1.0.0
 */
public class Table {

    /** 論理名 */
    private String logicalName;
    /** 物理名 */
    private String physicalName;
    /** カラムリスト */
    private List<Column> columnList = new ArrayList<>();

    public String getLogicalName() {
        return logicalName;
    }

    public void setLogicalName(String logicalName) {
        this.logicalName = logicalName;
    }

    public String getPhysicalName() {
        return physicalName;
    }

    public void setPhysicalName(String physicalName) {
        this.physicalName = physicalName;
    }

    public List<Column> getColumnList() {
        return columnList;
    }

    public void setColumnList(List<Column> columnList) {
        this.columnList = columnList;
    }

    public void addColumn(Column column) {
        this.columnList.add(column);
    }

}
