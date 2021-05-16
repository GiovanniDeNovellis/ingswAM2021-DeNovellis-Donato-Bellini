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

    public void setChoosedLeaderCards(int[] choosedLeaderCards) {
        this.choosedLeaderCards = choosedLeaderCards;
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
