package it.polimi.ingsw.Controller.Messages;

import it.polimi.ingsw.LeaderCard;

/**
 * Message used to communicate the activation of a leader card
 */
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
