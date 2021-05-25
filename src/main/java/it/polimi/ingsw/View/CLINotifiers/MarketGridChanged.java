package it.polimi.ingsw.View.CLINotifiers;

import com.google.gson.Gson;
import it.polimi.ingsw.Controller.Messages.MarketGridChangedMessage;
import it.polimi.ingsw.View.ModelPrinter;

public class MarketGridChanged extends CLINotifier {
    public MarketGridChanged(ModelPrinter modelPrinter) {
        super(modelPrinter);
    }

    @Override
    public void notifyCLI(String notification) {
        Gson gson = new Gson();
        MarketGridChangedMessage data = gson.fromJson(notification,MarketGridChangedMessage.class);
        modelPrinter.getMarketBoardPrinter().setMarbleOut(data.getMarbleout());
        modelPrinter.getMarketBoardPrinter().setMarbleGrid(data.getMarketGridConfiguration());
        printNotification();
    }

    public void printNotification(){
        System.out.println("The market grid changed! Use \"show market board\" to see the new configuration.");
    }
}
