package it.polimi.ingsw;

/**
 * This class represents a level of the game's warehouse.
 * It contains only one type of resource and it's
 * capacity is fixed.
 */
public class Level {
    /**
     * The current resource type
     * contained int the level.
     */
    private ResourceType currentResourceType;
    /**
     * The maximum capacity of the level.
     */
    private final int maxDimension;
    /**
     * Current number of resources
     * stored in the level.
     */
    private int numResources;

    /**
     * Builds the level of the specified dimension.
     * @param maxDimension Level's dimension.
     */
    public Level(int maxDimension) {
        this.numResources=0;
        this.maxDimension=maxDimension;
        this.currentResourceType=null;
    }

    /**
     * Method used to add resources into the level. Returns
     * false if trying to add too many resources.
     * @param type Type of resource.
     * @param quantity Quantity of resource
     * @return True or false if the request is correct.
     */
    public boolean addResources(ResourceType type, int quantity){
        if(quantity>maxDimension) return false;
        this.currentResourceType=type;
        this.numResources=quantity;
        return true;
    }

    /**
     * Method use to remove resources from the level. Returns
     * false if trying to get too many resources or a
     * negative quantity.
     * @param quantity Quantity to get.
     * @return  True or false if the request is correct.
     */
    public boolean removeResources(int quantity){
        if(quantity>this.numResources || quantity <0) return false;
        this.numResources-=quantity;
        if(this.numResources==0) this.currentResourceType=null;
        return true;
    }

    /**
     * Returns the current number of resources
     * stored into the level.
     * @return Number of resources.
     */
    public int getCurrNumResources(){return this.numResources; }

    /**
     * Returns the maximum number of resources storable
     * into the level.
     * @return Max num of resources.
     */
    public int getMaxNumResources(){return this.maxDimension;}

    /** Returns the current type of resource
     * stored into the level.
     * @return Type of resource.
     */
    ResourceType getResourceType(){return this.currentResourceType;}
}
