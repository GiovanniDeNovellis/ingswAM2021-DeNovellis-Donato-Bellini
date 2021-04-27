package it.polimi.ingsw.Controller.Messages;

public class EndTurnRequestMessage extends Message{
    private String senderNickname;

    public String getSenderNickname() {
        return senderNickname;
    }
}
