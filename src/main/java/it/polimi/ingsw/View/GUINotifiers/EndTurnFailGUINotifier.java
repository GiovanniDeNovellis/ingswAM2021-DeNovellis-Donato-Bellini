package it.polimi.ingsw.View.GUINotifiers;

import it.polimi.ingsw.View.GUI;
import javafx.application.Platform;
import javafx.scene.text.Font;

/**
 * Notifies the GUI of the failure to end turn
 */
public class EndTurnFailGUINotifier extends GUINotifier{
    @Override
    public void notifyGui(String notification) {
        Platform.runLater(()->{
            GUI.getMainSceneController().getNotificationLabel().setVisible(true);
            GUI.getMainSceneController().getNotificationLabel().setText("Do a basic action before ending your turn.");
        });
    }
}
