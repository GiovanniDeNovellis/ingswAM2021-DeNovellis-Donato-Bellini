package it.polimi.ingsw.View.Printers;

import it.polimi.ingsw.Colour;
import it.polimi.ingsw.Deckgrid;
import it.polimi.ingsw.DevelopmentCard;
import it.polimi.ingsw.ResourceType;
import it.polimi.ingsw.View.Colours;

public class DeckGridPrinter implements Printable{
    private Deckgrid deckgrid;
    private boolean built=false;

    @Override
    public void print(String whatIHaveToPrint) {
        if(!built){
            System.out.println("Game not started!!!");
            return;
        }
        DevelopmentCard greenCard, blueCard, yellowCard, purpleCard;

        System.out.println(Colours.ANSI_RED.escape() + "Legend: SH=SHIELDS, C=COINS, ST=STONES, SE=SERVANTS, FP=FAITH POINTS\n");

        for( int levelToSet=3; levelToSet>0; levelToSet-- ) {

            greenCard = getCard( levelToSet, Colour.GREEN);
            blueCard = getCard( levelToSet, Colour.BLUE);
            yellowCard = getCard( levelToSet, Colour.YELLOW);
            purpleCard = getCard( levelToSet, Colour.PURPLE);

            printCards(greenCard,blueCard,yellowCard,purpleCard);
        }
    }

    public void setDeckgrid(Deckgrid deckgrid) {
        if(!built)
            built=true;
        this.deckgrid = deckgrid;
    }

    private DevelopmentCard getCard(int level, Colour colour){
        return deckgrid.readCard(level, colour);
    }

