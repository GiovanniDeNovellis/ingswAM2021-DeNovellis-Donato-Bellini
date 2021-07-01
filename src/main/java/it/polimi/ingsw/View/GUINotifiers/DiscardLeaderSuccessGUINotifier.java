package it.polimi.ingsw.View.GUINotifiers;

import com.google.gson.Gson;
import it.polimi.ingsw.Controller.Messages.DiscardSuccessMessage;
import it.polimi.ingsw.View.GUI;
import it.polimi.ingsw.View.ModelPrinter;
import javafx.application.Platform;

/**
 * Notifies the GUI of a discard of a leader card
 */
public class DiscardLeaderSuccessGUINotifier extends GUINotifier{

    private final ModelPrinter modelPrinter;
    public DiscardLeaderSuccessGUINotifier(ModelPrinter modelPrinter) {
        this.modelPrinter = modelPrinter;
    }

    @Override
    public void notifyGui(String notification) {
        Gson gson = new Gson();
        DiscardSuccessMessage mex = gson.fromJson(notification, DiscardSuccessMessage.class);
        if(mex.getPosition()==0){
            modelPrinter.setHasDiscardedFirstLeader(true);
            Platform.runLater(()->{
                GUI.getMainSceneController().printClientPlayer(modelPrinter);
            });
        }
    }
}
