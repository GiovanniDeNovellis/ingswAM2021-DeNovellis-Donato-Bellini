package it.polimi.ingsw.View.CLINotifiers;

import com.google.gson.Gson;
import it.polimi.ingsw.Controller.Messages.MoveLorenzoMessage;
import it.polimi.ingsw.View.ModelPrinter;

/**
 * Notifies the CLI of a move action card activation
 */
public class MoveLorenzoReader extends CLINotifier {
    public MoveLorenzoReader(ModelPrinter modelPrinter) {
        super(modelPrinter);
    }

    @Override
    public void notifyCLI(String notification) {
        Gson gson = new Gson();
        MoveLorenzoMessage m = gson.fromJson(notification,MoveLorenzoMessage.class);
        modelPrinter.setBlackFaithPoints(m.getNewBlackFaithPoints());
        System.out.println("You activated a move action card. Lorenzo obtained 2 faith points");
    }
}
