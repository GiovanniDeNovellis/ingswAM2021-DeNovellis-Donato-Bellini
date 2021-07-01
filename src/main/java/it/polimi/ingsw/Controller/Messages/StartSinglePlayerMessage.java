package it.polimi.ingsw.Controller.Messages;

/**
 * Message used to request a single player game creation
 */
public class StartSinglePlayerMessage extends Message{


    @Override
    public String getMessageType() {
        return messageType;
    }
}