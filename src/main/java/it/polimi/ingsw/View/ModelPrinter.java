package it.polimi.ingsw.View;

import it.polimi.ingsw.View.Printers.*;

import java.util.ArrayList;

public class ModelPrinter {
    private MarketBoardPrinter marketBoardPrinter = new MarketBoardPrinter();
    private DeckGridPrinter deckGridPrinter = new DeckGridPrinter();
    private ArrayList<PersonalBoardPrinter> personalBoards = new ArrayList<>();
    private ArrayList<LeaderCardsPrinter> leaderCardsPrinters = new ArrayList<>();
    private int blackFaithPoints=-1;

    public void print(Printable p, String whatIHaveToPrint){
        if(whatIHaveToPrint.equals("blackFaithPoints")){
         //TODO FAITH TRACK DI LORENZO
        }
        else {
            p.print(whatIHaveToPrint);
        }
    }

    public MarketBoardPrinter getMarketBoardPrinter() {
        return marketBoardPrinter;
    }

    public DeckGridPrinter getDeckGridPrinter() {
        return deckGridPrinter;
    }

    public ArrayList<PersonalBoardPrinter> getPersonalBoards() {
        return personalBoards;
    }

    public ArrayList<LeaderCardsPrinter> getLeaderCardsPrinters() {
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
        //printo tutto quando cambia la CLI in seguito ad un'azione di un player
    }

}
