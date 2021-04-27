package it.polimi.ingsw.Controller.Messages;

public class LeaderCardSelectionMessage extends Message {
    private String senderNickname;
    private int leaderCardPosition1;
    private int leaderCardPosition2;

    public String getSenderNickname() {
        return senderNickname;
    }

    public int getLeaderCardPosition1() {
        return leaderCardPosition1;
    }

    public int getLeaderCardPosition2() {
        return leaderCardPosition2;
    }
}
