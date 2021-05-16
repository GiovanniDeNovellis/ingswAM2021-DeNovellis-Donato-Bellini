package it.polimi.ingsw.View.NotificationReaders;

import com.google.gson.Gson;
import it.polimi.ingsw.Controller.Messages.ChosenLeaderCardsMessage;
import it.polimi.ingsw.View.ModelPrinter;
import it.polimi.ingsw.View.Printers.LeaderCardsPrinter;

public class ChosenLeaderCards extends NotificationReader{

    String nickname;

    public ChosenLeaderCards(ModelPrinter modelPrinter){
        super(modelPrinter);
    }

    @Override
    public void readNotification(String notification) {
        Gson gson = new Gson();
        ChosenLeaderCardsMessage data = gson.fromJson(notification, ChosenLeaderCardsMessage.class);

        nickname = data.getSenderNickname();
        printNotification();
        int[] num = new int[2];
        for(LeaderCardsPrinter l : modelPrinter.getLeaderCardsPrinters() ){
            if( l.getOwnerNickname().equals(nickname) ){
                num[0]=data.getFirstChosenLeaderCardNumber();
                num[1]=data.getSecondChosenLeaderCardNumber();
                l.setChosenLeaderCards(num);
            }
        }
    }

    public void printNotification(){
        System.out.println("Player " + nickname + " has chosen Leader cards!");
    }
}
