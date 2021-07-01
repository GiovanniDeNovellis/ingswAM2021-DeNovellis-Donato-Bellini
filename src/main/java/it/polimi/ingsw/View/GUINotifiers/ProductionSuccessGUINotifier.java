package it.polimi.ingsw.View.GUINotifiers;

import it.polimi.ingsw.View.GUI;
import it.polimi.ingsw.View.ModelPrinter;
import javafx.application.Platform;


/**
 * Notifies the GUI of the success to activate a production
 */
public class ProductionSuccessGUINotifier extends GUINotifier{

    ModelPrinter modelPrinter;

    public ProductionSuccessGUINotifier(ModelPrinter modelPrinter) {
        this.modelPrinter = modelPrinter;
    }

    @Override
    public void notifyGui(String notification) {
        Platform.runLater(()->{
                GUI.getMarketSceneController().printScene(modelPrinter);
        });
    }
}
