package it.polimi.ingsw.View.NotificationReaders;

import com.google.gson.Gson;
import it.polimi.ingsw.Controller.Messages.ConnectedPlayersMessage;
import it.polimi.ingsw.View.ModelPrinter;
import it.polimi.ingsw.View.Printers.LeaderCardsPrinter;
import it.polimi.ingsw.View.Printers.PersonalBoardPrinter;

public class ConnectedPlayersMessageReader extends NotificationReader{
    public ConnectedPlayersMessageReader(ModelPrinter modelPrinter) {
        super(modelPrinter);
    }

    @Override
    public void readNotification(String notification) {
        Gson gson = new Gson();
        ConnectedPlayersMessage m = gson.fromJson(notification, ConnectedPlayersMessage.class);
        for(String s: m.getConnectedPlayers()){
            PersonalBoardPrinter p = new PersonalBoardPrinter();
            p.setOwnerNickname(s);
            LeaderCardsPrinter l = new LeaderCardsPrinter();
            l.setOwnerNickname(s);
            modelPrinter.getPersonalBoards().add(p);
            modelPrinter.getLeaderCardsPrinters().add(l);
        }
    }
}
