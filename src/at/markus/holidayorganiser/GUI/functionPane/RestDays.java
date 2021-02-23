package at.markus.holidayorganiser.GUI.functionPane;

import at.markus.holidayorganiser.GUI.StandardNodes;
import at.markus.holidayorganiser.GUI.functionPane.showHolidayPane.ShowHolidayPane;
import at.markus.holidayorganiser.Main;
import at.markus.holidayorganiser.excel.RestDaysAdding;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.text.DecimalFormat;
import java.time.Year;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal=true)
public class RestDays {
    StandardNodes nodes = new StandardNodes();
    Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
    ShowHolidayPane showHolidayPane = new ShowHolidayPane();

    public HBox restDays(){

        HBox restDaysPane = new HBox();

        restDaysPane.translateXProperty().setValue((screenBounds.getWidth()-1325)/2);
        restDaysPane.translateYProperty().setValue(115);

        Button restDays = nodes.button("Verbleibende Tage");
        TextField restDaysName= nodes.textfield("Name des Mitarbeiter",500,40);

        restDaysPane.getChildren().add(restDays);
        restDaysPane.getChildren().add(restDaysName);

        restDays.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, e -> {
            Main.getMain().setCenter(showHolidayPane.getShowHoliday(restDaysName.getText()));

            restDaysName.clear();
        });

        return restDaysPane;
    }
}
