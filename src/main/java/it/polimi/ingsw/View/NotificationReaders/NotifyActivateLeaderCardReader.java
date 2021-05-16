package it.polimi.ingsw.View.NotificationReaders;

import com.google.gson.Gson;
import it.polimi.ingsw.Controller.Messages.NotifyActivateLeaderCard;
import it.polimi.ingsw.View.ModelPrinter;
import it.polimi.ingsw.View.Printers.LeaderCardsPrinter;

public class NotifyActivateLeaderCardReader extends NotificationReader{
    public NotifyActivateLeaderCardReader(ModelPrinter modelPrinter) {
        super(modelPrinter);
    }

    @Override
    public void readNotification(String notification) {
        Gson gson = new Gson();
        NotifyActivateLeaderCard m = gson.fromJson(notification, NotifyActivateLeaderCard.class);
        for(LeaderCardsPrinter l : modelPrinter.getLeaderCardsPrinters()){
            if(l.getOwnerNickname().equals(m.getWhoActivatedLeaderCard()))
                l.activateLeaderCard(m.getActivatedLeaderCardPosition());
        }
        System.out.println(m.getWhoActivatedLeaderCard() + " activated a Leader Card in position: " + m.getActivatedLeaderCardPosition());
    }
}
