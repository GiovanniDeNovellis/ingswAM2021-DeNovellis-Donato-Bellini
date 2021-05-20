package it.polimi.ingsw.View.LeaderCardsBuilder;
import it.polimi.ingsw.Colour;
import it.polimi.ingsw.ResourceType;

public class LeaderCardDeposit extends LeaderCards{
    ResourceType resourceType;
    ResourceType resourceRequired;
    String type;
    int leaderCardNumber;
    int victoryPoints;


    public LeaderCardDeposit(ResourceType resourceRequired, ResourceType resourceType, int leaderCardNumber, int victoryPoints) {
        this.resourceType = resourceType;
        this.resourceRequired = resourceRequired;
        this.type = "Deposit";
        this.leaderCardNumber=leaderCardNumber;
        this.victoryPoints=victoryPoints;
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
        return resourceRequired;
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
        return null;
    }

    @Override
    public Colour getDoubleCardColour() {
        return null;
    }

    @Override
    public Colour getSingleCardColour() {
        return null;
    }
    /*

    @Override
    public ResourceType getResourceType() {
        return resourceType;
    }


    @Override
    public ResourceType getResourceRequired() {
        return resourceRequired;
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
    }*/
}
