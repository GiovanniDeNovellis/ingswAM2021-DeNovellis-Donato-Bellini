package it.polimi.ingsw.Controller.RequestManagers;

import com.google.gson.Gson;
import it.polimi.ingsw.Controller.ClientHandler;
import it.polimi.ingsw.Controller.Controller;
import it.polimi.ingsw.Controller.Messages.InsertResourceMessage;
import it.polimi.ingsw.Controller.Messages.Message;
import it.polimi.ingsw.Controller.Messages.NotifyInsertedOkMessage;
import it.polimi.ingsw.ExtraDeposit;

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
        System.out.println("I1");
        Gson gson = new Gson();
        InsertResourceMessage insertResourceMessage = gson.fromJson(jsonContent, InsertResourceMessage.class);
        System.out.println("i2");
        if (controller.getGame().getCurrentPlayer().getNickname().equals(insertResourceMessage.getSenderNickname())) {
            System.out.println("i3");
            if (controller.getGame().insertResourcesIntoWarehouse(insertResourceMessage.getResourceToInsert(), insertResourceMessage.getQuantityToInsert(), insertResourceMessage.isIntoExtraDeposit())) {
                System.out.println("i4");
                Message mex = new Message();
                mex.setMessageType("InsertedResourcesSuccessNotification");
                NotifyInsertedOkMessage notification = new NotifyInsertedOkMessage();
                notification.setMessageType("InsertedResourceChanged");
                System.out.println("i5");
                ExtraDeposit[] extraDeposits = new ExtraDeposit[2];
                extraDeposits[0] = controller.getGame().getCurrentPlayer().getPersonalBoard().getExtraDeposit1();
                extraDeposits[1] = controller.getGame().getCurrentPlayer().getPersonalBoard().getExtraDeposit2();
                notification.setExtraDepositsConfiguration(extraDeposits);
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

