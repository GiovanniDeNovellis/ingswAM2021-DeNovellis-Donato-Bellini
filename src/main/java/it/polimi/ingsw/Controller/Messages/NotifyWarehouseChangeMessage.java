package it.polimi.ingsw.Controller.Messages;

import it.polimi.ingsw.WareHouseDepot;

public class NotifyWarehouseChangeMessage extends Message {
    private WareHouseDepot warehouseConfiguration;
    private String playerToChange;

    public NotifyWarehouseChangeMessage() {
        super();
        messageType="NotifyWareHouseChangeMessage";
    }

    public WareHouseDepot getWarehouseConfiguration() {
        return warehouseConfiguration;
    }

    public void setWarehouseConfiguration(WareHouseDepot warehouseConfiguration) {
        this.warehouseConfiguration = warehouseConfiguration;
    }

    public String getPlayerToChange() {
        return playerToChange;
    }

    public void setPlayerToChange(String playerToChange) {
        this.playerToChange = playerToChange;
    }
}
