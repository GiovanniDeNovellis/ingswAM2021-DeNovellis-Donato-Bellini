package it.polimi.ingsw.View.Printers;

import it.polimi.ingsw.WareHouseDepot;

public class WarehousePrinter implements Printable {
    private WareHouseDepot warehouse;
    private boolean built=false;

    @Override
    public void print() {

    }

    public void setWarehouse(WareHouseDepot warehouse) {
        if(!built)
            built=true;
        this.warehouse = warehouse;
    }
}
