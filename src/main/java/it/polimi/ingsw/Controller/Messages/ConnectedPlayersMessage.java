package it.polimi.ingsw.Controller.Messages;

import java.util.ArrayList;

public class ConnectedPlayersMessage extends Message{
    ArrayList<String> connectedPLayers = new ArrayList<>();

    public ArrayList<String> getConnectedPlayers() {
        return connectedPLayers;
    }
}
