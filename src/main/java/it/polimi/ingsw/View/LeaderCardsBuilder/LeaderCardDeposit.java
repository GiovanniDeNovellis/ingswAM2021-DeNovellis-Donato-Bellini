package it.polimi.ingsw.View.LeaderCardsBuilder;
import it.polimi.ingsw.ResourceType;

public class LeaderCardDeposit {
    ResourceType resourceType;
    ResourceType resourceRequired;
    String type;
    int leaderCardNumber;
    public LeaderCardDeposit(ResourceType resourceRequired, ResourceType resourceType, int leaderCardNumber) {
        this.resourceType = resourceType;
        this.resourceRequired = resourceRequired;
        this.type = "Deposit";
        this.leaderCardNumber=leaderCardNumber;
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
}
