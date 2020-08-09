package jp.co.joshua.tools.excel;

import java.io.File;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import jp.co.joshua.tools.gen.ToolProperty;

/**
 * Enum用のExcelを読み込むReaderクラス
 *
 * @version 1.0.0
 */
public class EnumExcelReader extends ExcelReader {

    @Override
    public Excel read(ToolProperty prop) throws Exception {

        Iterator<org.apache.poi.ss.usermodel.Sheet> sheetIte;
        try (Workbook wb = WorkbookFactory.create(new File(prop.getEnumExcelPath()))) {
            sheetIte = wb.sheetIterator();
        }

        Excel excel = new Excel();

        // シート毎の処理
        while (sheetIte.hasNext()) {
            Sheet excelSheet = new Sheet();
            org.apache.poi.ss.usermodel.Sheet sheet = sheetIte.next();

            if (!prop.getEnumSheetList().contains(sheet.getSheetName())) {
                // Enum生成処理対象外の場合、次のシートへ
                continue;
            }

            excelSheet.setName(sheet.getSheetName());
            Iterator<org.apache.poi.ss.usermodel.Row> rowIte = sheet.iterator();

            // パッケージ行~クラス名行~クラスコメント行を読み取る
            for (int i = 0; i < 3; i++) {
                addRow(rowIte, excelSheet);
            }

            // 4行目が空白なので読みすすめる
            rowIte.next();

            // Enumの実際の定数部分の行を読み取る
            while (rowIte.hasNext()) {
                addRow(rowIte, excelSheet);
            }

            excel.addSheet(excelSheet);
        }

        return excel;
    }

    /**
     * {@linkplain Excel}に各行の情報を設定する
     *
     * @param rowIte
     *            Excel行のIterator
     * @param excelSheet
     *            エクセルシート
     */
    private static void addRow(Iterator<org.apache.poi.ss.usermodel.Row> rowIte,
            Sheet excelSheet) {
        org.apache.poi.ss.usermodel.Row row = rowIte.next();
        Iterator<org.apache.poi.ss.usermodel.Cell> cellIterator = row
                .cellIterator();

        Row r = new Row();
        while (cellIterator.hasNext()) {
            org.apache.poi.ss.usermodel.Cell cell = cellIterator.next();
            String cellValue = cell.getStringCellValue();
            Cell c = new Cell(cellValue);
            r.addCell(c);
        }
        excelSheet.addRow(r);
    }

}
