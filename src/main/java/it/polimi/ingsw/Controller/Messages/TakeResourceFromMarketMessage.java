package it.polimi.ingsw.Controller.Messages;

public class TakeResourceFromMarketMessage extends Message {
    private int[] marketIndex;

    public String getSenderNickname() {
        return senderNickname;
    }

    public void setSenderNickname(String senderNickname) {
        this.senderNickname = senderNickname;
    }

    private String senderNickname;

    public int[] getMarketIndex() {
        return marketIndex;
    }
}
