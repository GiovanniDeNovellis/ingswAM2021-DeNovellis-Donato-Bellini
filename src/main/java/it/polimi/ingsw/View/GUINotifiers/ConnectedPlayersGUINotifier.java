package it.polimi.ingsw.View.GUINotifiers;

import com.google.gson.Gson;
import it.polimi.ingsw.Controller.Messages.ConnectedPlayersMessage;
import it.polimi.ingsw.View.GUI;
import it.polimi.ingsw.View.ModelPrinter;
import it.polimi.ingsw.View.Printers.LeaderCardsPrinter;
import it.polimi.ingsw.View.Printers.PersonalBoardPrinter;
import javafx.application.Platform;

/**
 * Notifies the GUI of the connected players
 */
public class ConnectedPlayersGUINotifier extends GUINotifier{
    private final ModelPrinter modelPrinter;

    public ConnectedPlayersGUINotifier(ModelPrinter modelPrinter) {
        this.modelPrinter = modelPrinter;
    }

    @Override
    public void notifyGui(String notification) {
        ConnectedPlayersMessage message = parseNotification(notification);
        updateModelPrinter(message);
        Platform.runLater( ()->{
            for (String nickname: message.getConnectedPlayers()){
                GUI.getLobbyController().addPlayer(nickname);
            }
        });
    }

    private ConnectedPlayersMessage parseNotification(String notification){
        Gson gson = new Gson();
        return gson.fromJson(notification, ConnectedPlayersMessage.class);
    }

    private void updateModelPrinter(ConnectedPlayersMessage message){
        for(String s: message.getConnectedPlayers()){
            PersonalBoardPrinter p = new PersonalBoardPrinter();
            p.setOwnerNickname(s);
            LeaderCardsPrinter l = new LeaderCardsPrinter();
            l.setOwnerNickname(s);
            modelPrinter.getPersonalBoards().add(p);
            modelPrinter.getLeaderCardsPrinters().add(l);
        }
    }
}
