package it.polimi.ingsw;

import java.util.TreeMap;

/**
 * Represents the warehouse depot. Through this
 * class, every player can interact with three levels
 * where he can store resources. Different levels of
 * this class, can't have the same type of resource.
 */
public class WareHouseDepot {
    /**
     * Map associating an integer representing
     * the maximum capacity to the corresponding level.
     */
    private final TreeMap<Integer, Level> warehouseLevels;

    /**
     * Build the warehouse with three levels capable
     * of storing 1,2 and 3 resources.
     */
    public WareHouseDepot(){
        this.warehouseLevels = new TreeMap<>();
        Level level1 = new Level(1);
        Level level2 = new Level(2);
        Level level3 = new Level(3);
        warehouseLevels.put(1, level1);
        warehouseLevels.put(2, level2);
        warehouseLevels.put(3, level3);
    }

    /**
     * Method used by the GameBoard to interact with any level.
     * @param maxSlots The size of the level selected.
     * @return The level selected.
     */
    public Level getLevel(int maxSlots){return warehouseLevels.get(maxSlots);}

    /**
     * Method called to swap the resources from two levels.
     * @param maxSlotsFirst The first level to swap.
     * @param maxSlotsSecond The second level to swap.
     * @return True if the swap is possible, false if not.
     */
    public boolean switchLevels(int maxSlotsFirst, int maxSlotsSecond){
        int firstLvlSize = warehouseLevels.get(maxSlotsFirst).getMaxNumResources();
        int firstLvlCurr = warehouseLevels.get(maxSlotsFirst).getCurrNumResources();
        int secondLvlSize = warehouseLevels.get(maxSlotsSecond).getMaxNumResources();
        int secondLvlCurr = warehouseLevels.get(maxSlotsSecond).getCurrNumResources();
        if(firstLvlCurr>secondLvlSize || secondLvlCurr > firstLvlSize){
            return false;
        }
        ResourceType res1 = warehouseLevels.get(maxSlotsFirst).getResourceType();
        ResourceType res2 = warehouseLevels.get(maxSlotsSecond).getResourceType();
        warehouseLevels.get(maxSlotsFirst).addResources(res2,secondLvlCurr);
        warehouseLevels.get(maxSlotsSecond).addResources(res1,firstLvlCurr);
        return true;
    }
}
