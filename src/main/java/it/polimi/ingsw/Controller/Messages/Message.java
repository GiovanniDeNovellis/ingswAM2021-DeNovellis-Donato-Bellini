package it.polimi.ingsw.Controller.Messages;

/**
 * This class represents the various types of messages that contain
 * the data that clients and servers exchange each other
 */
public class Message {
    protected String messageType;

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }
}
