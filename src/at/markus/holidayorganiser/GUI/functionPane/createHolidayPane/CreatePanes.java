package at.markus.holidayorganiser.GUI.functionPane.createHolidayPane;

import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@FieldDefaults(level= AccessLevel.PRIVATE, makeFinal = true)
public class CreatePanes {
   CreateNodes createNodes = new CreateNodes();
   Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
   String[] dayNames = new String[]{"Montag","Dienstag","Mittwoch","Donnerstag","Freitag","Samstag","Sonntag"};
   boolean[] weekDay = new boolean[7];
   DatePicker date1 = createNodes.datePicker((int)(screenBounds.getWidth()/2-520),110);
   DatePicker date2 = createNodes.datePicker((int)(screenBounds.getWidth()/2-410),110);

    public  HBox createWeekDays(){
        HBox weekDays = new HBox();
        for(int i=0;i<7;i++){
            VBox pane = new VBox();
            pane.setTranslateX((screenBounds.getWidth()-1450)/2);
            pane.setTranslateY(100);

            Button box = createNodes.standardButton();
            Label label = createNodes.standardLabel(dayNames[i]);
            weekDay[i]=true;
            buttonPress(box, i);

            pane.getChildren().add(label);
            pane.getChildren().add(box);

            weekDays.getChildren().add(pane);
        }
        return weekDays;
    }
    public  HBox createDate(){
        HBox date = new HBox();
        date.getChildren().add(createNodes.label("Von:", (int)(screenBounds.getWidth()/2-520),110));
        date.getChildren().add(date1);
        date.getChildren().add(createNodes.label("Bis:",(int)(screenBounds.getWidth()/2-410),110));
        date.getChildren().add(date2);

        return date;
    }


    private void buttonPress(Button button, int ID) {
        button.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                if(weekDay[ID]){
                    weekDay[ID]  = false;
                    button.setStyle("-fx-background-color: green;"+
                            "-fx-border-color:  #7d7d7d");
                }else{
                    weekDay[ID]  = true;
                    button.setStyle("-fx-background-color: #36393f;"+
                            "-fx-border-color:  #7d7d7d");
                }
            }
        });
    }

    public boolean[] getWeekDay() {
        return weekDay;
    }
    public LocalDate getDate1(){
        return date1.getValue();
    }
    public LocalDate getDate2(){
        return date2.getValue();
    }
}
