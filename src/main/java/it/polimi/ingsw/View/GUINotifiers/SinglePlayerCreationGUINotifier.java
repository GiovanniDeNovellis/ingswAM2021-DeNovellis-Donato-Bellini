package it.polimi.ingsw.View.GUINotifiers;

import com.google.gson.Gson;
import it.polimi.ingsw.Controller.Messages.SinglePlayerCreationMessage;
import it.polimi.ingsw.View.GUI;
import it.polimi.ingsw.View.ModelPrinter;
import it.polimi.ingsw.View.Printers.LeaderCardsPrinter;
import javafx.application.Platform;

import java.io.IOException;

public class SinglePlayerCreationGUINotifier extends GUINotifier {
    private ModelPrinter modelPrinter;

    public SinglePlayerCreationGUINotifier(ModelPrinter modelPrinter) {
        this.modelPrinter = modelPrinter;
    }

    @Override
    public void notifyGui(String notification) {
        Gson gson = new Gson();
        SinglePlayerCreationMessage data = gson.fromJson(notification,SinglePlayerCreationMessage.class);
        updateModelPrinter(data);
        Platform.runLater(()->{
            try {
                GUI.setRoot("first_turn_scene");
                GUI.getFirstTurnController().printScene(modelPrinter,GUI.getClientNickname());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void updateModelPrinter(SinglePlayerCreationMessage data){
        for(LeaderCardsPrinter l: modelPrinter.getLeaderCardsPrinters()){
            l.setBuilt(true);
            System.out.println(l.getOwnerNickname());
        }
        modelPrinter.getMarketBoardPrinter().setMarbleGrid(data.getMarbleGridConfiguration());
        modelPrinter.getMarketBoardPrinter().setMarbleOut(data.getMarbleOut());
        modelPrinter.setBlackFaithPoints(0);
        modelPrinter.getDeckGridPrinter().setDeckgrid(data.getDeckgridConfiguration());
        modelPrinter.getLeaderCardsPrinters().get(0).setChoosableLeaderCards(data.getChoosableLeaderCardsNumbers());
    }
}
