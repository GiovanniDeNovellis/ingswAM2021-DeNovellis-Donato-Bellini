package it.polimi.ingsw.View.MessageBuilders;

/**
 * represents the classes used by cli to write messages,
 * reads the various commands and based on the input creates
 * the different message classes to send to the server
 */
public abstract class MessageBuilder {
    public abstract String buildMessage();
}
