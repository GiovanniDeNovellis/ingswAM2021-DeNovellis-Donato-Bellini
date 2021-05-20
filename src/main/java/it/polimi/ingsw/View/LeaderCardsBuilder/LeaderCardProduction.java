package it.polimi.ingsw.View.LeaderCardsBuilder;

import it.polimi.ingsw.Colour;
import it.polimi.ingsw.ResourceType;

public class LeaderCardProduction extends LeaderCards {

    ResourceType resourceType;
    String level2CardColour;
    String type;
    int leaderCardNumber;
    int victoryPoints;


    public LeaderCardProduction(String level2CardColour, ResourceType resourceType, int leaderCardNumber, int victoryPoints) {
        this.level2CardColour = level2CardColour;
        this.resourceType = resourceType;
        this.leaderCardNumber = leaderCardNumber;
        this.victoryPoints = victoryPoints;
        this.type = "Production";
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
    public int getLeaderCardNumber() {
        return leaderCardNumber;
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
        return level2CardColour;
    }

    @Override
    public Colour getDoubleCardColour() {
        return null;
    }

    @Override
    public Colour getSingleCardColour() {
        return null;
    }

    /*@Override
    public ResourceType getResourceType() {
        return resourceType;
    }

    @Override
    public String getLevel2CardColour() {
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

    @Override
    public int getVictoryPoints() {
        return victoryPoints;
    }
    */

}
