package it.polimi.ingsw.Controller.Messages;

import it.polimi.ingsw.Deckgrid;
import it.polimi.ingsw.LeaderCard;
import it.polimi.ingsw.Marble;
import it.polimi.ingsw.ResourceType;

import java.util.ArrayList;

public class MultiplayerCreationMessage extends Message{
    private int playerNumber;
    private String nickname;
    private int[] choosableLeaderCardsNumbers;
    private Deckgrid deckgridConfiguration;
    private String[][] marbleGridConfiguration;
    private String marbleOut;

    public void setPlayerNumber(int playerNumber) {
        this.playerNumber = playerNumber;
    }

    public void setChoosableLeaderCardsNumbers(int[] choosableLeaderCards) {
        this.choosableLeaderCardsNumbers = choosableLeaderCards;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    public int[] getChoosableLeaderCardsNumbers() {
        return choosableLeaderCardsNumbers;
    }

    public Deckgrid getDeckgridConfiguration() {
        return deckgridConfiguration;
    }

    public void setDeckgridConfiguration(Deckgrid deckgridConfiguration) {
        this.deckgridConfiguration = deckgridConfiguration;
    }

    public String[][] getMarbleGridConfiguration() {
        return marbleGridConfiguration;
    }

    public void setMarbleGridConfiguration(String[][] marbleGridConfiguration) {
        this.marbleGridConfiguration = marbleGridConfiguration;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getMarbleOut() {
        return marbleOut;
    }

    public void setMarbleOut(String marbleOut) {
        this.marbleOut = marbleOut;
    }
}
