package it.polimi.ingsw.Controller.Messages;

import it.polimi.ingsw.ResourceType;

public class InsertResourceMessage extends Message {
    private ResourceType resourceToInsert;
    private int quantityToInsert;
    private boolean intoExtraDeposit;

    public ResourceType getResourceToInsert() {
        return resourceToInsert;
    }

    public int getQuantityToInsert() {
        return quantityToInsert;
    }

    public boolean isIntoExtraDeposit() {
        return intoExtraDeposit;
    }
}
