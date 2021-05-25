package it.polimi.ingsw.View.CLINotifiers;

import com.google.gson.Gson;
import it.polimi.ingsw.Controller.Messages.MultiplayerCreationMessage;
import it.polimi.ingsw.View.ModelPrinter;
import it.polimi.ingsw.View.Printers.LeaderCardsPrinter;
import it.polimi.ingsw.View.Printers.PersonalBoardPrinter;

public class MultiPlayerCreation extends CLINotifier {

    public MultiPlayerCreation(ModelPrinter modelPrinter){
        super(modelPrinter);
    }

    @Override
    public void notifyCLI(String notification) {
        Gson gson = new Gson();
        MultiplayerCreationMessage data = gson.fromJson(notification, MultiplayerCreationMessage.class);
        printNotification();
        for(LeaderCardsPrinter l : modelPrinter.getLeaderCardsPrinters()){
            l.setBuilt(true);
        }

        modelPrinter.getDeckGridPrinter().setDeckgrid(data.getDeckgridConfiguration());
        modelPrinter.getMarketBoardPrinter().setMarbleGrid(data.getMarbleGridConfiguration());
        modelPrinter.getMarketBoardPrinter().setMarbleOut(data.getMarbleOut());
        for(LeaderCardsPrinter l : modelPrinter.getLeaderCardsPrinters() ){
            if( l.getOwnerNickname().equals(data.getNickname())) {
                l.setChoosableLeaderCards(data.getChoosableLeaderCardsNumbers());
            }
        }
        for(PersonalBoardPrinter p: modelPrinter.getPersonalBoards() ){
            if( p.getOwnerNickname().equals(data.getNickname()))
                p.setPlayerNumber(data.getPlayerNumber());
        }
    }

    public void printNotification(){
        if( !modelPrinter.isMultiplayerGameStarted() )
            modelPrinter.setMultiplayerGameStarted(true);
    }
}
