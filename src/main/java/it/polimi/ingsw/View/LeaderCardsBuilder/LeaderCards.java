package it.polimi.ingsw.View.LeaderCardsBuilder;

import it.polimi.ingsw.Colour;
import it.polimi.ingsw.ResourceType;

public abstract class LeaderCards {
    /*protected ResourceType resourceType;
    protected ResourceType resourceRequired;
    protected String type;
    protected int leaderCardNumber;
    protected Colour singleColour1;
    protected Colour singleColour2;
    protected String level2CardColour;
    protected Colour doubleCardColour;
    protected Colour singleCardColour;
    protected int victoryPoints;*/

    public abstract int getVictoryPoints();

    public abstract ResourceType getResourceType();

    public abstract ResourceType getResourceRequired();

    public abstract String getType();

    public abstract int getLeaderCardNumber();
    public abstract Colour getSingleColour1();

    public abstract Colour getSingleColour2();
    public abstract String getLevel2CardColour();

    public abstract Colour getDoubleCardColour();

    public abstract Colour getSingleCardColour();
}
