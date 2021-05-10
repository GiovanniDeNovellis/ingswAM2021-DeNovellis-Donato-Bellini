package it.polimi.ingsw.View.Printers;

public class LeaderCardsPrinter implements Printable{
    private String ownerNickname;
    private int[] choosableLeaderCards = new int[4];
    private int[] choosedLeaderCards = new int[2];
    private boolean[] activatedLeaderCards = {false,false};

    @Override
    public void print() {

    }

    public void setChoosableLeaderCards(int[] choosableLeaderCards) {
        this.choosableLeaderCards = choosableLeaderCards;
    }

    public void setChoosedLeaderCard(int position, int leaderCardNumber){
        choosedLeaderCards[position]=leaderCardNumber;
    }
    public void activateLeaderCard(int position){
        activatedLeaderCards[position]=true;
    }
}
