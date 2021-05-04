package it.polimi.ingsw.Controller.Messages;

public class VaticanReportMessage extends Message {
    boolean occurred;
    int whichOne;

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
}
