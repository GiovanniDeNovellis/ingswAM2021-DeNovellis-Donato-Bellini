package it.polimi.ingsw.Controller.Messages;

import it.polimi.ingsw.LeaderCard;

public class NotifyActivateLeaderCard extends Message{
    private int activatedLeaderCardPosition;

    public int getActivatedLeaderCardPosition() {
        return activatedLeaderCardPosition;
    }

    public void setActivatedLeaderCardPosition(int activatedLeaderCardPosition) {
        this.activatedLeaderCardPosition = activatedLeaderCardPosition;
    }
}
