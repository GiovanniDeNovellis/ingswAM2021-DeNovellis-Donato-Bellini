package it.polimi.ingsw.Controller.Messages;

import it.polimi.ingsw.ResourceType;

public class DistributionFourthMessage extends Message{
    private String senderNickname;
    private ResourceType resourceToDistribute;
    private ResourceType secondResourceToDistribute;

    public String getSenderNickname() {
        return senderNickname;
    }

    public ResourceType getResourceToDistribute() {
        return resourceToDistribute;
    }

    public ResourceType getSecondResourceToDistribute() {
        return secondResourceToDistribute;
    }
}
