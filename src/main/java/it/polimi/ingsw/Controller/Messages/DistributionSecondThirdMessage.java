package it.polimi.ingsw.Controller.Messages;

import it.polimi.ingsw.ResourceType;

public class DistributionSecondThirdMessage extends Message {
    private String senderNickname;
    private ResourceType resourceToDistribute;

    public String getSenderNickname() {
        return senderNickname;
    }

    public ResourceType getResourceToDistribute() {
        return resourceToDistribute;
    }
}
