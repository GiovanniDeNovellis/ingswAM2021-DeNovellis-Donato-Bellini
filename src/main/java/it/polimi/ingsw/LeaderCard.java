package it.polimi.ingsw;

public abstract class LeaderCard {
    private boolean isActive = false;
    private int victoryPoints;
    ResourceType resourceType;

    public boolean setActive (){ return true; }
    public boolean activateAbility(){ return true; }
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
        return true;
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
        return true;
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
        return true;
    }
    @Override
    public boolean activateAbility(){
        return true;
    }
}
