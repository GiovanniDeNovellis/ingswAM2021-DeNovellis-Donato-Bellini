package it.polimi.ingsw.Controller.Messages;

import it.polimi.ingsw.ExtraDeposit;
import it.polimi.ingsw.ResourceType;
import it.polimi.ingsw.WareHouseDepot;

import java.util.HashMap;

public class NotifyActivateProductionMessage extends Message{
    private WareHouseDepot warehouseConfiguration;
    private ExtraDeposit[] extraDepositConfiguration;
    private HashMap<ResourceType,Integer> strongboxConfiguration;
    private int newFaithPoints;

    public WareHouseDepot getWarehouseConfiguration() {
        return warehouseConfiguration;
    }

    public ExtraDeposit[] getExtraDepositConfiguration() {
        return extraDepositConfiguration;
    }

    public HashMap<ResourceType, Integer> getStrongboxConfiguration() {
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

    public void setStrongboxConfiguration(HashMap<ResourceType, Integer> strongboxConfiguration) {
        this.strongboxConfiguration = strongboxConfiguration;
    }

    public void setNewFaithPoints(int newFaithPoints) {
        this.newFaithPoints = newFaithPoints;
    }
}
