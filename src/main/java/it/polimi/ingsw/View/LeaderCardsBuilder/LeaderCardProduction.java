package it.polimi.ingsw.View.LeaderCardsBuilder;

import it.polimi.ingsw.Colour;
import it.polimi.ingsw.ResourceType;

public class LeaderCardProduction extends LeaderCards {

    ResourceType resourceType;
    Colour level2CardColour;
    String type;
    int leaderCardNumber;

    @Override
    public int getVictoryPoints() {
        return victoryPoints;
    }

    int victoryPoints;

    public LeaderCardProduction(Colour level2CardColour, ResourceType resourceType, int leaderCardNumber, int victoryPoints) {
        this.resourceType = resourceType;
        this.level2CardColour = level2CardColour;
        this.type = "Production";
        this.leaderCardNumber = leaderCardNumber;
        this.victoryPoints = victoryPoints;
    }

    @Override
    public ResourceType getResourceType() {
        return resourceType;
    }

    @Override
    public Colour getLevel2CardColour() {
        return level2CardColour;
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
