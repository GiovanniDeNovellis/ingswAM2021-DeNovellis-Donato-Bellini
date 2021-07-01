package it.polimi.ingsw.Controller.Messages;

/**
 * Message used to communicate the activation of a leader card
 */
public class ActivateLeaderCardMessage extends Message{
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
