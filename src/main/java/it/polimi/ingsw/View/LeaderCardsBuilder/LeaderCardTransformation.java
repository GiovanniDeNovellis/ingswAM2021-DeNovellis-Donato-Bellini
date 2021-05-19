package it.polimi.ingsw.View.LeaderCardsBuilder;

import it.polimi.ingsw.Colour;
import it.polimi.ingsw.ResourceType;

public class LeaderCardTransformation {
    ResourceType resourceType;
    Colour doubleCardColour;
    Colour singleCardColour;
    String type;
    int leaderCardNumber;

    public LeaderCardTransformation(Colour doubleCardColour, Colour singleCardColour, ResourceType resourceType, int leaderCardNumber) {
        this.resourceType = resourceType;
        this.doubleCardColour = doubleCardColour;
        this.singleCardColour = singleCardColour;
        this.type = "Transformation";
        this.leaderCardNumber=leaderCardNumber;
    }

    public ResourceType getResourceType() {
        return resourceType;
    }

    public Colour getDoubleCardColour() {
        return doubleCardColour;
    }

    public Colour getSingleCardColour() {
        return singleCardColour;
    }

    public String getType() {
        return type;
    }

    public int getLeaderCardNumber() {
        return leaderCardNumber;
    }
}
