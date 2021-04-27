package it.polimi.ingsw;

import java.util.ArrayList;
/** Abstracted class that represent the four different types of LeaderCard */
public abstract class LeaderCard {

    /** Attribute that indicate if the card is active or not */
    protected boolean active = false;

    /** The victory points that the card give to the player */
    private int victoryPoints;

    /** The resource type associate to the card */
    ResourceType resourceType;

    /** The owner of the card */
    Player owner;

    /** The type of the card */
    protected String type;

    /**
     * Public method.
     * Set the card active
     * @return the status of the card
     * */
    public boolean setActive (){
        return true; }

    /**
     * Public method.
     * Activate the ability of the card
     * @return true when the card is activated
     */
    public boolean activateAbility(){
        return true; }

    /**
     * Public method.
     * That method chek if the card is active
     * @return the status of the attribute
     */
    public boolean isActive(){
        return active;
    }

    /**
     * Public method.
     * Set the owner of the card
     * @param owner indicate the player that take the card
     */
    public void setOwner(Player owner) {
        this.owner = owner;
    }

    /**
     * Public method.
     * The getter of the method
     * @return the victory points
     */
    public int getVictoryPoints() {
        return victoryPoints;
    }

    /**
     * Public method.
     * The getter of the type.
     * @return the type of the card.
     */
    public String getType() {
        return type;
    }
}

/**
 * This type of card has the follow ability:
 * When you buy a Development Card, you can pay its cost with a discount of
 * the indicated Resource (if the card you are buying has that Resource as a cost).
 */
class LeaderCardDiscount extends LeaderCard{
    /** The colour of the first card required to activate the ability */
    Colour singleColour1;
    /** The colour of the second card required to activate the ability */
    Colour singleColour2;

    /**
     * Public method.
     * @param singleColour1 the first requirement of the card
     * @param singleColour2 the second requirement of the card
     * @param resourceType the type of resource to which you'll have a discount
     */
    public LeaderCardDiscount(Colour singleColour1,Colour singleColour2,ResourceType resourceType) {
        this.resourceType = resourceType;
        this.singleColour1 = singleColour1;
        this.singleColour2 = singleColour2;
        this.type = "Discount";
    }

    /**
     * Public method overrode from LeaderCard.
     * Set the card active if possible.
     */
    @Override
    public boolean setActive(){
        if(owner.getCardColours().get(singleColour1)==null||owner.getCardColours().get(singleColour2)==null)
            return false;
        if(owner.getCardColours().get(singleColour1)<1||owner.getCardColours().get(singleColour2)<1||active)
            return false;
        active=true;
        return true;
    }
    /**
     * Public method overrode from LeaderCard.
     * Activate the ability of the card.
     */
    @Override
    public boolean activateAbility(){
        if( owner.getPersonalBoard().getDiscount1()==null ) {
            owner.getPersonalBoard().setDiscount1(resourceType);
            return true;
        }
        else if( owner.getPersonalBoard().getDiscount2()==null ) {
            owner.getPersonalBoard().setDiscount2(resourceType);
            return true;
        }
        else
            return false;
    }
}

/**
 * This type of card has the follow ability:
 * This ability gives you an extra special 2-slot depot. This special depot can
 * only store the indicated Resources. You can also store the same type of Resource in a basic Warehouse depot.
 */
class LeaderCardDeposit extends LeaderCard{
    /** The type of resource required to activate the ability */
    ResourceType resourceRequired;
    /** These attributes indicates if the extra deposit is created */
    boolean extraDeposit1 = false;
    boolean extraDeposit2 = false;


    /**
     * Public method.
     * @param resourceRequired the requirement of the card
     * @param resourceType the type of deposit that will be created
     */
    public LeaderCardDeposit(ResourceType resourceRequired, ResourceType resourceType) {
        this.resourceType = resourceType;
        this.resourceRequired = resourceRequired;
        this.type = "Deposit";
    }

    /**
     * Public method overrode from LeaderCard.
     * Set the card active if possible.
     */
    @Override
    public boolean setActive(){
        if(active)
            return false;
        int missing=5;
        if(owner.getPersonalBoard().getExtraDeposit1()!=null && owner.getPersonalBoard().getExtraDeposit1().getResourceType().equals(resourceType)){
            missing-= owner.getPersonalBoard().getExtraDeposit1().getCurrentQuantity();
        }
        else if(owner.getPersonalBoard().getExtraDeposit2()!=null && owner.getPersonalBoard().getExtraDeposit2().getResourceType().equals(resourceType)){
            missing-= owner.getPersonalBoard().getExtraDeposit1().getCurrentQuantity();
        }
        missing=owner.getPersonalBoard().missingResourcesIntoWarehouseWithoutRemove(resourceRequired,missing);
        if(owner.getPersonalBoard().checkResourcesIntoStrongbox(resourceRequired,missing)){
            active=true;
            return true;
        }
        return false;
    }
    /**
     * Public method overrode from LeaderCard.
     * Activate the ability of the card.
     */
    @Override
    public boolean activateAbility(){
        return owner.getPersonalBoard().createExtraDeposit(resourceType);
    }
}

