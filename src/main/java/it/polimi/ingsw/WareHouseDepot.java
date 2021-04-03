package it.polimi.ingsw;

import java.util.TreeMap;

public class WareHouseDepot {
    private final TreeMap<Integer, Level> warehouseLevels;
    //Todo( ogni risorsa che non può essere aggiunta si trasforma in un punto fede per tutti gli altri giocatori)
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
}
