package it.polimi.ingsw.Controller.RequestManagers;

import com.google.gson.Gson;
import it.polimi.ingsw.Controller.ClientHandler;
import it.polimi.ingsw.Controller.Controller;
import it.polimi.ingsw.Controller.Messages.*;
import it.polimi.ingsw.Controller.NotifiableHandler;
import it.polimi.ingsw.Player;

public class DiscardLeaderCardManager implements Manageable{
    private final Controller controller;

    public DiscardLeaderCardManager(Controller controller) {
        this.controller = controller;
    }

    @Override
    public String manageRequest(String jsonContent) {
        if(!controller.getGame().isGameStarted()){
            Gson gson = new Gson();
            Message notification = new Message();
            notification.setMessageType("GameNotStartedNotification");
            return gson.toJson(notification);
        }
        int[] faithCardsBefore = controller.getVaticanReport().clone();
        Gson gson = new Gson();
        DiscardLeaderMessage discardLeaderMessage = gson.fromJson(jsonContent, DiscardLeaderMessage.class);
        if(controller.getGame().getCurrentPlayer().getNickname().equals(discardLeaderMessage.getSenderNickname())){
            if(controller.getGame().discardLeaderCard(discardLeaderMessage.getPosition())){
                DiscardSuccessMessage mex = new DiscardSuccessMessage();
                mex.setMessageType("DiscardLeaderCardSuccessNotification");
                mex.setPosition(discardLeaderMessage.getPosition());
                NotifyDiscardLeaderCard notification = new NotifyDiscardLeaderCard();
                notification.setMessageType("NotifyDiscardLeaderCard");
                notification.setWhoDiscardedLeaderCard(controller.getGame().getCurrentPlayer().getNickname());
                notification.setDiscardedPosition(discardLeaderMessage.getPosition());
                for(NotifiableHandler c: controller.getConnectedClients()){
                    c.notifyInterface(gson.toJson(notification));
                }
                int[] faithCardsAfter = controller.getVaticanReport();
                int whichReport = 0;
                boolean vaticanReportOccurred = false;
                for (int i = 0; i < 3; i++) {
                    if (faithCardsBefore[i] != faithCardsAfter[i]) {
                        switch (i) {
                            case 0:
                                whichReport = 1;
                                vaticanReportOccurred = true;
                                break;
                            case 1:
                                whichReport = 2;
                                vaticanReportOccurred = true;
                                break;
                            case 2:
                                whichReport = 3;
                                vaticanReportOccurred = true;
                                break;
                        }
                    }
                }
                VaticanReportMessage vaticanReportMessage = new VaticanReportMessage();
                vaticanReportMessage.setMessageType("VaticanReportMessage");
                vaticanReportMessage.setOccurred(vaticanReportOccurred);
                vaticanReportMessage.setWhichOne(whichReport);
                for (NotifiableHandler clientHandler : controller.getConnectedClients()) {
                    for(Player p: controller.getGame().getPlayers()) {
                        vaticanReportMessage.setNickname(p.getNickname());
                        vaticanReportMessage.setNewFaithPoints(p.getFaithPoints());
                        clientHandler.notifyInterface(gson.toJson(vaticanReportMessage));
                    }
                }
                return gson.toJson(mex);
            }
            else{
                Message mex = new Message();
                mex.setMessageType("DiscardLeaderCardFailureNotification");
                return gson.toJson(mex);
            }
        }
        Message mex = new Message();
        mex.setMessageType("NotYourTurnNotification");
        return gson.toJson(mex);
    }
}
