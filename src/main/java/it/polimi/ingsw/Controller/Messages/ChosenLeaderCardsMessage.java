package it.polimi.ingsw.Controller.Messages;

import it.polimi.ingsw.LeaderCard;

import java.util.ArrayList;

public class ChosenLeaderCardsMessage extends Message{
    private String messageContent;
    private int firstChosenLeaderCardNumber;
    private int secondChosenLeaderCardNumber;
    private String senderNickname;

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public String getSenderNickname() {
        return senderNickname;
    }

    public void setSenderNickname(String senderNickname) {
        this.senderNickname = senderNickname;
    }

    public int getFirstChosenLeaderCardNumber() {
        return firstChosenLeaderCardNumber;
    }

    public void setFirstChosenLeaderCardNumber(int firstChosenLeaderCardNumber) {
        this.firstChosenLeaderCardNumber = firstChosenLeaderCardNumber;
    }

    public int getSecondChosenLeaderCardNumber() {
        return secondChosenLeaderCardNumber;
    }

    public void setSecondChosenLeaderCardNumber(int secondChosenLeaderCardNumber) {
        this.secondChosenLeaderCardNumber = secondChosenLeaderCardNumber;
    }
}
