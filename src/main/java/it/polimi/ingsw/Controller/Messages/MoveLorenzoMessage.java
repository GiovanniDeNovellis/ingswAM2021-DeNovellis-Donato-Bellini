package it.polimi.ingsw.Controller.Messages;

public class MoveLorenzoMessage extends Message {
    private int newBlackFaithPoints;

    public int getNewBlackFaithPoints() {
        return newBlackFaithPoints;
    }

    public void setNewBlackFaithPoints(int newBlackFaithPoints) {
        this.newBlackFaithPoints = newBlackFaithPoints;
    }
}
