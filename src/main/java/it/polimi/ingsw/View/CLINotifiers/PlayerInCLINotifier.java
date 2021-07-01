package it.polimi.ingsw.View.CLINotifiers;

import com.google.gson.Gson;
import it.polimi.ingsw.Controller.Messages.PlayerInNotification;
import it.polimi.ingsw.View.ModelPrinter;

/**
 * Notifies the CLI of the connection of a player
 */
public class PlayerInCLINotifier extends CLINotifier {
    public PlayerInCLINotifier(ModelPrinter modelPrinter) {
        super(modelPrinter);
    }

    @Override
    public void notifyCLI(String notification) {
        Gson gson = new Gson();
        PlayerInNotification playerInNotification = gson.fromJson(notification,PlayerInNotification.class);
        System.out.println(playerInNotification.getSenderNickname() + " is back to the game!");
    }
}
