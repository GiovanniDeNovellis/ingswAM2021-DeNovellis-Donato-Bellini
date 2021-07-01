package it.polimi.ingsw.View.LeaderCardsBuilder;

import it.polimi.ingsw.Colour;
import it.polimi.ingsw.ResourceType;

/**
 * Used to print a transformation leader card
 */
public class LeaderCardTransformation extends LeaderCards{
    ResourceType resourceType;
    Colour doubleCardColour;
    Colour singleCardColour;
    String type;
    int leaderCardNumber;
    int victoryPoints;

    public LeaderCardTransformation(Colour doubleCardColour, Colour singleCardColour, ResourceType resourceType, int leaderCardNumber, int victoryPoints) {
        this.resourceType = resourceType;
        this.doubleCardColour = doubleCardColour;
        this.singleCardColour = singleCardColour;
        this.type = "Transformation";
        this.leaderCardNumber=leaderCardNumber;
        this.victoryPoints = victoryPoints;
    }

    @Override
    public int getVictoryPoints() {
        return victoryPoints;
    }

    @Override
    public ResourceType getResourceType() {
        return resourceType;
    }

    @Override
    public ResourceType getResourceRequired() {
        return null;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public Colour getSingleColour1() {
        return null;
    }

    @Override
    public Colour getSingleColour2() {
        return null;
    }

    @Override
    public String getLevel2CardColour() {
        return null;
    }

    @Override
    public Colour getDoubleCardColour() {
        return doubleCardColour;
    }

    @Override
    public Colour getSingleCardColour() {
        return singleCardColour;
    }
}
