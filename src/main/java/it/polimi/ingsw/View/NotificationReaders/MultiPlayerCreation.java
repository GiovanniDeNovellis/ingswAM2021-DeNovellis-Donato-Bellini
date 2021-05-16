package it.polimi.ingsw.View.NotificationReaders;

import com.google.gson.Gson;
import it.polimi.ingsw.Controller.Messages.MultiplayerCreationMessage;
import it.polimi.ingsw.View.Printers.LeaderCardsPrinter;
import it.polimi.ingsw.View.Printers.PersonalBoardPrinter;
import it.polimi.ingsw.View.VirtualView;

public class MultiPlayerCreation extends NotificationReader{

    public MultiPlayerCreation(VirtualView virtualView){
        super(virtualView);
    }

    @Override
    public void readNotification(String notification) {
        Gson gson = new Gson();
        MultiplayerCreationMessage data = gson.fromJson(notification, MultiplayerCreationMessage.class);
        printNotification();

        virtualView.getDeckGridPrinter().setDeckgrid(data.getDeckgridConfiguration());
        virtualView.getMarketBoardPrinter().setMarbleGrid(data.getMarbleGridConfiguration());
        virtualView.getMarketBoardPrinter().setMarbleOut(data.getMarbleOut());
        for(LeaderCardsPrinter l : virtualView.getLeaderCardsPrinters() ){
            if( l.getOwnerNickname().equals(data.getNickname())) {
                l.setChoosableLeaderCards(data.getChoosableLeaderCardsNumbers());
            }
        }
        for(PersonalBoardPrinter p: virtualView.getPersonalBoards() ){
            if( p.getOwnerNickname().equals(data.getNickname()))
                p.setPlayerNumber(data.getPlayerNumber());
        }
    }

    public void printNotification(){
        System.out.println("Game started in multiplayer mode!");
    }
}
