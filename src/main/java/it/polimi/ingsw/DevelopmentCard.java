package it.polimi.ingsw;

import java.util.TreeMap;

public class DevelopmentCard {
    private int level;
    private TreeMap<ResourceType, Integer> cost = new TreeMap<>();
    private TreeMap<ResourceType, Integer> earnedResources = new TreeMap<>();
    private TreeMap<ResourceType, Integer> productionCost = new TreeMap<>();
    private Colour type;
    private int victoryPoints;

    public int getVictoryPoints(){
        return victoryPoints;
    }

    public void activateProduction(){

    }
}
