package it.polimi.ingsw.Controller.Messages;

import it.polimi.ingsw.ResourceType;

import java.util.HashMap;

public class TemporaryResourcesChangedMessage extends Message{
    private HashMap<ResourceType,Integer> temporaryResourcesConfiguration;

    public HashMap<ResourceType, Integer> getTemporaryResourcesConfiguration() {
        return temporaryResourcesConfiguration;
    }

    public void setTemporaryResourcesConfiguration(HashMap<ResourceType, Integer> temporaryResourcesConfiguration) {
        this.temporaryResourcesConfiguration = temporaryResourcesConfiguration;
    }
}
