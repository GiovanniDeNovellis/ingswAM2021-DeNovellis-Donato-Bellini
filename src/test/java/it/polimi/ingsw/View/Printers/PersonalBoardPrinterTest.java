package it.polimi.ingsw.View.Printers;

import it.polimi.ingsw.ExtraDeposit;
import it.polimi.ingsw.ResourceType;
import it.polimi.ingsw.Strongbox;
import it.polimi.ingsw.WareHouseDepot;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonalBoardPrinterTest {
    @Test
    public void printWareTest() {
        PersonalBoardPrinter p = new PersonalBoardPrinter();
        p.setOwnerNickname("Giova");
        WareHouseDepot w = new WareHouseDepot();
        w.getLevel(1).addResources(ResourceType.STONES,1);
        w.getLevel(3).addResources(ResourceType.COINS,2);
        p.setWareHouseDepot(w);
        p.print("warehouse");
        Strongbox s = new Strongbox();
        s.addResources(ResourceType.STONES,2);
        s.addResources(ResourceType.COINS,1);
        p.setStrongbox(s.getResourcesContained());
        p.print("strongbox");
        ExtraDeposit ex= new ExtraDeposit(ResourceType.SERVANTS);
        ex.addResource(ResourceType.SERVANTS,2);
        p.setExtraDeposit1(ex);
        p.print("extraDeposit");
    }
}