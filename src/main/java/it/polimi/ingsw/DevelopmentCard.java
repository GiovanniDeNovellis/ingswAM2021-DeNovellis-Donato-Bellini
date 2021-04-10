package it.polimi.ingsw;

import java.util.TreeMap;

public class DevelopmentCard {
    /** attribute level is referred to developmentCard's level */
    private int level;
    /** The three TreeMaps are used to keep in memory ,respectively, the necessary resources to buy the development card,
     * cost expressed in resources to activate the developmentCard's production and resources obtained throw the production */
    private TreeMap<ResourceType, Integer> cost = new TreeMap<>();
    private TreeMap<ResourceType, Integer> earnedResources = new TreeMap<>();
    private TreeMap<ResourceType, Integer> productionCost = new TreeMap<>();

    /** attribute colour is referred to developmentCard's colour */
    private Colour type;
    /** attribute victoryPoints represents victoryPoints given by a developmentCard when game ends */
    private int victoryPoints;

    /**
     * Returns the colour of the card.
     * @return Color
     */
    public Colour getColour(){return this.type;}


    public int getLevel(){
        return level;
    }

    public int getVictoryPoints(){
        return victoryPoints;
    }


    public int getProductionCost(ResourceType resourceType) {
        return productionCost.get(resourceType);
    }

    // --  uncomment this constructor to execute DeckTest --
    /*
    public DevelopmentCard(int level, Colour type, int victoryPoints){
        this.level = level;
        this.type = type;
        this.victoryPoints = victoryPoints;
    }*/


    /** activateProduction method is called by PersonalBoard when the player wants tho activate production from a
     * DevelopmentCard in his own PersonalBoard. This method checks if the player has the required resources in his
     * warehouse to activate the production through the boolean returned by the personalBoard.takeResourcesFromWarehouse
     * method, invoked by this method. If the player can pay the production cost, this method invokes personalBoard.addResourceToStrongbox */

    public boolean activateProduction(PersonalBoard personalBoard){
        boolean doneWarehouse;
        boolean doneStrongbox;
        boolean canPay = false;

        for( ResourceType resource : productionCost.keySet()) {
            doneWarehouse = personalBoard.checkResourcesIntoWarehouse(resource, productionCost.get(resource));
            if( doneWarehouse )
                canPay = true;
            else {
                int missing = personalBoard.missingResourcesIntoWarehouseWithoutRemove(resource, productionCost.get(resource));
                doneStrongbox = personalBoard.checkResourcesIntoStrongbox(resource, missing);
                if (!doneStrongbox) {
                    canPay = false;
                    break;
                } else
                    canPay = true;
            }
        }
        if( canPay ) {
            for( ResourceType resource : productionCost.keySet()) {
                doneWarehouse = personalBoard.takeResourcesFromWarehouse(resource, productionCost.get(resource));
                if( !doneWarehouse ) {
                    int missing = personalBoard.missingResourcesIntoWarehouse(resource, productionCost.get(resource));
                    doneStrongbox = personalBoard.takeResourcesFromStrongbox(resource, missing);
                    if (!doneStrongbox) {
                        return false;
                    }
                }
            }
            for (ResourceType resource : earnedResources.keySet()) {
                personalBoard.addResourceToStrongboxTemp(resource, earnedResources.get(resource));
            }
            return true;
        }
        return false;
    }


    /** the method printCards give a representation of developmentCards and their attributes */
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


    /**
     * buyCard method calls personalBoard.payDevelopmentCard method for each resource necessary to buy the card
     * @param personalBoard is the personal board where the card will be added
     * @return true if player can add that card
     */
    public boolean buyCard(PersonalBoard personalBoard){

            if( !personalBoard.payDevelopmentCard(cost) )
                return false;

        return true;
    }
}
