package it.polimi.ingsw.View.GUINotifiers;

import it.polimi.ingsw.View.GUI;
import it.polimi.ingsw.View.ModelPrinter;
import javafx.application.Platform;

public class ActivateLeaderSuccessGUINotifier extends GUINotifier{
    private final ModelPrinter modelPrinter;

    public ActivateLeaderSuccessGUINotifier(ModelPrinter modelPrinter) {
        this.modelPrinter = modelPrinter;
    }

    @Override
    public void notifyGui(String notification) {
        Platform.runLater(()->{

            GUI.getMainSceneController().printClientPlayer(modelPrinter);
        });
    }
}
