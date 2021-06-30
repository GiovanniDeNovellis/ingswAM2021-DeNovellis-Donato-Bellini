package it.polimi.ingsw.Controller.RequestManagers;

/**
 * This class reads the message that arrives from the client,
 * and through the "manage" method it reads what to do and executes on the model of operations.
 * It takes a json message as input and returns another to send to the client of the same type
 */
public interface Manageable {
    String manageRequest(String jsonContent);
}
