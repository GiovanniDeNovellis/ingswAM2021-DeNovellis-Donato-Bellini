package it.polimi.ingsw.Controller.Messages;

import it.polimi.ingsw.LeaderCard;

public class ChoosedLeaderCardsMessage extends Message{
    private String messageContent;
    private LeaderCard[] chosenLeaderCards;

    public String getMessageContent() {
        return messageContent;
    }

    public LeaderCard[] getChosenLeaderCards() {
        return chosenLeaderCards;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public void setChosenLeaderCards(LeaderCard[] chosenLeaderCards) {
        this.chosenLeaderCards = chosenLeaderCards;
    }
}
