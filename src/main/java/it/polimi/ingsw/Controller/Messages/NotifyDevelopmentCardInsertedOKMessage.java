package it.polimi.ingsw.Controller.Messages;

import it.polimi.ingsw.*;

import java.util.ArrayList;
import java.util.Map;

/**
 * Message used to communicate the insertion of a development card
 */
public class NotifyDevelopmentCardInsertedOKMessage extends Message{
    private DevelopmentCard[] developmentCardsConfiguration;
    private ArrayList<DevelopmentCard> insertedDevelopmentCard;
    private WareHouseDepot warehouseConfiguration;
    private ExtraDeposit[] extraDepositsConfiguration;
    private Map<ResourceType,Integer> strongboxConfiguration;
    public String getNickname() {
        return senderNickname;
    }
    private Deckgrid deckgridConfiguration;

    public void setNickname(String senderNickname) {
        this.senderNickname = senderNickname;
    }

    private String senderNickname;

    public DevelopmentCard[] getDevelopmentCardsConfiguration() {
        return developmentCardsConfiguration;
    }

    public WareHouseDepot getWarehouseConfiguration() {
        return warehouseConfiguration;
    }

    public ExtraDeposit[] getExtraDepositsConfiguration() {
        return extraDepositsConfiguration;
    }

    public Map<ResourceType, Integer> getStrongboxConfiguration() {
        return strongboxConfiguration;
    }

    public void setDevelopmentCardsConfiguration(DevelopmentCard[] developmentCardsConfiguration) {
        this.developmentCardsConfiguration = developmentCardsConfiguration;
    }

    public void setWarehouseConfiguration(WareHouseDepot warehouseConfiguration) {
        this.warehouseConfiguration = warehouseConfiguration;
    }

    public void setExtraDepositsConfiguration(ExtraDeposit[] extraDepositsConfiguration) {
        this.extraDepositsConfiguration = extraDepositsConfiguration;
    }

    public void setStrongboxConfiguration(Map<ResourceType, Integer> strongboxConfiguration) {
        this.strongboxConfiguration = strongboxConfiguration;
    }

    public Deckgrid getDeckgridConfiguration() {
        return deckgridConfiguration;
    }

    public void setDeckgridConfiguration(Deckgrid deckgridConfiguration) {
        this.deckgridConfiguration = deckgridConfiguration;
    }

    public ArrayList<DevelopmentCard> getInsertedDevelopmentCard() {
        return insertedDevelopmentCard;
    }

    public void setInsertedDevelopmentCard(ArrayList<DevelopmentCard> insertedDevelopmentCard) {
        this.insertedDevelopmentCard = insertedDevelopmentCard;
    }

    public String getSenderNickname() {
        return senderNickname;
    }

    public void setSenderNickname(String senderNickname) {
        this.senderNickname = senderNickname;
    }
}
