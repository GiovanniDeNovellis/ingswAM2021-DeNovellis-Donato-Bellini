package it.polimi.ingsw;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LevelTest {

    @Test
    public void constructionLevelTest(){
        ResourceType res;
        Level level = new Level(3);
        assertEquals(level.getMaxNumResources(),3);
    }

    @Test
    public void getResourceLevelTest(){
        Level level = new Level(3);
        level.addResources(ResourceType.COINS, 2);
        assertEquals(level.getCurrNumResources(), 2);
        assertNotEquals(level.getResourceType(), ResourceType.SHIELDS);
        assertEquals(level.getResourceType(), ResourceType.COINS);
    }

    @Test
    public void addExceedingDimension(){
        boolean testCondition;
        Level level = new Level(2);
        testCondition  = level.addResources(ResourceType.SHIELDS, 3);
        assertFalse(testCondition);
        assertEquals(level.getCurrNumResources(),0);
    }

    @Test
    public void changeLevelComposition(){
        Level level = new Level(2);
        level.addResources(ResourceType.SHIELDS,1);
        assertEquals(level.getResourceType(),ResourceType.SHIELDS);
        assertEquals(level.getCurrNumResources(),1);
        level.addResources(ResourceType.COINS,2);
        assertEquals(level.getResourceType(),ResourceType.COINS);
        assertEquals(level.getCurrNumResources(),2);
    }

    @Test
    public void fillAndEmptyLevel(){
        Level level = new Level(3);
        level.addResources(ResourceType.COINS,2);
        assertFalse(level.getResources(3));
        assertTrue(level.getResources(2));
        assertEquals(level.getCurrNumResources(),0);
        assertNull(level.getResourceType());
    }

}