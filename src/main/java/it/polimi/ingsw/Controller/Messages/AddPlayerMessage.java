package it.polimi.ingsw.Controller.Messages;


/**
 * Message used to communicate the addition of a player
 */
public class AddPlayerMessage extends Message {
    public void setSenderNickname(String senderNickname) {
        this.senderNickname = senderNickname;
    }

    private String senderNickname;
    public String getSenderNickname() {
        return senderNickname;
    }
}
