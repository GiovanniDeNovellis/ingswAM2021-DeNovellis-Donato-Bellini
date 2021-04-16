package it.polimi.ingsw.MarbleTypes;

import it.polimi.ingsw.MarketBoard;
import it.polimi.ingsw.ResourceType;
import it.polimi.ingsw.Selectable;

/** This type of marble gives 1 storable resource. */
public class WareHouseMarbleType implements Selectable {

    /**
     * The type of resource given by the marble.
     * Can be: SERVANT, SHIELD, STONE OR COIN
     */
    private ResourceType resourceType;

    /**
     * Public method.
     * Builds a marble with the given ResourceType.
     * @param resourceType The resource to associate to the marble.
     */
    public WareHouseMarbleType(ResourceType resourceType) {
        this.resourceType = resourceType;
    }

    /**
     * Public method.
     * @return The ResourceType associated to the marble.
     */
    public ResourceType getResourceType() {
        return resourceType;
    }

    /**
     * Public method overrode from Selectable.
     * Adds the resource to the TemporaryResources of the MarketBoard.
     * @param marketBoard The marketBoard where all the marbles are stored.
     */
    @Override
    public void onSelection(MarketBoard marketBoard) {
        if(marketBoard.getTemporaryResources().get(resourceType)==null){
            marketBoard.getTemporaryResources().put(resourceType,1);
            return;
        }
        int quantity = marketBoard.getTemporaryResources().get(resourceType);
        marketBoard.getTemporaryResources().put(resourceType,quantity+1);
    }
}