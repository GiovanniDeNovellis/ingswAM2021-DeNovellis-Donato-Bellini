package it.polimi.ingsw.Controller.RequestManagers;

import com.google.gson.Gson;
import it.polimi.ingsw.Controller.ClientHandler;
import it.polimi.ingsw.Controller.Controller;
import it.polimi.ingsw.Controller.Messages.*;
import it.polimi.ingsw.Controller.NotifiableHandler;
import it.polimi.ingsw.ExtraDeposit;

public class ActivateLeaderAbilityManager implements Manageable{
    private final Controller controller;

    public ActivateLeaderAbilityManager(Controller controller) {
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
        ActivateLeaderAbilityMessage activateLeaderAbilityMessage = gson.fromJson(jsonContent,ActivateLeaderAbilityMessage.class);
        if(controller.getGame().getCurrentPlayer().getNickname().equals(activateLeaderAbilityMessage.getSenderNickname())) {
            if (controller.getGame().activateLeaderAbility(activateLeaderAbilityMessage.getPosition())) {
                Message mex = new Message();
                mex.setMessageType("ActivateLeaderAbilitySuccessNotification");
                switch (controller.getGame().getCurrentPlayer().getChoosedLeaderCards().get(activateLeaderAbilityMessage.getPosition()).getType()) {
                    case "Discount":
                        ActivateLeaderAbilityDiscountMessage notification = new ActivateLeaderAbilityDiscountMessage();
                        notification.setMessageType("ActivateLeaderAbilityDiscount");
                        notification.setPosition(activateLeaderAbilityMessage.getPosition());
                        notification.setSenderNickname(controller.getGame().getCurrentPlayer().getNickname());
                        for (NotifiableHandler clientHandler : controller.getConnectedClients()) {
                            clientHandler.notifyInterface(gson.toJson(notification));
                        }
                        break;
                    case "Deposit":
                        ActivateLeaderAbilityDepositMessage notification2 = new ActivateLeaderAbilityDepositMessage();
                        notification2.setMessageType("ActivateLeaderAbilityDeposit");
                        notification2.setPosition(activateLeaderAbilityMessage.getPosition());
                        ExtraDeposit extraDep1 = controller.getGame().getCurrentPlayer().getPersonalBoard().getExtraDeposit1();
                        ExtraDeposit extraDep2 = controller.getGame().getCurrentPlayer().getPersonalBoard().getExtraDeposit2();
                        ExtraDeposit[] extraDep = {extraDep1, extraDep2};
                        notification2.setExtraDepositConfiguration(extraDep);
                        notification2.setSenderNickname(controller.getGame().getCurrentPlayer().getNickname());
                        for (NotifiableHandler clientHandler : controller.getConnectedClients()) {
                            clientHandler.notifyInterface(gson.toJson(notification2));
                        }
                        break;
                    case "Production":
                        ActivateLeaderAbilityProduction notification3 = new ActivateLeaderAbilityProduction();
                        notification3.setMessageType("ActivateLeaderAbilityProduction");
                        notification3.setPosition(activateLeaderAbilityMessage.getPosition());
                        notification3.setSenderNickname(controller.getGame().getCurrentPlayer().getNickname());
                        for (NotifiableHandler clientHandler : controller.getConnectedClients()) {
                            clientHandler.notifyInterface(gson.toJson(notification3));
                        }
                        break;
                    case "Transformation":
                        ActivateLeaderAbilityTransformationMessage notification4 = new ActivateLeaderAbilityTransformationMessage();
                        notification4.setMessageType("ActivateLeaderAbilityTransformation");
                        notification4.setPosition(activateLeaderAbilityMessage.getPosition());
                        notification4.setTemporaryResourcesConfiguration(controller.getGame().getMarketBoard().getTemporaryResources());
                        notification4.setRemainingWhiteMarbles(controller.getGame().getMarketBoard().getWhiteMarblesSelected());
                        notification4.setSenderNickname(controller.getGame().getCurrentPlayer().getNickname());
                        for (NotifiableHandler clientHandler : controller.getConnectedClients()) {
                            clientHandler.notifyInterface(gson.toJson(notification4));
                        }
                        break;
                }
                return gson.toJson(mex);
            } else {
                Message mex = new Message();
                mex.setMessageType("ActivateLeaderAbilityFailureNotification");
                return gson.toJson(mex);
            }
        }
        Message mex = new Message();
        mex.setMessageType("NotYourTurnNotification");
        return gson.toJson(mex);
    }
}
