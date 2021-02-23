package at.markus.holidayorganiser.GUI.functionPane.createHolidayPane;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;

public class CreateNodes {

    public Label standardLabel(String text){

        Label label = new Label(text);
        label.setPrefSize(150,40);
        label.setAlignment(Pos.CENTER);

        return label;
    }
    public Button standardButton(){
        Button box = new Button();
        box.getStyleClass().add("working");
        box.setPrefSize(150,40);

        return box;
    }


    public Label label(String text, int x, int y){
        Label label = new Label(text);
        label.getStyleClass().add("date");
        label.setPrefSize(60,40);
        label.setTranslateX(x);
        label.setTranslateY(y);

        return label;
    }

    public DatePicker datePicker(int x, int y){
        DatePicker picker = new DatePicker();
        picker.setPrefSize(200,40);
        picker.setTranslateX(x);
        picker.setTranslateY(y);

        return picker;
    }
}
