package it.polimi.ingsw.Controller.Messages;

import it.polimi.ingsw.LeaderCard;

public class MultiplayerCreationMessage extends Message{
    private String messageContent;
    private int playerNumber;
    private LeaderCard[] choosableLeaderCards;

    public String getMessageContent() {
        return messageContent;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    public LeaderCard[] getChoosableLeaderCards() {
        return choosableLeaderCards;
    }
}
