package it.polimi.ingsw.Controller.RequestManagers;

import com.google.gson.Gson;
import it.polimi.ingsw.ActionCard;
import it.polimi.ingsw.Controller.Controller;
import it.polimi.ingsw.Controller.Messages.*;

import java.util.ArrayList;

public class ActionCardActivationManager implements Manageable{
    private final Controller controller;

    public ActionCardActivationManager(Controller controller) {
        this.controller = controller;
    }

    @Override
    public String manageRequest(String jsonContent) {
        String type = controller.getGame().getActionCardStack().getCards().get(0).getType();
        if (controller.getGame().activateActionCard()) {
            Gson gson = new Gson();
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
