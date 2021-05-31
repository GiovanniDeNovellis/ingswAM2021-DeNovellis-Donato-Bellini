package it.polimi.ingsw.View.CLINotifiers;

import com.google.gson.Gson;
import it.polimi.ingsw.Controller.Messages.ActivateLeaderAbilityMessage;
import it.polimi.ingsw.View.ModelPrinter;

public class ActivateLeaderAbilityDiscountReader extends CLINotifier {
    public ActivateLeaderAbilityDiscountReader(ModelPrinter modelPrinter) {
        super(modelPrinter);
    }

    @Override
    public void notifyCLI(String notification) {
        Gson gson = new Gson();
        ActivateLeaderAbilityMessage m = gson.fromJson(notification, ActivateLeaderAbilityMessage.class);
        System.out.println(m.getSenderNickname() + " has activated his discount leader card in position " + m.getPosition());
    }
}