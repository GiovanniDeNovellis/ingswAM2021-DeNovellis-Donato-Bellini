package it.polimi.ingsw.View.GUINotifiers;

import com.google.gson.Gson;
import it.polimi.ingsw.Controller.Messages.NoitifyDeckgridChangedMessage;
import it.polimi.ingsw.View.GUI;
import it.polimi.ingsw.View.ModelPrinter;
import javafx.application.Platform;

public class DeckgridChangedGUINotifier extends GUINotifier{
    private final ModelPrinter modelPrinter;

    public DeckgridChangedGUINotifier(ModelPrinter modelPrinter) {
        this.modelPrinter = modelPrinter;
    }

    @Override
    public void notifyGui(String notification) {
        updateModel(notification);
        Platform.runLater(()->{
            GUI.getMainSceneController().setActionCardLabel("ACTION TOKEN ACTIVATED: Discarded two development cards.");
            GUI.getMainSceneController().printClientPlayer(modelPrinter);
        });
    }

    private void updateModel(String notification){
        Gson gson = new Gson();
        NoitifyDeckgridChangedMessage m = gson.fromJson(notification,NoitifyDeckgridChangedMessage.class);
        modelPrinter.getDeckGridPrinter().setDeckgrid(m.getDeckgridConfiguration());
    }
}
