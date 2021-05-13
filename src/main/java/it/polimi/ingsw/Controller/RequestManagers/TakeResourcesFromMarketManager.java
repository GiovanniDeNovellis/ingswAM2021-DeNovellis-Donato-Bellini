package it.polimi.ingsw.Controller.RequestManagers;

import com.google.gson.Gson;
import it.polimi.ingsw.Controller.ClientHandler;
import it.polimi.ingsw.Controller.Controller;
import it.polimi.ingsw.Controller.Messages.*;
import it.polimi.ingsw.Player;

public class TakeResourcesFromMarketManager implements Manageable{
    private final Controller controller;

    public TakeResourcesFromMarketManager(Controller controller) { this.controller = controller; }

    @Override
    public String manageRequest(String jsonContent) {
        if(!controller.getGame().isGameStarted()){
            Gson gson = new Gson();
            Message notification = new Message();
            notification.setMessageType("GameNotStartedNotification");
            return gson.toJson(notification);
        }
        Gson gson = new Gson();
        int[] faithCardsBefore = controller.getVaticanReport().clone();
        TakeResourceFromMarketMessage takeResourceFromMarketMessage = gson.fromJson(jsonContent, TakeResourceFromMarketMessage.class);
        if (controller.getGame().getCurrentPlayer().getNickname().equals(takeResourceFromMarketMessage.getSenderNickname())) {
            if(controller.getGame().takeResourcesFromMarket(takeResourceFromMarketMessage.getMarketIndex()[0],takeResourceFromMarketMessage.getMarketIndex()[1])){
                Message mex = new Message();
                mex.setMessageType("TakeResourceFromMarketSuccessNotification");
                TemporaryResourcesChangedMessage notification = new TemporaryResourcesChangedMessage();
                notification.setMessageType("TemporaryResourcesChanged");
                notification.setNickname(controller.getGame().getCurrentPlayer().getNickname());
                notification.setTemporaryResourcesConfiguration(controller.getGame().getMarketBoard().getTemporaryResources());
                MarketGridChangedMessage notification1 = new MarketGridChangedMessage();
                notification1.setMarketGridConfiguration(controller.getGame().getMarketBoard().getMarketGrid(),controller.getGame().getMarketBoard().getMarbleOut());
                for (ClientHandler clientHandler : controller.getConnectedClients()) {
                    clientHandler.notifyInterface(gson.toJson(notification));
                    clientHandler.notifyInterface(gson.toJson(notification1));
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
                for (ClientHandler clientHandler : controller.getConnectedClients()) {
                    for(Player p: controller.getGame().getPlayers()){
                        if(p.getNickname().equals(clientHandler.getClientNickname()))
                            vaticanReportMessage.setNewFaithPoints(p.getFaithPoints());
                    }
                    clientHandler.notifyInterface(gson.toJson(vaticanReportMessage));
                }
                return gson.toJson(mex);
            } else {
                Message mex = new Message();
                mex.setMessageType("TakeResourceFromMarketFailureNotification");
                return gson.toJson(mex);
            }
        }
        Message mex = new Message();
        mex.setMessageType("NotYourTurnNotification");
        return gson.toJson(mex);
    }
}