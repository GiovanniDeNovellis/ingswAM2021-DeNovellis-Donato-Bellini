package it.polimi.ingsw.View.LeaderCardsBuilder;

import it.polimi.ingsw.Colour;
import it.polimi.ingsw.ResourceType;

public class LeaderCardTransformation extends LeaderCards{
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

    @Override
    public Colour getDoubleCardColour() {
        return doubleCardColour;
    }

    @Override
    public Colour getSingleCardColour() {
        return singleCardColour;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public int getLeaderCardNumber() {
        return leaderCardNumber;
    }
}