    private void printCards(DevelopmentCard greenCard, DevelopmentCard blueCard, DevelopmentCard yellowCard, DevelopmentCard purpleCard){

        String greenColour;
        String blueColour;
        String yellowColour;
        String purpleColour;
        if( greenCard==null)
            greenColour = "EMPTY STACK     ";
        else
            greenColour ="COLOUR: GREEN   ";

        if( blueCard==null)
            blueColour = "EMPTY STACK     ";
        else
            blueColour ="COLOUR: BLUE    ";

        if( yellowCard==null)
            yellowColour = "EMPTY STACK     ";
        else
            yellowColour ="COLOUR: YELLOW  ";

        if( purpleCard==null)
            purpleColour = "EMPTY STACK     ";
        else
            purpleColour ="COLOUR: PURPLE  ";

        System.out.println(Colours.ANSI_GREEN.escape() + "\u2554\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2557"
                + "    "
                + Colours.ANSI_BLUE.escape() + "\u2554\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2557"
                + "    "
                + Colours.ANSI_YELLOW.escape() + "\u2554\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2557"
                + "    "
                + Colours.ANSI_PURPLE.escape() + "\u2554\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2557"
                + ("    ") );

        System.out.println(Colours.ANSI_GREEN.escape() + "\u2551" +
                greenColour +
                "\u2551" +
                "    " +
                Colours.ANSI_BLUE.escape() + "\u2551" +
                blueColour +
                "\u2551" +
                "    " +
                Colours.ANSI_YELLOW.escape() + "\u2551" +
                yellowColour +
                "\u2551" +
                "    " +
                Colours.ANSI_PURPLE.escape() + "\u2551" +
                purpleColour +
                "\u2551");

        System.out.println(Colours.ANSI_GREEN.escape() + "\u2551" +
                "CARD COST:" +
                "      "+
                "\u2551" +
                "    " +
                Colours.ANSI_BLUE.escape() + "\u2551" +
                "CARD COST:" +
                "      "+
                "\u2551" +
                "    " +
                Colours.ANSI_YELLOW.escape() + "\u2551" +
                "CARD COST:" +
                "      "+
                "\u2551" +
                "    " +
                Colours.ANSI_PURPLE.escape() + "\u2551" +
                "CARD COST:" +
                "      "+
                "\u2551");

        StringBuilder greenCost = getCost(greenCard);
        StringBuilder blueCost = getCost(blueCard);
        StringBuilder yellowCost = getCost(yellowCard);
        StringBuilder purpleCost = getCost(purpleCard);

        System.out.println(Colours.ANSI_GREEN.escape() + "\u2551" +
                greenCost +
                "\u2551" +
                "    " +
                Colours.ANSI_BLUE.escape() + "\u2551" +
                blueCost +
                "\u2551" +
                "    " +
                Colours.ANSI_YELLOW.escape() + "\u2551" +
                yellowCost +
                "\u2551" +
                "    " +
                Colours.ANSI_PURPLE.escape() + "\u2551" +
                purpleCost +
                "\u2551");

        System.out.println(Colours.ANSI_GREEN.escape() + "\u2551" +
                "PRODUCTION COST:" +
                "\u2551" +
                "    " +
                Colours.ANSI_BLUE.escape() + "\u2551" +
                "PRODUCTION COST:" +
                "\u2551" +
                "    " +
                Colours.ANSI_YELLOW.escape() + "\u2551" +
                "PRODUCTION COST:" +
                "\u2551" +
                "    " +
                Colours.ANSI_PURPLE.escape() + "\u2551" +
                "PRODUCTION COST:" +
                "\u2551");

        StringBuilder greenProdCost = getProductionCost(greenCard);
        StringBuilder blueProdCost = getProductionCost(blueCard);
        StringBuilder yellowProdCost = getProductionCost(yellowCard);
        StringBuilder purpleProdCost = getProductionCost(purpleCard);

        System.out.println(Colours.ANSI_GREEN.escape() + "\u2551" +
                greenProdCost +
                "\u2551" +
                "    " +
                Colours.ANSI_BLUE.escape() + "\u2551" +
                blueProdCost +
                "\u2551" +
                "    " +
                Colours.ANSI_YELLOW.escape() + "\u2551" +
                yellowProdCost +
                "\u2551" +
                "    " +
                Colours.ANSI_PURPLE.escape() + "\u2551" +
                purpleProdCost +
                "\u2551");

        System.out.println(Colours.ANSI_GREEN.escape() + "\u2551" +
                "PRODUCTION GAIN:" +
                "\u2551" +
                "    " +
                Colours.ANSI_BLUE.escape() + "\u2551" +
                "PRODUCTION GAIN:" +
                "\u2551" +
                "    " +
                Colours.ANSI_YELLOW.escape() + "\u2551" +
                "PRODUCTION GAIN:" +
                "\u2551" +
                "    " +
                Colours.ANSI_PURPLE.escape() + "\u2551" +
                "PRODUCTION GAIN:" +
                "\u2551");

        StringBuilder greenGain = getEarnedResources(greenCard);
        StringBuilder blueGain = getEarnedResources(blueCard);
        StringBuilder yellowGain = getEarnedResources(yellowCard);
        StringBuilder purpleGain = getEarnedResources(purpleCard);

        System.out.println(Colours.ANSI_GREEN.escape() + "\u2551" +
                greenGain +
                "\u2551" +
                "    " +
                Colours.ANSI_BLUE.escape() + "\u2551" +
                blueGain +
                "\u2551" +
                "    " +
                Colours.ANSI_YELLOW.escape() + "\u2551" +
                yellowGain +
                "\u2551" +
                "    " +
                Colours.ANSI_PURPLE.escape() + "\u2551" +
                purpleGain +
                "\u2551");

        System.out.println(Colours.ANSI_GREEN.escape() + "\u2551" +
                "VICTORY POINTS:" +
                " " +
                "\u2551" +
                "    " +
                Colours.ANSI_BLUE.escape() + "\u2551" +
                "VICTORY POINTS:" +
                " " +
                "\u2551" +
                "    " +
                Colours.ANSI_YELLOW.escape() + "\u2551" +
                "VICTORY POINTS:" +
                " " +
                "\u2551" +
                "    " +
                Colours.ANSI_PURPLE.escape() + "\u2551" +
                "VICTORY POINTS:" +
                " " +
                "\u2551");

        String greenVictory,blueVictory,yellowVictory,purpleVictory;
        greenVictory = getVictoryPoints(greenCard);
        blueVictory = getVictoryPoints(blueCard);
        yellowVictory = getVictoryPoints(yellowCard);
        purpleVictory = getVictoryPoints(purpleCard);

        System.out.println(Colours.ANSI_GREEN.escape() + "\u2551" +
                greenVictory +
                "\u2551" +
                "    " +
                Colours.ANSI_BLUE.escape() + "\u2551" +
                blueVictory +
                "\u2551" +
                "    " +
                Colours.ANSI_YELLOW.escape() + "\u2551" +
                yellowVictory +
                "\u2551" +
                "    " +
                Colours.ANSI_PURPLE.escape() + "\u2551" +
                purpleVictory +
                "\u2551");

        System.out.println(Colours.ANSI_GREEN.escape() + "\u2551" +
                "CARD LEVEL:" +
                "     " +
                "\u2551" +
                "    " +
                Colours.ANSI_BLUE.escape() + "\u2551" +
                "CARD LEVEL:" +
                "     " +
                "\u2551" +
                "    " +
                Colours.ANSI_YELLOW.escape() + "\u2551" +
                "CARD LEVEL:" +
                "     " +
                "\u2551" +
                "    " +
                Colours.ANSI_PURPLE.escape() + "\u2551" +
                "CARD LEVEL:" +
                "     " +
                "\u2551");

        String greenLevel, blueLevel, yellowLevel, purpleLevel;
        greenLevel = getLevel(greenCard);
        blueLevel = getLevel(blueCard);
        yellowLevel = getLevel(yellowCard);
        purpleLevel = getLevel(greenCard);

        System.out.println(Colours.ANSI_GREEN.escape() + "\u2551" +
                greenLevel +
                "\u2551" +
                "    " +
                Colours.ANSI_BLUE.escape() + "\u2551" +
                blueLevel +
                "\u2551" +
                "    " +
                Colours.ANSI_YELLOW.escape() + "\u2551" +
                yellowLevel +
                "\u2551" +
                "    " +
                Colours.ANSI_PURPLE.escape() + "\u2551" +
                purpleLevel +
                "\u2551");

        System.out.println(Colours.ANSI_GREEN.escape() + "\u255a\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u255d"
                + "    "
                + Colours.ANSI_BLUE.escape() + "\u255a\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u255d"
                + "    "
                + Colours.ANSI_YELLOW.escape() + "\u255a\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u255d"
                + "    "
                + Colours.ANSI_PURPLE.escape() + "\u255a\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u255d"
                + ("    ")  + Colours.RESET);
    }

