package it.polimi.ingsw.Controller.Messages;

import it.polimi.ingsw.ResourceType;

import java.util.HashMap;
import java.util.Map;

public class EndTurnNotificationMessage extends Message{
    private String actualCurrentPlayer;
    private int numResourcesDiscarded;
    private String winnerPlayerNickname;
    private boolean gameEnding;
    private Map<ResourceType,Integer> temporaryResources;

    public String getActualCurrentPlayer() {
        return actualCurrentPlayer;
    }

    public void setActualCurrentPlayer(String actualCurrentPlayer) {
        this.actualCurrentPlayer = actualCurrentPlayer;
    }

    public int getNumResourcesDiscarded() {
        return numResourcesDiscarded;
    }

    public void setNumResourcesDiscarded(int numResourcesDiscarded) {
        this.numResourcesDiscarded = numResourcesDiscarded;
    }

    public String getWinnerPlayerNickname() {
        return winnerPlayerNickname;
    }

    public void setWinnerPlayerNickname(String winnerPlayerNickname) {
        this.winnerPlayerNickname = winnerPlayerNickname;
    }

    public boolean isGameEnding() {
        return gameEnding;
    }

    public void setGameEnding(boolean gameEnding) {
        this.gameEnding = gameEnding;
    }

    public Map<ResourceType, Integer> getTemporaryResources() {
        return temporaryResources;
    }

    public void setTemporaryResources(Map<ResourceType, Integer> temporaryResources) {
        this.temporaryResources = temporaryResources;
    }
}
