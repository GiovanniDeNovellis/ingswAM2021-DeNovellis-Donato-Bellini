package it.polimi.ingsw.Controller.Messages;

import it.polimi.ingsw.Colour;

public class BuyDevelopmentCardMessage extends Message{
    private int level;
    private Colour colour;
    private int slot;
    private int[] payUsingExtraDeposit;

    public int getLevel() {
        return level;
    }

    public Colour getColour() {
        return colour;
    }

    public int getSlot() {
        return slot;
    }

    public int[] getPayUsingExtraDeposit() {
        return payUsingExtraDeposit;
    }
}
