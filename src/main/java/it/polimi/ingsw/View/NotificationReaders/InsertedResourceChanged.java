package it.polimi.ingsw.View.NotificationReaders;

import com.google.gson.Gson;
import it.polimi.ingsw.Controller.Messages.NotifyInsertedOkMessage;
import it.polimi.ingsw.View.Printers.PersonalBoardPrinter;
import it.polimi.ingsw.View.ModelPrinter;

public class InsertedResourceChanged extends NotificationReader{
    String nickname;

    public InsertedResourceChanged(ModelPrinter modelPrinter) {
        super(modelPrinter);
    }

    @Override
    public void readNotification(String notification) {
        Gson gson = new Gson();
        NotifyInsertedOkMessage data = gson.fromJson(notification, NotifyInsertedOkMessage.class);
        nickname = data.getNickname();
        printNotification();
        data.getExtraDepositsConfiguration();
        modelPrinter.getMarketBoardPrinter().setTemporaryResources(data.getTemporaryResourcesConfiguration());
        for(PersonalBoardPrinter p: modelPrinter.getPersonalBoards() ){
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
