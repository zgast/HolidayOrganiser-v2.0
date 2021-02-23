package at.markus.holidayorganiser.excel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class RestDaysAdding {

    private  final File excelFile =  new File("Data\\employee.xlsx");
    
    public  void createRestDays(int days, int year, String name){
        XSSFWorkbook book = null;
        XSSFSheet sheet = null;
        try {
            FileInputStream inp = new FileInputStream(excelFile);

            book = (XSSFWorkbook) WorkbookFactory.create(inp);

            for (int i = book.getNumberOfSheets() - 1; i > 0; i--) {
                XSSFSheet tmpSheet = book.getSheetAt(i);
                if (tmpSheet.getSheetName().equals(name)) {
                    sheet = book.getSheetAt(i);
                }
            }

            Row row = sheet.getRow(1);
            Cell cell= row.getCell(0);
            double holiday = cell.getNumericCellValue();

            boolean equalsYear = false;
            for (int i = sheet.getLastRowNum(); i >= 2; i--) {
                XSSFRow tmpRow = sheet.getRow(i);
                XSSFCell tmpCell = tmpRow.getCell(0);

                if (tmpCell.getNumericCellValue()==year) {
                    Cell tmpProof = tmpRow.getCell(tmpRow.getLastCellNum()-1);
                    Cell restDays = tmpRow.createCell(tmpRow.getLastCellNum());
                    if(tmpProof.getNumericCellValue()==year){
                        double value = holiday - (double)days;
                        restDays.setCellValue(value);
                    }else{
                        double tmpHoliday = tmpProof.getNumericCellValue();
                        double value = tmpHoliday- (double)days;
                        restDays.setCellValue(value);
                    }
                    equalsYear=true;
                }
            }

            if(!equalsYear){
                XSSFRow tmpRow = sheet.createRow(sheet.getLastRowNum()+1);
                Cell tmpCell = tmpRow.createCell(0);

                tmpCell.setCellValue(year);

                Cell restDays = tmpRow.createCell(tmpRow.getLastCellNum());
                double value = holiday - (double)days;
                restDays.setCellValue(value);
            }


            FileOutputStream out = new FileOutputStream(excelFile);

            book.write(out);
            out.close();
            book.close();


        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    public int getRestdays(String sheetName, int year) {
        XSSFWorkbook book = null;
        XSSFSheet sheet = null;

        double days = 0;
        try {
            FileInputStream inp = new FileInputStream(excelFile);

            book = (XSSFWorkbook) WorkbookFactory.create(inp);

            for (int i = book.getNumberOfSheets() - 1; i >= 0; i--) {
                XSSFSheet tmpSheet = book.getSheetAt(i);
                if (tmpSheet.getSheetName().equals(sheetName)) {
                    sheet = book.getSheetAt(i);
                }
            }

            boolean tmp = false;
            for (int i = sheet.getLastRowNum(); i >= 2; i--) {
                XSSFRow tmpRow = sheet.getRow(i);
                XSSFCell tmpCell = tmpRow.getCell(0);

                if (tmpCell.getNumericCellValue() == year) {
                    Cell tmpRowCell = tmpRow.getCell(tmpRow.getLastCellNum() - 1);
                    days = tmpRowCell.getNumericCellValue();
                    tmp = true;
                }
            }
            if (!tmp) {
                XSSFRow row = sheet.getRow(1);
                XSSFCell cell = row.getCell(0);
                days = cell.getNumericCellValue();
            }

            FileOutputStream out = new FileOutputStream(excelFile);

            book.write(out);
            out.close();
            book.close();


        }catch(IOException e){
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        return (int) days;

    }
}
