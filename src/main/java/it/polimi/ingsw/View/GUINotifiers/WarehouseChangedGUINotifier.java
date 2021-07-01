package it.polimi.ingsw.View.GUINotifiers;

import com.google.gson.Gson;
import it.polimi.ingsw.Controller.Messages.NotifyWarehouseChangedMessage;
import it.polimi.ingsw.View.GUI;
import it.polimi.ingsw.View.ModelPrinter;
import it.polimi.ingsw.View.Printers.PersonalBoardPrinter;
import javafx.application.Platform;

/**
 * Notifies the GUI of the changement of the warehouse
 */
public class WarehouseChangedGUINotifier extends GUINotifier {
    private ModelPrinter modelPrinter;

    public WarehouseChangedGUINotifier(ModelPrinter modelPrinter) {
        this.modelPrinter = modelPrinter;
    }

    @Override
    public void notifyGui(String notification) {
        Gson gson = new Gson();
        NotifyWarehouseChangedMessage data = gson.fromJson(notification,NotifyWarehouseChangedMessage.class);
        updateModelPrinter(data);
        if(GUI.getStatus()!=null) {
            Platform.runLater(() -> {
                switch (GUI.getStatus()) {
                    case "Market":
                        GUI.getMarketSceneController().printScene(modelPrinter);
                        GUI.getMarketSceneController().notifyChangement("has changed his warehouse.", data.getNickname());
                        break;
                    case "Deck":
                        GUI.getDeckgridSceneController().printScene(modelPrinter);
                        GUI.getDeckgridSceneController().notifyChanged("has changed his warehouse.", data.getNickname());
                        break;
                    case "Main":
                        GUI.getMainSceneController().printClientPlayer(modelPrinter);
                        GUI.getMainSceneController().notifyChangement("has changed his warehouse.", data.getNickname());
                        break;
                }
            });
        }
    }

    private void updateModelPrinter(NotifyWarehouseChangedMessage data){
        String nickname = data.getNickname();
        for (PersonalBoardPrinter p : modelPrinter.getPersonalBoards()) {
            if (p.getOwnerNickname().equals(nickname)) {
                p.setWareHouseDepot(data.getWarehouseConfiguration());
            }
        }
    }
}
