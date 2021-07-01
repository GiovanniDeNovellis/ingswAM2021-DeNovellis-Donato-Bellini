package it.polimi.ingsw.Controller.Messages;

import it.polimi.ingsw.ResourceType;

import java.util.HashMap;
import java.util.Map;

/**
 * Message used to communicate the changement of the temporary resources
 */
public class TemporaryResourcesChangedMessage extends Message{

    private String senderNickname;
    private Map<ResourceType,Integer> temporaryResourcesConfiguration;

    public String getNickname() {
        return senderNickname;
    }

    public void setNickname(String senderNickname) {
        this.senderNickname = senderNickname;
    }

    public Map<ResourceType, Integer> getTemporaryResourcesConfiguration() {
        return temporaryResourcesConfiguration;
    }

    public void setTemporaryResourcesConfiguration(Map<ResourceType, Integer> temporaryResourcesConfiguration) {
        this.temporaryResourcesConfiguration = temporaryResourcesConfiguration;
    }

}
