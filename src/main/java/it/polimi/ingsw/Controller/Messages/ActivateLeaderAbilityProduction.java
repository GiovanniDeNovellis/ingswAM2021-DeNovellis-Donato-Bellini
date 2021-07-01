package it.polimi.ingsw.Controller.Messages;

/**
 * Message used to communicate the activation of a leader ability production
 */
public class ActivateLeaderAbilityProduction extends Message{
    private int position;

    public String getSenderNickname() {
        return senderNickname;
    }

    public void setSenderNickname(String senderNickname) {
        this.senderNickname = senderNickname;
    }

    private String senderNickname;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
