package it.polimi.ingsw.Controller.Messages;

/**
 * Message used to communicate the success of the discard of a leader card
 */
public class DiscardSuccessMessage extends Message{
    private int position;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
