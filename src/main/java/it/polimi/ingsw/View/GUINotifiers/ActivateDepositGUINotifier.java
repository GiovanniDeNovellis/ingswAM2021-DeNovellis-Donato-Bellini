package it.polimi.ingsw.View.GUINotifiers;

import com.google.gson.Gson;
import it.polimi.ingsw.Controller.Messages.ActivateLeaderAbilityDepositMessage;
import it.polimi.ingsw.View.GUI;
import it.polimi.ingsw.View.ModelPrinter;
import it.polimi.ingsw.View.Printers.PersonalBoardPrinter;
import javafx.application.Platform;

public class ActivateDepositGUINotifier extends GUINotifier {
    private ModelPrinter modelPrinter;

    public ActivateDepositGUINotifier(ModelPrinter modelPrinter) {
        this.modelPrinter = modelPrinter;
    }

    @Override
    public void notifyGui(String notification) {
        Gson gson = new Gson();
        ActivateLeaderAbilityDepositMessage data = gson.fromJson(notification,ActivateLeaderAbilityDepositMessage.class);
        updateModelPrinter(data);
        Platform.runLater(()->{
            switch (GUI.getStatus()) {
                case "Market":
                    GUI.getMarketSceneController().printScene(modelPrinter);
                    GUI.getMarketSceneController().notifyChangement("has built an extra deposit.", data.getSenderNickname());
                    break;
                case "Deck":
                    GUI.getDeckgridSceneController().printScene(modelPrinter);
                    GUI.getDeckgridSceneController().notifyChanged("has built an extra deposit.", data.getSenderNickname());
                    break;
                case "Main":
                    GUI.getMainSceneController().printClientPlayer(modelPrinter);
                    GUI.getMainSceneController().notifyChangement("has built an extra deposit.", data.getSenderNickname());
                    break;
            }

        });
    }

    private void updateModelPrinter(ActivateLeaderAbilityDepositMessage data){
        for(PersonalBoardPrinter p : modelPrinter.getPersonalBoards()){
            if(p.getOwnerNickname().equals(data.getSenderNickname())){
                if(data.getExtraDepositConfiguration()[0]!=null)
                    p.setExtraDeposit1(data.getExtraDepositConfiguration()[0]);
                if(data.getExtraDepositConfiguration()[1]!=null)
                    p.setExtraDeposit1(data.getExtraDepositConfiguration()[1]);
            }
        }
    }
}
