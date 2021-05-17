package it.polimi.ingsw.Controller.Messages;

import it.polimi.ingsw.WareHouseDepot;

public class NotifyWarehouseChangedMessage extends Message {
    private WareHouseDepot warehouseConfiguration;

    public String getNickname() {
        return senderNickname;
    }

    public void setNickname(String senderNickname) {
        this.senderNickname = senderNickname;
    }

    private String senderNickname;

    public WareHouseDepot getWarehouseConfiguration() {
        return warehouseConfiguration;
    }

    public void setWarehouseConfiguration(WareHouseDepot warehouseConfiguration) {
        this.warehouseConfiguration = warehouseConfiguration;
    }
}
