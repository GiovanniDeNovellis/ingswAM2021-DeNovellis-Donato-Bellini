package it.polimi.ingsw.View.GUINotifiers;

import com.google.gson.Gson;
import it.polimi.ingsw.Controller.Messages.TemporaryResourcesChangedMessage;
import it.polimi.ingsw.View.GUI;
import it.polimi.ingsw.View.ModelPrinter;
import javafx.application.Platform;

import java.io.IOException;

/**
 * Notifies the GUI of the changement of the temporary resources
 */
public class TempResChangeGUINotifier extends GUINotifier{
    private ModelPrinter modelPrinter;

    public TempResChangeGUINotifier(ModelPrinter modelPrinter) {
        this.modelPrinter = modelPrinter;
    }

    @Override
    public void notifyGui(String notification) {
        Gson gson = new Gson();
        TemporaryResourcesChangedMessage data = gson.fromJson(notification,TemporaryResourcesChangedMessage.class);
        updateModel(data);
        Platform.runLater(()->{
            try {
                GUI.setRoot("market_scene");
                GUI.getMarketSceneController().printScene(modelPrinter);
                GUI.getMarketSceneController().getNotificationLabel().setVisible(true);
                GUI.getMarketSceneController().getNotificationLabel().setText("Current player has taken resources from market.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void updateModel(TemporaryResourcesChangedMessage data){
        modelPrinter.getMarketBoardPrinter().setTemporaryResources(data.getTemporaryResourcesConfiguration());
    }
}
