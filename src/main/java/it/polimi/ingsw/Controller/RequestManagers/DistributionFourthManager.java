package it.polimi.ingsw.Controller.RequestManagers;

import com.google.gson.Gson;
import it.polimi.ingsw.Controller.ClientHandler;
import it.polimi.ingsw.Controller.Controller;
import it.polimi.ingsw.Controller.Messages.DistributionFourthMessage;
import it.polimi.ingsw.Controller.Messages.Message;
import it.polimi.ingsw.Controller.Messages.NotifyWarehouseChangeMessage;
import it.polimi.ingsw.WareHouseDepot;

public class DistributionFourthManager implements Manageable {
    private final Controller controller;

    public DistributionFourthManager(Controller controller) {
        this.controller = controller;
    }

    @Override
    public String manageRequest(String jsonContent) {
        boolean ans1;
        boolean ans2=false;
        Message message = new Message();
        Gson gson = new Gson();
        String nickname=null;
        WareHouseDepot whouse=null;
        DistributionFourthMessage distributionFourthMessage = gson.fromJson(jsonContent,DistributionFourthMessage.class);
            ans1=distributionFourthMessage.getSenderNickname().equals(controller.getGame().getCurrentPlayer().getNickname());
            if(ans1) {
                ans2 = controller.getGame().distributionResourceFourthPlayer(distributionFourthMessage.getResourceToDistribute(),
                        distributionFourthMessage.getSecondResourceToDistribute());
                if (ans2) {
                    nickname = controller.getGame().getCurrentPlayer().getNickname();
                    whouse = controller.getGame().getCurrentPlayer().getPersonalBoard().getWarehouseDepot();
                }
            }
        if(ans1){
            if(ans2){
                NotifyWarehouseChangeMessage mex = new NotifyWarehouseChangeMessage();
                mex.setWarehouseConfiguration(whouse);
                mex.setPlayerToChange(nickname);
                String notificationForAll = gson.toJson(mex);
                    for(ClientHandler c : controller.getConnectedClients()){
                        c.notifyInterface(notificationForAll);
                    }
                message.setMessageType("DistributionOkNotification");
                return gson.toJson(message);
            }
            message.setMessageType("NotRightToDistributionNotification");
            return gson.toJson(message);
        }
        message.setMessageType("NotYourTurnNotification");
        return gson.toJson(message);
    }
}
