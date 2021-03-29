package it.polimi.ingsw;

import java.util.TreeMap;

public class WareHouseDepot {

    private final TreeMap<Integer, Level> warehouseLevels;

    public WareHouseDepot(){
        this.warehouseLevels = new TreeMap<>();
        Level level1 = new Level(1);
        Level level2 = new Level(2);
        Level level3 = new Level(3);
        warehouseLevels.put(1, level1);
        warehouseLevels.put(2, level2);
        warehouseLevels.put(3, level3);
    }

    public Level getLevel(int maxSlots){return warehouseLevels.get(maxSlots);}
}
