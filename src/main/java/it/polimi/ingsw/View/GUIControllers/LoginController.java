package it.polimi.ingsw.View.GUIControllers;

import com.google.gson.Gson;
import it.polimi.ingsw.Controller.Messages.AddPlayerMessage;
import it.polimi.ingsw.View.PrinterSingleton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class LoginController {
    @FXML
    private TextField nicknameField;


    public void login(ActionEvent actionEvent) {
        Gson gson = new Gson();
        AddPlayerMessage addPlayerMessage = new AddPlayerMessage();
        addPlayerMessage.setSenderNickname(nicknameField.getText());
        addPlayerMessage.setMessageType("AddPlayer");
        PrinterSingleton.getPrinterSingleton().print(gson.toJson(addPlayerMessage));
    }
}
