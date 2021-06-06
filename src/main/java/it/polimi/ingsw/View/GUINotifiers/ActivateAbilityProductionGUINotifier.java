package it.polimi.ingsw.View.GUINotifiers;

import com.google.gson.Gson;
import it.polimi.ingsw.Controller.Messages.ActivateLeaderAbilityProduction;
import it.polimi.ingsw.View.GUI;
import it.polimi.ingsw.View.ModelPrinter;
import javafx.application.Platform;

public class ActivateAbilityProductionGUINotifier extends GUINotifier{
    private ModelPrinter modelPrinter;

    public ActivateAbilityProductionGUINotifier(ModelPrinter modelPrinter) {
        this.modelPrinter = modelPrinter;
    }

    @Override
    public void notifyGui(String notification) {
        Gson gson = new Gson();
        ActivateLeaderAbilityProduction data = gson.fromJson(notification,ActivateLeaderAbilityProduction.class);
        Platform.runLater(()->{
            switch (GUI.getStatus()) {
                case "Market":
                    GUI.getMarketSceneController().printScene(modelPrinter);
                    GUI.getMarketSceneController().notifyChangement("has activated a production leader ability.", data.getSenderNickname());
                    break;
                case "Deck":
                    GUI.getDeckgridSceneController().printScene(modelPrinter);
                    GUI.getDeckgridSceneController().notifyChanged("has activated a production leader ability.", data.getSenderNickname());
                    break;
                case "Main":
                    GUI.getMainSceneController().printClientPlayer(modelPrinter);
                    GUI.getMainSceneController().notifyChangement("has activated a production leader ability.", data.getSenderNickname());
                    break;
            }
        });
    }
}
