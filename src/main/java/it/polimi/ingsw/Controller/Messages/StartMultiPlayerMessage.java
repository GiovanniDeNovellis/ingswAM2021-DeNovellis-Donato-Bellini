package it.polimi.ingsw.Controller.Messages;

public class StartMultiPlayerMessage extends Message{

    private final String messageType = "StartMultiPlayer";

    @Override
    public String getMessageType() {
        return messageType;
    }
}
