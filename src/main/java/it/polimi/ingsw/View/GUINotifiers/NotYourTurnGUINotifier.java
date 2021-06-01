package it.polimi.ingsw.View.GUINotifiers;

import it.polimi.ingsw.View.GUI;
import it.polimi.ingsw.View.ModelPrinter;
import javafx.application.Platform;

public class NotYourTurnGUINotifier extends GUINotifier{
    private ModelPrinter modelPrinter;

    public NotYourTurnGUINotifier(ModelPrinter modelPrinter) {
        this.modelPrinter = modelPrinter;
    }

    @Override
    public void notifyGui(String notification) {
        Platform.runLater(()->{
            switch ((GUI.getStatus())){
                case "Market":
                    GUI.getMarketSceneController().printScene(modelPrinter);
                    GUI.getMarketSceneController().getNotificationLabel().setVisible(true);
                    GUI.getMarketSceneController().getNotificationLabel().setText("It's not your turn!");
                    break;
                case "Deck":
                    GUI.getDeckgridSceneController().printScene(modelPrinter);
                    GUI.getDeckgridSceneController().getNotificationLabel().setVisible(true);
                    GUI.getDeckgridSceneController().getNotificationLabel().setText("It's not your turn!");
                    break;
                case "Main":
                    GUI.getMainSceneController().printClientPlayer(modelPrinter);
                    GUI.getMainSceneController().getNotificationLabel().setVisible(true);
                    GUI.getMainSceneController().getNotificationLabel().setText("It's not your turn!");
                    break;
            }
        });
    }
}
