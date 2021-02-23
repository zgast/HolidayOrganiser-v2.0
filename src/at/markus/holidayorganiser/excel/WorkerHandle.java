package at.markus.holidayorganiser.excel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;


public class WorkerHandle {
    private  final File excelFile =  new File("Data\\employee.xlsx");

    public  void deleteWorker(String sheetName) throws IOException {
        XSSFWorkbook book = null;

        String FilenameAndPath = "Data\\AppointmentFiles\\" + sheetName + ".csv";
        File file = new File(FilenameAndPath);
        file.delete();
        try{
        FileInputStream inp = new FileInputStream(excelFile);

            book = (XSSFWorkbook) WorkbookFactory.create(inp);

            for (int i = book.getNumberOfSheets() - 1; i >= 0; i--) {
                XSSFSheet tmpSheet = book.getSheetAt(i);
                if (tmpSheet.getSheetName().equals(sheetName)) {
                    book.removeSheetAt(i);
                }
            }
            XSSFSheet sheet = book.getSheetAt(0);
            XSSFRow row = sheet.getRow(0);

            for(int i= row.getLastCellNum()-1;0<i;i--){
                Cell cell = row.getCell(i);
                if(cell.getStringCellValue().equals(sheetName)){
                    cell.setCellValue("");
                }

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
    public  void createWorker(String Name, int Time, int day, int month, int year){

        String FilenameAndPath = "Data\\AppointmentFiles\\" + Name + ".csv";

        File file = new File(FilenameAndPath);
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        XSSFWorkbook workbook = null;

        XSSFSheet sheet = null;

        XSSFSheet Names= null;

        try {

            FileInputStream inp = new FileInputStream(excelFile);

            workbook = (XSSFWorkbook) WorkbookFactory.create(inp);

            Names = workbook.getSheetAt(0);
            sheet = workbook.createSheet(Name);

            int rowNum = 0;
            int cellNum = 0;

            sheet.setColumnWidth(0, 5000);
            sheet.setColumnWidth(1, 5000);

            Row rowOne = sheet.createRow(rowNum);
            Cell cellOneRowOne = rowOne.createCell(cellNum);
            cellOneRowOne.setCellValue(Name);

            Row rowTwo = sheet.createRow(rowNum+1);
            Cell cellTwo = rowTwo.createCell(cellNum);
            cellTwo.setCellValue(Time);
            Cell cell3 = rowTwo.createCell(cellNum+1);
            cell3.setCellValue(day);
            Cell cell4 = rowTwo.createCell(cellNum+2);
            cell4.setCellValue(month);
            Cell cell5 = rowTwo.createCell(cellNum+3);
            cell5.setCellValue(year);

            Row proofRow = Names.getRow(0);

            int max = proofRow.getLastCellNum();

            Cell proofCell = proofRow.createCell(max++);
            proofCell.setCellValue(Name);

            FileOutputStream out = new FileOutputStream(excelFile);

            workbook.write(out);
            out.close();
            workbook.close();


        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public int[] getStart(String name){
        int[] result = new int[3];
        FileInputStream inp = null;
        try {
            inp = new FileInputStream(excelFile);

            XSSFWorkbook book = (XSSFWorkbook) WorkbookFactory.create(inp);
            XSSFSheet sheet = null;
            for (int i = book.getNumberOfSheets() - 1; i > 0; i--) {
                XSSFSheet tmpSheet = book.getSheetAt(i);
                if (tmpSheet.getSheetName().equals(name)) {
                    sheet = book.getSheetAt(i);
                }
            }
            Row row = sheet.getRow(1);
            Cell cell = row.getCell(1);
            int joinDay = (int) cell.getNumericCellValue();
            result[0] = joinDay;
            Cell cell2 = row.getCell(2);
            int joinMonth = (int) cell2.getNumericCellValue();
            result[1] = joinMonth;
            Cell cell3 = row.getCell(3);
            int joinYear = (int) cell3.getNumericCellValue();
            result[2] = joinYear;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }
}
