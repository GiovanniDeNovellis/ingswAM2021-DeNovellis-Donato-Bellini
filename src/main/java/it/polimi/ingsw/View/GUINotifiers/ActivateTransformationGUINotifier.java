package it.polimi.ingsw.View.GUINotifiers;

import com.google.gson.Gson;
import it.polimi.ingsw.Controller.Messages.ActivateLeaderAbilityTransformationMessage;
import it.polimi.ingsw.View.GUI;
import it.polimi.ingsw.View.ModelPrinter;
import javafx.application.Platform;

public class ActivateTransformationGUINotifier extends GUINotifier{
    private ModelPrinter modelPrinter;

    public ActivateTransformationGUINotifier(ModelPrinter modelPrinter) {
        this.modelPrinter = modelPrinter;
    }

    @Override
    public void notifyGui(String notification) {
        Gson gson = new Gson();
        ActivateLeaderAbilityTransformationMessage data = gson.fromJson(notification,ActivateLeaderAbilityTransformationMessage.class);
        updateModelPrinter(data);
        Platform.runLater(()->{
            switch (GUI.getStatus()) {
                case "Market":
                    GUI.getMarketSceneController().printScene(modelPrinter);
                    GUI.getMarketSceneController().notifyChangement("has transformed white marbles. Remaining: " + data.getRemainingWhiteMarbles(), data.getSenderNickname());
                    break;
                case "Deck":
                    GUI.getDeckgridSceneController().printScene(modelPrinter);
                    GUI.getDeckgridSceneController().notifyChanged("has transformed white marbles. Remaining: " + data.getRemainingWhiteMarbles(), data.getSenderNickname());
                    break;
                case "Main":
                    GUI.getMainSceneController().printClientPlayer(modelPrinter);
                    GUI.getMainSceneController().notifyChangement("has transformed white marbles. Remaining: " + data.getRemainingWhiteMarbles(), data.getSenderNickname());
                    break;
            }
        });
    }

    private void updateModelPrinter(ActivateLeaderAbilityTransformationMessage data){
        modelPrinter.getMarketBoardPrinter().setTemporaryResources(data.getTemporaryResourcesConfiguration());
    }
}

