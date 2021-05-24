package it.polimi.ingsw.View.GUIControllers;

import com.google.gson.Gson;
import com.sun.java.accessibility.util.GUIInitializedListener;
import it.polimi.ingsw.Controller.Messages.AddPlayerMessage;
import it.polimi.ingsw.View.GUI;
import it.polimi.ingsw.View.NotificationManager;
import it.polimi.ingsw.View.PrinterSingleton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML
    private TextField nicknameField;
    @FXML
    private Label loginFailed;
    @FXML
    private Label loginFailedReconnection;




    public void login(ActionEvent actionEvent) {
        Gson gson = new Gson();
        AddPlayerMessage addPlayerMessage = new AddPlayerMessage();
        addPlayerMessage.setSenderNickname(nicknameField.getText());
        addPlayerMessage.setMessageType("AddPlayer");
        PrinterSingleton.getPrinterSingleton().print(gson.toJson(addPlayerMessage));
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loginFailed.setVisible(false);
        loginFailedReconnection.setVisible(false);
        GUI.setLoginController(this);
    }

    public void setLoginError(){
        loginFailed.setVisible(true);
        loginFailedReconnection.setVisible(true);
    }
}
