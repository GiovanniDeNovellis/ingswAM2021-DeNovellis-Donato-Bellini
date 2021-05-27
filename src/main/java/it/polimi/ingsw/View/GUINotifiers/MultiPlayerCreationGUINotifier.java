package it.polimi.ingsw.View.GUINotifiers;

import com.google.gson.Gson;
import it.polimi.ingsw.Controller.Messages.MultiplayerCreationMessage;
import it.polimi.ingsw.View.GUI;
import it.polimi.ingsw.View.ModelPrinter;
import it.polimi.ingsw.View.Printers.LeaderCardsPrinter;
import it.polimi.ingsw.View.Printers.PersonalBoardPrinter;
import javafx.application.Platform;

import java.io.IOException;

public class MultiPlayerCreationGUINotifier extends GUINotifier{
    private final ModelPrinter modelPrinter;

    public MultiPlayerCreationGUINotifier(ModelPrinter modelPrinter) {
        this.modelPrinter = modelPrinter;
    }

    @Override
    public void notifyGui(String notification) {
        Gson gson = new Gson();
        MultiplayerCreationMessage mex = gson.fromJson(notification,MultiplayerCreationMessage.class);
        updateModelPrinter(mex);
        if(mex.getNickname().equals(GUI.getClientNickname())) {
            Platform.runLater(() -> {
                try {
                    GUI.getPrimaryStage().setFullScreen(true);
                    GUI.setRoot("first_turn_scene");
                    GUI.getFirstTurnController().printScene(modelPrinter, mex.getNickname());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                GUI.getFirstTurnController().printScene(modelPrinter, GUI.getClientNickname());
            });
        }
    }

    public void updateModelPrinter(MultiplayerCreationMessage mex){
        for(LeaderCardsPrinter l : modelPrinter.getLeaderCardsPrinters()){
            l.setBuilt(true);
        }
        modelPrinter.getDeckGridPrinter().setDeckgrid(mex.getDeckgridConfiguration());
        modelPrinter.getMarketBoardPrinter().setMarbleGrid(mex.getMarbleGridConfiguration());
        modelPrinter.getMarketBoardPrinter().setMarbleOut(mex.getMarbleOut());
        for(LeaderCardsPrinter l : modelPrinter.getLeaderCardsPrinters() ){
            if( l.getOwnerNickname().equals(mex.getNickname())) {
                l.setChoosableLeaderCards(mex.getChoosableLeaderCardsNumbers());
            }
        }
        for(PersonalBoardPrinter p: modelPrinter.getPersonalBoards() ){
            if( p.getOwnerNickname().equals(mex.getNickname()))
                p.setPlayerNumber(mex.getPlayerNumber());
        }
    }
}
