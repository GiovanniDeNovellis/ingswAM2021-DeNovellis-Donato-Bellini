package it.polimi.ingsw.Controller.Messages;

public class DiscardLeaderMessage extends Message{
    private String SenderNickname;
    private int position;

    public String getSenderNickname() {
        return SenderNickname;
    }

    public void setSenderNickname(String senderNickname) {
        SenderNickname = senderNickname;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
