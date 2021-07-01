package it.polimi.ingsw.View.GUINotifiers;

import com.google.gson.Gson;
import it.polimi.ingsw.Controller.Messages.MoveLorenzoMessage;
import it.polimi.ingsw.View.GUI;
import it.polimi.ingsw.View.ModelPrinter;
import javafx.application.Platform;

/**
 * Notifies the GUI of the activation of a move action card
 */
public class LorenzoMovedGUINotifier extends GUINotifier{
    private final ModelPrinter modelPrinter;

    public LorenzoMovedGUINotifier(ModelPrinter modelPrinter) {
        this.modelPrinter = modelPrinter;
    }

    @Override
    public void notifyGui(String notification) {
        updateModel(notification);
        Platform.runLater(()->{
            modelPrinter.setLastActionToken("ACTION TOKEN ACTIVATED: Lorenzo moved for two spaces.");
            GUI.getMainSceneController().printClientPlayer(modelPrinter);
        });
    }

    private void updateModel(String notification){
        Gson gson = new Gson();
        MoveLorenzoMessage m = gson.fromJson(notification,MoveLorenzoMessage.class);
        modelPrinter.setBlackFaithPoints(m.getNewBlackFaithPoints());
    }
}
