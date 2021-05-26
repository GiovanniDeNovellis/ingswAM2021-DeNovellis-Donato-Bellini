package it.polimi.ingsw.View.GUIControllers;

import com.google.gson.Gson;
import it.polimi.ingsw.Controller.Messages.StartMultiPlayerMessage;
import it.polimi.ingsw.Controller.Messages.StartSinglePlayerMessage;
import it.polimi.ingsw.View.GUI;
import it.polimi.ingsw.View.PrinterSingleton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

public class LobbyController implements Initializable {
    @FXML
    Button single;
    @FXML
    Button multi;
    @FXML
    Label firstPlayer;
    @FXML
    Label secondPlayer;
    @FXML
    Label thirdPlayer;
    @FXML
    Label fourthPlayer;

    int numPlayers = 0;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        GUI.setLobbyController(this);
    }

    public void startSinglePlayer(ActionEvent actionEvent) {
            Gson gson = new Gson();
            StartSinglePlayerMessage message = new StartSinglePlayerMessage();
            message.setMessageType("StartSinglePlayer");
            PrinterSingleton.getPrinterSingleton().sendMessage(gson.toJson(message));    }

    public void startMultiPlayer(ActionEvent actionEvent) {
        Gson gson = new Gson();
        StartMultiPlayerMessage message = new StartMultiPlayerMessage();
        message.setMessageType("StartMultiPlayer");
        PrinterSingleton.getPrinterSingleton().sendMessage(gson.toJson(message));
    }

    public void addPlayer(String playerNickname){
        numPlayers++;
        switch (numPlayers){
            case 1:
                firstPlayer.setText(playerNickname);
                firstPlayer.setVisible(true);
                break;
            case 2:
                secondPlayer.setText(playerNickname);
                secondPlayer.setVisible(true);
                break;
            case 3:
                thirdPlayer.setText(playerNickname);
                thirdPlayer.setVisible(true);
                break;
            case 4:
                fourthPlayer.setText(playerNickname);
                fourthPlayer.setVisible(true);
                break;
        }
        buttonActivator();
    }

    private void buttonActivator(){
        if(numPlayers == 1) {
            single.setTextFill(Color.RED);
            multi.setDisable(true);
        }
        else{
            single.setTextFill(Color.LIGHTGRAY);
            multi.setDisable(false);
            multi.setTextFill(Color.RED);
            single.setDisable(true);
        }
    }


}
