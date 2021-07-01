package it.polimi.ingsw.View.GUINotifiers;

import com.google.gson.Gson;
import it.polimi.ingsw.Controller.Messages.NotifyInsertedOkMessage;
import it.polimi.ingsw.View.GUI;
import it.polimi.ingsw.View.ModelPrinter;
import it.polimi.ingsw.View.Printers.PersonalBoardPrinter;
import javafx.application.Platform;

/**
 * Notifies the GUI of the insertion of a resource
 */
public class InsertedResGUINotifier extends GUINotifier{
    private final ModelPrinter modelPrinter;

    public InsertedResGUINotifier(ModelPrinter modelPrinter) {
        this.modelPrinter = modelPrinter;
    }

    @Override
    public void notifyGui(String notification) {
        Gson gson = new Gson();
        NotifyInsertedOkMessage data = gson.fromJson(notification,NotifyInsertedOkMessage.class);
        updateModelPrinter(data);
        if(GUI.getStatus()!=null) {
            Platform.runLater(() -> {
                switch (GUI.getStatus()) {
                    case "Market":
                        GUI.getMarketSceneController().printScene(modelPrinter);
                        GUI.getMarketSceneController().notifyChangement("has inserted resources.", data.getNickname());
                        break;
                    case "Deck":
                        GUI.getDeckgridSceneController().printScene(modelPrinter);
                        GUI.getDeckgridSceneController().notifyChanged("has inserted resources.", data.getNickname());
                        break;
                    case "Main":
                        GUI.getMainSceneController().printClientPlayer(modelPrinter);
                        GUI.getMainSceneController().notifyChangement("has inserted resources.", data.getNickname());
                        break;
                }
            });
        }
    }

    private void updateModelPrinter(NotifyInsertedOkMessage data){
        data.getExtraDepositsConfiguration();
        String nickname = data.getNickname();
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
}
