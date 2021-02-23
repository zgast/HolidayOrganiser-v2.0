package at.markus.holidayorganiser.GUI.functionPane.createHolidayPane;

import at.markus.holidayorganiser.GUI.functionPane.createHolidayPane.logic.CreateHoliday;
import at.markus.holidayorganiser.Main;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Screen;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal=false)
public class CreateHolidayPane {
    static BorderPane createHoliday;
    final CreatePanes createPanes = new CreatePanes();
    final Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
    HBox date = createPanes.createDate();
    HBox weekDays = createPanes.createWeekDays();
    final CreateHoliday createHolidayClass  = new CreateHoliday();
    static String name;

    public BorderPane createHolidayPane() {
        HBox button = new HBox();
        button.getChildren().add(button("back", (int)(screenBounds.getWidth()-1200)/2,0, (byte) 1));
        button.getChildren().add(button("next", (int)(screenBounds.getWidth()-1200)/2,0, (byte) 0));

        createHoliday = new BorderPane();
        createHoliday.setStyle("-fx-background-color: #36393f;");
        createHoliday.setPrefSize(1400, 100);
        createHoliday.setTop(button);

        createHoliday.setCenter(weekDays);
        return createHoliday;
    }

    public Button button(String text, int x, int y, byte ID){
        Button button = new Button(text);
        button.setTranslateX(x);
        button.setTranslateY(y);
        button.setPrefSize(400,40);
        button.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                if(ID==0){
                    if(createHoliday.getCenter()==date){
                        createHolidayClass.createHoliday(name,createPanes.getWeekDay(), createPanes.getDate1(), createPanes.getDate2());
                        date = createPanes.createDate();
                        weekDays = createPanes.createWeekDays();
                        createHoliday.setCenter(weekDays);
                        Main.setFunctionPane();
                    }else{
                        createHoliday.setCenter(date);
                    }
                }else{
                    createHoliday.setCenter(weekDays);
                }
            }
        });
        return button;
    }

    public BorderPane getCreateHoliday(String name) {
        this.name = name;
        return createHoliday;
    }
}