package it.polimi.ingsw.Controller.Messages;

import it.polimi.ingsw.LeaderCard;

import java.util.ArrayList;

public class MultiplayerCreationMessage extends Message{
    private int playerNumber;
    private ArrayList<LeaderCard> choosableLeaderCards;

    public MultiplayerCreationMessage() {
        super();
        messageType= "MultiPlayerCreationMessage";
    }

    public void setPlayerNumber(int playerNumber) {
        this.playerNumber = playerNumber;
    }

    public void setChoosableLeaderCards(ArrayList<LeaderCard> choosableLeaderCards) {
        this.choosableLeaderCards = choosableLeaderCards;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    public ArrayList<LeaderCard> getChoosableLeaderCards() {
        return choosableLeaderCards;
    }
}
