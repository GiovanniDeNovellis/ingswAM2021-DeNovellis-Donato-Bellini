package it.polimi.ingsw.Controller.Messages;

public class EndTurnNotificationMessage extends Message{
    private String actualCurrentPlayer;

    public String getActualCurrentPlayer() {
        return actualCurrentPlayer;
    }

    public void setActualCurrentPlayer(String actualCurrentPlayer) {
        this.actualCurrentPlayer = actualCurrentPlayer;
    }
}
