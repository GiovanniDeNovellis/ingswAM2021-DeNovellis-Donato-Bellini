package it.polimi.ingsw.Controller.RequestManagers;

import com.google.gson.Gson;
import it.polimi.ingsw.Controller.ClientHandler;
import it.polimi.ingsw.Controller.Controller;
import it.polimi.ingsw.Controller.Messages.AddPlayerMessage;
import it.polimi.ingsw.Controller.Messages.Message;

public class AddPlayerManager implements Manageable {
    private final Controller controller;

    public AddPlayerManager(Controller controller) {
        this.controller = controller;
    }


    @Override
    public String manageRequest(String jsonContent) {
        boolean ans;
        Gson gson = new Gson();
        AddPlayerMessage addMessage = gson.fromJson(jsonContent,AddPlayerMessage.class);
        ans = controller.getGame().addPlayer(addMessage.getSenderNickname());
        Message mex = new Message();
        if (ans) {
            mex.setMessageType("PlayerAddedNotification");
            AddPlayerMessage notification = new AddPlayerMessage();
            notification.setMessageType("AddPlayerNotificationForEveryone");
            notification.setSenderNickname(addMessage.getSenderNickname());
                for (ClientHandler clientHandler : controller.getConnectedClients()) {
                    clientHandler.notifyInterface(gson.toJson(notification));
                }
        } else {
            mex.setMessageType("InvalidPlayerAddNotification");
        }
        return gson.toJson(mex);
    }
}
