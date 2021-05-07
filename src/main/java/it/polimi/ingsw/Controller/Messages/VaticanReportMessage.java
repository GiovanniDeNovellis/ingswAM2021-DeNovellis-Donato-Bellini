package it.polimi.ingsw.Controller.Messages;

public class VaticanReportMessage extends Message {
    boolean occurred;
    int whichOne;
    int newFaithPoints;

    public boolean isOccurred() {
        return occurred;
    }

    public void setOccurred(boolean occurred) {
        this.occurred = occurred;
    }

    public int getWhichOne() {
        return whichOne;
    }

    public void setWhichOne(int whichOne) {
        this.whichOne = whichOne;
    }

    public int getNewFaithPoints() {
        return newFaithPoints;
    }

    public void setNewFaithPoints(int newFaithPoints) {
        this.newFaithPoints = newFaithPoints;
    }
}