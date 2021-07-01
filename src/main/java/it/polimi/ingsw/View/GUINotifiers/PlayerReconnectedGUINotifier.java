package it.polimi.ingsw.View.GUINotifiers;

import com.google.gson.Gson;
import it.polimi.ingsw.Controller.Messages.PlayerInNotification;
import it.polimi.ingsw.Controller.Messages.PlayerOutNotification;
import it.polimi.ingsw.View.GUI;
import it.polimi.ingsw.View.ModelPrinter;
import javafx.application.Platform;

/**
 * Notifies the GUI of the reconnection of a player
 */
public class PlayerReconnectedGUINotifier extends GUINotifier {
    private final ModelPrinter modelPrinter;
    public PlayerReconnectedGUINotifier(ModelPrinter modelPrinter) {
        this.modelPrinter = modelPrinter;
    }

    @Override
    public void notifyGui(String notification) {
        Gson gson = new Gson();
        PlayerInNotification p = gson.fromJson(notification,PlayerInNotification.class);
        Platform.runLater(()->{
            switch ((GUI.getStatus())){
                case "Market":
                    GUI.getMarketSceneController().printScene(modelPrinter);
                    GUI.getMarketSceneController().getNotificationLabel().setVisible(true);
                    GUI.getMarketSceneController().getNotificationLabel().setText(p.getSenderNickname() + " is back to the game!");
                    break;
                case "Deck":
                    GUI.getDeckgridSceneController().printScene(modelPrinter);
                    GUI.getDeckgridSceneController().getNotificationLabel().setVisible(true);
                    GUI.getDeckgridSceneController().getNotificationLabel().setText(p.getSenderNickname() + " is back to the game!");
                    break;
                case "Main":
                    GUI.getMainSceneController().printClientPlayer(modelPrinter);
                    GUI.getMainSceneController().getNotificationLabel().setVisible(true);
                    GUI.getMainSceneController().getNotificationLabel().setText(p.getSenderNickname() + " is back to the game!");
                    break;
            }
        });
    }
}
