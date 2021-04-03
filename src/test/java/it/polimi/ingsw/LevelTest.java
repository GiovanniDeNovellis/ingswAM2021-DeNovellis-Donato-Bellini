package it.polimi.ingsw;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LevelTest {

    @Test
    public void constructionLevelTest(){
        ResourceType res;
        Level level = new Level(3);
        assertEquals(3,level.getMaxNumResources());
    }

    @Test
    public void getResourceLevelTest(){
        Level level = new Level(3);
        level.addResources(ResourceType.COINS, 2);
        assertEquals(2, level.getCurrNumResources());
        assertNotEquals(ResourceType.SHIELDS, level.getResourceType());
        assertEquals(ResourceType.COINS, level.getResourceType());
    }

    @Test
    public void addExceedingDimension(){
        boolean testCondition;
        Level level = new Level(2);
        testCondition  = level.addResources(ResourceType.SHIELDS, 3);
        assertFalse(testCondition);
        assertEquals(0,level.getCurrNumResources());
    }

    @Test
    public void changeLevelComposition(){
        Level level = new Level(2);
        level.addResources(ResourceType.SHIELDS,1);
        assertEquals(ResourceType.SHIELDS,level.getResourceType());
        assertEquals(1,level.getCurrNumResources());
        level.addResources(ResourceType.COINS,2);
        assertEquals(ResourceType.COINS,level.getResourceType());
        assertEquals(2,level.getCurrNumResources());
    }

    @Test
    public void fillAndEmptyLevel(){
        Level level = new Level(3);
        level.addResources(ResourceType.COINS,2);
        assertFalse(level.removeResources(3));
        assertTrue(level.removeResources(2));
        assertEquals(0,level.getCurrNumResources());
        assertNull(level.getResourceType());
    }

}