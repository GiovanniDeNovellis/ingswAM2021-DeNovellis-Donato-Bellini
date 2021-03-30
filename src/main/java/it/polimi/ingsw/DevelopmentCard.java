package it.polimi.ingsw;


import java.util.HashMap;
import java.util.TreeMap;

public class DevelopmentCard {
    private int level;
    private TreeMap<ResourceType, Integer> cost = new TreeMap<>();
    private TreeMap<ResourceType, Integer> earnedResources = new TreeMap<>();
    private TreeMap<ResourceType, Integer> productionCost = new TreeMap<>();

    private Colour type;
    private int victoryPoints;

    public DevelopmentCard(int level, Colour type, int victoryPoints){
        this.level = level;
        this.type = type;
        this.victoryPoints = victoryPoints;
    }

    public int getVictoryPoints(){
        return victoryPoints;
    }

    public void activateProduction(){

    }


    public void printCards(){
        System.out.println("Colour: " + type);
        System.out.println("Level: " + level);
        for (ResourceType resource : cost.keySet()) {
            System.out.println("Costo della carta: " + resource + " ,Quantità: " + cost.get(resource));
        }
        for (ResourceType resource : earnedResources.keySet()) {
            System.out.println("Risorse guadagnate: " + resource + " ,Quantità: " + earnedResources.get(resource));
        }
        for (ResourceType resource : productionCost.keySet()) {
            System.out.println("Costo di produzione: " + resource + " ,Quantità: " + productionCost.get(resource));
        }
        System.out.println("\n");
    }



}
