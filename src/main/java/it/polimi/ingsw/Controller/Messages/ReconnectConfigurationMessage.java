package it.polimi.ingsw.Controller.Messages;

import it.polimi.ingsw.*;

import java.util.ArrayList;

public class ReconnectConfigurationMessage extends Message{
    private String senderNickname;
    private String[][] marbleGridConfiguration;
    private String marbleOut;
    private Deckgrid deckgridConfiguration;
    private WareHouseDepot wareHouseConfiguration;
    private Strongbox strongboxConfiguration;
    private ArrayList<ExtraDeposit> extraDepositConfiguration;
    private int newFaithPoints;
    private DevelopmentCard[] developmentCardsConfiguration;
    private ArrayList<Integer> choosableLeaderCards = new ArrayList<>();
    private ArrayList<Integer> choosedLeaderCards = new ArrayList<>();
    private boolean[] activeLeaderCards;

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

    public Deckgrid getDeckgridConfiguration() {
        return deckgridConfiguration;
    }

    public void setDeckgridConfiguration(Deckgrid deckgridConfiguration) {
        this.deckgridConfiguration = deckgridConfiguration;
    }

    public WareHouseDepot getWareHouseConfiguration() {
        return wareHouseConfiguration;
    }

    public void setWareHouseConfiguration(WareHouseDepot wareHouseConfiguration) {
        this.wareHouseConfiguration = wareHouseConfiguration;
    }

    public Strongbox getStrongboxConfiguration() {
        return strongboxConfiguration;
    }

    public void setStrongboxConfiguration(Strongbox strongboxConfiguration) {
        this.strongboxConfiguration = strongboxConfiguration;
    }

    public ArrayList<ExtraDeposit> getExtraDepositConfiguration() {
        return extraDepositConfiguration;
    }

    public void setExtraDepositConfiguration(ArrayList<ExtraDeposit> extraDepositConfiguration) {
        this.extraDepositConfiguration = extraDepositConfiguration;
    }

    public int getNewFaithPoints() {
        return newFaithPoints;
    }

    public void setNewFaithPoints(int newFaithPoints) {
        this.newFaithPoints = newFaithPoints;
    }

    public DevelopmentCard[] getDevelopmentCardsConfiguration() {
        return developmentCardsConfiguration;
    }

    public void setDevelopmentCardsConfiguration(DevelopmentCard[] developmentCardsConfiguration) {
        this.developmentCardsConfiguration = developmentCardsConfiguration;
    }

    public ArrayList<Integer> getChoosableLeaderCards() {
        return choosableLeaderCards;
    }

    public void setChoosableLeaderCards(ArrayList<Integer> choosableLeaderCards) {
        this.choosableLeaderCards = choosableLeaderCards;
    }

    public ArrayList<Integer> getChoosedLeaderCards() {
        return choosedLeaderCards;
    }

    public void setChoosedLeaderCards(ArrayList<Integer> choosedLeaderCards) {
        this.choosedLeaderCards = choosedLeaderCards;
    }

    public String getSenderNickname() {
        return senderNickname;
    }

    public void setSenderNickname(String senderNickname) {
        this.senderNickname = senderNickname;
    }

    public boolean[] getActiveLeaderCards() {
        return activeLeaderCards;
    }

    public void setActiveLeaderCards(boolean[] activeLeaderCards) {
        this.activeLeaderCards = activeLeaderCards;
    }
}
