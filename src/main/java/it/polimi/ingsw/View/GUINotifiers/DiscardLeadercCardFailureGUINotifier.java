package it.polimi.ingsw.View.GUINotifiers;

import it.polimi.ingsw.View.GUI;
import it.polimi.ingsw.View.ModelPrinter;
import javafx.application.Platform;

public class DiscardLeadercCardFailureGUINotifier extends GUINotifier{

    private final ModelPrinter modelPrinter;
    public DiscardLeadercCardFailureGUINotifier(ModelPrinter modelPrinter) {
        this.modelPrinter = modelPrinter;
    }

    @Override
    public void notifyGui(String notification) {
        Platform.runLater(()->{
            GUI.getMainSceneController().printClientPlayer(modelPrinter);
            GUI.getMainSceneController().getNotificationLabel().setText("You can't discard this leader card.");
            GUI.getMainSceneController().getNotificationLabel().setVisible(true);
        });
    }
}
