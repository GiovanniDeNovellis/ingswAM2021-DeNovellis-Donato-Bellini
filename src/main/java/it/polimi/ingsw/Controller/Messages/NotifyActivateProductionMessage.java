package it.polimi.ingsw.Controller.Messages;

import it.polimi.ingsw.ExtraDeposit;
import it.polimi.ingsw.ResourceType;
import it.polimi.ingsw.WareHouseDepot;
import java.util.Map;

/**
 * Message used to communicate the activation of a production
 */
public class NotifyActivateProductionMessage extends Message{
    private WareHouseDepot warehouseConfiguration;
    private ExtraDeposit[] extraDepositConfiguration;
    private Map<ResourceType,Integer> strongboxConfiguration;
    private int newFaithPoints;
    private String whoActivatesProduction;

    public String getWhoActivatesProduction() {
        return whoActivatesProduction;
    }

    public void setWhoActivatesProduction(String whoActivatesProduction) {
        this.whoActivatesProduction = whoActivatesProduction;
    }

    public WareHouseDepot getWarehouseConfiguration() {
        return warehouseConfiguration;
    }

    public ExtraDeposit[] getExtraDepositConfiguration() {
        return extraDepositConfiguration;
    }

    public Map<ResourceType, Integer> getStrongboxConfiguration() {
        return strongboxConfiguration;
    }

    public int getNewFaithPoints() {
        return newFaithPoints;
    }

    public void setWarehouseConfiguration(WareHouseDepot warehouseConfiguration) {
        this.warehouseConfiguration = warehouseConfiguration;
    }

    public void setExtraDepositConfiguration(ExtraDeposit[] extraDepositConfiguration) {
        this.extraDepositConfiguration = extraDepositConfiguration;
    }

    public void setStrongboxConfiguration(Map<ResourceType, Integer> strongboxConfiguration) {
        this.strongboxConfiguration = strongboxConfiguration;
    }

    public void setNewFaithPoints(int newFaithPoints) {
        this.newFaithPoints = newFaithPoints;
    }
}
