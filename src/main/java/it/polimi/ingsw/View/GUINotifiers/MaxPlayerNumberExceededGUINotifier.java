package it.polimi.ingsw.View.GUINotifiers;

import it.polimi.ingsw.View.GUI;
import javafx.application.Platform;

public class MaxPlayerNumberExceededGUINotifier extends GUINotifier{
    @Override
    public void notifyGui(String notification) {
        Platform.runLater(()->{
            GUI.getLoginController().setPlayerNumberExceeded();
        });
    }
}
