package it.polimi.ingsw.View.Printers;

import it.polimi.ingsw.Colour;
import it.polimi.ingsw.DevelopmentCard;
import it.polimi.ingsw.LeaderCard;
import it.polimi.ingsw.ResourceType;
import it.polimi.ingsw.View.Colours;
import it.polimi.ingsw.View.LeaderCardsBuilder.*;

import java.util.ArrayList;

public class LeaderCardsPrinter implements Printable {
    private String ownerNickname;

    private int[] choosableLeaderCards = new int[4];
    private int[] chosenLeaderCards = new int[2];
    private boolean[] activatedLeaderCards = {false, false};
    private boolean built = false;
    private LeaderCardsDeck deck = new LeaderCardsDeck();
    private ArrayList<LeaderCards> leaderCards = deck.getLeaderCards();

    @Override
    public void print(String whatIHaveToPrint) {
        LeaderCards leaderCards1 = new LeaderCards(), leaderCards2=new LeaderCards(), leaderCards3=new LeaderCards(), leaderCards4=new LeaderCards();
        /*if(!built){
            System.out.println("Game not started!!!");
            return;
        }*/

        System.out.println("Legend: SH=SHIELDS, C=COINS, ST=STONES, SE=SERVANTS, FP=FAITH POINTS, ?=ANY RESOURCE\n");

    printChoosableCards(leaderCards1,leaderCards2,leaderCards3,leaderCards4);
}

