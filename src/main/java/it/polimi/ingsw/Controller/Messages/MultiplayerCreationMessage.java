package it.polimi.ingsw.Controller.Messages;

import it.polimi.ingsw.Deckgrid;
import it.polimi.ingsw.LeaderCard;
import it.polimi.ingsw.Marble;

import java.util.ArrayList;

public class MultiplayerCreationMessage extends Message{
    private int playerNumber;
    private String nickname;
    private ArrayList<LeaderCard> choosableLeaderCards;
    private Deckgrid deckgridConfiguration;
    private Marble[][] marbleGridConfiguration;
    private Marble marbleOut;

    public void setPlayerNumber(int playerNumber) {
        this.playerNumber = playerNumber;
    }

    public void setChoosableLeaderCards(ArrayList<LeaderCard> choosableLeaderCards) {
        this.choosableLeaderCards = choosableLeaderCards;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    public int[] getChoosableLeaderCardsNumbers() {
        int[] leaderCardsNumbers = new int[4];
        int i = 0;
        for( LeaderCard l: choosableLeaderCards){
            leaderCardsNumbers[i] = l.getLeaderCardNumber();
            i++;
        }
        return leaderCardsNumbers;
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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Marble getMarbleOut() {
        return marbleOut;
    }

    public void setMarbleOut(Marble marbleOut) {
        this.marbleOut = marbleOut;
    }
}
