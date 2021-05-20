package it.polimi.ingsw.View.LeaderCardsBuilder;

import it.polimi.ingsw.Colour;
import it.polimi.ingsw.ResourceType;

public class LeaderCards {
    ResourceType resourceType;
    ResourceType resourceRequired;
    String type;
    int leaderCardNumber;
    Colour singleColour1;
    Colour singleColour2;
    Colour level2CardColour;
    Colour doubleCardColour;
    Colour singleCardColour;
    int victoryPoints;

    public int getVictoryPoints() {
        return victoryPoints;
    }

    public ResourceType getResourceType() {
        return resourceType;
    }

    public ResourceType getResourceRequired() {
        return resourceRequired;
    }

    public String getType() {
        return type;
    }

    public int getLeaderCardNumber() {
        return leaderCardNumber;
    }

    public Colour getSingleColour1() {
        return singleColour1;
    }

    public Colour getSingleColour2() {
        return singleColour2;
    }

    public Colour getLevel2CardColour() {
        return level2CardColour;
    }

    public Colour getDoubleCardColour() {
        return doubleCardColour;
    }

    public Colour getSingleCardColour() {
        return singleCardColour;
    }
}
