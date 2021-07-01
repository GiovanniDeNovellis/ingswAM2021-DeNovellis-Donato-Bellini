package it.polimi.ingsw.View.CLINotifiers;

import com.google.gson.Gson;
import it.polimi.ingsw.Controller.Messages.TemporaryResourcesChangedMessage;
import it.polimi.ingsw.View.ModelPrinter;

/**
 * Notifies the CLI of the changement of the temporary resources
 */
public class TemporaryResourcesChanged extends CLINotifier {

    public TemporaryResourcesChanged(ModelPrinter modelPrinter){
        super(modelPrinter);
    }

    @Override
    public void notifyCLI(String notification) {
        Gson gson = new Gson();
        TemporaryResourcesChangedMessage data = gson.fromJson(notification, TemporaryResourcesChangedMessage.class);
        printNotification();
        modelPrinter.getMarketBoardPrinter().setTemporaryResources(data.getTemporaryResourcesConfiguration());
    }

    public void printNotification(){
        System.out.println("Write \"show temporary resources\" to see the remaining resource(s)");
    }
}
