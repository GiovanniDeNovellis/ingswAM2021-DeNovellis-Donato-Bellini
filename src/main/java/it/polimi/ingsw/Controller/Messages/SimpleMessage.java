package it.polimi.ingsw.Controller.Messages;

public class SimpleMessage {
    private final String messageType = "SimpleMessage";
    private String messageContent;

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }
}
