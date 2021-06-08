package it.polimi.ingsw.View.GUINotifiers;

import com.google.gson.Gson;
import it.polimi.ingsw.Controller.Messages.EndTurnNotificationMessage;
import it.polimi.ingsw.View.GUI;
import it.polimi.ingsw.View.ModelPrinter;
import it.polimi.ingsw.View.Printers.LeaderCardsPrinter;
import it.polimi.ingsw.View.Printers.PersonalBoardPrinter;
import javafx.application.Platform;

import java.io.IOException;

public class EndTurnGuiNotifier extends GUINotifier{
    private final ModelPrinter modelPrinter;
    int oldValue=0;

    public EndTurnGuiNotifier(ModelPrinter modelPrinter) {
        this.modelPrinter = modelPrinter;
    }

    @Override
    public void notifyGui(String notification) {
        Gson gson = new Gson();
        EndTurnNotificationMessage data = gson.fromJson(notification,EndTurnNotificationMessage.class);
        updateModelPrinter(data);
        boolean firstRound=false;
        String nextPlayerNickname = data.getActualCurrentPlayer();
        for(LeaderCardsPrinter l: modelPrinter.getLeaderCardsPrinters()){
            if(l.getOwnerNickname().equals(nextPlayerNickname)){
                if(l.getChosenLeaderCards()[0]==0) {
                    firstRound = true;
                }
            }
        }
        if(firstRound){
            if(nextPlayerNickname.equals(GUI.getClientNickname())){
                Platform.runLater(()->{
                    GUI.getFirstTurnController().notifyYourTurn();
                });
            }
        }
        else{
            Platform.runLater(()->{
                try {
                    GUI.setRoot("personalBoard_scene");
                    GUI.getMainSceneController().printClientPlayer(modelPrinter);
                    GUI.getMainSceneController().getNotificationLabel().setVisible(true);
                    if(modelPrinter.getLastActionToken()!=null)
                        GUI.getMainSceneController().setActionCardLabel(modelPrinter.getLastActionToken());
                    //GUI.getFirstTurnController().printScene(modelPrinter, mex.getNickname());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    private void updateModelPrinter(EndTurnNotificationMessage data) {
        if (modelPrinter.getBlackFaithPoints() != -1)
            modelPrinter.setBlackFaithPoints(data.getBlackFaithPoints());

        modelPrinter.setCurrentPlayerNickname(data.getActualCurrentPlayer());
        for (PersonalBoardPrinter p : modelPrinter.getPersonalBoards()) {
            oldValue = p.getFaithPoints();
            p.setFaithPoints(data.getNumResourcesDiscarded() + oldValue);
        }
        modelPrinter.getMarketBoardPrinter().setTemporaryResources(data.getTemporaryResources());
        if (data.isGameEnding()) {
            if ((data.getWinnerPlayerNickname() != null)) {
                //winner = data.getWinnerPlayerNickname();
                //TODO LOAD END GAME SCENE HA VINTO UN GIOCATORE VERO
            }
            else if(data.getWinnerPlayerNumber()==5){
                //TODO HA VINTO LORENZO
            }
            else {
                //TODO NOTIFY THE GAME WILL END
            }
        }
    }
}
