package it.polimi.ingsw.View.LeaderCardsBuilder;

import it.polimi.ingsw.Colour;
import it.polimi.ingsw.ResourceType;

public abstract class LeaderCards {

    public abstract int getVictoryPoints();

    public abstract ResourceType getResourceType();

    public abstract ResourceType getResourceRequired();

    public abstract String getType();

    public abstract Colour getSingleColour1();

    public abstract Colour getSingleColour2();

    public abstract String getLevel2CardColour();

    public abstract Colour getDoubleCardColour();

    public abstract Colour getSingleCardColour();
}
