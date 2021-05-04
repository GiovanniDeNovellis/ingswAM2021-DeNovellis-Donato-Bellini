package it.polimi.ingsw.Controller.Messages;

public class EndTurnNotificationMessage extends Message{
    private String actualCurrentPlayer;
    private int numResourcesDiscarded;
    private String winnerPlayerNickname;
    private boolean gameEnding;

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
}
