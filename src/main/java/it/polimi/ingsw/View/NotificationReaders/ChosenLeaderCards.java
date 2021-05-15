package it.polimi.ingsw.View.NotificationReaders;

import com.google.gson.Gson;
import it.polimi.ingsw.Controller.Messages.ChosenLeaderCardsMessage;
import it.polimi.ingsw.View.Printers.LeaderCardsPrinter;
import it.polimi.ingsw.View.VirtualView;

public class ChosenLeaderCards extends NotificationReader{

    String nickname;

    public ChosenLeaderCards(VirtualView virtualView){
        super(virtualView);
    }

    @Override
    public void readNotification(String notification) {
        Gson gson = new Gson();
        ChosenLeaderCardsMessage data = gson.fromJson(notification, ChosenLeaderCardsMessage.class);

        nickname = data.getSenderNickname();
        printNotification();
        for(LeaderCardsPrinter l : virtualView.getLeaderCardsPrinters() ){
            if( l.getOwnerNickname().equals(nickname) ){
                l.setChoosedLeaderCard(0, data.getFirstChosenLeaderCardNumber() );
                l.setChoosedLeaderCard(1, data.getSecondChosenLeaderCardNumber() );
            }
        }
    }

    public void printNotification(){
        System.out.println("Player " + nickname + " has chosen Leader cards!");
    }
}
