package it.polimi.ingsw.Controller.Messages;

public class NotifyDiscardLeaderCard extends Message{
    private int discardedPosition;
    private String whoDiscardedLeaderCard;

    public int getDiscardedPosition() {
        return discardedPosition;
    }

    public void setDiscardedPosition(int discardedPosition) {
        this.discardedPosition = discardedPosition;
    }

    public String getWhoDiscardedLeaderCard() {
        return whoDiscardedLeaderCard;
    }

    public void setWhoDiscardedLeaderCard(String whoDiscardedLeaderCard) {
        this.whoDiscardedLeaderCard = whoDiscardedLeaderCard;
    }
}
