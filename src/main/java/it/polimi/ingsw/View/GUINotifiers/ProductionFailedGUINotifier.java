package it.polimi.ingsw.View.GUINotifiers;

import it.polimi.ingsw.View.GUI;
import javafx.application.Platform;

public class ProductionFailedGUINotifier extends GUINotifier{
    @Override
    public void notifyGui(String notification) {
        Platform.runLater(()->{
            GUI.getMainSceneController().getNotificationLabel().setText("Production failed.");
            GUI.getMainSceneController().getNotificationLabel().setVisible(true);
        });
    }
}
