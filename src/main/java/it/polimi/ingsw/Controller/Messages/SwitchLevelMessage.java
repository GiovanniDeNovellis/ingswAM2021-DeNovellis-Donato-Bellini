package it.polimi.ingsw.Controller.Messages;

public class SwitchLevelMessage extends Message {
    private String senderNickname;
    private int[] levelsToSwitch;

    public String getSenderNickname() {
        return senderNickname;
    }

    public int[] getLevelsToSwitch() {
        return levelsToSwitch;
    }
}
