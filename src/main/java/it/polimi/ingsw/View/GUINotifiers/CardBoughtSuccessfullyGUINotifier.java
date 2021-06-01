package it.polimi.ingsw.View.GUINotifiers;

import it.polimi.ingsw.View.GUI;
import it.polimi.ingsw.View.ModelPrinter;
import javafx.application.Platform;

public class CardBoughtSuccessfullyGUINotifier extends GUINotifier{

    ModelPrinter modelPrinter;

    public CardBoughtSuccessfullyGUINotifier(ModelPrinter modelPrinter) {
        this.modelPrinter = modelPrinter;
    }

    @Override
    public void notifyGui(String notification) {
        Platform.runLater(()->{
            GUI.getDeckgridSceneController().printScene(modelPrinter);
        });
    }
}
