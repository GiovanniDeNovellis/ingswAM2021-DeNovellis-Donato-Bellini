package it.polimi.ingsw.Controller.RequestManagers;

import com.google.gson.Gson;
import it.polimi.ingsw.Controller.ClientHandler;
import it.polimi.ingsw.Controller.Controller;
import it.polimi.ingsw.Controller.Messages.Message;
import it.polimi.ingsw.Controller.Messages.NotifyWarehouseChangedMessage;
import it.polimi.ingsw.Controller.Messages.SwitchLevelMessage;
import it.polimi.ingsw.Controller.NotifiableHandler;

/**
 * This manager handles a switch levels request
 */
public class SwitchLevelsManager implements Manageable {
    private final Controller controller;

    public SwitchLevelsManager(Controller controller) {
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
        SwitchLevelMessage switchMessage = gson.fromJson(jsonContent, SwitchLevelMessage.class);
        if (controller.getGame().getCurrentPlayer().getNickname().equals(switchMessage.getSenderNickname())) {
            if (controller.getGame().switchLevels(switchMessage.getLevelsToSwitch()[0], switchMessage.getLevelsToSwitch()[1])) {
                Message mex = new Message();
                mex.setMessageType("SwitchLevelsSuccessNotification");
                NotifyWarehouseChangedMessage notification = new NotifyWarehouseChangedMessage();
                notification.setMessageType("NotifyWareHouseChangedMessage");
                notification.setNickname(controller.getGame().getCurrentPlayer().getNickname());
                notification.setWarehouseConfiguration(controller.getGame().getCurrentPlayer().getPersonalBoard().getWarehouseDepot());
                for (NotifiableHandler clientHandler : controller.getConnectedClients()) {
                    clientHandler.notifyInterface(gson.toJson(notification));
                }
                return gson.toJson(mex);
            } else {
                Message mex = new Message();
                mex.setMessageType("SwitchLevelsFailureNotification");
                return gson.toJson(mex);
            }
        }
        Message mex = new Message();
        mex.setMessageType("NotYourTurnNotification");
        return gson.toJson(mex);
    }
}
