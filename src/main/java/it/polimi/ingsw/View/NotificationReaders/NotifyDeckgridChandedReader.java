package it.polimi.ingsw.View.NotificationReaders;

import com.google.gson.Gson;
import it.polimi.ingsw.Controller.Messages.NoitifyDeckgridChangedMessage;
import it.polimi.ingsw.View.ModelPrinter;

public class NotifyDeckgridChandedReader extends NotificationReader{
    public NotifyDeckgridChandedReader(ModelPrinter modelPrinter) {
        super(modelPrinter);
    }

    @Override
    public void readNotification(String notification) {
        Gson gson = new Gson();
        NoitifyDeckgridChangedMessage m = gson.fromJson(notification,NoitifyDeckgridChangedMessage.class);
        modelPrinter.getDeckGridPrinter().setDeckgrid(m.getDeckgridConfiguration());
        System.out.println("You activated a remove Development Card action card and the deckgrid changed");
    }
}
