package it.polimi.ingsw.View.CLINotifiers;

import com.google.gson.Gson;
import it.polimi.ingsw.Controller.Messages.PlayerOutNotification;
import it.polimi.ingsw.View.ModelPrinter;

/**
 * Notifies the CLI of the disconnection of a player
 */
public class PlayerOutCLINotifier extends CLINotifier {
    public PlayerOutCLINotifier(ModelPrinter modelPrinter) {
        super(modelPrinter);
    }

    @Override
    public void notifyCLI(String notification) {
        Gson gson = new Gson();
        PlayerOutNotification p = gson.fromJson(notification,PlayerOutNotification.class);
        System.out.println(p.getSenderNickname() + " has disconnected from the game. His turn will be skipped until he reconnects.");
    }
}
