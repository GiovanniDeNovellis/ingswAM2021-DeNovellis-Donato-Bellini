package it.polimi.ingsw.Controller.RequestManagers;

import com.google.gson.Gson;
import it.polimi.ingsw.Controller.ClientHandler;
import it.polimi.ingsw.Controller.Controller;
import it.polimi.ingsw.Controller.Messages.ChoosedLeaderCardsMessage;
import it.polimi.ingsw.Controller.Messages.LeaderCardSelectionMessage;
import it.polimi.ingsw.Controller.Messages.Message;
import it.polimi.ingsw.LeaderCard;

import java.util.ArrayList;

public class LeaderCardSelectionManager implements Manageable{
    private final Controller controller;

    public LeaderCardSelectionManager(Controller controller) {
        this.controller = controller;
    }

    @Override
    public String manageRequest(String jsonContent) {
        boolean ans1;
        boolean ans2=false;
        Message message = new Message();
        Gson gson = new Gson();
        String nickname=null;
        ArrayList<LeaderCard> lead;
        int num1=0,num2=0;
        LeaderCardSelectionMessage leaderCardSelectionMessage = gson.fromJson(jsonContent,LeaderCardSelectionMessage.class);
        synchronized (controller.getGame()){
            ans1= leaderCardSelectionMessage.getSenderNickname().equals(controller.getGame().getCurrentPlayer().getNickname());
            if(ans1) {
                ans2 = controller.getGame().chooseLeaderCards(leaderCardSelectionMessage.getLeaderCardPosition1(),
                        leaderCardSelectionMessage.getLeaderCardPosition2());
                if (ans2) {
                    nickname = controller.getGame().getCurrentPlayer().getNickname();
                    lead = controller.getGame().getCurrentPlayer().getChoosedLeaderCards();
                    num1=lead.get(0).getLeaderCardNumber();
                    num2=lead.get(1).getLeaderCardNumber();
                }
            }
        }
        if(ans1){
            if(ans2){
                ChoosedLeaderCardsMessage mex = new ChoosedLeaderCardsMessage();
                mex.setSenderNickname(nickname);
                mex.setFirstChosenLeaderCardNumber(num1);
                mex.setSecondChosenLeaderCardNumber(num2);
                String notificationForAll = gson.toJson(mex);
                synchronized (controller.getConnectedClients()){
                    for(ClientHandler c : controller.getConnectedClients()){
                        c.notifyInterface(notificationForAll);
                    }
                }
                message.setMessageType("LeaderCardSelectionOkNotification");
                return gson.toJson(message);
            }
            message.setMessageType("NotRightToLeaderCardSelectionNotification");
            return gson.toJson(message);
        }
        message.setMessageType("NotYourTurnNotification");
        return gson.toJson(message);
    }
}
