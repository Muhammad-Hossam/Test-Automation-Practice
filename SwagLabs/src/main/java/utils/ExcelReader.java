package utils;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {
    //bad practice
    //row and col > 0

    private static final String Test_Data_path = "src/main/resources/test-data/";

    public static String getExcelData(String excelFileName, String sheetName, int rowNum, int colNum) {
        XSSFWorkbook workbook;
        XSSFSheet sheet;
        String cellData;

        try {
            workbook = new XSSFWorkbook(Test_Data_path + excelFileName);
            sheet = workbook.getSheet(sheetName);
            cellData = sheet.getRow(rowNum-1).getCell(colNum).getStringCellValue();
            return cellData;
        } catch (Exception e) {
            System.out.println("Error in reading excel file" + e.getMessage());
            return "";
        }
    }
}