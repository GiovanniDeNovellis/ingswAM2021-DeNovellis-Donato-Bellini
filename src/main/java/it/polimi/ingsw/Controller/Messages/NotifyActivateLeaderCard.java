package it.polimi.ingsw.Controller.Messages;

import it.polimi.ingsw.LeaderCard;

public class NotifyActivateLeaderCard extends Message{
    private int activatedLeaderCardPosition;
    private String whoActivatedLeaderCard;

    public String getWhoActivatedLeaderCard() {
        return whoActivatedLeaderCard;
    }

    public void setWhoActivatedLeaderCard(String whoActivatedLeaderCard) {
        this.whoActivatedLeaderCard = whoActivatedLeaderCard;
    }

    public int getActivatedLeaderCardPosition() {
        return activatedLeaderCardPosition;
    }

    public void setActivatedLeaderCardPosition(int activatedLeaderCardPosition) {
        this.activatedLeaderCardPosition = activatedLeaderCardPosition;
    }
}
