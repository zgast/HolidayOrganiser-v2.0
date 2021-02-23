package at.markus.holidayorganiser.GUI.bars;

import at.markus.holidayorganiser.GUI.calendarPane.CalendarPane;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Screen;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class Leftbar {
    static BorderPane main = new BorderPane();
    CalendarPane CalendarPane = new CalendarPane();
    Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();

    public  VBox leftbar(VBox FunctionPane){
        VBox leftbar = new VBox();
        leftbar.setStyle("-fx-background-color: #2f3136;");

        leftbar.setPrefSize(200,1080);
        main.setLeft(leftbar);
        Button calender = new Button("Kalender");
        calender.setPrefSize(400,40);
        calender.getStyleClass().add("bar");
        animation(calender);

        Button functions = new Button("Funktionen");
        functions.setPrefSize(400,40 );
        functions.setTranslateY(0.5);
        functions.getStyleClass().add("bar");
        animation(functions);
        Button exit = new Button("Exit");
        exit.setPrefSize(400,40 );
        exit.setTranslateY(0.5);
        exit.setTranslateY(screenBounds.getHeight()-150);
        exit.getStyleClass().add("bar");
        animation(exit);

        if(screenBounds.getWidth()<1500){
            functions.setPrefSize(150,40 );
            calender.setPrefSize(150,40);
            leftbar.setPrefSize(150,1080);
        }


        leftbar.getChildren().add(calender);
        leftbar.getChildren().add(functions);
        leftbar.getChildren().add(exit);

        functions.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                main.setCenter(FunctionPane);
            }
        });
        exit.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                System.exit(0);
            }
        });


        calender.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, new EventHandler<javafx.scene.input.MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                main.setCenter(CalendarPane.calendarPane());
            }
        });


        return leftbar;
    }
    
    private void animation(Button button){
        button.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<javafx.scene.input.MouseEvent>() {
            @Override public void handle(MouseEvent e) {
                button.setStyle("-fx-background-color: white");
                button.addEventHandler(MouseEvent.MOUSE_RELEASED, new EventHandler<javafx.scene.input.MouseEvent>() {
                    @Override public void handle(MouseEvent e) {
                        button.setStyle(  "-fx-background-color: #2f3136;" +
                                "-fx-text-fill: white;"+
                                "-fx-font: 20 Calibri;");
                    }
                });
            }
        });
    }
    public static void setMain(BorderPane main) {
        Leftbar.main = main;
    }
}
