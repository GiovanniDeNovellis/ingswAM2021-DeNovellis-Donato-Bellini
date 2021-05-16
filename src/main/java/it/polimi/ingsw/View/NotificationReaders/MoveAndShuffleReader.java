package it.polimi.ingsw.View.NotificationReaders;

import com.google.gson.Gson;
import it.polimi.ingsw.Controller.Messages.MoveAndShuffleMessage;
import it.polimi.ingsw.View.ModelPrinter;

public class MoveAndShuffleReader extends NotificationReader{
    public MoveAndShuffleReader(ModelPrinter modelPrinter) {
        super(modelPrinter);
    }

    @Override
    public void readNotification(String notification) {
        Gson gson = new Gson();
        MoveAndShuffleMessage m = gson.fromJson(notification, MoveAndShuffleMessage.class);
        modelPrinter.setBlackFaithPoints(m.getNewBlackFaithPoints());
        System.out.println("You activated a move and shuffle card. Lorenzo obtained 1 faith point and the action card stack has been shuffled.");
    }
}
