package it.polimi.ingsw.Controller.Messages;

import it.polimi.ingsw.WareHouseDepot;

public class NotifyWarehouseChangeMessage extends Message {
    private WareHouseDepot warehouseConfiguration;
    private String playerToChange;

    public NotifyWarehouseChangeMessage() {
        super();
        messageType="NotifyWareHouseChangeMessage";
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    private String nickname;

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
