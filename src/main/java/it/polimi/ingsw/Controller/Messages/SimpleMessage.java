package it.polimi.ingsw.Controller.Messages;

public class SimpleMessage extends Message{
    private final String messageType = "SimpleMessage";
    private String messageContent;

    public SimpleMessage() {
        super();
        messageType= "SimpleMessage";
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }
}
