package it.polimi.ingsw.View.GUINotifiers;

import it.polimi.ingsw.View.GUI;
import it.polimi.ingsw.View.ModelPrinter;
import javafx.application.Platform;
import javafx.scene.text.Font;

/**
 * Notifies the GUI of the failure of a card purchase
 */
public class BuyCardFailGUINotifier extends GUINotifier{

    public BuyCardFailGUINotifier(ModelPrinter modelPrinter) {
        this.modelPrinter = modelPrinter;
    }

    ModelPrinter modelPrinter;

    @Override
    public void notifyGui(String notification) {
        Platform.runLater(()->{
            GUI.getDeckgridSceneController().printScene(modelPrinter);
            GUI.getDeckgridSceneController().getNotificationLabel().setVisible(true);
            GUI.getDeckgridSceneController().getNotificationLabel().setFont(Font.font(10.0));
            GUI.getDeckgridSceneController().getNotificationLabel().setText("You don't have enough resources to buy this card, or you have selected an incompatible slot.");
        });
    }
}
