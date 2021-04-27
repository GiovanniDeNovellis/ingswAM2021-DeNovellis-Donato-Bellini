package it.polimi.ingsw.Controller.Messages;

public class EarnedFaithPointsMessage extends Message {
    private String messageContent;
    private int newFaithPoints;

    public String getMessageContent() {
        return messageContent;
    }

    public int getNewFaithPoints() {
        return newFaithPoints;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public void setNewFaithPoints(int newFaithPoints) {
        this.newFaithPoints = newFaithPoints;
    }
}
