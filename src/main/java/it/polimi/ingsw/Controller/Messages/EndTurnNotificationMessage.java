package it.polimi.ingsw.Controller.Messages;

import it.polimi.ingsw.ResourceType;

import java.util.HashMap;
import java.util.Map;

/**
 * Message used to communicate the end turn
 */
public class EndTurnNotificationMessage extends Message{
    private String actualCurrentPlayer;
    private int numResourcesDiscarded;
    private String winnerPlayerNickname;
    private int winnerPlayerNumber;
    private int winnerPoints;
    private boolean gameEnding;
    private Map<ResourceType,Integer> temporaryResources;
    private int blackFaithPoints;
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

    public int getBlackFaithPoints() {
        return blackFaithPoints;
    }

    public void setBlackFaithPoints(int blackFaithPoints) {
        this.blackFaithPoints = blackFaithPoints;
    }

    public int getWinnerPlayerNumber() {
        return winnerPlayerNumber;
    }

    public void setWinnerPlayerNumber(int winnerPlayerNumber) {
        this.winnerPlayerNumber = winnerPlayerNumber;
    }

    public int getWinnerPoints() {
        return winnerPoints;
    }

    public void setWinnerPoints(int winnerPoints) {
        this.winnerPoints = winnerPoints;
    }
}
