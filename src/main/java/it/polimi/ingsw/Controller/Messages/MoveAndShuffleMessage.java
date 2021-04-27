package it.polimi.ingsw.Controller.Messages;

import it.polimi.ingsw.ActionCardStack;

public class MoveAndShuffleMessage extends Message{
    private int newBlackFaithPoints;
    private ActionCardStack actionCardConfiguration;

    public int getNewBlackFaithPoints() {
        return newBlackFaithPoints;
    }

    public void setNewBlackFaithPoints(int newBlackFaithPoints) {
        this.newBlackFaithPoints = newBlackFaithPoints;
    }

    public ActionCardStack getActionCardConfiguration() {
        return actionCardConfiguration;
    }

    public void setActionCardConfiguration(ActionCardStack actionCardConfiguration) {
        this.actionCardConfiguration = actionCardConfiguration;
    }
}
