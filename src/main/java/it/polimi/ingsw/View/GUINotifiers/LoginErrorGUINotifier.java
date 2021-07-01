package it.polimi.ingsw.View.GUINotifiers;

import it.polimi.ingsw.View.GUI;
import javafx.application.Platform;

/**
 * Notifies the GUI of the fail to login
 */
public class LoginErrorGUINotifier extends GUINotifier{

    @Override
    public void notifyGui(String notification) {
        if( notification==null ) {
            Platform.runLater(() -> {
                GUI.getLoginController().setLoginError();
            });
        } else if( notification.equals("game started")){
            Platform.runLater(() -> {
                GUI.getLoginController().setGameStartedError();
            });
        }
    }
}
