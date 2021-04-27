package it.polimi.ingsw.Controller.Messages;

public class TakeResourceFromMarketMessage extends Message {
    private int[] marketIndex;

    public int[] getMarketIndex() {
        return marketIndex;
    }
}
