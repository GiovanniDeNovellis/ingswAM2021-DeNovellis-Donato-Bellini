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

        DevelopmentCard greenCard;
        DevelopmentCard blueCard;
        DevelopmentCard yellowCard;
        DevelopmentCard purpleCard;

        for( int levelToSet=3; levelToSet>=0; levelToSet-- ) {

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

    public DevelopmentCard getCard(int level, Colour colour){
        return deckgrid.readCard(level, colour);
    }

    public void setInfo(DevelopmentCard card){
        colour = card.getColour();
        level = card.getLevel();
        victoryPoints = card.getVictoryPoints();
        cost = card.getCost();
        productionCost = card.getProductionCost();
        earnedResources = card.getEarnedResources();
    }

    public void printCards(DevelopmentCard greenCard, DevelopmentCard blueCard, DevelopmentCard yellowCard, DevelopmentCard purpleCard){

        System.out.println(Colours.ANSI_GREEN.escape() + "\u2554\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2557"
                + "    "
                + Colours.ANSI_BLUE.escape() + "\u2554\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2557"
                + "    "
                + Colours.ANSI_YELLOW.escape() + "\u2554\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2557"
                + "    "
                + Colours.ANSI_PURPLE.escape() + "\u2554\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2557"
                + ("    ") );

        System.out.println(Colours.ANSI_GREEN.escape() + "\u2551" +
                "COLOUR: GREEN" +
                "   " +
                "\u2551" +
                "    " +
                Colours.ANSI_BLUE.escape() + "\u2551" +
                "COLOUR: BLUE" +
                "    " +
                "\u2551" +
                "    " +
                Colours.ANSI_YELLOW.escape() + "\u2551" +
                "COLOUR: YELLOW" +
                "  " +
                "\u2551" +
                "    " +
                Colours.ANSI_PURPLE.escape() + "\u2551" +
                "COLOUR: PURPLE" +
                "  "+
                "\u2551");
    }


}
