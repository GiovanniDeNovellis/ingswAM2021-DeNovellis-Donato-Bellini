package it.polimi.ingsw.Controller.Messages;

/**
 * Message used to request the end turn
 */
public class EndTurnRequestMessage extends Message{
    private String senderNickname;

    public String getSenderNickname() {
        return senderNickname;
    }

    public void setSenderNickname(String senderNickname) {
        this.senderNickname = senderNickname;
    }
}
