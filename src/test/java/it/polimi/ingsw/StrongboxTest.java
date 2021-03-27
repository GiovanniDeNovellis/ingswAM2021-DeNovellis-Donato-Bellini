package it.polimi.ingsw;

import static it.polimi.ingsw.ResourceType.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;


class StrongboxTest{

    @Test
    public void addResourceTest(){
        Strongbox strongbox = new Strongbox();
        strongbox.addResources(SHIELDS, 3);
        assertEquals(3, strongbox.resourcesContained.get(SHIELDS));
        strongbox.addResources(SHIELDS, 0);
        assertEquals(3, strongbox.resourcesContained.get(SHIELDS));
        strongbox.addResources(SHIELDS, 2);
        assertEquals(5, strongbox.resourcesContained.get(SHIELDS));
        strongbox.addResources(SERVANTS, 9);
        assertEquals(9, strongbox.resourcesContained.get(SERVANTS));
        strongbox.addResources(SHIELDS, 0);
        assertEquals(5, strongbox.resourcesContained.get(SHIELDS));
        strongbox.addResources(SHIELDS, -1);
        assertEquals(5, strongbox.resourcesContained.get(SHIELDS));


    }

    @Test
    public void viewAllResourcesTest(){
        String printResult = "nullRisorsa: SHIELDS ,Quantità: 0" +
                "Risorsa: COINS ,Quantità: 1" +
                "Risorsa: SERVANTS ,Quantità: 2" +
                "Risorsa: STONES ,Quantità: 6";
        Strongbox strongbox = new Strongbox();
        strongbox.addResources(SHIELDS, 0);
        strongbox.addResources(COINS, 1);
        strongbox.addResources(SERVANTS, 2);
        strongbox.addResources(STONES, 3);
        strongbox.addResources(STONES, 3);
        strongbox.addResources(COINS, 0);
        //must uncomment viewAllResource method with return statement to work
        assertEquals(printResult, strongbox.viewAllResources());
    }

    @Test
    public void getVictoryPointsTest(){
        Strongbox strongbox = new Strongbox();
        strongbox.addResources(SHIELDS, 0);
        strongbox.addResources(COINS, 1);
        strongbox.addResources(SERVANTS, 2);
        strongbox.addResources(STONES, 3);
        strongbox.addResources(STONES, 3);
        strongbox.addResources(COINS, 0);
        assertEquals(1, strongbox.getVictoryPoints());
    }

    @Test
    public void removeResourcesTest(){
        Strongbox strongbox = new Strongbox();
        strongbox.addResources(SHIELDS, 3);
        assertTrue( strongbox.removeResources(SHIELDS, 3) );
        assertEquals(0, strongbox.resourcesContained.get(SHIELDS));
        assertFalse( strongbox.removeResources(SHIELDS, 5) );
        assertEquals(0, strongbox.resourcesContained.get(SHIELDS));
        strongbox.addResources(SHIELDS, 30);
        assertFalse( strongbox.removeResources(SHIELDS, 31) );
        assertEquals(30, strongbox.resourcesContained.get(SHIELDS));
        assertTrue( strongbox.removeResources(SHIELDS, 29) );
        assertEquals(1, strongbox.resourcesContained.get(SHIELDS));
        assertTrue( strongbox.removeResources(SHIELDS, -5) );
        assertEquals(1, strongbox.resourcesContained.get(SHIELDS));
        assertFalse( strongbox.removeResources(COINS, 2) );
    }
}