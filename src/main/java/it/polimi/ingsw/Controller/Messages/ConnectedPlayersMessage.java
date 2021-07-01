package it.polimi.ingsw.Controller.Messages;

import java.util.ArrayList;

/**
 * Message used to communicate the list of the connected players
 */
public class ConnectedPlayersMessage extends Message{
    ArrayList<String> connectedPLayers = new ArrayList<>();

    public ArrayList<String> getConnectedPlayers() {
        return connectedPLayers;
    }
}
