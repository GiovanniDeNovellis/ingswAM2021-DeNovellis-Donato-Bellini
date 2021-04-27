package it.polimi.ingsw.Controller.Messages;

import it.polimi.ingsw.ResourceType;

import java.util.HashMap;

public class ActivateLeaderAbilityTransformationMessage {
    private int position;
    private HashMap<ResourceType,Integer> temporaryResourcesConfiguration;
    private int remainingWhiteMarbles;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public HashMap<ResourceType, Integer> getTemporaryResourcesConfiguration() {
        return temporaryResourcesConfiguration;
    }

    public void setTemporaryResourcesConfiguration(HashMap<ResourceType, Integer> temporaryResourcesConfiguration) {
        this.temporaryResourcesConfiguration = temporaryResourcesConfiguration;
    }

    public int getRemainingWhiteMarbles() {
        return remainingWhiteMarbles;
    }

    public void setRemainingWhiteMarbles(int remainingWhiteMarbles) {
        this.remainingWhiteMarbles = remainingWhiteMarbles;
    }
}
