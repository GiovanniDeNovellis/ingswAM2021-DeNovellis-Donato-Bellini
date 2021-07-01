package it.polimi.ingsw.Controller.Messages;

import it.polimi.ingsw.Deckgrid;

/**
 * Message used to communicate the creation of a single player game
 */
public class SinglePlayerCreationMessage extends Message{
    private int[] choosableLeaderCardsNumbers;
    private Deckgrid deckgridConfiguration;
    private String[][] marbleGridConfiguration;
    private String marbleOut;

    public int[] getChoosableLeaderCardsNumbers() {
        return choosableLeaderCardsNumbers;
    }

    public void setChoosableLeaderCardsNumbers(int[] choosableLeaderCardsNumbers) {
        this.choosableLeaderCardsNumbers = choosableLeaderCardsNumbers;
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

    public String getMarbleOut() {
        return marbleOut;
    }

    public void setMarbleOut(String marbleOut) {
        this.marbleOut = marbleOut;
    }
}
