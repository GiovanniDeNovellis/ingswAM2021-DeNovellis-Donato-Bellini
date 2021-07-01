package it.polimi.ingsw.View.CLINotifiers;

import com.google.gson.Gson;
import it.polimi.ingsw.Controller.Messages.NotifyInsertedOkMessage;
import it.polimi.ingsw.View.Printers.PersonalBoardPrinter;
import it.polimi.ingsw.View.ModelPrinter;

/**
 * Notifies the CLI of a resource insertion
 */
public class InsertedResourceChanged extends CLINotifier {
    String nickname;

    public InsertedResourceChanged(ModelPrinter modelPrinter) {
        super(modelPrinter);
    }

    @Override
    public void notifyCLI(String notification) {
        Gson gson = new Gson();
        NotifyInsertedOkMessage data = gson.fromJson(notification, NotifyInsertedOkMessage.class);
        nickname = data.getNickname();
        printNotification();
        data.getExtraDepositsConfiguration();
        modelPrinter.getMarketBoardPrinter().setTemporaryResources(data.getTemporaryResourcesConfiguration());
        for(PersonalBoardPrinter p: modelPrinter.getPersonalBoards() ){
            if( p.getOwnerNickname().equals(nickname) ){
                p.setWareHouseDepot(data.getWarehouseConfiguration());
                if (data.getExtraDepositsConfiguration()[0]!=null)
                    p.setExtraDeposit1(data.getExtraDepositsConfiguration()[0]);
                if (data.getExtraDepositsConfiguration()[1]!=null)
                    p.setExtraDeposit2(data.getExtraDepositsConfiguration()[1]);
            }
        }
    }

    public void printNotification(){
        System.out.println("Player " + nickname + " inserted resources into warehouse!");
    }
}
