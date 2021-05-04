package it.polimi.ingsw.Controller.RequestManagers;

import com.google.gson.Gson;
import it.polimi.ingsw.Controller.ClientHandler;
import it.polimi.ingsw.Controller.Controller;
import it.polimi.ingsw.Controller.Messages.Message;
import it.polimi.ingsw.Controller.Messages.NotifyWarehouseChangeMessage;
import it.polimi.ingsw.Controller.Messages.SwitchLevelMessage;

public class SwitchLevelsManager implements Manageable {
    private final Controller controller;

    public SwitchLevelsManager(Controller controller) {
        this.controller = controller;
    }

    @Override
    public String manageRequest(String jsonContent) {
        Gson gson = new Gson();
        SwitchLevelMessage switchMessage = gson.fromJson(jsonContent, SwitchLevelMessage.class);
        if (controller.getGame().getCurrentPlayer().getNickname().equals(switchMessage.getSenderNickname())) {
            if (controller.getGame().switchLevels(switchMessage.getLevelsToSwitch()[0], switchMessage.getLevelsToSwitch()[1])) {
                Message mex = new Message();
                mex.setMessageType("SwitchLevelsSuccessNotification");
                NotifyWarehouseChangeMessage notification = new NotifyWarehouseChangeMessage();
                notification.setMessageType("NotifyWarehouseChanged");
                notification.setNickname(controller.getGame().getCurrentPlayer().getNickname());
                notification.setWarehouseConfiguration(controller.getGame().getCurrentPlayer().getPersonalBoard().getWarehouseDepot());
                for (ClientHandler clientHandler : controller.getConnectedClients()) {
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
