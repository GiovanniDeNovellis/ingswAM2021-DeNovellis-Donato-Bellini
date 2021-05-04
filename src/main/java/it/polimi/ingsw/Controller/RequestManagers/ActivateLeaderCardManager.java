package it.polimi.ingsw.Controller.RequestManagers;

import com.google.gson.Gson;
import it.polimi.ingsw.Controller.ClientHandler;
import it.polimi.ingsw.Controller.Controller;
import it.polimi.ingsw.Controller.Messages.ActivateLeaderCardMessage;
import it.polimi.ingsw.Controller.Messages.AddPlayerMessage;
import it.polimi.ingsw.Controller.Messages.NotifyActivateLeaderCard;
import it.polimi.ingsw.Controller.Messages.SimpleMessage;

public class ActivateLeaderCardManager implements Manageable{
    private final Controller controller;

    public ActivateLeaderCardManager(Controller controller) {
        this.controller = controller;
    }

    @Override
    public String manageRequest(String jsonContent){
        Gson gson = new Gson();
        ActivateLeaderCardMessage activateLeaderCardMessage = gson.fromJson(jsonContent,ActivateLeaderCardMessage.class);
        if (controller.getGame().activateLeaderCard(activateLeaderCardMessage.getPosition())){
            SimpleMessage mex = new SimpleMessage();
            mex.setMessageContent("ActivateLeaderCardSuccessNotification");
            NotifyActivateLeaderCard notification = new NotifyActivateLeaderCard();
            notification.setMessageType("NotifyActivateLeaderCard");
            notification.setActivatedLeaderCardPosition(activateLeaderCardMessage.getPosition());
            notification.setWhoActivatedLeaderCard(activateLeaderCardMessage.getSenderNickname());
            for( ClientHandler clientHandler: controller.getConnectedClients() ){
                clientHandler.notifyInterface(gson.toJson(notification));
            }
            return gson.toJson(mex);
        }
        else{
            SimpleMessage mex = new SimpleMessage();
            mex.setMessageContent("ActivateLeaderCardFailureNotification");
            return gson.toJson(mex);
        }
    }
}
