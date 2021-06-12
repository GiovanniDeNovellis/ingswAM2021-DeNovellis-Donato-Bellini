package it.polimi.ingsw.View.GUIControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class WinnerSceneController {
    @FXML
    private Label notificationLabel;

    public void exit(ActionEvent actionEvent) {
        System.exit(1);
    }

    public void setText(String text){
        notificationLabel.setText(text);
        notificationLabel.setVisible(true);
    }
}
