package it.polimi.ingsw.View.GUINotifiers;

import com.google.gson.Gson;
import it.polimi.ingsw.Controller.Messages.ConnectedPlayersMessage;
import it.polimi.ingsw.View.GUI;
import javafx.application.Platform;

public class ConnectedPlayersGUINotifier extends GUINotifier{
    @Override
    public void notifyGui(String notification) {
        ConnectedPlayersMessage message = parseNotification(notification);
        Platform.runLater( ()->{
            for (String nickname: message.getConnectedPlayers()){
                GUI.getLobbyController().addPlayer(nickname);
            }
        });
    }

    private ConnectedPlayersMessage parseNotification(String notification){
        Gson gson = new Gson();
        return gson.fromJson(notification, ConnectedPlayersMessage.class);
    }
}
