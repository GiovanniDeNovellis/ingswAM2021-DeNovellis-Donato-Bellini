package it.polimi.ingsw.Controller.Messages;

/**
 * Message used to communicate the selection of a leader card
 */
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

    public void setSenderNickname(String senderNickname) {
        this.senderNickname = senderNickname;
    }

    public void setLeaderCardPosition1(int leaderCardPosition1) {
        this.leaderCardPosition1 = leaderCardPosition1;
    }

    public void setLeaderCardPosition2(int leaderCardPosition2) {
        this.leaderCardPosition2 = leaderCardPosition2;
    }
}
