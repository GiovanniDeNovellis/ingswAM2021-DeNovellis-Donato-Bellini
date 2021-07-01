package it.polimi.ingsw.Controller.Messages;

import it.polimi.ingsw.*;

import java.util.ArrayList;

/**
 * Message used to communicate the creation of a multiplayer game
 */
public class MultiplayerCreationMessage extends Message{
    private int playerNumber;
    private String nickname;
    private int[] choosableLeaderCardsNumbers;
    private Deckgrid deckgridConfiguration;
    private String[][] marbleGridConfiguration;
    private String marbleOut;
    private WareHouseDepot warehouseConfiguration;
    private int[] choosedLeaderCardsNumbers;

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

    public WareHouseDepot getWarehouseConfiguration() {
        return warehouseConfiguration;
    }

    public void setWarehouseConfiguration(WareHouseDepot warehouseConfiguration) {
        this.warehouseConfiguration = warehouseConfiguration;
    }

    public int[] getChoosedLeaderCardsNumbers() {
        return choosedLeaderCardsNumbers;
    }

    public void setChoosedLeaderCardsNumbers(int[] choosedLeaderCardsNumbers) {
        this.choosedLeaderCardsNumbers = choosedLeaderCardsNumbers;
    }
}
