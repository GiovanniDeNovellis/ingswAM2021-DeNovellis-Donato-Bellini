package it.polimi.ingsw.Controller.Messages;

import it.polimi.ingsw.ResourceType;

/**
 * Message used to communicate the activation of a production
 */
public class ActivateProductionMessage extends Message{
    private String senderNickname;
    private boolean[] whichDevCardSlot;
    private boolean fromPersonalBoard;
    private boolean[] whichLeaderCard;
    private ResourceType[] resourceBaseProduction;
    private ResourceType[] resourceFromLeader;
    private int[] payUsingExtraDeposit;

    public String getSenderNickname() {
        return senderNickname;
    }

    public boolean[] getWhichDevCardSlot() {
        return whichDevCardSlot;
    }

    public boolean isFromPersonalBoard() {
        return fromPersonalBoard;
    }

    public boolean[] getWhichLeaderCard() {
        return whichLeaderCard;
    }

    public ResourceType[] getResourceBaseProduction() {
        return resourceBaseProduction;
    }

    public ResourceType[] getResourceFromLeader() {
        return resourceFromLeader;
    }

    public int[] getPayUsingExtraDeposit() {
        return payUsingExtraDeposit;
    }

    public void setSenderNickname(String senderNickname) {
        this.senderNickname = senderNickname;
    }

    public void setWhichDevCardSlot(boolean[] whichDevCardSlot) {
        this.whichDevCardSlot = whichDevCardSlot;
    }

    public void setFromPersonalBoard(boolean fromPersonalBoard) {
        this.fromPersonalBoard = fromPersonalBoard;
    }

    public void setResourceBaseProduction(ResourceType[] resourceBaseProduction) {
        this.resourceBaseProduction = resourceBaseProduction;
    }

    public void setWhichLeaderCard(boolean[] whichLeaderCard) {
        this.whichLeaderCard = whichLeaderCard;
    }

    public void setResourceFromLeader(ResourceType[] resourceFromLeader) {
        this.resourceFromLeader = resourceFromLeader;
    }

    public void setPayUsingExtraDeposit(int[] payUsingExtraDeposit) {
        this.payUsingExtraDeposit = payUsingExtraDeposit;
    }
}
