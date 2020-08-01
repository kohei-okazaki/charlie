package jp.co.joshua.tools.gen;

import java.util.ArrayList;
import java.util.List;

import jp.co.joshua.common.io.property.annotation.Property;

public class ToolProperty {

    /** 基底パス */
    @Property(name = "tool.basedir")
    private String baseDir;
    /** 自動生成ツールExcelまでのパス */
    @Property(name = "tool.excel.path")
    private String excelPath;
    /** Daoで使用するSQLを格納するディレクトリ */
    @Property(name = "tool.dao.sql.dir.path")
    private String sqlDirPath;
    /** 処理対象テーブルリスト */
    @Property(name = "tool.target.tables")
    private String targetTables;
    /** DML対象テーブル */
    @Property(name = "tool.dml.tables")
    private String dmlTables;
    /** バージョン情報 */
    @Property(name = "tool.version")
    private String version;
    /** 処理対象テーブルリスト */
    private List<String> targetTableList = new ArrayList<>();
    /** DML対象テーブルリスト */
    private List<String> dmlTableList = new ArrayList<>();

    public String getBaseDir() {
        return baseDir;
    }

    public void setBaseDir(String baseDir) {
        this.baseDir = baseDir;
    }

    public String getExcelPath() {
        return excelPath;
    }

    public void setExcelPath(String excelPath) {
        this.excelPath = excelPath;
    }

    public String getSqlDirPath() {
        return sqlDirPath;
    }

    public void setSqlDirPath(String sqlDirPath) {
        this.sqlDirPath = sqlDirPath;
    }

    public String getTargetTables() {
        return targetTables;
    }

    public void setTargetTables(String targetTables) {
        this.targetTables = targetTables;
    }

    public String getDmlTables() {
        return dmlTables;
    }

    public void setDmlTables(String dmlTables) {
        this.dmlTables = dmlTables;
    }

    public List<String> getTargetTableList() {
        return targetTableList;
    }

    public void addTargetTable(String targetTable) {
        this.targetTableList.add(targetTable);
    }

    public List<String> getDmlTableList() {
        return dmlTableList;
    }

    public void addDmlTable(String dmlTable) {
        this.dmlTableList.add(dmlTable);
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

}
