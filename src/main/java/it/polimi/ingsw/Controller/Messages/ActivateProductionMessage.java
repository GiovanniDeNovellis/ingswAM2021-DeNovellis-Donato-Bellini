package it.polimi.ingsw.Controller.Messages;

import it.polimi.ingsw.ResourceType;

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
}
