package it.polimi.ingsw.Controller.RequestManagers;

import com.google.gson.Gson;
import it.polimi.ingsw.ActionCard;
import it.polimi.ingsw.Controller.Controller;
import it.polimi.ingsw.Controller.Messages.*;

import java.util.ArrayList;

/**
 * This manager handles an action card activation request
 */
public class ActionCardActivationManager implements Manageable{
    private final Controller controller;

    public ActionCardActivationManager(Controller controller) {
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
        String type = controller.getGame().getActionCardStack().getCards().get(0).getType();
        int[] faithCardsBefore = controller.getVaticanReport().clone();
        if (controller.getGame().activateActionCard()) {
            Gson gson = new Gson();
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
            vaticanReportMessage.setNickname(controller.getGame().getCurrentPlayer().getNickname());
            vaticanReportMessage.setMessageType("VaticanReportMessage");
            vaticanReportMessage.setOccurred(vaticanReportOccurred);
            vaticanReportMessage.setWhichOne(whichReport);
            controller.getConnectedClients().get(0).notifyInterface(gson.toJson(vaticanReportMessage));
            switch (type) {
                case "Move":
                    MoveLorenzoMessage message = new MoveLorenzoMessage();
                    message.setMessageType("MoveLorenzo");
                    message.setNewBlackFaithPoints(controller.getGame().getLorenzo().getBlackFaithPoints());
                    return gson.toJson(message);
                case "MoveAndShuffle":
                    MoveAndShuffleMessage message1 = new MoveAndShuffleMessage();
                    message1.setMessageType("MoveAndShuffle");
                    message1.setNewBlackFaithPoints(controller.getGame().getLorenzo().getBlackFaithPoints());
                    ArrayList<String> config = new ArrayList<>();
                    for(ActionCard c: controller.getGame().getActionCardStack().getCards()){
                        config.add(c.getType());
                    }
                    message1.setActionCardConfiguration(config);
                    return gson.toJson(message1);
                case "Remove":
                    NoitifyDeckgridChangedMessage message2 = new NoitifyDeckgridChangedMessage();
                    message2.setMessageType("NotifyDeckgridChanged");
                    message2.setDeckgridConfiguration(controller.getGame().getDeckgrid());
                    return gson.toJson(message2);
            }
        }
        Gson gson = new Gson();
        Message notification = new Message();
        notification.setMessageType("ActionCardActivationFailureNotification");
        return gson.toJson(notification);
    }
}
