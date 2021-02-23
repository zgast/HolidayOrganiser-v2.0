package at.markus.holidayorganiser.GUI.calendarPane;

import at.markus.holidayorganiser.GUI.StandardNodes;
import at.markus.holidayorganiser.calendar.CalendarMethods;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.TextField;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Screen;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal=true)
public class CalendarPane {
    CalendarMethods Calendar = new CalendarMethods();
    StandardNodes nodes = new StandardNodes();
    Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();

    public  BorderPane calendarPane() {
        BorderPane calendarPane = new BorderPane();
        calendarPane.setStyle("-fx-background-color: #36393f;");

        HBox calenderSelectPane = new HBox();
        calenderSelectPane.translateXProperty().setValue((screenBounds.getWidth()-1325)/2);
        calenderSelectPane.translateYProperty().setValue(305);

        Button calendarWorker = nodes.button("Kalender Oeffnen");
        TextField calendarWorkerName = nodes.textfield("Name des Mitarbeiter",500,40);

        calenderSelectPane.getChildren().add(calendarWorker);
        calenderSelectPane.getChildren().add(calendarWorkerName);


        calendarWorker.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                calendarWorker.setStyle("-fx-background-color: #2e2d2d");
                String name = calendarWorkerName.getText();
                String FilenameAndPath =("Data\\AppointmentFiles\\"+name+".csv");
                Calendar.setAppointmentFile(FilenameAndPath);
                calendarPane.setCenter(Calendar.start());
            }
        });

        calendarPane.setCenter(calenderSelectPane);

        return calendarPane;
    }
}

