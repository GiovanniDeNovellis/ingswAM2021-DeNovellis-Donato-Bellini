package it.polimi.ingsw.Controller.Messages;

public class ActivateLeaderAbilityMessage extends Message{
    private String senderNickname;
    private int position;

    public String getSenderNickname() {
        return senderNickname;
    }

    public int getPosition() {
        return position;
    }

    public void setSenderNickname(String senderNickname) {
        this.senderNickname = senderNickname;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
