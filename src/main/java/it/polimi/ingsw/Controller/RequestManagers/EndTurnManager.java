package it.polimi.ingsw.Controller.RequestManagers;

import com.google.gson.Gson;
import it.polimi.ingsw.Controller.ClientHandler;
import it.polimi.ingsw.Controller.Controller;
import it.polimi.ingsw.Controller.Messages.EndTurnNotificationMessage;
import it.polimi.ingsw.Controller.Messages.EndTurnRequestMessage;
import it.polimi.ingsw.Controller.Messages.Message;
import it.polimi.ingsw.ResourceType;

public class EndTurnManager implements Manageable{
    private final Controller controller;

    public EndTurnManager(Controller controller) {
        this.controller = controller;
    }

    @Override
    public String manageRequest(String jsonContent) {
        Gson gson = new Gson();
        Message message = new Message();
        boolean ans1;
        boolean ans2=false;
        boolean endGame=false;
        String nickname=null;
        String winnerNickname=null;
        int tempResourcesDiscarded=0;
        EndTurnRequestMessage endTurnRequestMessage = gson.fromJson(jsonContent, EndTurnRequestMessage.class);
            ans1= endTurnRequestMessage.getSenderNickname().equals(controller.getGame().getCurrentPlayer().getNickname());
            if(ans1){
                for(ResourceType res: controller.getGame().getMarketBoard().getTemporaryResources().keySet()){
                tempResourcesDiscarded+=controller.getGame().getMarketBoard().getTemporaryResources().get(res);
                }
                ans2= controller.getGame().endTurn();
                if(ans2){
                    nickname=controller.getGame().getCurrentPlayer().getNickname();
                    endGame=controller.getGame().isEndGame();
                    if(controller.getGame().getWinnerPlayer()!=null)
                        winnerNickname=controller.getGame().getWinnerPlayer().getNickname();
                }
            }
        if(ans1){
            if(ans2){
                EndTurnNotificationMessage mex = new EndTurnNotificationMessage();
                mex.setActualCurrentPlayer(nickname);
                mex.setNumResourcesDiscarded(tempResourcesDiscarded);
                mex.setWinnerPlayerNickname(winnerNickname);
                mex.setGameEnding(endGame);
                String notificationForAll = gson.toJson(mex);
                    for(ClientHandler c : controller.getConnectedClients()){
                        c.notifyInterface(notificationForAll);
                    }
                message.setMessageType("EndTurnOkNotification");
                return gson.toJson(message);
            }
            message.setMessageType("NotRightToEndTurnNotification");
            return gson.toJson(message);
        }
        message.setMessageType("NotYourTurnNotification");
        return gson.toJson(message);
    }
}
