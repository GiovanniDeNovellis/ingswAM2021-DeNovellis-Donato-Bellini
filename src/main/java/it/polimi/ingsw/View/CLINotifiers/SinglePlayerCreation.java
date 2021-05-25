package it.polimi.ingsw.View.CLINotifiers;

import com.google.gson.Gson;
import it.polimi.ingsw.Controller.Messages.SinglePlayerCreationMessage;
import it.polimi.ingsw.View.ModelPrinter;
import it.polimi.ingsw.View.Printers.LeaderCardsPrinter;

public class SinglePlayerCreation extends CLINotifier {
    public SinglePlayerCreation(ModelPrinter modelPrinter) {
        super(modelPrinter);
    }

    @Override
    public void notifyCLI(String notification) {
        Gson gson = new Gson();
        SinglePlayerCreationMessage data = gson.fromJson(notification, SinglePlayerCreationMessage.class);
        for(LeaderCardsPrinter l: modelPrinter.getLeaderCardsPrinters()){
            l.setBuilt(true);
        }
        modelPrinter.getMarketBoardPrinter().setMarbleGrid(data.getMarbleGridConfiguration());
        modelPrinter.getMarketBoardPrinter().setMarbleOut(data.getMarbleOut());
        modelPrinter.setBlackFaithPoints(0);
        modelPrinter.getDeckGridPrinter().setDeckgrid(data.getDeckgridConfiguration());
        modelPrinter.getLeaderCardsPrinters().get(0).setChoosableLeaderCards(data.getChoosableLeaderCardsNumbers());
    }
}
