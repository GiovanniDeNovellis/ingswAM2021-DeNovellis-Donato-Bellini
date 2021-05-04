package it.polimi.ingsw.Controller.RequestManagers;

import com.google.gson.Gson;
import it.polimi.ingsw.Controller.ClientHandler;
import it.polimi.ingsw.Controller.Controller;
import it.polimi.ingsw.Controller.Messages.AddPlayerMessage;
import it.polimi.ingsw.Controller.Messages.SimpleMessage;

public class AddPlayerManager implements Manageable {
    private final Controller controller;

    public AddPlayerManager(Controller controller) {
        this.controller = controller;
    }


    @Override
    public String manageRequest(String jsonContent) {
        Gson gson = new Gson();
            AddPlayerMessage addMessage = gson.fromJson(jsonContent,AddPlayerMessage.class);
            if (controller.getGame().addPlayer(addMessage.getSenderNickname())){
                SimpleMessage mex = new SimpleMessage();
                mex.setMessageContent("Player added to the game.");
                AddPlayerMessage notification = new AddPlayerMessage();
                notification.setMessageType("AddPlayerNotificationForEveryone");
                notification.setSenderNickname(addMessage.getSenderNickname());
                for( ClientHandler clientHandler: controller.getConnectedClients() ){
                    clientHandler.notifyInterface(gson.toJson(notification));
                }
                return gson.toJson(mex);
            }
            else{
                SimpleMessage mex = new SimpleMessage();
                mex.setMessageContent("Error: invalid nickname or game already started");
                return gson.toJson(mex);
        }
    }
}
