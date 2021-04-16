package it.polimi.ingsw;

/**
 * Represent the extra deposit create with the power of the LeaderCard
 */
public class ExtraDeposit {

    /** Indicate the type of slot of the ExtraDeposit */
    private ResourceType resourceType;

    /**Initialize the quantity into the deposit to 0 */
    private int currentQuantity = 0;

    /** Set the max quantity of resource in the deposit to 2*/
    private final int MAXQUANTITY = 2;

    /**
     *Public method.
     * Constructor of ExtraDeposit.
     * @param resourceType indicate the type of resource that the leader card contain.
     */
    public ExtraDeposit (ResourceType resourceType){
        this.resourceType = resourceType;
    }

    /**
     * Public method.
     * Getter of the ResourceType
     * @return the ResourceType
     */
    public ResourceType getResourceType() {
        return resourceType;
    }

    /**
     * Public method.
     * Getter of the CurrentQuantity
     * @return the currentQuantity of resource types
     */
    public int getCurrentQuantity() {
        return currentQuantity;
    }

    /**
     * Public method.
     * Add resource into the ExtraDeposit if possible.
     * @param resourceType indicates the type of resource
     * @param quantity indicates the quantity of resource to add
     * @return false if the action cannot be perform
     */
    public boolean addResource (ResourceType resourceType,int quantity){
        if (this.resourceType != resourceType || quantity >MAXQUANTITY-currentQuantity || quantity<0 ){
            return false;
        }
        currentQuantity += quantity;
        return true;
    }

    /**
     * Public method.
     * Add resource into the ExtraDeposit if possible.
     * @param quantityToRemove indicates the quantity of resource to remove
     * @return false if the action cannot be perform
     */
    public boolean removeResources( int quantityToRemove ){
        if( currentQuantity-quantityToRemove<0 )
            return false;
        else{
            currentQuantity-=quantityToRemove;
            return true;
        }
    }
}