/**
 * This type of card has the follow ability:
 * This ability gives you an additional production power. When you activate the
 * production, you can freely use this power as usual. You will receive a Resource of your choosing and 1 Faith Point.
 */
class LeaderCardProduction extends LeaderCard{
    /** The colour of the card required to activate the ability */
    Colour level2CardColour;

    /**
     * Public method.
     * @param level2CardColour the requirement of the card
     * @param resourceType the resource that want to produce
     */
    public LeaderCardProduction(Colour level2CardColour,ResourceType resourceType) {
        this.resourceType = resourceType;
        this.level2CardColour = level2CardColour;
        this.type = "Production";
    }

    /**
     * Public method overrode from LeaderCard.
     * Set the card active if possible.
     */
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
    /**
     * Public method overrode from LeaderCard.
     * Activate the ability of the card.
     */
    @Override
    public boolean activateAbility(){
        if( owner.getPersonalBoard().getRequirementForLeaderProduction1()==null ) {
            owner.getPersonalBoard().setRequirementForLeaderProduction1(resourceType);
            return true;
        }
        else if( owner.getPersonalBoard().getRequirementForLeaderProduction2()==null ) {
            owner.getPersonalBoard().setRequirementForLeaderProduction2(resourceType);
            return true;
        }
        else
            return false;
    }
}

/**
 * This type of card has the follow ability:
 * When you take Resources from the Market, each white Marble in the chosen column/row gives you the indicated Resource.
 * If you play two Leaders with this ability,when you take Resources from the Market, you must choose which Resource to take
 * (from those given by your Leaders) for each of the white Marbles (i.e. you can’t take both Resources from a single white Marble).
 */
class LeaderCardTransformation extends LeaderCard{

    /** The colour of the two cards required to active the card */
    Colour doubleCardColour;
    /** The colour of the card required to active the card, different from the above */
    Colour singleCardColour;

    /**
     * Public method.
     * @param doubleCardColour the first requirement of the card
     * @param singleCardColour the second requirement of the card
     * @param resourceType the resource that the card transform for the player
     */
    public LeaderCardTransformation(Colour doubleCardColour, Colour singleCardColour,ResourceType resourceType) {
        this.resourceType = resourceType;
        this.doubleCardColour = doubleCardColour;
        this.singleCardColour = singleCardColour;
        this.type = "Transformation";
    }

    /**
     * Public method overrode from LeaderCard.
     * Set the card active if possible.
     */
    @Override
    public boolean setActive(){
        if(owner.getCardColours().get(singleCardColour)==null || owner.getCardColours().get(singleCardColour)==null) return false;
        if(owner.getCardColours().get(singleCardColour)<1||owner.getCardColours().get(doubleCardColour)<2||active) return false;
        active=true;
        owner.increaseNumTrasformationAbility();
        return true;
    }

    /**
     * Public method overrode from LeaderCard.
     * Activate the ability of the card.
     */
    @Override
    public boolean activateAbility(){
        int whiteMarbles = owner.getGame().getMarketBoard().getWhiteMarblesSelected();
        if(whiteMarbles<=0) return false;
        if(owner.getNumTransformationAbility()==1){
            if (owner.getGame().getMarketBoard().getTemporaryResources().isEmpty() || !owner.getGame().getMarketBoard().getTemporaryResources()
                    .containsKey(this.resourceType)) {
                owner.getGame().getMarketBoard().getTemporaryResources().put(this.resourceType, whiteMarbles);
            }
            else {
                int oldval = owner.getGame().getMarketBoard().getTemporaryResources().get(this.resourceType);
                owner.getGame().getMarketBoard().getTemporaryResources().put(this.resourceType, oldval + whiteMarbles);
            }
            owner.getGame().getMarketBoard().setWhiteMarblesSelected(0);
            return true;
        }
        else {
            if (owner.getGame().getMarketBoard().getTemporaryResources().isEmpty() || !owner.getGame().getMarketBoard().getTemporaryResources()
                    .containsKey(this.resourceType)) {
                owner.getGame().getMarketBoard().getTemporaryResources().put(this.resourceType, 1);
            }
            else {
                int oldval = owner.getGame().getMarketBoard().getTemporaryResources().get(this.resourceType);
                owner.getGame().getMarketBoard().getTemporaryResources().put(this.resourceType, oldval + 1);
            }
            owner.getGame().getMarketBoard().setWhiteMarblesSelected(whiteMarbles - 1);
            return true;
        }
    }
}
