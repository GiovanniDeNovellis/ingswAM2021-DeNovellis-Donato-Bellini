package it.polimi.ingsw.Controller.Messages;

import it.polimi.ingsw.Deckgrid;

/**
 * Message used to communicate the changement of the deckgrid
 */
public class NoitifyDeckgridChangedMessage extends Message{
    private Deckgrid deckgridConfiguration;

    public Deckgrid getDeckgridConfiguration() {
        return deckgridConfiguration;
    }

    public void setDeckgridConfiguration(Deckgrid deckgridConfiguration) {
        this.deckgridConfiguration = deckgridConfiguration;
    }
}
