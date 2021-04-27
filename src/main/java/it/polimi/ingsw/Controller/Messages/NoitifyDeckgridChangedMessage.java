package it.polimi.ingsw.Controller.Messages;

import it.polimi.ingsw.Deckgrid;

public class NoitifyDeckgridChangedMessage extends Message{
    private Deckgrid deckgridConfiguration;

    public Deckgrid getDeckgridConfiguration() {
        return deckgridConfiguration;
    }

    public void setDeckgridConfiguration(Deckgrid deckgridConfiguration) {
        this.deckgridConfiguration = deckgridConfiguration;
    }
}
