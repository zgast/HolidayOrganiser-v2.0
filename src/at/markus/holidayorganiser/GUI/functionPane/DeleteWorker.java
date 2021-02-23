package at.markus.holidayorganiser.GUI.functionPane;

import at.markus.holidayorganiser.GUI.bars.WorkerList;
import at.markus.holidayorganiser.GUI.StandardNodes;
import at.markus.holidayorganiser.Main;
import at.markus.holidayorganiser.Worker;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import at.markus.holidayorganiser.excel.WorkerHandle;
import javafx.stage.Screen;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.io.IOException;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal=true)
public class DeleteWorker {
    WorkerList workerList = new WorkerList();
    Main Main = new Main();
    BorderPane main = Main.getMain();
    StandardNodes nodes = new StandardNodes();
    Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();

    public  HBox deleteWorker(){
        HBox deleteWorkerPane = new HBox();
        deleteWorkerPane.translateXProperty().setValue((screenBounds.getWidth()-1325)/2);
        deleteWorkerPane.translateYProperty().setValue(305);

        Button deleteWorker = nodes.button("Mitarbeiter loeschen");
        TextField deleteWorkerName = nodes.textfield("Name des Mitarbeiter",500,40);

        deleteWorkerPane.getChildren().add(deleteWorker);
        deleteWorkerPane.getChildren().add(deleteWorkerName);

        deleteWorker.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent e) {
                Worker worker = new Worker(deleteWorkerName.getText());
                worker.delete();

                main.setRight(workerList.workerlist());
                Main.setMain(main);
                deleteWorkerName.clear();
            }
        });
        return deleteWorkerPane;
    }
}
