package it.polimi.ingsw.View.NotificationReaders;

import com.google.gson.Gson;
import it.polimi.ingsw.Controller.Messages.PlayerInNotification;
import it.polimi.ingsw.View.Printers.LeaderCardsPrinter;
import it.polimi.ingsw.View.Printers.PersonalBoardPrinter;
import it.polimi.ingsw.View.ModelPrinter;

public class PlayerInNotificationReader extends NotificationReader{
    public PlayerInNotificationReader(ModelPrinter modelPrinter) {
        super(modelPrinter);
    }

    @Override
    public void readNotification(String notification) {
        Gson gson = new Gson();
        PlayerInNotification playerInNotification = gson.fromJson(notification,PlayerInNotification.class);
        System.out.println(playerInNotification.getSenderNickname() + " is back to the game!");
    }
}
