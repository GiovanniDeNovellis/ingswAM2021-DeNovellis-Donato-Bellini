package it.polimi.ingsw.View.GUINotifiers;

import it.polimi.ingsw.View.GUI;
import javafx.application.Platform;

public class TakeResFailGUINotifier extends GUINotifier{
    @Override
    public void notifyGui(String notification) {
        Platform.runLater(()->{
            GUI.getMarketSceneController().getNotificationLabel().setVisible(true);
            GUI.getMarketSceneController().getNotificationLabel().setText("You have already done a basic action.");
        });
    }
}
