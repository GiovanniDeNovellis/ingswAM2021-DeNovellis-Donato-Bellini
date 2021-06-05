package it.polimi.ingsw.View.GUINotifiers;

import it.polimi.ingsw.View.GUI;
import it.polimi.ingsw.View.ModelPrinter;
import it.polimi.ingsw.View.Printers.PersonalBoardPrinter;
import javafx.application.Platform;

import java.io.IOException;

public class ReconnectOkGUINotifier extends GUINotifier{
    private final ModelPrinter modelPrinter;

    public ReconnectOkGUINotifier(ModelPrinter modelPrinter) {
        this.modelPrinter = modelPrinter;
    }

    @Override
    public void notifyGui(String notification) {
        boolean isMultiPlayerGameStarted=false;
        for(PersonalBoardPrinter p: modelPrinter.getPersonalBoards()){
            if(p.getPlayerNumber()!=0)
                isMultiPlayerGameStarted=true;
        }
        if(!isMultiPlayerGameStarted&&modelPrinter.getPersonalBoards().size()>1){
            Platform.runLater(()->{
                try {
                    GUI.setRoot("lobby_scene");
                    for(PersonalBoardPrinter p: modelPrinter.getPersonalBoards()){
                        GUI.getLobbyController().addPlayer(p.getOwnerNickname());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
        else {
            Platform.runLater(()->{
                try {
                    GUI.setRoot("personalBoard_scene");
                    GUI.getMainSceneController().printClientPlayer(modelPrinter);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}

