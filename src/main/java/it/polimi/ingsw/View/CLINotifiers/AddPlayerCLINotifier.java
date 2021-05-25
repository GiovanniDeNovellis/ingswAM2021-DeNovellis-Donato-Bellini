package it.polimi.ingsw.View.CLINotifiers;

import com.google.gson.Gson;
import it.polimi.ingsw.Controller.Messages.AddPlayerMessage;
import it.polimi.ingsw.View.ModelPrinter;
import it.polimi.ingsw.View.Printers.LeaderCardsPrinter;
import it.polimi.ingsw.View.Printers.PersonalBoardPrinter;

public class AddPlayerCLINotifier extends CLINotifier {
    String nickname;
    public AddPlayerCLINotifier(ModelPrinter modelPrinter){super(modelPrinter);}

    @Override
    public void notifyCLI(String notification) {
        Gson gson = new Gson();
        AddPlayerMessage data = gson.fromJson(notification,AddPlayerMessage.class);
        nickname = data.getSenderNickname();
        PersonalBoardPrinter p = new PersonalBoardPrinter();
        p.setOwnerNickname(nickname);
        LeaderCardsPrinter l = new LeaderCardsPrinter();
        l.setOwnerNickname(nickname);
        modelPrinter.getPersonalBoards().add(p);
        modelPrinter.getLeaderCardsPrinters().add(l);
        printNotification();
    }
    public void printNotification(){
        System.out.println("Player " + nickname + " now play the game.");
    }
}
