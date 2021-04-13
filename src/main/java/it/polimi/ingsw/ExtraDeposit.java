package it.polimi.ingsw;

public class ExtraDeposit {
    private ResourceType resourceType;
    private int currentQuantity = 0;
    private final int MAXQUANTITY = 2;

    public ExtraDeposit (ResourceType resourceType){
        this.resourceType = resourceType;
    }
    public ResourceType getResourceType() {
        return resourceType;
    }

    public int getCurrentQuantity() {
        return currentQuantity;
    }

    public boolean addResource (ResourceType resourceType,int quantity){
        if (this.resourceType != resourceType || quantity >MAXQUANTITY-currentQuantity || quantity<0 ){
            return false;
        }
        currentQuantity += quantity;
        return true;
    }

    public boolean removeResources( int quantityToRemove ){
        if( currentQuantity-quantityToRemove<0 )
            return false;
        else{
            currentQuantity-=quantityToRemove;
            return true;
        }
    }
}
