package it.polimi.ingsw.Controller.Messages;


public class AddPlayerMessage extends Message {
    public void setSenderNickname(String senderNickname) {
        this.senderNickname = senderNickname;
    }

    private String senderNickname;
    public String getSenderNickname() {
        return senderNickname;
    }
}
