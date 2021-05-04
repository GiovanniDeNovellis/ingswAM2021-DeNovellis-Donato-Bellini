package it.polimi.ingsw.Controller.RequestManagers;

import com.google.gson.Gson;
import it.polimi.ingsw.Controller.Controller;
import it.polimi.ingsw.Controller.Messages.Message;

public class StartSinglePlayerManager implements Manager{
    private final Controller controller;

    public StartSinglePlayerManager(Controller controller) {
        this.controller = controller;
    }

    @Override
    public String manageRequest(String jsonContent) {
        Gson gson = new Gson();
        boolean ans;
        synchronized (controller.getGame()){
            ans=controller.getGame().startSinglePlayer();
        }
        if (ans) {
            Message message = new Message();
            message.setMessageType("SinglePLayerCreationOkNotification");
            return gson.toJson(message);
        } else {
            Message message = new Message();
            message.setMessageType("SinglePLayerCreationFailedNotification");
            return gson.toJson(message);
        }
    }
}
