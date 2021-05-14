package it.polimi.ingsw.View.NotificationReaders;

import com.google.gson.Gson;
import it.polimi.ingsw.Controller.Messages.NotifyInsertedOkMessage;
import it.polimi.ingsw.View.Printers.PersonalBoardPrinter;
import it.polimi.ingsw.View.Printers.Printable;
import it.polimi.ingsw.View.VirtualView;

public class InsertedResourceChanged extends NotificationReader{
    String nickname;

    public InsertedResourceChanged(VirtualView virtualView) {
        super(virtualView);
    }

    @Override
    public void readNotification(String notification) {
        Gson gson = new Gson();
        NotifyInsertedOkMessage data = gson.fromJson(notification, NotifyInsertedOkMessage.class);
        nickname = data.getNickname();
        printNotification();
        data.getExtraDepositsConfiguration();
        //TODO FARE UN PRINTER PER TEMPORARY RESOURCES data.getTemporaryResourcesConfiguration();
        for(PersonalBoardPrinter p: virtualView.getPersonalBoards() ){
            if( p.getOwnerNickname().equals(nickname) ){
                p.setWareHouseDepot(data.getWarehouseConfiguration());
                p.setExtraDeposit1(data.getExtraDepositsConfiguration()[0]);
                p.setExtraDeposit2(data.getExtraDepositsConfiguration()[1]);
            }
        }
    }

    public void printNotification(){
        System.out.println("Player " + nickname + " inserted resources into warehouse!");
    }
}
