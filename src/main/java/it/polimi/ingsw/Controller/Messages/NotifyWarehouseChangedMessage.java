package it.polimi.ingsw.Controller.Messages;

import it.polimi.ingsw.WareHouseDepot;

public class NotifyWarehouseChangedMessage extends Message {
    private WareHouseDepot warehouseConfiguration;
    private String playerToChange;

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

    public String getPlayerToChange() {
        return playerToChange;
    }

    public void setPlayerToChange(String playerToChange) {
        this.playerToChange = playerToChange;
    }

}
