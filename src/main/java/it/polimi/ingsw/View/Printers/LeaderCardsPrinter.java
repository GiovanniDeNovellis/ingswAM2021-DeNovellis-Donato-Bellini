package it.polimi.ingsw.View.Printers;

import it.polimi.ingsw.DevelopmentCard;
import it.polimi.ingsw.LeaderCard;
import it.polimi.ingsw.ResourceType;
import it.polimi.ingsw.View.Colours;

public class LeaderCardsPrinter implements Printable{
    private String ownerNickname;

    private int[] choosableLeaderCards = new int[4];
    private int[] chosenLeaderCards = new int[2];
    private boolean[] activatedLeaderCards = {false,false};
    private boolean built=false;


    @Override
    public void print(String whatIHaveToPrint) {
        if(!built){
        System.out.println("Game not started!!!");
        return;
    }
        LeaderCard leaderCard1, leaderCard2, leaderCard3, leaderCard4;

        System.out.println(Colours.ANSI_RED.escape() + "Legend: SH=SHIELDS, C=COINS, ST=STONES, SE=SERVANTS, FP=FAITH POINTS\n");

    }

    /*private void printChoosableCards(){

        //RIGA APERTURA
        System.out.println("\u2554\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2557"
                + "    " +
                "\u2554\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2557"
                + "    " +
                "\u2554\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2557"
                + "    " +
                "\u2554\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2557");

        //NOME CARTA
        System.out.println("\u2551" +
                "LEADER CARD ⓵" +
                "\u2551" +
                "    " +
                "\u2551" +
                "LEADER CARD ⓶" +
                "\u2551" +
                "    " +
                "\u2551" +
                "LEADER CARD ⓷" +
                "\u2551" +
                "    " +
                "\u2551" +
                "LEADER CARD ⓸" +
                "\u2551");

        //COSTO
        System.out.println("\u2551" +
                "CARD COST:" +
                "      "+
                "\u2551" +
                "    " +
                "\u2551" +
                "CARD COST:" +
                "      "+
                "\u2551" +
                "    " +
                "\u2551" +
                "CARD COST:" +
                "      "+
                "\u2551" +
                "    " +
                "\u2551" +
                "CARD COST:" +
                "      "+
                "\u2551");

        StringBuilder leaderCard1Cost = getCost(leaderCard1);
        StringBuilder leaderCard2Cost = getCost(leaderCard2);
        StringBuilder leaderCard3Cost = getCost(leaderCard3);
        StringBuilder leaderCard4Cost = getCost(leaderCard4);

        System.out.println(Colours.ANSI_GREEN.escape() + "\u2551" +
                leaderCard1Cost +
                "\u2551" +
                "    " +
                Colours.ANSI_BLUE.escape() + "\u2551" +
                leaderCard2Cost +
                "\u2551" +
                "    " +
                Colours.ANSI_YELLOW.escape() + "\u2551" +
                leaderCard3Cost +
                "\u2551" +
                "    " +
                Colours.ANSI_PURPLE.escape() + "\u2551" +
                leaderCard4Cost +
                "\u2551");

        //PUNTI VITTORIA
        System.out.println("\u2551" +
                "VICTORY POINTS:" +
                " " +
                "\u2551" +
                "    " +
                "\u2551" +
                "VICTORY POINTS:" +
                " " +
                "\u2551" +
                "    " +
                "\u2551" +
                "VICTORY POINTS:" +
                " " +
                "\u2551" +
                "    " +
                "\u2551" +
                "VICTORY POINTS:" +
                " " +
                "\u2551");

        String firstVictory,secondVictory,thirdVictory,fourthVictory;
        firstVictory = getVictoryPoints(leaderCard1);
        secondVictory = getVictoryPoints(leaderCard2);
        thirdVictory = getVictoryPoints(leaderCard3);
        fourthVictory = getVictoryPoints(leaderCard4);

        System.out.println(Colours.ANSI_GREEN.escape() + "\u2551" +
                firstVictory +
                "\u2551" +
                "    " +
                "\u2551" +
                secondVictory +
                "\u2551" +
                "    " +
                "\u2551" +
                thirdVictory +
                "\u2551" +
                "    " +
                "\u2551" +
                fourthVictory +
                "\u2551");

        //TIPO ABILITA' SPECIALE
        System.out.println("\u2551" +
                "SPECIAL ABILITY TYPE:" +
                " " +
                "\u2551" +
                "    " +
                "\u2551" +
                "SPECIAL ABILITY TYPE:" +
                " " +
                "\u2551" +
                "    " +
                "\u2551" +
                "SPECIAL ABILITY TYPE:" +
                " " +
                "\u2551" +
                "    " +
                "\u2551" +
                "SPECIAL ABILITY TYPE:" +
                " " +
                "\u2551");



        //RIGA CHIUSURA
        System.out.println("\u255a\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u255d"
                + "    "
                + "\u255a\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u255d"
                + "    "
                + "\u255a\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u255d"
                + "    "
                + "\u255a\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u255d"
                + ("    "));
    }

    
    private String getVictoryPoints(LeaderCard card){
        String  cardsVictoryPoints;
        if( card == null )
            cardsVictoryPoints = "                ";
        else if (card.getVictoryPoints()>9)
            cardsVictoryPoints = card.getVictoryPoints() + "              ";
        else
            cardsVictoryPoints = card.getVictoryPoints() + "               ";
        return cardsVictoryPoints;
    }*/

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
