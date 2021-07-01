package it.polimi.ingsw.Controller.Messages;

/**
 * Message used to communicate the creation of a multi player game
 */
public class StartMultiPlayerMessage extends Message{


    @Override
    public String getMessageType() {
        return messageType;
    }
}
