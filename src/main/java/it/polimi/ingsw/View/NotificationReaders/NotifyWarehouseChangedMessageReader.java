package it.polimi.ingsw.View.NotificationReaders;

import com.google.gson.Gson;
import it.polimi.ingsw.Controller.Messages.NotifyWarehouseChangedMessage;
import it.polimi.ingsw.View.ModelPrinter;
import it.polimi.ingsw.View.Printers.PersonalBoardPrinter;

public class NotifyWarehouseChangedMessageReader extends NotificationReader {
    public NotifyWarehouseChangedMessageReader(ModelPrinter modelPrinter) {
        super(modelPrinter);
    }

    String nickname;

    @Override
    public void readNotification(String notification) {
        Gson gson = new Gson();
        NotifyWarehouseChangedMessage data = gson.fromJson(notification, NotifyWarehouseChangedMessage.class);
        nickname = data.getNickname();
        printNotification();
        for (PersonalBoardPrinter p : modelPrinter.getPersonalBoards()) {
            if (p.getOwnerNickname().equals(nickname)) {
                p.setWareHouseDepot(data.getWarehouseConfiguration());
            }
        }
    }
    public void printNotification(){
        System.out.println("Player " + nickname + " has changed his warehouse configuration.");
    }
}

