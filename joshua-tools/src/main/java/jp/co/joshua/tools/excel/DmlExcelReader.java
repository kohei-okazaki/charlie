package jp.co.joshua.tools.excel;

import java.io.File;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import jp.co.joshua.tools.gen.ToolProperty;

/**
 * DML用のExcelを読み込むReaderクラス
 *
 * @version 1.0.0
 */
public class DmlExcelReader extends ExcelReader {

    @Override
    public Excel read(ToolProperty prop) throws Exception {

        Iterator<org.apache.poi.ss.usermodel.Sheet> sheetIte;
        try (Workbook wb = WorkbookFactory.create(new File(prop.getExcelPath()))) {
            sheetIte = wb.sheetIterator();
        }
        Excel excel = new Excel();

        // シート毎の処理
        while (sheetIte.hasNext()) {
            Sheet excelSheet = new Sheet();
            org.apache.poi.ss.usermodel.Sheet sheet = sheetIte.next();

            if ("TABLE_LIST".equals(sheet.getSheetName())) {
                // TABLE_LISTは読み込み対象外
                continue;
            }

            excelSheet.setName(sheet.getSheetName());
            Iterator<org.apache.poi.ss.usermodel.Row> rowIte = sheet.iterator();

            // 行毎の処理
            while (rowIte.hasNext()) {
                org.apache.poi.ss.usermodel.Row row = rowIte.next();

                int lastPosition = row.getLastCellNum();
                System.out.println("lastPosition=" + lastPosition);
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
            excel.addSheet(excelSheet);
        }

        return excel;
    }

}
