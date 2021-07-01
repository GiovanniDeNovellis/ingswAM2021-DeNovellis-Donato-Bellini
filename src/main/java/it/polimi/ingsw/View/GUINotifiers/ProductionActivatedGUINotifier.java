package it.polimi.ingsw.View.GUINotifiers;

import com.google.gson.Gson;
import it.polimi.ingsw.Controller.Messages.NotifyActivateProductionMessage;
import it.polimi.ingsw.View.GUI;
import it.polimi.ingsw.View.ModelPrinter;
import it.polimi.ingsw.View.Printers.PersonalBoardPrinter;
import javafx.application.Platform;

/**
 * Notifies the GUI of the activation of a production
 */
public class ProductionActivatedGUINotifier extends GUINotifier{

    ModelPrinter modelPrinter;

    public ProductionActivatedGUINotifier(ModelPrinter modelPrinter) {
        this.modelPrinter = modelPrinter;
    }

    @Override
    public void notifyGui(String notification) {
        NotifyActivateProductionMessage data = parseMessage(notification);
        updateModel(data);
        String nickname = data.getWhoActivatesProduction();
        if(GUI.getStatus()!=null) {
            Platform.runLater(() -> {
                switch (GUI.getStatus()) {
                    case "Market":
                        GUI.getMarketSceneController().printScene(modelPrinter);
                        GUI.getMarketSceneController().notifyChangement("has activated production.", nickname);
                        break;
                    case "Deck":
                        GUI.getDeckgridSceneController().printScene(modelPrinter);
                        GUI.getDeckgridSceneController().notifyChanged("has activated production.", nickname);
                        break;
                    case "Main":
                        GUI.getMainSceneController().printClientPlayer(modelPrinter);
                        GUI.getMainSceneController().notifyChangement("has activated production.", nickname);
                        break;
                }
            });
        }
    }

    private NotifyActivateProductionMessage parseMessage(String message){
        Gson gson = new Gson();
        return gson.fromJson(message,NotifyActivateProductionMessage.class);
    }

    private void updateModel(NotifyActivateProductionMessage data){
        for(PersonalBoardPrinter p: modelPrinter.getPersonalBoards() ) {
            if( p.getOwnerNickname().equals(data.getWhoActivatesProduction())) {
                p.setWareHouseDepot(data.getWarehouseConfiguration() );
                if(data.getExtraDepositConfiguration()[0]!=null)
                    p.setExtraDeposit1(data.getExtraDepositConfiguration()[0]);
                if(data.getExtraDepositConfiguration()[1]!=null)
                    p.setExtraDeposit2(data.getExtraDepositConfiguration()[1]);
                p.setStrongbox(data.getStrongboxConfiguration());
                p.setFaithPoints(data.getNewFaithPoints());
            }
        }
    }
}