    private void printChoosableCards(LeaderCards leaderCard1,LeaderCards leaderCard2,LeaderCards leaderCard3,LeaderCards leaderCard4){
        String[] requirements = new String[4];
        String[] victoryPoints = new String[4];
        String[] powers = new String[4];
        String[] resources = new String[4];

        choosableLeaderCards[0] = 1;
        choosableLeaderCards[1] = 2;
        choosableLeaderCards[2] = 3;
        choosableLeaderCards[3] = 4;

        for( int j=0; j<4; j++ ){
            System.out.println(choosableLeaderCards[j]+" ");
        }


        for (int i = 0; i < 4; i++) {
            if( choosableLeaderCards[i]>=1 && choosableLeaderCards[i] <=4 ) {
                leaderCard1 = deck.getLeaderCards().get(i);
                buildDiscountType(leaderCard1, requirements[i], victoryPoints[i], powers[i], resources[i]);
            }
            else if( choosableLeaderCards[i]>=5 && choosableLeaderCards[i] <=8 ) {
                leaderCard2 = deck.getLeaderCards().get(i);
                buildDepositType(leaderCard2, requirements[i], victoryPoints[i], powers[i], resources[i]);
            }
            else if( choosableLeaderCards[i]>=9 && choosableLeaderCards[i] <=12 ) {
                leaderCard3 = deck.getLeaderCards().get(i);
                buildTransformationType(leaderCard3, requirements[i], victoryPoints[i], powers[i], resources[i]);
            }
            else if( choosableLeaderCards[i]>=13 && choosableLeaderCards[i] <=16 ) {
                leaderCard4 = deck.getLeaderCards().get(i);
                buildProductionType(leaderCard4, requirements[i], victoryPoints[i], powers[i], resources[i]);
            }
        }

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

        //REQUIREMENT
        System.out.println("\u2551" +
                "REQUIREMENT:" +
                "    "+
                "\u2551" +
                "    " +
                "\u2551" +
                "REQUIREMENT:" +
                "    "+
                "\u2551" +
                "    " +
                "\u2551" +
                "REQUIREMENT:" +
                "    "+
                "\u2551" +
                "    " +
                "\u2551" +
                "REQUIREMENT:" +
                "    "+
                "\u2551");

        System.out.println("\u2551" +
                requirements[0] +
                "\u2551" +
                "    " +
                "\u2551" +
                requirements[1] +
                "\u2551" +
                "    " +
                "\u2551" +
                requirements[2] +
                "\u2551" +
                "    " +
                "\u2551" +
                requirements[3] +
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

        System.out.println("\u2551" +
                victoryPoints[0] +
                "\u2551" +
                "    " +
                "\u2551" +
                victoryPoints[1] +
                "\u2551" +
                "    " +
                "\u2551" +
                victoryPoints[2] +
                "\u2551" +
                "    " +
                "\u2551" +
                victoryPoints[3] +
                "\u2551");

        //TIPO ABILITA' SPECIALE
        System.out.println("\u2551" +
                "POWER TYPE:" +
                "     " +
                "\u2551" +
                "    " +
                "\u2551" +
                "POWER TYPE:" +
                "     " +
                "\u2551" +
                "    " +
                "\u2551" +
                "POWER TYPE:" +
                "     " +
                "\u2551" +
                "    " +
                "\u2551" +
                "POWER TYPE:" +
                "     " +
                "\u2551");

        System.out.println("\u2551" +
                powers[0] +
                "\u2551" +
                "    " +
                "\u2551" +
                powers[1] +
                "\u2551" +
                "    " +
                "\u2551" +
                powers[2] +
                "\u2551" +
                "    " +
                "\u2551" +
                powers[3] +
                "\u2551");

        System.out.println("\u2551" +
                "INVOLVED RES:" +
                "   " +
                "\u2551" +
                "    " +
                "INVOLVED RES:" +
                "   " +
                "\u2551" +
                "INVOLVED RES:" +
                "   " +
                "\u2551" +
                "INVOLVED RES:" +
                "   " +
                "\u2551");
        System.out.println("\u2551" +
                resources[0] +
                "\u2551" +
                "    " +
                "\u2551" +
                resources[1] +
                "\u2551" +
                "    " +
                "\u2551" +
                resources[2] +
                "\u2551" +
                "    " +
                "\u2551" +
                resources[3] +
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

    private void printActiveCards(){

        //RIGA APERTURA
        System.out.println("\u2554\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2557");

        //NOME CARTA
        System.out.println("\u2551" +
                "LEADER CARD ⓵" +
                "\u2551");

        //COSTO
        System.out.println("\u2551" +
                "CARD COST:" +
                "      "+
                "\u2551" +
                "    " +
                "\u2551");

        StringBuilder leaderCard1Cost;

        System.out.println("\u2551" +

                "\u2551");

        //PUNTI VITTORIA
        System.out.println("\u2551" +
                "VICTORY POINTS:" +
                " " +
                "\u2551");



        System.out.println("\u2551" +
                "\u2551" +
                "    " +
                "\u2551");

        //TIPO ABILITA' SPECIALE
        System.out.println("\u2551" +
                "SPECIAL ABILITY TYPE:" +
                " " +
                "\u2551");

        //RIGA CHIUSURA
        System.out.println("\u255a\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u255d");
    }

    private void buildDiscountType(LeaderCards card,String requirement, String victoryPoint, String power, String resource){
        requirement = getRequirementDiscount(card);
        victoryPoint = getVictoryPoints(card);
        power = "Discount on res:";
        resource = getResourceDiscount(card);
    }
    private void buildDepositType(LeaderCards card,String requirement, String victoryPoint, String power, String resource){
        requirement = getRequirementDeposit(card);
        victoryPoint = getVictoryPoints(card);
        power = "ExtraDeposit :  ";
        resource = getResourceDeposit(card);
    }
    private void buildTransformationType(LeaderCards card,String requirement, String victoryPoint, String power, String resource){
        requirement = getRequirementTransformation(card);
        victoryPoint = getVictoryPoints(card);
        power = "Transformation :";
        resource = getResourceTransformation(card);
    }

    private void buildProductionType(LeaderCards card,String requirement, String victoryPoint, String power, String resource){
        requirement = getRequirementProduction(card);
        victoryPoint = getVictoryPoints(card);
        power = "ExtraProduction:";
        resource = getResourceProduction(card);
    }


    private String getResourceProduction(LeaderCards card){
        String cardResource = null;
        switch (card.getResourceType().toString()) {
            case "COINS":
                cardResource= Colours.ANSI_YELLOW.escape()+ "1COIN-->1?1FP   ";
                break;
            case "SHIELDS":
                cardResource= Colours.ANSI_BLUE.escape() + "1SHIELD-->1?1FP ";
                break;
            case "STONES":
                cardResource= Colours.ANSI_BRIGHTBLACK.escape() + "1STONE-->1?1FP  ";
                break;
            case "SERVANTS":
                cardResource= Colours.ANSI_PURPLE.escape() + "1SERVANT-->1?1FP";
                break;
        }
        cardResource += Colours.RESET;
        return cardResource;
    }
    private String getRequirementProduction (LeaderCards card){
        String cardRequirement = null;
        switch (card.getLevel2CardColour().toString()) {
            case "YELLOW":
                cardRequirement+= Colours.ANSI_YELLOW.escape()+ "YELLOW LVL2 CARD";
                break;
            case "BLUE":
                cardRequirement+= Colours.ANSI_BLUE.escape() + "BLUE LVL2 CARD  ";
                break;
            case "GREEN":
                cardRequirement+= Colours.ANSI_GREEN.escape() + "GREEN LVL2 CARD ";
                break;
            case "PURPLE":
                cardRequirement+= Colours.ANSI_PURPLE.escape() + "PURPLE LVL2 CARD";
                break;
        }
        cardRequirement += Colours.RESET;
        return cardRequirement;
    }

    private String getResourceTransformation (LeaderCards card){
            String cardResource = null;
            switch (card.getResourceType().toString()) {
                case "COINS":
                    cardResource= Colours.ANSI_YELLOW.escape()+ "WHITE-->COINS   ";
                    break;
                case "SHIELDS":
                    cardResource= Colours.ANSI_BLUE.escape() + "WHITE-->SHIELDS ";
                    break;
                case "STONES":
                    cardResource= Colours.ANSI_BRIGHTBLACK.escape() + "WHITE-->STONES  ";
                    break;
                case "SERVANTS":
                    cardResource= Colours.ANSI_PURPLE.escape() + "WHITE-->SERVANTS";
                    break;
            }
            cardResource += Colours.RESET;
            return cardResource;
        }
    private String getRequirementTransformation(LeaderCards card){
        String cardRequirement = null;
        switch (card.getDoubleCardColour().toString()) {
            case "YELLOW":
                cardRequirement= Colours.ANSI_YELLOW.escape()+ "2Y ";
                break;
            case "BLUE":
                cardRequirement= Colours.ANSI_BLUE.escape() + "2B ";
                break;
            case "GREEN":
                cardRequirement= Colours.ANSI_GREEN.escape() + "2G ";
                break;
            case "PURPLE":
                cardRequirement= Colours.ANSI_PURPLE.escape() + "2P ";
                break;
        }
        switch (card.getSingleCardColour().toString()) {
            case "YELLOW":
                cardRequirement+= Colours.ANSI_YELLOW.escape()+ "1Y";
                break;
            case "BLUE":
                cardRequirement+= Colours.ANSI_BLUE.escape() + "1B";
                break;
            case "GREEN":
                cardRequirement+= Colours.ANSI_GREEN.escape() + "1G";
                break;
            case "PURPLE":
                cardRequirement+= Colours.ANSI_PURPLE.escape() + "1P";
                break;
        }
        cardRequirement += "           ";
        cardRequirement += Colours.RESET;
        return cardRequirement;
    }

    private String getRequirementDeposit(LeaderCards card) {
        String cardRequirement = null;
        switch (card.getResourceType().toString()) {
            case "COINS":
                cardRequirement = Colours.ANSI_YELLOW.escape() + "5 COINS         ";
                break;
            case "SHIELDS":
                cardRequirement = Colours.ANSI_BLUE.escape() + "5 SHIELDS       ";
                break;
            case "STONES":
                cardRequirement = Colours.ANSI_BRIGHTBLACK.escape() + "5 STONES        ";
                break;
            case "SERVANTS":
                cardRequirement = Colours.ANSI_PURPLE.escape() + "5 SERVANTS      ";
                break;
        }
        cardRequirement += Colours.RESET;
        return cardRequirement;
    }
    private String getResourceDeposit(LeaderCards card){
        String cardResource = null;
        switch (card.getResourceType().toString()) {
            case "COINS":
                cardResource= Colours.ANSI_YELLOW.escape()+ "2 COINS         ";
                break;
            case "SHIELDS":
                cardResource= Colours.ANSI_BLUE.escape() + "2 SHIELDS       ";
                break;
            case "STONES":
                cardResource= Colours.ANSI_BRIGHTBLACK.escape() + "2 STONES        ";
                break;
            case "SERVANTS":
                cardResource= Colours.ANSI_PURPLE.escape() + "2 SERVANTS      ";
                break;
        }
        cardResource += Colours.RESET;
        return cardResource;
    }

    private String getResourceDiscount(LeaderCards card){
        String cardResource = null;
        switch (card.getResourceType().toString()) {
            case "COINS":
                cardResource= Colours.ANSI_YELLOW.escape()+ "-1 COIN         ";
                break;
            case "SHIELDS":
                cardResource= Colours.ANSI_BLUE.escape() + "-1 SHIELD       ";
                break;
            case "STONES":
                cardResource= Colours.ANSI_BRIGHTBLACK.escape() + "-1 STONE        ";
                break;
            case "SERVANTS":
                cardResource= Colours.ANSI_PURPLE.escape() + "-1 SERVANT      ";
                break;
        }
        cardResource += Colours.RESET;
        return cardResource;
    }
    private String getRequirementDiscount(LeaderCards card) {
        String cardRequirement = null;
        switch (card.getSingleColour1().toString()) {
            case "YELLOW":
                cardRequirement= Colours.ANSI_YELLOW.escape()+ "1Y ";
                break;
            case "BLUE":
                cardRequirement= Colours.ANSI_BLUE.escape() + "1B ";
                break;
            case "GREEN":
                cardRequirement= Colours.ANSI_GREEN.escape() + "1G ";
                break;
            case "PURPLE":
                cardRequirement= Colours.ANSI_PURPLE.escape() + "1P ";
                break;
        }
        switch (card.getSingleColour2().toString()) {
            case "YELLOW":
                cardRequirement+= Colours.ANSI_YELLOW.escape()+ "1Y";
                break;
            case "BLUE":
                cardRequirement+= Colours.ANSI_BLUE.escape() + "1B";
                break;
            case "GREEN":
                cardRequirement+= Colours.ANSI_GREEN.escape() + "1G";
                break;
            case "PURPLE":
                cardRequirement+= Colours.ANSI_PURPLE.escape() + "1P";
                break;
        }
        cardRequirement += "           ";
        cardRequirement += Colours.RESET;
        return cardRequirement;
    }

    private String getVictoryPoints(LeaderCards card){
        String  cardsVictoryPoints;
        if( card == null )
            cardsVictoryPoints = "                ";
        else if (card.getVictoryPoints()>9)
            cardsVictoryPoints = card.getVictoryPoints() + "              ";
        else
            cardsVictoryPoints = card.getVictoryPoints() + "               ";
        return cardsVictoryPoints;
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
