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
        else {
            if (GUI.getWinnerSceneController() == null) {
                Platform.runLater(() -> {
                    try {
                        GUI.setRoot("personalBoard_scene");
                        GUI.getMainSceneController().printClientPlayer(modelPrinter);
                        GUI.getMainSceneController().getNotificationLabel().setVisible(true);
                        GUI.getMainSceneController().resetLeaderProductions();
                        if (modelPrinter.getLastActionToken() != null)
                            GUI.getMainSceneController().setActionCardLabel(modelPrinter.getLastActionToken());
                        //GUI.getFirstTurnController().printScene(modelPrinter, mex.getNickname());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            }
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
                loadEndGame(data.getWinnerPlayerNickname(),data.getWinnerPoints());
            }
            else if(data.getWinnerPlayerNumber()==5){
                loadEndGame("Lorenzo", data.getWinnerPoints());
            }
            else {
                notifyGameWillEnd();
            }
        }
    }

    private void loadEndGame(String winner, int points){
        Platform.runLater(()->{
            try {
                GUI.setRoot("winner_scene");
                GUI.getWinnerSceneController().setText("GAME OVER.\n Player " + winner + " has won the game with " + points + " points.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void notifyGameWillEnd(){
        Platform.runLater(()->{
            switch (GUI.getStatus()) {
                case "Market":
                    GUI.getMarketSceneController().printScene(modelPrinter);
                    GUI.getMarketSceneController().getNotificationLabel().setText("A player has done an end game action. The game will end after this round.");
                    GUI.getMarketSceneController().getNotificationLabel().setVisible(true);
                    break;
                case "Deck":
                    GUI.getDeckgridSceneController().printScene(modelPrinter);
                    GUI.getDeckgridSceneController().getNotificationLabel().setText("A player has done an end game action. The game will end after this round.");
                    GUI.getDeckgridSceneController().getNotificationLabel().setVisible(true);
                    break;
                case "Main":
                    GUI.getMainSceneController().printClientPlayer(modelPrinter);
                    GUI.getMainSceneController().getNotificationLabel().setText("A player has done an end game action. The game will end after this round.");
                    GUI.getMainSceneController().getNotificationLabel().setVisible(true);
                    break;
            }
        });
    }
}
