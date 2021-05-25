package it.polimi.ingsw.View.GUINotifiers;

import com.google.gson.Gson;
import it.polimi.ingsw.Controller.Messages.AddPlayerMessage;
import it.polimi.ingsw.Controller.Messages.ConnectedPlayersMessage;
import it.polimi.ingsw.View.GUI;
import javafx.application.Platform;

public class PlayerConnectionGUINotifier extends GUINotifier{

    @Override
    public void notifyGui(String notification) {
        AddPlayerMessage message = parseNotification(notification);
        Platform.runLater(()->{
            GUI.getLobbyController().addPlayer(message.getSenderNickname());
        });
    }

    private AddPlayerMessage parseNotification(String notification){
        Gson gson = new Gson();
        return gson.fromJson(notification, AddPlayerMessage.class);
    }
}
