package it.polimi.ingsw;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Strongbox {


    /** The TreeMap resourceContained is used to keep in memory number and type of resources contained in the strongbox */
    private final Map<ResourceType, Integer> resourcesContained = new HashMap<>();
    //for testing...
    //private final TreeMap<ResourceType, Integer> resourcesContained = new TreeMap<>();
    //private String printTest;
    /** attribute victoryPoints is referred to victory points obtained throw resources in the strongbox, when the game ends.*/
    private int victoryPoints;
    /** attribute numOf is used to return the number of a specific resource contained in the strongbox.*/
    private int numOf;

    public Strongbox(){
        resourcesContained.put(ResourceType.SHIELDS,0);
        resourcesContained.put(ResourceType.COINS,0);
        resourcesContained.put(ResourceType.SERVANTS,0);
        resourcesContained.put(ResourceType.STONES,0);
        victoryPoints = 0;
        numOf = 0;
    }

    /** addResources method allows the player to add a certain quantity of one type of resource into the strongbox. */
    public void addResources(ResourceType resource, int quantity){
        if(quantity<0)
            quantity=0;
        if( resourcesContained.get(resource)!= null && resource != ResourceType.FAITHPOINTS)
            quantity += resourcesContained.get(resource);
        if( resource != ResourceType.FAITHPOINTS )
        resourcesContained.put(resource, quantity);
    }

    /** viewAllResources method allows the player to see the totality of resources (number and type of resources)
     *  contained in its own strongbox. */
    public void viewAllResources() {
        for (ResourceType resource : resourcesContained.keySet()) {
            System.out.println("Risorsa: " + resource + " ,Quantità: " + resourcesContained.get(resource));
        }
    }
            //for testing...
    /*
    public String viewAllResources(){
        for (ResourceType resource : resourcesContained.keySet()) {
            printTest = printTest + ("Risorsa: " + resource + " ,Quantità: " + resourcesContained.get(resource));
        }
        return printTest;
    } */

    /** getVictoryPoints method is called when game ends. This method calculate the total amount of victory points
     *  earned in base of the total number of resources contained in the strongbox. */
    public int getVictoryPoints(){
        for (ResourceType resource : resourcesContained.keySet()) {
            victoryPoints += resourcesContained.get(resource);
        }
    return victoryPoints;
    }

    /** removeResources method allows to remove a specific quantity of a one type resource contained in the strongbox.
     * The player can't remove more resources than resources contained in the strongbox. */
    public boolean removeResources(ResourceType resource, int quantity){
        if(quantity<0)
            quantity=0;
        quantity = -quantity;
        if( resourcesContained.get(resource)!= null )
            quantity += resourcesContained.get(resource);
        else
            return false;
        if( quantity>=0 ) {
            resourcesContained.put(resource, quantity);
            return true;
        }
        else
            return false;
    }

    /** getNumOf allows the player to know the number of a specific resource contained in the strongbox. */
    public int getNumOf( ResourceType resource ){

        if( resourcesContained.get(resource) != null ) {
            numOf = resourcesContained.get(resource);
            return numOf;
        }
        else
            return 0;
    }

    public Map<ResourceType, Integer> getResourcesContained() {
        return resourcesContained;
    }
}
