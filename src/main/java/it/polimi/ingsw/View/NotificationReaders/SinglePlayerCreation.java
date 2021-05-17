package it.polimi.ingsw.View.NotificationReaders;

import com.google.gson.Gson;
import it.polimi.ingsw.Controller.Messages.SinglePlayerCreationMessage;
import it.polimi.ingsw.View.ModelPrinter;

public class SinglePlayerCreation extends NotificationReader{
    public SinglePlayerCreation(ModelPrinter modelPrinter) {
        super(modelPrinter);
    }

    @Override
    public void readNotification(String notification) {
        Gson gson = new Gson();
        SinglePlayerCreationMessage data = gson.fromJson(notification, SinglePlayerCreationMessage.class);
        modelPrinter.getMarketBoardPrinter().setMarbleGrid(data.getMarbleGridConfiguration());
        modelPrinter.getMarketBoardPrinter().setMarbleOut(data.getMarbleOut());
        modelPrinter.setBlackFaithPoints(0);
        modelPrinter.getDeckGridPrinter().setDeckgrid(data.getDeckgridConfiguration());
        modelPrinter.getLeaderCardsPrinters().get(0).setChoosableLeaderCards(data.getChoosableLeaderCardsNumbers());
    }
}
