package at.markus.holidayorganiser;

import at.markus.holidayorganiser.GUI.bars.Leftbar;
import at.markus.holidayorganiser.GUI.bars.WorkerList;
import at.markus.holidayorganiser.GUI.FunctionPane;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import at.markus.holidayorganiser.calendar.CalendarMethods;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Main extends Application {
    static BorderPane main = new BorderPane();
    Scene scene;
    CalendarMethods calendarMethods = new CalendarMethods();
    Leftbar leftbar = new Leftbar();
    WorkerList workerList = new WorkerList();
    static FunctionPane functionPane = new FunctionPane();

    public void start(Stage primaryStage){
        primaryStage.getIcons().add(new Image("file:Production\\icon.png"));
        primaryStage.setMaximized(true);
        scene = new Scene(main);
        scene.getStylesheets().add("at/markus/holidayorganiser/style.css");
        primaryStage.setScene(scene);

        Leftbar.setMain(main);
        calendarMethods.setMain(main);
        main.setRight(workerList.workerlist());
        VBox FunctionPane = functionPane.functionPane();
        main.setCenter(FunctionPane);


        primaryStage.setTitle(" Holiday Organiser");

        primaryStage.show();
        functionPane.createFunctionpane();
    }

    public static BorderPane getMain() {
        return main;
    }
    public static void setFunctionPane(){main.setCenter(functionPane.functionPane()); }

    public static void setMain(BorderPane main) {
        Main.main = main;
    }


}

