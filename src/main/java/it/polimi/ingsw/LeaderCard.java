package it.polimi.ingsw;

public abstract class LeaderCard {
    protected boolean active = false;
    private int victoryPoints;
    ResourceType resourceType;
    Player owner;

    public boolean setActive (){
        return true; }

    public boolean activateAbility(){
        return true; }

    public boolean isActive(){
        return active;
    }

    public int getVictoryPoints() {
        return victoryPoints;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }
}

class LeaderCardDiscount extends LeaderCard{
    Colour singleColour1;
    Colour singleColour2;

    public LeaderCardDiscount(Colour singleColour1,Colour singleColour2,ResourceType resourceType) {
        this.resourceType = resourceType;
        this.singleColour1 = singleColour1;
        this.singleColour2 = singleColour2;
    }
    @Override
    public boolean setActive(){
        if(owner.getCardColours().get(singleColour1)==null||owner.getCardColours().get(singleColour2)==null)
            return false;
        if(owner.getCardColours().get(singleColour1)<1||owner.getCardColours().get(singleColour2)<1||active)
            return false;
        active=true;
        return true;
    }
    @Override
    public boolean activateAbility(){
        return true;
    }
}

class LeaderCardDeposit extends LeaderCard{
    ResourceType resourceRequired;

    public LeaderCardDeposit(ResourceType resourceRequired, ResourceType resourceType) {
        this.resourceType = resourceType;
        this.resourceRequired = resourceRequired;
    }

    @Override
    public boolean setActive(){
        if(active)
            return false;
        int missing;
        missing=owner.getPersonalBoard().missingResourcesIntoWarehouseWithoutRemove(resourceRequired,5);
        if(owner.getPersonalBoard().checkResourcesIntoStrongbox(resourceRequired,missing)){
            active=true;
            return true;
        }
        return false;
    }
    @Override
    public boolean activateAbility(){
        return true;
    }
}

class LeaderCardProduction extends LeaderCard{
    Colour level2CardColour;

    public LeaderCardProduction(Colour level2CardColour,ResourceType resourceType) {
        this.resourceType = resourceType;
        this.level2CardColour = level2CardColour;
    }

    @Override
    public boolean setActive(){
        for(DevelopmentCard d: owner.getInsertedDevCards()){
            if(d.getLevel()==2 && d.getColour().equals(level2CardColour)){
                active=true;
                return true;
            }
        }
        return false;
    }
    @Override
    public boolean activateAbility(){
        return true;
    }
}

class LeaderCardTransformation extends LeaderCard{
    Colour doubleCardColour;
    Colour singleCardColour;

    public LeaderCardTransformation(Colour doubleCardColour, Colour singleCardColour,ResourceType resourceType) {
        this.resourceType = resourceType;
        this.doubleCardColour = doubleCardColour;
        this.singleCardColour = singleCardColour;
    }

    @Override
    public boolean setActive(){
        if(owner.getCardColours().get(singleCardColour)==null || owner.getCardColours().get(singleCardColour)==null) return false;
        if(owner.getCardColours().get(singleCardColour)<1||owner.getCardColours().get(doubleCardColour)<2||active) return false;
        active=true;
        return true;
    }
    @Override
    public boolean activateAbility(){
        return true;
    }
}
