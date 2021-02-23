package at.markus.holidayorganiser.GUI.functionPane.showHolidayPane;

import at.markus.holidayorganiser.Worker;
import javafx.geometry.Rectangle2D;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.time.Year;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal=false)
public class ShowHolidayPane {
    static BorderPane showHoliday;
    static String name = "";
    final Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
    Worker worker;
    ShowHolidayNodes showHolidayNodes;


    public BorderPane createShowHolidayPane() {
        HBox button = new HBox();

        showHoliday = new BorderPane();
        showHoliday.setStyle("-fx-background-color: #36393f;");
        showHoliday.setPrefSize(1400, 100);
        showHoliday.setTop(button);

        return showHoliday;
    }

    private void setupPane(){
        worker = new Worker(name);
        showHolidayNodes = new ShowHolidayNodes(worker);
        HBox box = new HBox();
        box.setTranslateX((screenBounds.getWidth()-400)/2-600);
        box.setTranslateY(40);
        VBox labels = new VBox();
        labels.getChildren().add(showHolidayNodes.label("Urlaube uebrig:"));

        for(int i = Year.now().getValue();i>Year.now().getValue()-21;i--){
            if(i>=worker.getYear()){
                labels.getChildren().add(showHolidayNodes.yearDisplay(i,true));
            }else{
                labels.getChildren().add(showHolidayNodes.yearDisplay(i,false));
            }
        }
        labels.getChildren().add(showHolidayNodes.label("Alle restlichen tage:" + showHolidayNodes.getRestdays()));
        box.getChildren().add(labels);
        box.getChildren().add(showHolidayNodes.lastHolidays());

        showHoliday.setCenter(box);
    }
    
    public BorderPane getShowHoliday(String name) {
        this.name =name;
        setupPane();
        return showHoliday;
    }
}
