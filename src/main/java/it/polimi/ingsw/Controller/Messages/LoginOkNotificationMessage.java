package it.polimi.ingsw.Controller.Messages;

/**
 * Message used to communicate the login done successfully
 */
public class LoginOkNotificationMessage extends Message {
    private String senderNickname;

    public String getSenderNickname() {
        return senderNickname;
    }

    public void setSenderNickname(String senderNickname) {
        this.senderNickname = senderNickname;
    }
}
