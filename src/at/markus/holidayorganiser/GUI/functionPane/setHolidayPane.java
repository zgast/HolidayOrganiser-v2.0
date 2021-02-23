package at.markus.holidayorganiser.GUI.functionPane;

import at.markus.holidayorganiser.GUI.StandardNodes;
import at.markus.holidayorganiser.GUI.functionPane.createHolidayPane.CreateHolidayPane;
import at.markus.holidayorganiser.Main;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Screen;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class setHolidayPane {
    StandardNodes node = new StandardNodes();
    CreateHolidayPane createHolidayPaneClass = new CreateHolidayPane();
    Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();

    public HBox createHolidayPane(){
        HBox createHolidayPane = new HBox();

        createHolidayPane.translateXProperty().setValue((screenBounds.getWidth()-1325)/2);
        createHolidayPane.translateYProperty().setValue(110);

        Button createHoliday = node.button("Urlaub erstellen");
        TextField holidayName = node.textfield("Name des Mitarbeiter",500,40);

        createHolidayPane.getChildren().add(createHoliday);
        createHolidayPane.getChildren().add(holidayName);

        createHoliday.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            BorderPane main = Main.getMain();
            main.setCenter(createHolidayPaneClass.getCreateHoliday(holidayName.getText()));
            holidayName.clear();
        });
        return createHolidayPane;
    }
}