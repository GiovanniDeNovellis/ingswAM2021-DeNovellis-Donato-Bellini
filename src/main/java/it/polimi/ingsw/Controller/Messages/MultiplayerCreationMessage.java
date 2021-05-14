package it.polimi.ingsw.Controller.Messages;

import it.polimi.ingsw.Deckgrid;
import it.polimi.ingsw.LeaderCard;
import it.polimi.ingsw.Marble;

import java.util.ArrayList;

public class MultiplayerCreationMessage extends Message{
    private int playerNumber;
    private ArrayList<LeaderCard> choosableLeaderCards;
    private Deckgrid deckgridConfiguration;
    private Marble[][] marbleGridConfiguration;

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

    public Deckgrid getDeckgridConfiguration() {
        return deckgridConfiguration;
    }

    public void setDeckgridConfiguration(Deckgrid deckgridConfiguration) {
        this.deckgridConfiguration = deckgridConfiguration;
    }

    public Marble[][] getMarbleGridConfiguration() {
        return marbleGridConfiguration;
    }

    public void setMarbleGridConfiguration(Marble[][] marbleGridConfiguration) {
        this.marbleGridConfiguration = marbleGridConfiguration;
    }
}
