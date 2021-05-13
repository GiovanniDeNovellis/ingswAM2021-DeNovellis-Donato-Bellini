package it.polimi.ingsw.Controller.RequestManagers;

import com.google.gson.Gson;
import it.polimi.ingsw.Controller.ClientHandler;
import it.polimi.ingsw.Controller.Controller;
import it.polimi.ingsw.Controller.Messages.InsertResourceMessage;
import it.polimi.ingsw.Controller.Messages.Message;
import it.polimi.ingsw.Controller.Messages.NotifyInsertedOkMessage;

public class InsertResourcesIntoWarehouseManager implements Manageable {
    private final Controller controller;

    public InsertResourcesIntoWarehouseManager(Controller controller) {
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
        Gson gson = new Gson();
        InsertResourceMessage insertResourceMessage = gson.fromJson(jsonContent, InsertResourceMessage.class);
        if (controller.getGame().getCurrentPlayer().getNickname().equals(insertResourceMessage.getSenderNickname())) {
            if (controller.getGame().insertResourcesIntoWarehouse(insertResourceMessage.getResourceToInsert(), insertResourceMessage.getQuantityToInsert(), insertResourceMessage.isIntoExtraDeposit())) {
                Message mex = new Message();
                mex.setMessageType("InsertedResourcesSuccessNotification");
                NotifyInsertedOkMessage notification = new NotifyInsertedOkMessage();
                notification.setMessageType("InsertedResourceChanged");
                notification.setNickname(controller.getGame().getCurrentPlayer().getNickname());
                notification.setTemporaryResourcesConfiguration(controller.getGame().getMarketBoard().getTemporaryResources());
                notification.setWarehouseConfiguration(controller.getGame().getCurrentPlayer().getPersonalBoard().getWarehouseDepot());
                for (ClientHandler clientHandler : controller.getConnectedClients()) {
                    clientHandler.notifyInterface(gson.toJson(notification));
                }
                return gson.toJson(mex);
            } else {
                Message mex = new Message();
                mex.setMessageType("InsertedResourcesFailureNotification");
                return gson.toJson(mex);
            }
        }
        Message mex = new Message();
        mex.setMessageType("NotYourTurnNotification");
        return gson.toJson(mex);
    }
}

