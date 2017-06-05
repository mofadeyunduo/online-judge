package per.piers.onlineJudge.util;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashSet;

public class ExcelUtil {

    private boolean isValidExcelFile(File file) {
        return file.getName().endsWith("xls") || file.getName().endsWith("xlsx");
    }

    private Workbook getWorkbook(File file) throws IOException {
        Workbook wb = null;
        if (file.getName().endsWith("xls")) {  //Excel 2003
            wb = new HSSFWorkbook(new FileInputStream(file));
        } else if (file.getName().endsWith("xlsx")) {  // Excel 2007/2010
            wb = new XSSFWorkbook(new FileInputStream(file));
        }
        return wb;
    }

    public HashSet<String> readColumns(File excelFile, String columnName) throws IOException {
        if (!isValidExcelFile(excelFile)) throw new IllegalArgumentException("not a excel file");
        Workbook workbook = getWorkbook(excelFile);
        Sheet sheet = workbook.getSheetAt(0);
        Row row0 = sheet.getRow(0);
        if(row0 == null) return null;
        int index = -1;
        for (int i = 0; i < row0.getPhysicalNumberOfCells(); i++) {
            if (row0.getCell(i).getStringCellValue().equals(columnName)) {
                index = i;
                break;
            }
        }
        if (index == -1) return null;

        HashSet<String> columns = new HashSet<>(sheet.getPhysicalNumberOfRows());
        for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
            columns.add(sheet.getRow(i).getCell(index).getStringCellValue());
        }
        return columns;
    }

}
