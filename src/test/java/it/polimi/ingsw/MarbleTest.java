package it.polimi.ingsw;

import it.polimi.ingsw.MarbleTypes.WareHouseMarbleType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MarbleTest {
    //JUST A SIMPLE TEST HERE, BECAUSE ALL THE
    //onSelection TESTS ARE NO THE MarketBoard TEST CLASS
    @Test
    public void wareHouseBuildTest(){
        Marble m = new Marble(new WareHouseMarbleType(ResourceType.COINS), "yellow");
        WareHouseMarbleType ware = (WareHouseMarbleType) m.getCurrentMarbleType();
        assertEquals(ResourceType.COINS,ware.getResourceType());
    }


}