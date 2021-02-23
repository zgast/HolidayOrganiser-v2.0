package at.markus.holidayorganiser.GUI;

import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Screen;

public class StandardNodes {
    private final Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();

    public Button button(String text){
        Button button = new Button(text);
        if(screenBounds.getWidth()<1500){
            button.setPrefSize(200,40);
        }else{
            button.setPrefSize(400,40);
        }
        buttonAnimation(button);

        return button;
    }

    public TextField textfield(String text, int width, int height){
        TextField textfield= new TextField();
        textfield.setPromptText(text);
        textfield.setPrefSize(width,height);
        textfield.setTranslateX(25);

        return textfield;
    }

    private void buttonAnimation(Button button) {
        button.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                button.setStyle("-fx-background-color: white");
                button.addEventHandler(MouseEvent.MOUSE_RELEASED, new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent e) {
                        button.setStyle("-fx-background-color: #36393f;" +
                                "-fx-border-color: white; " +
                                "-fx-text-fill: white;");
                    }
                });
            }
        });
    }
}

