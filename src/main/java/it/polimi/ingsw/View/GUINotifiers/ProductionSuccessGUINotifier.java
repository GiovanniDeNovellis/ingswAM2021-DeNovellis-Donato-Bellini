package it.polimi.ingsw.View.GUINotifiers;

import it.polimi.ingsw.View.GUI;
import it.polimi.ingsw.View.ModelPrinter;
import javafx.application.Platform;


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
