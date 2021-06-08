package it.polimi.ingsw.Controller.RequestManagers;

import com.google.gson.Gson;
import it.polimi.ingsw.Controller.ClientHandler;
import it.polimi.ingsw.Controller.Controller;
import it.polimi.ingsw.Controller.Messages.*;
import it.polimi.ingsw.Controller.NotifiableHandler;

public class ActivateLeaderCardManager implements Manageable{
    private final Controller controller;

    public ActivateLeaderCardManager(Controller controller) {
        this.controller = controller;
    }

    @Override
    public String manageRequest(String jsonContent){
        if(!controller.getGame().isGameStarted()){
            Gson gson = new Gson();
            Message notification = new Message();
            notification.setMessageType("GameNotStartedNotification");
            return gson.toJson(notification);
        }
        Gson gson = new Gson();
        ActivateLeaderCardMessage activateLeaderCardMessage = gson.fromJson(jsonContent,ActivateLeaderCardMessage.class);
        if(controller.getGame().getCurrentPlayer().getNickname().equals(activateLeaderCardMessage.getSenderNickname())) {
            if (controller.getGame().activateLeaderCard(activateLeaderCardMessage.getPosition())) {
                Message mex = new Message();
                mex.setMessageType("ActivateLeaderCardSuccessNotification");
                NotifyActivateLeaderCard notification = new NotifyActivateLeaderCard();
                notification.setMessageType("NotifyActivateLeaderCard");
                notification.setActivatedLeaderCardPosition(activateLeaderCardMessage.getPosition());
                notification.setWhoActivatedLeaderCard(activateLeaderCardMessage.getSenderNickname());
                for (NotifiableHandler clientHandler : controller.getConnectedClients()) {
                    clientHandler.notifyInterface(gson.toJson(notification));
                }
                return gson.toJson(mex);
            } else {
                Message mex = new Message();
                mex.setMessageType("ActivateLeaderCardFailureNotification");
                return gson.toJson(mex);
            }
        }
        Message mex = new Message();
        mex.setMessageType("NotYourTurnNotification");
        return gson.toJson(mex);
    }
}
