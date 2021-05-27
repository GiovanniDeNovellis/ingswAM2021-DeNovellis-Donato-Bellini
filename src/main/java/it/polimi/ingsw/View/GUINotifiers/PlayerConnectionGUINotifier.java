package it.polimi.ingsw.View.GUINotifiers;

import com.google.gson.Gson;
import it.polimi.ingsw.Controller.Messages.AddPlayerMessage;
import it.polimi.ingsw.Controller.Messages.ConnectedPlayersMessage;
import it.polimi.ingsw.View.GUI;
import it.polimi.ingsw.View.ModelPrinter;
import it.polimi.ingsw.View.Printers.LeaderCardsPrinter;
import it.polimi.ingsw.View.Printers.PersonalBoardPrinter;
import javafx.application.Platform;

public class PlayerConnectionGUINotifier extends GUINotifier{
    private ModelPrinter modelPrinter;

    public PlayerConnectionGUINotifier(ModelPrinter modelPrinter) {
        this.modelPrinter = modelPrinter;
    }

    @Override
    public void notifyGui(String notification) {
        AddPlayerMessage message = parseNotification(notification);
        updateModelPrinter(message);
        Platform.runLater(()->{
            GUI.getLobbyController().addPlayer(message.getSenderNickname());
        });
    }

    private AddPlayerMessage parseNotification(String notification){
        Gson gson = new Gson();
        return gson.fromJson(notification, AddPlayerMessage.class);
    }
    private void updateModelPrinter(AddPlayerMessage data){
        String nickname;
        nickname = data.getSenderNickname();
        PersonalBoardPrinter p = new PersonalBoardPrinter();
        p.setOwnerNickname(nickname);
        LeaderCardsPrinter l = new LeaderCardsPrinter();
        l.setOwnerNickname(nickname);
        modelPrinter.getPersonalBoards().add(p);
        modelPrinter.getLeaderCardsPrinters().add(l);
    }
}
