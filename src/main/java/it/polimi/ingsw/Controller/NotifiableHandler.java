package it.polimi.ingsw.Controller;

/**
 * This interface represents a client handler that can be notified of a message (JSON in our case).
 */
public interface NotifiableHandler {
    void notifyInterface(String notification);
}
