package it.polimi.ingsw.View;

import it.polimi.ingsw.View.Printers.DeckGridPrinter;
import it.polimi.ingsw.View.Printers.MarketBoardPrinter;
import it.polimi.ingsw.View.Printers.Printable;

import java.util.ArrayList;

public class VirtualView {
    private Printable marketBoardPrinter = new MarketBoardPrinter();
    private Printable deckGridPrinter = new DeckGridPrinter();
    private ArrayList<Printable> personalBoards = new ArrayList<>();
    private ArrayList<Printable> leaderCardsPrinters = new ArrayList<>();
    private int blackFaithPoints;

    public void print(Printable p){
        p.print();
    }

    public Printable getMarketBoardPrinter() {
        return marketBoardPrinter;
    }

    public Printable getDeckGridPrinter() {
        return deckGridPrinter;
    }

    public ArrayList<Printable> getPersonalBoards() {
        return personalBoards;
    }

    public ArrayList<Printable> getLeaderCardsPrinters() {
        return leaderCardsPrinters;
    }

    public int getBlackFaithPoints() {
        return blackFaithPoints;
    }

    public void setBlackFaithPoints(int blackFaithPoints) {
        this.blackFaithPoints = blackFaithPoints;
    }

    public void printAll(){
        for(int i=0;i<10;i++){
            System.out.print("\n");
        }
        //printo tutto
    }
}
