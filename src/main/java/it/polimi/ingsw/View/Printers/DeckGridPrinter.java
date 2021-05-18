package it.polimi.ingsw.View.Printers;

import it.polimi.ingsw.Colour;
import it.polimi.ingsw.Deckgrid;
import it.polimi.ingsw.DevelopmentCard;
import it.polimi.ingsw.ResourceType;
import it.polimi.ingsw.View.Colours;

import java.util.HashMap;
import java.util.Map;

public class DeckGridPrinter implements Printable{
    private Deckgrid deckgrid;
    private boolean built=false;

    private Colour colour = null;
    private int level = -1;
    private int victoryPoints = -1;
    private Map<ResourceType,Integer> cost = new HashMap<>();
    private Map<ResourceType,Integer> productionCost = new HashMap<>();
    private Map<ResourceType,Integer> earnedResources = new HashMap<>();

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
            if( greenCard!=null ){
                setInfo(greenCard);
            }

            blueCard = getCard( levelToSet, Colour.BLUE);
            if( blueCard!=null ){
                setInfo(blueCard);
            }

            yellowCard = getCard( levelToSet, Colour.YELLOW);
            if( yellowCard!=null ){
                setInfo(yellowCard);
            }

            purpleCard = getCard( levelToSet, Colour.PURPLE);
            if( purpleCard!=null ){
                setInfo(purpleCard);
            }

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

    private void setInfo(DevelopmentCard card){
        colour = card.getColour();
        level = card.getLevel();
        victoryPoints = card.getVictoryPoints();
        cost = card.getCost();
        productionCost = card.getProductionCost();
        earnedResources = card.getEarnedResources();
    }

    private void printCards(DevelopmentCard greenCard, DevelopmentCard blueCard, DevelopmentCard yellowCard, DevelopmentCard purpleCard){

        String greenColour = greenCard.getColour().toString();
        String blueColour = blueCard.getColour().toString();
        String yellowColour = yellowCard.getColour().toString();
        String purpleColour = purpleCard.getColour().toString();
        if( greenColour==null)
            greenColour = "EMPTY STACK     ";
        else
            greenColour ="COLOUR: GREEN   ";

        if( blueColour==null)
            blueColour = "EMPTY STACK     ";
        else
            blueColour ="COLOUR: BLUE    ";

        if( yellowColour==null)
            yellowColour = "EMPTY STACK     ";
        else
            yellowColour ="COLOUR: YELLOW  ";

        if( purpleColour==null)
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

        StringBuilder greenCost = setCost(greenCard);
        StringBuilder blueCost = setCost(blueCard);
        StringBuilder yellowCost = setCost(yellowCard);
        StringBuilder purpleCost = setCost(purpleCard);

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

        StringBuilder greenProdCost = setProductionCost(greenCard);
        StringBuilder blueProdCost = setProductionCost(blueCard);
        StringBuilder yellowProdCost = setProductionCost(yellowCard);
        StringBuilder purpleProdCost = setProductionCost(purpleCard);

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

        StringBuilder greenGain = setEarnedResources(greenCard);
        StringBuilder blueGain = setEarnedResources(blueCard);
        StringBuilder yellowGain = setEarnedResources(yellowCard);
        StringBuilder purpleGain = setEarnedResources(purpleCard);

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
        String greenSpaces,blueSpaces,yellowSpaces,purpleSpaces;
        if (greenCard.getVictoryPoints()>9)
            greenSpaces = "              ";
        else
            greenSpaces = "               ";

        if (blueCard.getVictoryPoints()>9)
            blueSpaces = "              ";
        else
            blueSpaces = "               ";

        if (yellowCard.getVictoryPoints()>9)
            yellowSpaces = "              ";
        else
            yellowSpaces = "               ";

        if (purpleCard.getVictoryPoints()>9)
            purpleSpaces = "              ";
        else
            purpleSpaces = "               ";

        System.out.println(Colours.ANSI_GREEN.escape() + "\u2551" +
                greenCard.getVictoryPoints() +
                greenSpaces +
                "\u2551" +
                "    " +
                Colours.ANSI_BLUE.escape() + "\u2551" +
                blueCard.getVictoryPoints() +
                blueSpaces +
                "\u2551" +
                "    " +
                Colours.ANSI_YELLOW.escape() + "\u2551" +
                yellowCard.getVictoryPoints() +
                yellowSpaces +
                "\u2551" +
                "    " +
                Colours.ANSI_PURPLE.escape() + "\u2551" +
                purpleCard.getVictoryPoints() +
                purpleSpaces +
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

        System.out.println(Colours.ANSI_GREEN.escape() + "\u2551" +
                greenCard.getLevel() +
                "               " +
                "\u2551" +
                "    " +
                Colours.ANSI_BLUE.escape() + "\u2551" +
                blueCard.getLevel() +
                "               " +
                "\u2551" +
                "    " +
                Colours.ANSI_YELLOW.escape() + "\u2551" +
                yellowCard.getLevel() +
                "               " +
                "\u2551" +
                "    " +
                Colours.ANSI_PURPLE.escape() + "\u2551" +
                purpleCard.getLevel() +
                "               " +
                "\u2551");

        System.out.println(Colours.ANSI_GREEN.escape() + "\u255a\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u255d"
                + "    "
                + Colours.ANSI_BLUE.escape() + "\u255a\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u255d"
                + "    "
                + Colours.ANSI_YELLOW.escape() + "\u255a\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u255d"
                + "    "
                + Colours.ANSI_PURPLE.escape() + "\u255a\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u255d"
                + ("    ") );
    }

    private StringBuilder setCost(DevelopmentCard card){
        StringBuilder cost = new StringBuilder();
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

    private StringBuilder setProductionCost(DevelopmentCard card){
        StringBuilder cost = new StringBuilder();
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

    private StringBuilder setEarnedResources(DevelopmentCard card){
        StringBuilder cost = new StringBuilder();
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


}
