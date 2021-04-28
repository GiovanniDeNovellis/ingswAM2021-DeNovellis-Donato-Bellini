package it.polimi.ingsw.Controller.Messages;

import it.polimi.ingsw.ExtraDeposit;
import it.polimi.ingsw.ResourceType;
import it.polimi.ingsw.WareHouseDepot;

import java.util.HashMap;

public class NotifyInsertedOkMessage extends Message {
    private HashMap<ResourceType,Integer> temporaryResourcesConfiguration;
    private WareHouseDepot warehouseConfiguration;
    private ExtraDeposit[] extraDepositsConfiguration;

    public HashMap<ResourceType, Integer> getTemporaryResourcesConfiguration() {
        return temporaryResourcesConfiguration;
    }

    public WareHouseDepot getWarehouseConfiguration() {
        return warehouseConfiguration;
    }

    public ExtraDeposit[] getExtraDepositsConfiguration() {
        return extraDepositsConfiguration;
    }

    public void setTemporaryResourcesConfiguration(HashMap<ResourceType, Integer> temporaryResourcesConfiguration) {
        this.temporaryResourcesConfiguration = temporaryResourcesConfiguration;
    }

    public void setWarehouseConfiguration(WareHouseDepot warehouseConfiguration) {
        this.warehouseConfiguration = warehouseConfiguration;
    }

    public void setExtraDepositsConfiguration(ExtraDeposit[] extraDepositsConfiguration) {
        this.extraDepositsConfiguration = extraDepositsConfiguration;
    }
}