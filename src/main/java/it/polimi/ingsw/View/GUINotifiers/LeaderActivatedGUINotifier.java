package it.polimi.ingsw.View.GUINotifiers;

import com.google.gson.Gson;
import it.polimi.ingsw.Controller.Messages.NotifyActivateLeaderCard;
import it.polimi.ingsw.View.GUI;
import it.polimi.ingsw.View.ModelPrinter;
import it.polimi.ingsw.View.Printers.LeaderCardsPrinter;
import javafx.application.Platform;

public class LeaderActivatedGUINotifier extends GUINotifier{
    private final ModelPrinter modelPrinter;

    public LeaderActivatedGUINotifier(ModelPrinter modelPrinter) {
        this.modelPrinter = modelPrinter;
    }

    @Override
    public void notifyGui(String notification) {
        Gson gson = new Gson();
        NotifyActivateLeaderCard data = gson.fromJson(notification, NotifyActivateLeaderCard.class);
        updateModelPrinter(data);
        Platform.runLater(()->{
            switch(GUI.getStatus()){
                case "Market":
                    GUI.getMarketSceneController().printScene(modelPrinter);
                    GUI.getMarketSceneController().notifyChangement("has activated a leader card.", data.getWhoActivatedLeaderCard());
                    break;
                case "Deck":
                    GUI.getDeckgridSceneController().printScene(modelPrinter);
                    GUI.getDeckgridSceneController().notifyChangement("has activated a leader card.", data.getWhoActivatedLeaderCard());
                    break;
                case "Main":
                    GUI.getMainSceneController().printClientPlayer(modelPrinter);
                    GUI.getMainSceneController().notifyChangement("has activated a leader card.", data.getWhoActivatedLeaderCard());
                    break;
            }
        });
    }

    private void updateModelPrinter(NotifyActivateLeaderCard data){
        for(LeaderCardsPrinter l : modelPrinter.getLeaderCardsPrinters()){
            if(l.getOwnerNickname().equals(data.getWhoActivatedLeaderCard()))
                if(data.getActivatedLeaderCardPosition()==0 && l.getChosenLeaderCards()[0]==0)
                    l.activateLeaderCard(1);
                else
                    l.activateLeaderCard(data.getActivatedLeaderCardPosition());
        }
    }
}
