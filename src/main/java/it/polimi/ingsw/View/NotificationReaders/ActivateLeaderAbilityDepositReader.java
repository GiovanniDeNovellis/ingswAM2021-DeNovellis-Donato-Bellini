package it.polimi.ingsw.View.NotificationReaders;

import com.google.gson.Gson;
import it.polimi.ingsw.Controller.Messages.ActivateLeaderAbilityDepositMessage;
import it.polimi.ingsw.View.ModelPrinter;
import it.polimi.ingsw.View.Printers.PersonalBoardPrinter;

public class ActivateLeaderAbilityDepositReader extends NotificationReader{
    public ActivateLeaderAbilityDepositReader(ModelPrinter modelPrinter) {
        super(modelPrinter);
    }

    @Override
    public void readNotification(String notification) {
        Gson gson = new Gson();
        ActivateLeaderAbilityDepositMessage m = gson.fromJson(notification, ActivateLeaderAbilityDepositMessage.class);
        for(PersonalBoardPrinter p : modelPrinter.getPersonalBoards()){
            if(p.getOwnerNickname().equals(m.getSenderNickname())){
                if(m.getExtraDepositConfiguration()[0]!=null)
                    p.setExtraDeposit1(m.getExtraDepositConfiguration()[0]);
                if(m.getExtraDepositConfiguration()[1]!=null)
                    p.setExtraDeposit1(m.getExtraDepositConfiguration()[1]);
            }
        }
        System.out.println(m.getSenderNickname() + "has activated a deposit Leader Card in position " + m.getPosition());
    }
}

