package it.polimi.ingsw.View.GUINotifiers;

import it.polimi.ingsw.View.GUI;
import it.polimi.ingsw.View.ModelPrinter;
import javafx.application.Platform;

import java.io.IOException;

public class SwitchSuccessGUINotifier extends GUINotifier {
    private ModelPrinter modelPrinter;

    public SwitchSuccessGUINotifier(ModelPrinter modelPrinter) {
        this.modelPrinter = modelPrinter;
    }

    @Override
    public void notifyGui(String notification) {
        Platform.runLater(()->{
            try {
                GUI.setRoot("market_scene");
                GUI.getMarketSceneController().printScene(modelPrinter);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
