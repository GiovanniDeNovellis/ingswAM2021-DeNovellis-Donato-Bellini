package it.polimi.ingsw.Controller.Messages;

import it.polimi.ingsw.Colour;

public class BuyDevelopmentCardMessage extends Message{
    public void setLevel(int level) {
        this.level = level;
    }

    public void setColour(Colour colour) {
        this.colour = colour;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }

    public void setPayUsingExtraDeposit(int payUsingExtraDeposit1,int payUsingExtraDeposit2) {
        this.payUsingExtraDeposit[0] = payUsingExtraDeposit1;
        this.payUsingExtraDeposit[1] = payUsingExtraDeposit2;
    }

    private int level;
    private Colour colour;
    private int slot;
    private final int[] payUsingExtraDeposit = new int[2];

    public void setSenderNickname(String senderNickname) {
        this.senderNickname = senderNickname;
    }

    private String senderNickname;


    public String getSenderNickname() {
        return senderNickname;
    }
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
