package it.polimi.ingsw.View.Printers;

import it.polimi.ingsw.*;
import it.polimi.ingsw.View.LeaderCardsBuilder.*;

import java.util.ArrayList;

public class LeaderCardsPrinter implements Printable{
    private String ownerNickname;
    private int[] choosableLeaderCards = new int[4];
    private int[] chosenLeaderCards = new int[2];
    private boolean[] activatedLeaderCards = {false,false};
    private LeaderCardsDeck deck = new LeaderCardsDeck();

    ArrayList<LeaderCardDiscount> discount = deck.getDiscount();
    ArrayList<LeaderCardDeposit> deposit = deck.getDeposit();
    ArrayList<LeaderCardTransformation> transformation = deck.getTransformation();
    ArrayList<LeaderCardProduction> production = deck.getProduction();



    @Override
    public void print(String whatIHaveToPrint) {

        // if( discount.get(1).getLeaderCardNumber() == 2 ) ....

    }

        public void setChoosableLeaderCards(int[] choosableLeaderCards) {
        this.choosableLeaderCards = choosableLeaderCards;
    }

    public void setChosenLeaderCards(int[] chosenLeaderCards) {
        this.chosenLeaderCards = chosenLeaderCards;
    }

    public void activateLeaderCard(int position){
        activatedLeaderCards[position]=true;
    }

    public void setOwnerNickname(String ownerNickname) {
        this.ownerNickname = ownerNickname;
    }

    public String getOwnerNickname() {
        return ownerNickname;
    }
}
