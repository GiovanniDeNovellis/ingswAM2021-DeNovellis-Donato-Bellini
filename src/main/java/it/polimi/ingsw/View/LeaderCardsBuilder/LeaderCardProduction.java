package it.polimi.ingsw.View.LeaderCardsBuilder;

import it.polimi.ingsw.Colour;
import it.polimi.ingsw.ResourceType;

public class LeaderCardProduction {

    ResourceType resourceType;
    Colour level2CardColour;
    String type;
    int leaderCardNumber;

    public LeaderCardProduction(Colour level2CardColour, ResourceType resourceType, int leaderCardNumber) {
        this.resourceType = resourceType;
        this.level2CardColour = level2CardColour;
        this.type = "Production";
        this.leaderCardNumber=leaderCardNumber;
    }

    public ResourceType getResourceType() {
        return resourceType;
    }

    public Colour getLevel2CardColour() {
        return level2CardColour;
    }

    public String getType() {
        return type;
    }

    public int getLeaderCardNumber() {
        return leaderCardNumber;
    }
}
