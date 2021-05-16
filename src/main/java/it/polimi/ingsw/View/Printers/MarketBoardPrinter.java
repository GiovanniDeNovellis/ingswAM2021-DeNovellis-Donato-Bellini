package it.polimi.ingsw.View.Printers;

import it.polimi.ingsw.Marble;
import it.polimi.ingsw.ResourceType;

import java.util.HashMap;
import java.util.Map;

public class MarketBoardPrinter implements Printable {
    private String[][] marbleGrid;
    private String marbleOut;
    private Map<ResourceType,Integer> temporaryResources = new HashMap<>();
    private boolean built=false;

    @Override
    public void print() {

    }

    public void setMarbleGrid(String[][] marbleGrid) {
        if(!built)
            built=true;
        this.marbleGrid = marbleGrid;
    }

    public void setMarbleOut(String marbleOut) {
        if(!built)
            built=true;
        this.marbleOut = marbleOut;
    }

    public void setTemporaryResources(Map<ResourceType, Integer> temporaryResources) {
        this.temporaryResources = temporaryResources;
    }
}
