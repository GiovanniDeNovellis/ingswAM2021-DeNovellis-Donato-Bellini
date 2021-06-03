package it.polimi.ingsw.View.GUINotifiers;

import com.google.gson.Gson;
import it.polimi.ingsw.Controller.Messages.NotifyDiscardLeaderCard;
import it.polimi.ingsw.View.GUI;
import it.polimi.ingsw.View.ModelPrinter;
import it.polimi.ingsw.View.Printers.LeaderCardsPrinter;
import javafx.application.Platform;

public class DiscardLeaderGUINotifier extends GUINotifier{
    private final ModelPrinter modelPrinter;

    public DiscardLeaderGUINotifier(ModelPrinter modelPrinter) {
        this.modelPrinter = modelPrinter;
    }

    @Override
    public void notifyGui(String notification) {
        Gson gson = new Gson();
        NotifyDiscardLeaderCard data = gson.fromJson(notification,NotifyDiscardLeaderCard.class);
        updateModelPrinter(data);
        Platform.runLater(()->{
            switch(GUI.getStatus()){
                case "Market":
                    GUI.getMarketSceneController().printScene(modelPrinter);
                    GUI.getMarketSceneController().notifyChangement("has discarded a leader card.", data.getWhoDiscardedLeaderCard());
                    break;
                case "Deck":
                    GUI.getDeckgridSceneController().printScene(modelPrinter);
                    GUI.getDeckgridSceneController().notifyChangement("has discarded a leader card.", data.getWhoDiscardedLeaderCard());
                    break;
                case "Main":
                    GUI.getMainSceneController().printClientPlayer(modelPrinter);
                    GUI.getMainSceneController().notifyChangement("has discarded a leader card.", data.getWhoDiscardedLeaderCard());
                    break;
            }
        });
    }

    private void updateModelPrinter(NotifyDiscardLeaderCard data){
        for(LeaderCardsPrinter l : modelPrinter.getLeaderCardsPrinters()){
            if(l.getOwnerNickname().equals(data.getWhoDiscardedLeaderCard())){
                if(data.getDiscardedPosition()==0&&l.getChosenLeaderCards()[0]==0){
                    l.getChosenLeaderCards()[1]=0;
                    l.getActivatedLeaderCards()[1]=false;
                }
                else {
                    l.getChosenLeaderCards()[data.getDiscardedPosition()] = 0;
                    l.getActivatedLeaderCards()[data.getDiscardedPosition()] = false;
                }
            }
        }
    }
}
