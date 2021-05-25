package it.polimi.ingsw.View.CLINotifiers;

import com.google.gson.Gson;
import it.polimi.ingsw.Controller.Messages.ActivateLeaderAbilityProduction;
import it.polimi.ingsw.View.ModelPrinter;

public class ActivateLeaderAbilityProductionReader extends CLINotifier {
    public ActivateLeaderAbilityProductionReader(ModelPrinter modelPrinter) {
        super(modelPrinter);
    }

    @Override
    public void notifyCLI(String notification) {
        Gson gson = new Gson();
        ActivateLeaderAbilityProduction m = gson.fromJson(notification,ActivateLeaderAbilityProduction.class);
        System.out.println(m.getSenderNickname() + " has activated a production Leader Ability in position " + m.getPosition());
    }
}
