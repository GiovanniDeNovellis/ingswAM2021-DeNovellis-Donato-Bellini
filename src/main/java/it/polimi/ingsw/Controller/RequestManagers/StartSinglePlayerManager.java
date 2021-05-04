package it.polimi.ingsw.Controller.RequestManagers;

import com.google.gson.Gson;
import it.polimi.ingsw.Controller.Controller;
import it.polimi.ingsw.Controller.Messages.Message;

public class StartSinglePlayerManager implements Manageable{
    private final Controller controller;

    public StartSinglePlayerManager(Controller controller) {
        this.controller = controller;
    }

    @Override
    public String manageRequest(String jsonContent) {
        Gson gson = new Gson();
        boolean ans=controller.getGame().startSinglePlayer();
        Message message = new Message();
        if (ans) {
            message.setMessageType("SinglePLayerCreationOkNotification");
        } else {
            message.setMessageType("SinglePLayerCreationFailedNotification");
        }
        return gson.toJson(message);
    }
}
