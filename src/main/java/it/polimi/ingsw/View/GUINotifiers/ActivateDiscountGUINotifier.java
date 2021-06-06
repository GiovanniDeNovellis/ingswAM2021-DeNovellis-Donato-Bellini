package it.polimi.ingsw.View.GUINotifiers;

import com.google.gson.Gson;
import it.polimi.ingsw.Controller.Messages.ActivateLeaderAbilityDiscountMessage;
import it.polimi.ingsw.View.GUI;
import it.polimi.ingsw.View.ModelPrinter;
import javafx.application.Platform;

public class ActivateDiscountGUINotifier extends GUINotifier{
    private ModelPrinter modelPrinter;

    public ActivateDiscountGUINotifier(ModelPrinter modelPrinter) {
        this.modelPrinter = modelPrinter;
    }

    @Override
    public void notifyGui(String notification) {
        Gson gson = new Gson();
        ActivateLeaderAbilityDiscountMessage data = gson.fromJson(notification,ActivateLeaderAbilityDiscountMessage.class);
        Platform.runLater(()->{
            switch (GUI.getStatus()) {
                case "Market":
                    GUI.getMarketSceneController().printScene(modelPrinter);
                    GUI.getMarketSceneController().notifyChangement("has activated a discount ability.", data.getSenderNickname());
                    break;
                case "Deck":
                    GUI.getDeckgridSceneController().printScene(modelPrinter);
                    GUI.getDeckgridSceneController().notifyChanged("has activated a discount ability.", data.getSenderNickname());
                    break;
                case "Main":
                    GUI.getMainSceneController().printClientPlayer(modelPrinter);
                    GUI.getMainSceneController().notifyChangement("has activated a discount ability.", data.getSenderNickname());
                    break;
            }
        });
    }
}
