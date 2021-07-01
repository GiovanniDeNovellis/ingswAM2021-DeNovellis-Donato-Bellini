package it.polimi.ingsw.Controller.Messages;

import it.polimi.ingsw.ResourceType;

import java.util.Map;

/**
 * Message used to communicate the activation of a leader ability transformation
 */
public class ActivateLeaderAbilityTransformationMessage extends Message{
    private int position;
    private Map<ResourceType,Integer> temporaryResourcesConfiguration;
    private int remainingWhiteMarbles;

    public String getSenderNickname() {
        return senderNickname;
    }

    public void setSenderNickname(String senderNickname) {
        this.senderNickname = senderNickname;
    }

    private String senderNickname;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public Map<ResourceType, Integer> getTemporaryResourcesConfiguration() {
        return temporaryResourcesConfiguration;
    }

    public void setTemporaryResourcesConfiguration(Map<ResourceType, Integer> temporaryResourcesConfiguration) {
        this.temporaryResourcesConfiguration = temporaryResourcesConfiguration;
    }

    public int getRemainingWhiteMarbles() {
        return remainingWhiteMarbles;
    }

    public void setRemainingWhiteMarbles(int remainingWhiteMarbles) {
        this.remainingWhiteMarbles = remainingWhiteMarbles;
    }
}
