package it.polimi.ingsw.View.GUINotifiers;

import it.polimi.ingsw.View.GUI;
import javafx.application.Platform;

/**
 * Notifies the GUI of the failure to switch levels
 */
public class SwitchLevelsFailGUINotifier extends GUINotifier{
    @Override
    public void notifyGui(String notification) {
        Platform.runLater(()->{
            GUI.getMarketSceneController().getNotificationLabel().setVisible(true);
            GUI.getMarketSceneController().getNotificationLabel().setText("You can't switch those levels");
        });
    }
}
