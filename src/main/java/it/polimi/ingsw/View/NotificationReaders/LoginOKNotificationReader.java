package it.polimi.ingsw.View.NotificationReaders;

import com.google.gson.Gson;
import it.polimi.ingsw.Controller.Messages.LoginOkNotificationMessage;
import it.polimi.ingsw.View.ModelPrinter;
import it.polimi.ingsw.View.Printers.LeaderCardsPrinter;
import it.polimi.ingsw.View.Printers.PersonalBoardPrinter;

public class LoginOKNotificationReader extends NotificationReader{
    public LoginOKNotificationReader(ModelPrinter modelPrinter) {
        super(modelPrinter);
    }

    @Override
    public void readNotification(String notification) {
        Gson gson = new Gson();
        LoginOkNotificationMessage m = gson.fromJson(notification, LoginOkNotificationMessage.class);
        PersonalBoardPrinter p = new PersonalBoardPrinter();
        p.setOwnerNickname(m.getSenderNickname());
        LeaderCardsPrinter l = new LeaderCardsPrinter();
        l.setOwnerNickname(m.getSenderNickname());
        modelPrinter.getLeaderCardsPrinters().add(l);
        modelPrinter.getPersonalBoards().add(p);
        System.out.println("Logged in.");
    }
}
