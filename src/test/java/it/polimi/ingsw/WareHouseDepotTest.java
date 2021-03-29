package it.polimi.ingsw;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WareHouseDepotTest {

    @Test
    public void whouseFirstTest(){
        WareHouseDepot whouse = new WareHouseDepot();
        assertFalse(whouse.getLevel(1).addResources(ResourceType.COINS,2));
    }

    @Test
    public void whouseAddAllLevels(){
        WareHouseDepot whouse = new WareHouseDepot();
        whouse.getLevel(1).addResources(ResourceType.SHIELDS,1);
        whouse.getLevel(2).addResources(ResourceType.COINS,1);
        whouse.getLevel(3).addResources(ResourceType.STONES,3);
        assertEquals(ResourceType.SHIELDS, whouse.getLevel(1).getResourceType());
        assertNotEquals(ResourceType.COINS, whouse.getLevel(1).getResourceType());
        assertEquals(ResourceType.COINS, whouse.getLevel(2).getResourceType());
        assertEquals(3,whouse.getLevel(3).getCurrNumResources());
    }

    @Test
    public void whouseAddRemoveTest(){
        WareHouseDepot whouse = new WareHouseDepot();
        whouse.getLevel(1).addResources(ResourceType.SHIELDS,1);
        whouse.getLevel(2).addResources(ResourceType.COINS,1);
        whouse.getLevel(3).addResources(ResourceType.STONES,3);
        whouse.getLevel(1).getResources(1);
        assertEquals(0,whouse.getLevel(1).getCurrNumResources());
        assertNull(whouse.getLevel(1).getResourceType());
        whouse.getLevel(3).getResources(0);
        assertEquals(3, whouse.getLevel(3).getCurrNumResources());
        assertEquals(ResourceType.STONES,whouse.getLevel(3).getResourceType());
    }
}