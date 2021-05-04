package it.polimi.ingsw.Controller.Messages;

public class SimpleMessage extends Message{
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
