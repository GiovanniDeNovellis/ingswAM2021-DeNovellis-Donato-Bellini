package it.polimi.ingsw.Controller.Messages;

import it.polimi.ingsw.ResourceType;

import java.util.HashMap;
import java.util.Map;

public class TemporaryResourcesChangedMessage extends Message{
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    private String nickname;

    private Map<ResourceType,Integer> temporaryResourcesConfiguration;

    public Map<ResourceType, Integer> getTemporaryResourcesConfiguration() {
        return temporaryResourcesConfiguration;
    }

    public void setTemporaryResourcesConfiguration(Map<ResourceType, Integer> temporaryResourcesConfiguration) {
        this.temporaryResourcesConfiguration = temporaryResourcesConfiguration;
    }
}
