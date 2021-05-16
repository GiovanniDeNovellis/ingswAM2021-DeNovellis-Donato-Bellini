package it.polimi.ingsw.View.NotificationReaders;

import com.google.gson.Gson;
import it.polimi.ingsw.Controller.Messages.PlayerOutNotification;
import it.polimi.ingsw.View.ModelPrinter;

public class PlayerOutNotificationReader extends NotificationReader{
    public PlayerOutNotificationReader(ModelPrinter modelPrinter) {
        super(modelPrinter);
    }

    @Override
    public void readNotification(String notification) {
        Gson gson = new Gson();
        PlayerOutNotification p = gson.fromJson(notification,PlayerOutNotification.class);
        System.out.println(p.getSenderNickname() + " has disconnected from the game. His turn will be skipped until he reconnects.");
    }
}
