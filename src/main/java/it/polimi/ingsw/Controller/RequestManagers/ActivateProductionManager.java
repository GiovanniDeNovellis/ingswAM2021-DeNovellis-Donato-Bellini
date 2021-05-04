package it.polimi.ingsw.Controller.RequestManagers;

import com.google.gson.Gson;
import it.polimi.ingsw.Controller.ClientHandler;
import it.polimi.ingsw.Controller.Controller;
import it.polimi.ingsw.Controller.Messages.*;
import it.polimi.ingsw.ExtraDeposit;

public class ActivateProductionManager implements Manageable {
    private final Controller controller;

    public ActivateProductionManager(Controller controller) {
        this.controller = controller;
    }

    @Override
    public String manageRequest(String jsonContent) {
        Gson gson = new Gson();
        ActivateProductionMessage message = gson.fromJson(jsonContent, ActivateProductionMessage.class);
        int[] faithCardsBefore = controller.getVaticanReport();

        if(controller.getGame().getCurrentPlayer().getNickname().equals(message.getSenderNickname())) {
            if (controller.getGame().activateProduction(message.getWhichDevCardSlot(),
                    message.isFromPersonalBoard(),
                    message.getWhichLeaderCard(),
                    message.getResourceBaseProduction()[0],
                    message.getResourceBaseProduction()[1],
                    message.getResourceBaseProduction()[2],
                    message.getResourceFromLeader(),
                    message.getPayUsingExtraDeposit()[0],
                    message.getPayUsingExtraDeposit()[1])) {

                SimpleMessage mex = new SimpleMessage();
                mex.setMessageContent("ActivateProductionSuccessNotification");
                NotifyActivateProductionMessage notification = new NotifyActivateProductionMessage();
                notification.setMessageType("NotifyActivateProductionMessage");
                notification.setWhoActivatesProduction(message.getSenderNickname());
                notification.setWarehouseConfiguration(controller.getGame().getCurrentPlayer().getPersonalBoard().getWarehouseDepot());
                ExtraDeposit extraDep1 = controller.getGame().getCurrentPlayer().getPersonalBoard().getExtraDeposit1();
                ExtraDeposit extraDep2 = controller.getGame().getCurrentPlayer().getPersonalBoard().getExtraDeposit2();
                ExtraDeposit[] extraDep = {extraDep1, extraDep2};
                notification.setExtraDepositConfiguration(extraDep);
                notification.setStrongboxConfiguration(controller.getGame().getCurrentPlayer().getPersonalBoard().getStrongbox().getResourcesContained());
                notification.setNewFaithPoints(controller.getGame().getCurrentPlayer().getFaithPoints());
                for (ClientHandler clientHandler : controller.getConnectedClients()) {
                    clientHandler.notifyInterface(gson.toJson(notification));
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
                    clientHandler.notifyInterface(gson.toJson(vaticanReportMessage));
                }

                return gson.toJson(mex);
            } else {
                SimpleMessage mex = new SimpleMessage();
                mex.setMessageContent("ActivateProductionFailureNotification");
                return gson.toJson(mex);
            }
        }
        SimpleMessage mex = new SimpleMessage();
        mex.setMessageContent("NotYourTurnNotification");
        return gson.toJson(mex);
    }
}