    private StringBuilder getCost(DevelopmentCard card){
        StringBuilder cost = new StringBuilder();
        if (card==null){
            cost.append("                ");
            return cost;
        }
        for( ResourceType res : card.getCost().keySet() ){
            if( res==ResourceType.COINS){
                Integer quantity = card.getCost().get(res);
                if(quantity>0) {
                    cost.append(quantity);
                    cost.append("C ");
                }
            }
            if( res==ResourceType.STONES){
                Integer quantity = card.getCost().get(res);
                if(quantity>0) {
                    cost.append(quantity);
                    cost.append("ST ");
                }
            }
            if( res==ResourceType.SHIELDS){
                Integer quantity = card.getCost().get(res);
                if(quantity>0) {
                    cost.append(quantity);
                    cost.append("SH ");
                }
            }
            if( res==ResourceType.SERVANTS){
                Integer quantity = card.getCost().get(res);
                if(quantity>0) {
                    cost.append(quantity);
                    cost.append("SE ");
                }
            }
        }
        for (int i=cost.length();i<=15;i++){
            cost.append(" ");
        }
        return cost;
    }

    private StringBuilder getProductionCost(DevelopmentCard card){
        StringBuilder cost = new StringBuilder();
        if (card==null){
            cost.append("                ");
            return cost;
        }
        for( ResourceType res : card.getProductionCost().keySet() ){
            if( res==ResourceType.COINS){
                Integer quantity = card.getProductionCost().get(res);
                if(quantity>0) {
                    cost.append(quantity);
                    cost.append("C ");
                }
            }
            if( res==ResourceType.STONES){
                Integer quantity = card.getProductionCost().get(res);
                if(quantity>0) {
                    cost.append(quantity);
                    cost.append("ST ");
                }
            }
            if( res==ResourceType.SHIELDS){
                Integer quantity = card.getProductionCost().get(res);
                if(quantity>0) {
                    cost.append(quantity);
                    cost.append("SH ");
                }
            }
            if( res==ResourceType.SERVANTS){
                Integer quantity = card.getProductionCost().get(res);
                if(quantity>0) {
                    cost.append(quantity);
                    cost.append("SE ");
                }
            }
        }
        for (int i=cost.length();i<=15;i++){
            cost.append(" ");
        }
        return cost;
    }

    private StringBuilder getEarnedResources(DevelopmentCard card){
        StringBuilder cost = new StringBuilder();
        if (card==null){
            cost.append("                ");
            return cost;
        }
        for( ResourceType res : card.getEarnedResources().keySet() ){
            if( res==ResourceType.COINS){
                Integer quantity = card.getEarnedResources().get(res);
                if(quantity>0) {
                    cost.append(quantity);
                    cost.append("C ");
                }
            }
            if( res==ResourceType.STONES){
                Integer quantity = card.getEarnedResources().get(res);
                if(quantity>0) {
                    cost.append(quantity);
                    cost.append("ST ");
                }
            }
            if( res==ResourceType.SHIELDS){
                Integer quantity = card.getEarnedResources().get(res);
                if(quantity>0) {
                    cost.append(quantity);
                    cost.append("SH ");
                }
            }
            if( res==ResourceType.SERVANTS){
                Integer quantity = card.getEarnedResources().get(res);
                if(quantity>0) {
                    cost.append(quantity);
                    cost.append("SE ");
                }
            }
            if( res==ResourceType.FAITHPOINTS){
                Integer quantity = card.getEarnedResources().get(res);
                if(quantity>0) {
                    cost.append(quantity);
                    cost.append("FP ");
                }
            }
        }
        for (int i=cost.length();i<=15;i++){
            cost.append(" ");
        }
        return cost;
    }
    private String getVictoryPoints(DevelopmentCard card){
        String  cardsVictoryPoints;
        if( card == null )
            cardsVictoryPoints = "                ";
        else if (card.getVictoryPoints()>9)
            cardsVictoryPoints = card.getVictoryPoints() + "              ";
        else
            cardsVictoryPoints = card.getVictoryPoints() + "               ";
        return cardsVictoryPoints;
    }
    private String getLevel(DevelopmentCard card){
        if( card==null )
            return "                ";
        else
            return card.getLevel() + "               ";
    }


}
