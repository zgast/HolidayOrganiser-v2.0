package at.markus.holidayorganiser.GUI;

import at.markus.holidayorganiser.GUI.bars.Leftbar;
import at.markus.holidayorganiser.GUI.functionPane.createHolidayPane.CreateHolidayPane;
import at.markus.holidayorganiser.GUI.functionPane.setHolidayPane;
import at.markus.holidayorganiser.GUI.functionPane.CreateWorker;
import at.markus.holidayorganiser.GUI.functionPane.RestDays;
import at.markus.holidayorganiser.GUI.functionPane.DeleteWorker;
import at.markus.holidayorganiser.GUI.functionPane.showHolidayPane.ShowHolidayPane;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal=true)
public class FunctionPane extends Node {
    Leftbar Leftbar = new Leftbar();
    static VBox FunctionPane = new VBox();
    setHolidayPane setHolidayPane = new setHolidayPane();
    RestDays Restdays = new RestDays();
    DeleteWorker deleteWorker = new DeleteWorker();
    CreateWorker createWorker = new CreateWorker();
    CreateHolidayPane createHolidayPane = new CreateHolidayPane();
    ShowHolidayPane showHolidayPane = new ShowHolidayPane();

    public VBox functionPane(){

        FunctionPane.setStyle("-fx-background-color: #36393f;");
        FunctionPane.setPrefSize(1400,100);
        createHolidayPane.createHolidayPane();
        showHolidayPane.createShowHolidayPane();

        return FunctionPane;
    }

    public void createFunctionpane(){
        Leftbar.leftbar(FunctionPane);
        FunctionPane.getChildren().add(setHolidayPane.createHolidayPane());
        FunctionPane.getChildren().add(Restdays.restDays());
        FunctionPane.getChildren().add(createWorker.createWorker());
        FunctionPane.getChildren().add(deleteWorker.deleteWorker());
    }
    public static VBox getFunctionPane() {
        return FunctionPane;
    }
}
