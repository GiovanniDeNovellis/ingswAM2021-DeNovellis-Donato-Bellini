package it.polimi.ingsw.View.Printers;

import it.polimi.ingsw.Deckgrid;

public class DeckGridPrinter implements Printable{
    private Deckgrid deckgrid;
    private boolean built=false;

    @Override
    public void print() {
        if(!built){
            System.out.println("Game not started!!!");
            return;
        }
    }

    public void setDeckgrid(Deckgrid deckgrid) {
        if(!built)
            built=true;
        this.deckgrid = deckgrid;
    }
}
