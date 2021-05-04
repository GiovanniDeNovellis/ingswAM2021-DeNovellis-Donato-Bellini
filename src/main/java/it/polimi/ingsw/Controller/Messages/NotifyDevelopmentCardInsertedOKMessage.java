package it.polimi.ingsw.Controller.Messages;

import it.polimi.ingsw.DevelopmentCard;
import it.polimi.ingsw.ExtraDeposit;
import it.polimi.ingsw.ResourceType;
import it.polimi.ingsw.WareHouseDepot;

import java.util.HashMap;

public class NotifyDevelopmentCardInsertedOKMessage extends Message{
    private DevelopmentCard[] developmentCardsConfiguration;
    private WareHouseDepot warehouseConfiguration;
    private ExtraDeposit[] extraDepositsConfiguration;
    private HashMap<ResourceType,Integer> strongboxConfiguration;
    public String getNickname() {
        return Nickname;
    }

    public void setNickname(String senderNickname) {
        this.Nickname = senderNickname;
    }

    private String Nickname;

    public DevelopmentCard[] getDevelopmentCardsConfiguration() {
        return developmentCardsConfiguration;
    }

    public WareHouseDepot getWarehouseConfiguration() {
        return warehouseConfiguration;
    }

    public ExtraDeposit[] getExtraDepositsConfiguration() {
        return extraDepositsConfiguration;
    }

    public HashMap<ResourceType, Integer> getStrongboxConfiguration() {
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

    public void setStrongboxConfiguration(HashMap<ResourceType, Integer> strongboxConfiguration) {
        this.strongboxConfiguration = strongboxConfiguration;
    }
}
