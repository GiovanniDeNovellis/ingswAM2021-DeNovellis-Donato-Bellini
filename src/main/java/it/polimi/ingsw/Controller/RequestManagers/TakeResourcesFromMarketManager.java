package it.polimi.ingsw.Controller.RequestManagers;

import com.google.gson.Gson;
import it.polimi.ingsw.Controller.ClientHandler;
import it.polimi.ingsw.Controller.Controller;
import it.polimi.ingsw.Controller.Messages.*;

public class TakeResourcesFromMarketManager implements Manageable{
    private final Controller controller;

    public TakeResourcesFromMarketManager(Controller controller) { this.controller = controller; }

    @Override
    public String manageRequest(String jsonContent) {
        Gson gson = new Gson();
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