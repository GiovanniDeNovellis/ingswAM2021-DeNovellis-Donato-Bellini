package it.polimi.ingsw;

import java.util.TreeMap;

/** The strongbox is the resource's container of the players. It has free slot to fill with different type of resources */
public class Strongbox {

    /** The TreeMap resourceContained is used to keep in memory number and type of resources contained in the strongbox */
    private TreeMap<ResourceType, Integer> resourcesContained = new TreeMap<>();

    //for testing...
    // private String printTest;
    /** attribute victoryPoints is referred to victory points obtained throw resources in the strongbox, when the game ends.*/
    private int victoryPoints;

    /** attribute numOf is used to return the number of a specific resource contained in the strongbox.*/
    private int numOf;

    /** Public method.
     * Constructor of Strongbox
     */
    public Strongbox(){
        victoryPoints = 0;
        numOf = 0;
    }

    /**
     * Public method.
     * Allows the player to add a certain quantity of one type of resource into the strongbox.
     * @param resource indicates the resource type to add
     * @param quantity indicates the quantity to add
     */
    public void addResources(ResourceType resource, int quantity){
        if(quantity<0)
            quantity=0;
        if( resourcesContained.get(resource)!= null && resource != ResourceType.FAITHPOINTS)
            quantity += resourcesContained.get(resource);
        if( resource != ResourceType.FAITHPOINTS )
        resourcesContained.put(resource, quantity);
    }

    /**
     * Public method.
     * viewAllResources method allows the player to see the totality of resources (number and type of resources)
     *  contained in its own strongbox. */
    public void viewAllResources(){
        for (ResourceType resource : resourcesContained.keySet()) {
            System.out.println("Risorsa: " + resource + " ,Quantità: " + resourcesContained.get(resource));
        }

         /*   //for testing...
    public String viewAllResources(){
        for (ResourceType resource : resourcesContained.keySet()) {
            printTest = printTest + ("Risorsa: " + resource + " ,Quantità: " + resourcesContained.get(resource));
        }
        return printTest;
    }
        */
    }

    /**
     * Public method.
     * getVictoryPoints method is called when game ends. This method calculate the total amount of victory points
     * earned in base of the total number of resources contained in the strongbox.
     * @return the number of victoryPoints earned
     */
    public int getVictoryPoints(){
        for (ResourceType resource : resourcesContained.keySet()) {
            victoryPoints += resourcesContained.get(resource);
        }
    return victoryPoints;
    }


    /** Public method.
     * Allows to remove a specific quantity of a one type resource contained in the strongbox.
     * The player can't remove more resources than resources contained in the strongbox.
     * @param resource  indicates the resource type to remove
     * @param quantity indicates how many resource to remove
     * @return true if the remove is done or false if the resource can be done
     */
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

    /**
     * Public method.
     * getNumOf allows the player to know the number of a specific resource contained in the strongbox.
     * @param resource indicate the ResourceType
     */
    public int getNumOf( ResourceType resource ){

        if( resourcesContained.get(resource) != null ) {
            numOf = resourcesContained.get(resource);
            return numOf;
        }
        else
            return 0;
    }
}
