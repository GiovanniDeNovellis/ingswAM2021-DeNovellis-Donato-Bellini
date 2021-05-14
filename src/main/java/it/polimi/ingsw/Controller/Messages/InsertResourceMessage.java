package it.polimi.ingsw.Controller.Messages;

import it.polimi.ingsw.ResourceType;

public class InsertResourceMessage extends Message {

    private int quantityToInsert;
    private boolean intoExtraDeposit;
    private ResourceType resourceToInsert;
    private String senderNickname;

    public void setResourceToInsert(ResourceType resourceToInsert) {
        this.resourceToInsert = resourceToInsert;
    }

    public void setIntoExtraDeposit(boolean intoExtraDeposit) {
        this.intoExtraDeposit = intoExtraDeposit;
    }

    public String getSenderNickname() {
        return senderNickname;
    }

    public void setSenderNickname(String senderNickname) {
        this.senderNickname = senderNickname;
    }

    public ResourceType getResourceToInsert() {
        return resourceToInsert;
    }

    public int getQuantityToInsert() {
        return quantityToInsert;
    }

    public boolean isIntoExtraDeposit() {
        return intoExtraDeposit;
    }

    public void setQuantityToInsert(int quantityToInsert) {
        this.quantityToInsert = quantityToInsert;
    }
}
