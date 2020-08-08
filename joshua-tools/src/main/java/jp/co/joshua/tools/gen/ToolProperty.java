package jp.co.joshua.tools.gen;

import java.util.ArrayList;
import java.util.List;

import jp.co.joshua.common.io.property.annotation.Property;

public class ToolProperty {

    /** バージョン情報 */
    @Property(name = "tool.version")
    private String version;
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
    /** 列挙一覧Excelまでのパス */
    @Property(name = "tool.enum.excel.path")
    private String enumExcelPath;
    /** 列挙作成対象シート名リスト */
    @Property(name = "tool.enum.sheets")
    private String enumSheets;

    /** 処理対象テーブルリスト */
    private List<String> targetTableList = new ArrayList<>();
    /** DML対象テーブルリスト */
    private List<String> dmlTableList = new ArrayList<>();
    /** 列挙作成対象シート名リスト */
    private List<String> enumSheetList = new ArrayList<>();

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

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

    public String getEnumExcelPath() {
        return enumExcelPath;
    }

    public void setEnumExcelPath(String enumExcelPath) {
        this.enumExcelPath = enumExcelPath;
    }

    public String getEnumSheets() {
        return enumSheets;
    }

    public void setEnumSheets(String enumSheets) {
        this.enumSheets = enumSheets;
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

    public List<String> getEnumSheetList() {
        return enumSheetList;
    }

    public void addEnumSheet(String enumSheet) {
        this.enumSheetList.add(enumSheet);
    }

}
