package it.polimi.ingsw.View.UInotifiers;

/**
 * This class is used by the client depending on the message coming from the server doing the updates.
 * The method reads the messages and calls them. There are two different types of UI based on whether it is CLI or GUI
 */
public interface UI {
    void notify(String notification);
}
