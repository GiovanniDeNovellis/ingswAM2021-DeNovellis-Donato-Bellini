package it.polimi.ingsw.View.CLINotifiers;

import com.google.gson.Gson;
import it.polimi.ingsw.Controller.Messages.NoitifyDeckgridChangedMessage;
import it.polimi.ingsw.View.ModelPrinter;

public class NotifyDeckgridChangedReader extends CLINotifier {
    public NotifyDeckgridChangedReader(ModelPrinter modelPrinter) {
        super(modelPrinter);
    }

    @Override
    public void notifyCLI(String notification) {
        Gson gson = new Gson();
        NoitifyDeckgridChangedMessage m = gson.fromJson(notification,NoitifyDeckgridChangedMessage.class);
        modelPrinter.getDeckGridPrinter().setDeckgrid(m.getDeckgridConfiguration());
        System.out.println("You activated a remove Development Card action card and the deckgrid changed");
    }
}
