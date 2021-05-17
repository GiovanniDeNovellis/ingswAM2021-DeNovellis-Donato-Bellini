package it.polimi.ingsw.View.NotificationReaders;

import com.google.gson.Gson;
import it.polimi.ingsw.Controller.Messages.NotifyActivateProductionMessage;
import it.polimi.ingsw.View.ModelPrinter;
import it.polimi.ingsw.View.Printers.PersonalBoardPrinter;

public class NotifyActivateProductionReader extends NotificationReader {
    public NotifyActivateProductionReader(ModelPrinter modelPrinter) {
        super(modelPrinter);
    }
    String nickname;

    @Override
    public void readNotification(String notification) {
        Gson gson = new Gson();
        NotifyActivateProductionMessage data = gson.fromJson(notification, NotifyActivateProductionMessage.class);
        nickname = data.getWhoActivatesProduction();
        printNotification();
        for (PersonalBoardPrinter p : modelPrinter.getPersonalBoards()) {
            if (p.getOwnerNickname().equals(nickname)) {
                p.setWareHouseDepot(data.getWarehouseConfiguration());
                if(data.getExtraDepositConfiguration()[0]!=null)
                    p.setExtraDeposit1(data.getExtraDepositConfiguration()[0]);
                if (data.getExtraDepositConfiguration()[1]!=null)
                    p.setExtraDeposit1(data.getExtraDepositConfiguration()[1]);
                p.setStrongbox(data.getStrongboxConfiguration());
            }
        }
    }
        public void printNotification() {
            System.out.println("Player " + nickname + " has activated a production.");
        }
    }