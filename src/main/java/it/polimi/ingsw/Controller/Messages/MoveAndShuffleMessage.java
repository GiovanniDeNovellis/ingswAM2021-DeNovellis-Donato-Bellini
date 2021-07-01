package it.polimi.ingsw.Controller.Messages;

import it.polimi.ingsw.ActionCard;
import it.polimi.ingsw.ActionCardStack;

import java.util.ArrayList;

/**
 * Message used to communicate the move and shuffle action card
 */
public class MoveAndShuffleMessage extends Message{
    private int newBlackFaithPoints;
    private ArrayList<String> actionCardConfiguration;

    public int getNewBlackFaithPoints() {
        return newBlackFaithPoints;
    }

    public void setNewBlackFaithPoints(int newBlackFaithPoints) {
        this.newBlackFaithPoints = newBlackFaithPoints;
    }

    public ArrayList<String> getActionCardConfiguration() {
        return actionCardConfiguration;
    }

    public void setActionCardConfiguration(ArrayList<String> actionCardConfiguration) {
        this.actionCardConfiguration = actionCardConfiguration;
    }
}
