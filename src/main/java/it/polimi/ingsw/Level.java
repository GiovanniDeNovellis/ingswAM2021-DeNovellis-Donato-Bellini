package it.polimi.ingsw;

public class Level {
    private ResourceType currentResourceType;
    private int maxDimension;
    private int numResources;

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
    boolean addResources(ResourceType type, int quantity){
        if(quantity>maxDimension ) return false;
        this.currentResourceType=type;
        this.numResources=quantity;
        //TODO add points to the other players if discarding resources
        return true;
    }

    /**
     * Method use to remove resources from the level. Returns
     * false if trying to get too many resources or a
     * negative quantity.
     * @param quantity Quantity to get.
     * @return  True or false if the request is correct.
     */
    boolean getResources(int quantity){
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
    int getCurrNumResources(){return this.numResources; }

    /**
     * Returns the maximum number of resources storable
     * into the level.
     * @return Max num of resources.
     */
    int getMaxNumResources(){return this.maxDimension;}

    /** Returns the current type of resource
     * stored into the level.
     * @return Type of resource.
     */
    ResourceType getResourceType(){return this.currentResourceType;}
}
