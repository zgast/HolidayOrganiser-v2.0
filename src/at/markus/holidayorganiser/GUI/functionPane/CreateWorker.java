package at.markus.holidayorganiser.GUI.functionPane;
import at.markus.holidayorganiser.GUI.StandardNodes;
import at.markus.holidayorganiser.GUI.bars.WorkerList;
import at.markus.holidayorganiser.Main;
import at.markus.holidayorganiser.Worker;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Screen;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal=true)
public class CreateWorker {
    WorkerList workerList = new WorkerList();
    BorderPane main = Main.getMain();
    StandardNodes nodes = new StandardNodes();
    Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();

    public HBox createWorker(){
        HBox createWorkerPane = new HBox();
        createWorkerPane.translateXProperty().setValue((screenBounds.getWidth()-1325)/2);
        createWorkerPane.translateYProperty().setValue(300);

        Button createWorker = nodes.button("Mitarbeiter erstellen");
        TextField workerName = nodes.textfield("Name des Mitarbeiter",300,40);
        TextField workerTime = nodes.textfield("Urlaubstage",100,40);
        TextField workerJoin = nodes.textfield("Eintrittsdatum",100,40);

        createWorkerPane.getChildren().add(createWorker);
        createWorkerPane.getChildren().add(workerName);
        createWorkerPane.getChildren().add(workerTime);
        createWorkerPane.getChildren().add(workerJoin);

        createWorker.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent e) {
                int Day,Month,Year;
                String[] date = workerJoin.getText().split("\\.");
                Worker worker = new Worker(workerName.getText());

                worker.create(toInt(date[0]),toInt(date[1]),toInt(date[2]),toInt(workerTime.getText()));

                main.setRight(workerList.workerlist());
                Main.setMain(main);
                workerName.clear();
                workerTime.clear();
                workerJoin.clear();
            }
        });

        return createWorkerPane;
    }

    private int toInt(String number){
        return Integer.parseInt(number);
    }
}
