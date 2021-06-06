package it.polimi.ingsw.View.GUINotifiers;

import com.google.gson.Gson;
import it.polimi.ingsw.Controller.Messages.MoveAndShuffleMessage;
import it.polimi.ingsw.View.GUI;
import it.polimi.ingsw.View.ModelPrinter;
import javafx.application.Platform;

public class MovedAndShuffledGUINotifier extends GUINotifier{
    private final ModelPrinter modelPrinter;

    public MovedAndShuffledGUINotifier(ModelPrinter modelPrinter) {
        this.modelPrinter = modelPrinter;
    }

    @Override
    public void notifyGui(String notification) {
        updateModel(notification);
        Platform.runLater(()->{
            GUI.getMainSceneController().setActionCardLabel("ACTION TOKEN ACTIVATED: Lorenzo moved and shuffled action tokens deck.");
            GUI.getMainSceneController().printClientPlayer(modelPrinter);
        });
    }

    private void updateModel(String notification){
        Gson gson = new Gson();
        MoveAndShuffleMessage m = gson.fromJson(notification,MoveAndShuffleMessage.class);
        modelPrinter.setBlackFaithPoints(m.getNewBlackFaithPoints());
    }
}
