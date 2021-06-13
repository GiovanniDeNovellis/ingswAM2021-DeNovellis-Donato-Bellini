package it.polimi.ingsw.View.GUIControllers;

import it.polimi.ingsw.View.GUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class WinnerSceneController implements Initializable {
    @FXML
    private Label notificationLabel;



    public void exit(ActionEvent actionEvent) {
        System.exit(1);
    }

    public void setText(String text){
        notificationLabel.setText(text);
        notificationLabel.setVisible(true);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        GUI.setWinnerSceneController(this);
    }
}
