package it.polimi.ingsw.Controller.Messages;

import it.polimi.ingsw.ExtraDeposit;
import it.polimi.ingsw.ResourceType;
import it.polimi.ingsw.WareHouseDepot;

import java.util.HashMap;
import java.util.Map;
/**
 * Message used to communicate the insertion of a resource
 */
public class NotifyInsertedOkMessage extends Message {
    private Map<ResourceType,Integer> temporaryResourcesConfiguration;
    private WareHouseDepot warehouseConfiguration;
    private ExtraDeposit[] extraDepositsConfiguration = new ExtraDeposit[2];
    public String getNickname() {
        return senderNickname;
    }

    public void setNickname(String senderNickname) {
        this.senderNickname = senderNickname;
    }

    private String senderNickname;

    public Map<ResourceType, Integer> getTemporaryResourcesConfiguration() {
        return temporaryResourcesConfiguration;
    }

    public WareHouseDepot getWarehouseConfiguration() {
        return warehouseConfiguration;
    }

    public ExtraDeposit[] getExtraDepositsConfiguration() {
        return extraDepositsConfiguration;
    }

    public void setTemporaryResourcesConfiguration(Map<ResourceType, Integer> temporaryResourcesConfiguration) {
        this.temporaryResourcesConfiguration = temporaryResourcesConfiguration;
    }

    public void setWarehouseConfiguration(WareHouseDepot warehouseConfiguration) {
        this.warehouseConfiguration = warehouseConfiguration;
    }

    public void setExtraDepositsConfiguration(ExtraDeposit[] extraDepositsConfiguration) {
        this.extraDepositsConfiguration = extraDepositsConfiguration;
    }
}
