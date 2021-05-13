package it.polimi.ingsw.Controller.Messages;

import it.polimi.ingsw.ResourceType;

public class InsertResourceMessage extends Message {
    public void setResourceToInsert(ResourceType resourceToInsert) {
        this.resourceToInsert = resourceToInsert;
    }

    public void setIntoExtraDeposit(boolean intoExtraDeposit) {
        this.intoExtraDeposit = intoExtraDeposit;
    }

    private ResourceType resourceToInsert;

    public void setQuantityToInsert(int quantityToInsert) {
        this.quantityToInsert = quantityToInsert;
    }

    private int quantityToInsert;
    private boolean intoExtraDeposit;
    public String getSenderNickname() {
        return senderNickname;
    }

    public void setSenderNickname(String senderNickname) {
        this.senderNickname = senderNickname;
    }

    private String senderNickname;

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
