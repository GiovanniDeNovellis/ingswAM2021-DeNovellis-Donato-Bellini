package it.polimi.ingsw.View.CLINotifiers;
import com.google.gson.Gson;
import it.polimi.ingsw.Controller.Messages.NotifyDevelopmentCardInsertedOKMessage;

import it.polimi.ingsw.View.ModelPrinter;
import it.polimi.ingsw.View.Printers.PersonalBoardPrinter;

/**
 * Notifies the CLI of a development card purchase
 */
public class DevelopmentCardBoughtReader extends CLINotifier {
    String nickname;
    public DevelopmentCardBoughtReader(ModelPrinter modelPrinter) {
        super(modelPrinter);
    }

    @Override
    public void notifyCLI(String notification) {
        Gson gson = new Gson();
        NotifyDevelopmentCardInsertedOKMessage data = gson.fromJson(notification, NotifyDevelopmentCardInsertedOKMessage.class);
        modelPrinter.getDeckGridPrinter().setDeckgrid(data.getDeckgridConfiguration());
        nickname = data.getNickname();
        printNotification();
        for(PersonalBoardPrinter p : modelPrinter.getPersonalBoards()){
            if(p.getOwnerNickname().equals(nickname)){
                p.setDevelopmentCards(data.getDevelopmentCardsConfiguration());
                p.setExtraDeposit1(data.getExtraDepositsConfiguration()[0]);
                p.setExtraDeposit2(data.getExtraDepositsConfiguration()[1]);
                p.setWareHouseDepot(data.getWarehouseConfiguration());
                p.setStrongbox(data.getStrongboxConfiguration());
                p.setAllCardsInserted(data.getInsertedDevelopmentCard());
            }
        }
    }
    public void printNotification(){
        System.out.println("Player " + nickname + " has bought a development card.");
    }
}

