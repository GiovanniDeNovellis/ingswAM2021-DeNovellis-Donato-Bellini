package it.polimi.ingsw.View.CLINotifiers;

import com.google.gson.Gson;
import it.polimi.ingsw.Controller.Messages.NotifyDiscardLeaderCard;
import it.polimi.ingsw.View.ModelPrinter;
import it.polimi.ingsw.View.Printers.LeaderCardsPrinter;

/**
 * Notifies the CLI of the discard of a leader card
 */
public class NotifyDiscardLeaderCardReader extends CLINotifier {
    private int position;
    private String nickname;

    public NotifyDiscardLeaderCardReader(ModelPrinter modelPrinter) {
        super(modelPrinter);
    }

    @Override
    public void notifyCLI(String notification) {
        Gson gson = new Gson();
        NotifyDiscardLeaderCard data = gson.fromJson(notification,NotifyDiscardLeaderCard.class);
        position= data.getDiscardedPosition();
        nickname=data.getWhoDiscardedLeaderCard();
        for(LeaderCardsPrinter l : modelPrinter.getLeaderCardsPrinters()){
            if(l.getOwnerNickname().equals(data.getWhoDiscardedLeaderCard())){
                if(data.getDiscardedPosition()==0&&l.getChosenLeaderCards()[0]==0){
                    l.getChosenLeaderCards()[1]=0;
                    l.getActivatedLeaderCards()[1]=false;
                }
                else {
                    l.getChosenLeaderCards()[data.getDiscardedPosition()] = 0;
                    l.getActivatedLeaderCards()[data.getDiscardedPosition()] = false;
                }
            }
        }
        printNotification();
    }

    public void printNotification(){
        System.out.println(nickname + " has discarded a leader card in position " + position);
    }
}
