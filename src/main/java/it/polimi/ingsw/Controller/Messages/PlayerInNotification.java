package it.polimi.ingsw.Controller.Messages;

/**
 * Message used to communicate the connection of a player
 */
public class PlayerInNotification extends Message{
    private String senderNickname;

    public String getSenderNickname() {
        return senderNickname;
    }

    public void setSenderNickname(String senderNickname) {
        this.senderNickname = senderNickname;
    }
}
