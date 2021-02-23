package at.markus.holidayorganiser.GUI.bars;

import javafx.geometry.Rectangle2D;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class WorkerList {
    private BorderPane main = new BorderPane();
    private final Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();

    public HBox workerlist(){
        HBox worker = new HBox();
        worker.setStyle("-fx-background-color: #2f3136;");

        Text Names= new Text();
        String name = "";

        Names.setWrappingWidth( 200);
        if(screenBounds.getWidth()<1500){
            Names.setWrappingWidth( 100);
        }

        File excelFile =  new File("Data\\employee.xlsx");
        XSSFWorkbook book = null;

        try {
            FileInputStream inp = new FileInputStream(excelFile);

            book = (XSSFWorkbook) WorkbookFactory.create(inp);

            XSSFSheet sheet= book.getSheetAt(0);
            Row row= sheet.getRow(0);

            for(int i= row.getLastCellNum()-1;0<i;i--){
                Cell cell = row.getCell(i);
                name+= cell.getStringCellValue();
                if(cell.getStringCellValue()!="")
                    name+="\n";
            }
            Names.setText(name);
            Names.setFill(Color.WHITE);
            Names.setStyle("-fx-font: 20 Calibri;");
            Names.setTranslateY(5);
            Names.setTranslateX(5);

            worker.getChildren().add(Names);

            FileOutputStream out = new FileOutputStream(excelFile);

            book.write(out);
            out.close();
            book.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return worker;
    }
    public  BorderPane getMain() {
        return main;
    }

    public void setMain(BorderPane main) {
        this.main = main;
    }
}
