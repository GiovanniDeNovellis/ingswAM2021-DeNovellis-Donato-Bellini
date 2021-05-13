package it.polimi.ingsw.Controller.Messages;

public class SwitchLevelMessage extends Message {
    public void setSenderNickname(String senderNickname) {
        this.senderNickname = senderNickname;
    }

    public void setLevelsToSwitch(int[] levelsToSwitch) {
        this.levelsToSwitch = levelsToSwitch;
    }

    private String senderNickname;
    private int[] levelsToSwitch;

    public String getSenderNickname() {
        return senderNickname;
    }

    public int[] getLevelsToSwitch() {
        return levelsToSwitch;
    }
}
