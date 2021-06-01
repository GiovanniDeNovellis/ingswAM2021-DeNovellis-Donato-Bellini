package it.polimi.ingsw.View.Printers;

import it.polimi.ingsw.*;
import it.polimi.ingsw.View.Colours;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PersonalBoardPrinter implements Printable{
    private String ownerNickname;
    private int playerNumber;
    private DevelopmentCard[] developmentCards = new DevelopmentCard[3]; //RICORDA CONTROLLO A NULL SE UNO SLOT E' VUOTO
    private ArrayList<DevelopmentCard> allCardsInserted = new ArrayList<>();
    private WareHouseDepot wareHouseDepot = new WareHouseDepot();
    private Map<ResourceType,Integer> strongbox = new HashMap<>();
    private ExtraDeposit extraDeposit1 = new ExtraDeposit(null);
    private ExtraDeposit extraDeposit2 = new ExtraDeposit(null);
    private final Map<ResourceType, Colours> resourceTypeColoursMap = new HashMap<>();
    private int[] faithCards = {2,3,4};
    private int faithPoints=0;

    private final DevelopmentCard[] level1cards = new DevelopmentCard[3];
    private final DevelopmentCard[] level2cards = new DevelopmentCard[3];
    private final DevelopmentCard[] level3cards = new DevelopmentCard[3];

    public int getFaithPoints() {
        return faithPoints;
    }

    public PersonalBoardPrinter() {
        resourceTypeColoursMap.put(ResourceType.COINS,Colours.ANSI_YELLOW);
        resourceTypeColoursMap.put(ResourceType.SERVANTS, Colours.ANSI_PURPLE);
        resourceTypeColoursMap.put(ResourceType.SHIELDS,Colours.ANSI_CYAN);
        resourceTypeColoursMap.put(ResourceType.STONES,Colours.ANSI_WHITE);
    }

    @Override
    public void print(String whatIHaveToPrint) {
        switch (whatIHaveToPrint){
            case "warehouse":
                printWarehouse();
                break;
            case "strongbox":
                printStrongbox();
                break;
            case "extraDeposit":
                printExtraDeposits();
                break;
            case "faithTrack":
                printFaithTrack();
                break;
            case "developmentCards":
                printDevelopmentCards();
                break;
            case "insertedDevCards":
                printAllInsertedDevCards();
                break;
            default:
                System.out.println("I don't understand what you want to see.");
        }
    }

    public void setDevelopmentCards(DevelopmentCard[] developmentCards) {
        this.developmentCards = developmentCards;
        for(int i=0; i<3; i++ ) {
            if (developmentCards[i] != null) {
                if (developmentCards[i].getLevel() == 1) {
                    level1cards[i] = developmentCards[i];
                } else if (developmentCards[i].getLevel() == 2) {
                    level2cards[i] = developmentCards[i];
                } else if( developmentCards[i].getLevel() == 3) {
                    level3cards[i] = developmentCards[i];
                }
            }
        }
    }

    public void setWareHouseDepot(WareHouseDepot wareHouseDepot) {
        this.wareHouseDepot = wareHouseDepot;
    }

    public void setStrongbox(Map<ResourceType, Integer> strongbox) {
        this.strongbox = strongbox;
    }

    public void setExtraDeposit1(ExtraDeposit extraDeposit1) {
        this.extraDeposit1 = extraDeposit1;
    }

    public void setExtraDeposit2(ExtraDeposit extraDeposit2) {
        this.extraDeposit2 = extraDeposit2;
    }

    public void setFaithPoints(int faithPoints) {
        this.faithPoints = faithPoints;
    }

    public void setFaithCards(int[] faithCards) {
        this.faithCards = faithCards;
    }

    public void setOwnerNickname(String ownerNickname) {
        this.ownerNickname = ownerNickname;
    }

    public String getOwnerNickname() {
        return ownerNickname;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    public void setPlayerNumber(int playerNumber) {
        this.playerNumber = playerNumber;
    }

    public ArrayList<DevelopmentCard> getAllCardsInserted() {
        return allCardsInserted;
    }

    public void setAllCardsInserted(ArrayList<DevelopmentCard> allCardsInserted) {
        this.allCardsInserted = allCardsInserted;
    }

    private void printWarehouse(){
        System.out.println(ownerNickname + "'s Warehouse:");
        if(wareHouseDepot==null){
            System.out.println("Warehouse not present");
            return;
        }
        if(wareHouseDepot.getLevel(1).getResourceType()==null){
            for(int i=0;i<16;i++)
                System.out.print("\u2550");
            System.out.print("\u2551 EMPTY \u2551");
            for(int i=0;i<16;i++)
                System.out.print("\u2550");
            System.out.println(" ");
        }
        else{
            ResourceType res = wareHouseDepot.getLevel(1).getResourceType();
            for(int i=0;i<16;i++)
                System.out.print(resourceTypeColoursMap.get(res).escape() + "\u2550");
            System.out.print("\u2551" + res + "\u2551");
            for(int i=0;i<16;i++)
                System.out.print(resourceTypeColoursMap.get(res).escape() + "\u2550");
            System.out.print("\n");
        }
        if(wareHouseDepot.getLevel(2).getResourceType()==null){
            for(int i=0;i<8;i++)
                System.out.print(Colours.RESET+ "\u2550");
            System.out.print("\u2551 EMPTY \u2551");
            for(int i=0;i<8;i++)
                System.out.print("\u2550");
            System.out.print("\u2551 EMPTY \u2551");
            for(int i=0;i<8;i++)
                System.out.print("\u2550");
            System.out.print("\n");
        }
        else{
            ResourceType res = wareHouseDepot.getLevel(2).getResourceType();
            int i,max=2,curr=wareHouseDepot.getLevel(2).getCurrNumResources();
            for(i=curr; i>0;i--){
                for(int j=0;j<8;j++)
                    System.out.print(resourceTypeColoursMap.get(res).escape()+"\u2550");
                System.out.print("\u2551 " + res + " \u2551");
            }
            for(i=max-curr;i>0;i--){
                for(int j=0;j<8;j++)
                    System.out.print("\u2550");
                System.out.print("\u2551 EMPTY \u2551");
            }
            System.out.print("\n");
        }
        if(wareHouseDepot.getLevel(3).getResourceType()==null){
            for(int i=0;i<4;i++)
                System.out.print(Colours.RESET + "\u2550");
            System.out.print("\u2551 EMPTY \u2551");
            for(int i=0;i<4;i++)
                System.out.print("\u2550");
            System.out.print("\u2551 EMPTY \u2551");
            for(int i=0;i<4;i++)
                System.out.print("\u2550");
            System.out.print("\u2551 EMPTY \u2551");
            for(int i=0;i<4;i++)
                System.out.print("\u2550");
            System.out.print("\n");
        }
        else{
            ResourceType res = wareHouseDepot.getLevel(3).getResourceType();
            int i,max=3,curr=wareHouseDepot.getLevel(3).getCurrNumResources();
            for(i=curr; i>0;i--){
                for(int j=0;j<4;j++)
                    System.out.print(resourceTypeColoursMap.get(res).escape() + "\u2550");
                System.out.print("\u2551 " + resourceTypeColoursMap.get(res).escape() + res + " \u2551");
            }
            for(i=max-curr;i>0;i--){
                for(int j=0;j<4;j++)
                    System.out.print("\u2550");
                System.out.print("\u2551 EMPTY \u2551");
            }
            System.out.print("\n");
        }
        System.out.print(Colours.RESET + "\n");
        System.out.print(Colours.RESET + "\n");
    }

    private void printStrongbox(){
        System.out.println(ownerNickname + "'s strongbox");
        if(strongbox.isEmpty())
            System.out.println("Strongbox not created");
        else {
            for (ResourceType r : strongbox.keySet()) {
                System.out.println(resourceTypeColoursMap.get(r).escape() + "\u2550\u2550\u2550 " + r + ":" + strongbox.get(r) + Colours.RESET);
            }
        }
    }

    private void printExtraDeposits(){
        System.out.println(ownerNickname + "'s first extra deposit:");
        if(extraDeposit1.getResourceType()==null){
            System.out.println("\u2550\u2550\u2550\u2550\u2550\u2550 First deposit not activated \u2550\u2550\u2550\u2550\u2550\u2550 ");
        }
        else{
            System.out.println(resourceTypeColoursMap.get(extraDeposit1.getResourceType()).escape()+"\u2550\u2550\u2550\u2550\u2550\u2550 "+ extraDeposit1.getResourceType()+":"+extraDeposit1.getCurrentQuantity()+"\u2550\u2550\u2550\u2550\u2550\u2550"+Colours.RESET);
        }
        System.out.println(ownerNickname + "'s second extra deposit:");
        if(extraDeposit2.getResourceType()==null){
            System.out.println("\u2550\u2550\u2550\u2550\u2550\u2550 Second deposit not activated \u2550\u2550\u2550\u2550\u2550\u2550 ");
        }
        else{
            System.out.println(resourceTypeColoursMap.get(extraDeposit2.getResourceType()).escape()+"\u2550\u2550\u2550\u2550\u2550\u2550 "+ extraDeposit2.getResourceType()+":"+extraDeposit2.getCurrentQuantity()+"\u2550\u2550\u2550\u2550\u2550\u2550"+Colours.RESET);
        }
    }

    private void printFaithTrack(){
        System.out.println(ownerNickname + "'s faith track:");
        System.out.print("                                          ");
        if(faithCards[0]==0)
            System.out.print(Colours.ANSI_YELLOW.escape() +"FCX");
        else
            System.out.print(Colours.ANSI_YELLOW.escape() +"FC2");
        System.out.print("                                              ");
        if(faithCards[1]==0)
            System.out.print(Colours.ANSI_YELLOW.escape() +"FCX");
        else
            System.out.print(Colours.ANSI_YELLOW.escape() +"FC3");
        System.out.print("                                                  ");
        if(faithCards[1]==0)
            System.out.print(Colours.ANSI_YELLOW.escape() +"FCX");
        else
            System.out.print(Colours.ANSI_YELLOW.escape() +"FC4");
        System.out.print("\n" + Colours.RESET);

        int i;
        for(i=0;i<=166;i++){
            if(i==0)
                System.out.print(Colours.RESET + "\u2554");
            else if(i==166)
                System.out.print(Colours.ANSI_YELLOW.escape() + "\u2557");
            else if((i>=1 && i<=30)||(i>=55&&i<=74)||(i>=110&&i<=123))
                System.out.print(Colours.RESET + "\u2550");
            else
                System.out.print(Colours.ANSI_YELLOW.escape() + "\u2550");
        }
        System.out.print(Colours.RESET + "\n");
        for(i=0; i<=4; i++){
            if(i==faithPoints)
                System.out.print(Colours.ANSI_RED.escape() + "\u2551  " + "\u271F   ");
            else
                System.out.print(Colours.RESET + "\u2551  " + i + "  ");
        }
        for(;i<=8;i++){
            if(i==faithPoints)
                System.out.print(Colours.ANSI_RED.escape() + "\u2551  " + "\u271F   ");
            else
                System.out.print(Colours.ANSI_YELLOW.escape() + "\u2551  " + i + "  ");
        }
        for(;i<=11;i++){
            if(i==faithPoints)
                System.out.print(Colours.ANSI_RED.escape() + "\u2551  " + "\u271F   ");
            else
                System.out.print(Colours.RESET + "\u2551  " + i + "  ");
        }
        for(;i<=16;i++){
            if(i==faithPoints)
                System.out.print(Colours.ANSI_RED.escape() + "\u2551  " + "\u271F   ");
            else
                System.out.print(Colours.ANSI_YELLOW.escape() + "\u2551  " + i + "  ");
        }
        for(;i<=18;i++){
            if(i==faithPoints)
                System.out.print(Colours.ANSI_RED.escape() + "\u2551  " + "\u271F   ");
            else
                System.out.print(Colours.RESET + "\u2551  " + i + "  ");
        }
        for(;i<=24;i++){
            if(i==faithPoints)
                System.out.print(Colours.ANSI_RED.escape() + "\u2551  " + "\u271F   ");
            else
                System.out.print(Colours.ANSI_YELLOW.escape() + "\u2551  " + i + "  ");
        }
        System.out.print(Colours.ANSI_YELLOW.escape() + "\u2551" + Colours.RESET + "\n");
        for(i=0;i<=166;i++){
            if(i==0)
                System.out.print(Colours.RESET + "\u255A");
            else if(i==166)
                System.out.print(Colours.ANSI_YELLOW.escape() + "\u255D");
            else if((i>=1 && i<=30)||(i>=55&&i<=74)||(i>=110&&i<=123))
                System.out.print(Colours.RESET + "\u2550");
            else
                System.out.print(Colours.ANSI_YELLOW.escape() + "\u2550");
        }
        System.out.print(Colours.RESET + "\n");
        System.out.println(Colours.ANSI_CYAN.escape() + "VP:                   1                 2                 4                   6                    9                    12                   16                   20");
        System.out.print(Colours.RESET + "\n");
    }

    private void printDevelopmentCards(){

        System.out.println(ownerNickname + "'s development cards slots:");

        DevelopmentCard firstCard = developmentCards[0];
        DevelopmentCard secondCard = developmentCards[1];
        DevelopmentCard thirdCard = developmentCards[2];

        String firstColour,secondColour,thirdColour;
        firstColour = getColour(firstCard);
        secondColour = getColour(secondCard);
        thirdColour = getColour(thirdCard);

        String firstDrawingColour, secondDrawingColour, thirdDrawingColour;
        firstDrawingColour = drawColour(firstCard);
        secondDrawingColour = drawColour(secondCard);
        thirdDrawingColour = drawColour(thirdCard);

        System.out.println(firstDrawingColour + "\u2554\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2557"
                + "    "
                + secondDrawingColour + "\u2554\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2557"
                + "    "
                + thirdDrawingColour + "\u2554\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2557");

        System.out.println(firstDrawingColour + "\u2551" +
                firstColour +
                "\u2551" +
                "    " +
                secondDrawingColour + "\u2551" +
                secondColour +
                "\u2551" +
                "    " +
                thirdDrawingColour + "\u2551" +
                thirdColour +
                "\u2551");

        System.out.println(firstDrawingColour + "\u2551" +
                "CARD COST:" +
                "      "+
                "\u2551" +
                "    " +
                secondDrawingColour + "\u2551" +
                "CARD COST:" +
                "      "+
                "\u2551" +
                "    " +
                thirdDrawingColour + "\u2551" +
                "CARD COST:" +
                "      "+
                "\u2551");

        StringBuilder firstCost = getCost(firstCard);
        StringBuilder secondCost = getCost(secondCard);
        StringBuilder thirdCost = getCost(thirdCard);

        System.out.println(firstDrawingColour + "\u2551" +
                firstCost +
                "\u2551" +
                "    " +
                secondDrawingColour + "\u2551" +
                secondCost +
                "\u2551" +
                "    " +
                thirdDrawingColour + "\u2551" +
                thirdCost +
                "\u2551" );

        System.out.println(firstDrawingColour + "\u2551" +
                "PRODUCTION COST:" +
                "\u2551" +
                "    " +
                secondDrawingColour + "\u2551" +
                "PRODUCTION COST:" +
                "\u2551" +
                "    " +
                thirdDrawingColour + "\u2551" +
                "PRODUCTION COST:" +
                "\u2551");

        StringBuilder firstProdCost = getProductionCost(firstCard);
        StringBuilder secondProdCost = getProductionCost(secondCard);
        StringBuilder thirdProdCost = getProductionCost(thirdCard);

        System.out.println(firstDrawingColour + "\u2551" +
                firstProdCost +
                "\u2551" +
                "    " +
                secondDrawingColour + "\u2551" +
                secondProdCost +
                "\u2551" +
                "    " +
                thirdDrawingColour + "\u2551" +
                thirdProdCost +
                "\u2551");

        System.out.println(firstDrawingColour + "\u2551" +
                "PRODUCTION GAIN:" +
                "\u2551" +
                "    " +
                secondDrawingColour + "\u2551" +
                "PRODUCTION GAIN:" +
                "\u2551" +
                "    " +
                thirdDrawingColour + "\u2551" +
                "PRODUCTION GAIN:" +
                "\u2551");

        StringBuilder firstGain = getEarnedResources(firstCard);
        StringBuilder secondGain = getEarnedResources(secondCard);
        StringBuilder thirdGain = getEarnedResources(thirdCard);

        System.out.println(firstDrawingColour + "\u2551" +
                firstGain +
                "\u2551" +
                "    " +
                secondDrawingColour + "\u2551" +
                secondGain +
                "\u2551" +
                "    " +
                thirdDrawingColour + "\u2551" +
                thirdGain +
                "\u2551");

        System.out.println(firstDrawingColour + "\u2551" +
                "VICTORY POINTS:" +
                " " +
                "\u2551" +
                "    " +
                secondDrawingColour + "\u2551" +
                "VICTORY POINTS:" +
                " " +
                "\u2551" +
                "    " +
                thirdDrawingColour + "\u2551" +
                "VICTORY POINTS:" +
                " " +
                "\u2551");

        String firstVictory,secondVictory,thirdVictory;
        firstVictory = getVictoryPoints(firstCard);
        secondVictory = getVictoryPoints(secondCard);
        thirdVictory = getVictoryPoints(thirdCard);

        System.out.println(firstDrawingColour + "\u2551" +
                firstVictory +
                "\u2551" +
                "    " +
                secondDrawingColour + "\u2551" +
                secondVictory +
                "\u2551" +
                "    " +
                thirdDrawingColour + "\u2551" +
                thirdVictory +
                "\u2551");

        System.out.println(firstDrawingColour + "\u2551" +
                "CARD LEVEL:" +
                "     " +
                "\u2551" +
                "    " +
                secondDrawingColour + "\u2551" +
                "CARD LEVEL:" +
                "     " +
                "\u2551" +
                "    " +
                thirdDrawingColour + "\u2551" +
                "CARD LEVEL:" +
                "     " +
                "\u2551");

        String firstLevel, secondLevel, thirdLevel;
        firstLevel = getLevel(firstCard);
        secondLevel = getLevel(secondCard);
        thirdLevel = getLevel(thirdCard);

        System.out.println(firstDrawingColour + "\u2551" +
                firstLevel +
                "\u2551" +
                "    " +
                secondDrawingColour + "\u2551" +
                secondLevel +
                "\u2551" +
                "    " +
                thirdDrawingColour + "\u2551" +
                thirdLevel +
                "\u2551");

        System.out.println(firstDrawingColour + "\u255a\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u255d"
                + "    "
                + secondDrawingColour + "\u255a\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u255d"
                + "    "
                + thirdDrawingColour + "\u255a\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u255d"
                + Colours.RESET + "\n");
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

    private String getColour(DevelopmentCard card){
        String cardColour = "EMPTY SLOT      ";
        if( card==null)
            cardColour = "EMPTY SLOT      ";
        else if( card.getColour() == Colour.GREEN )
            cardColour ="COLOUR: GREEN   ";
        else if( card.getColour() == Colour.BLUE )
            cardColour ="COLOUR: BLUE    ";
        else if( card.getColour() == Colour.YELLOW )
            cardColour ="COLOUR: YELLOW  ";
        else if( card.getColour() == Colour.PURPLE )
            cardColour ="COLOUR: PURPLE  ";
        return cardColour;
    }

    private String drawColour(DevelopmentCard card){
        String drawingColour = Colours.RESET;
        if( card==null)
            drawingColour = Colours.RESET;
        else if( card.getColour() == Colour.GREEN )
            drawingColour = Colours.ANSI_GREEN.escape();
        else if( card.getColour() == Colour.BLUE )
            drawingColour = Colours.ANSI_BLUE.escape();
        else if( card.getColour() == Colour.YELLOW )
            drawingColour = Colours.ANSI_YELLOW.escape();
        else if( card.getColour() == Colour.PURPLE )
            drawingColour = Colours.ANSI_PURPLE.escape();
        return drawingColour;
    }

    private void printAllInsertedDevCards(){
        if( allCardsInserted.isEmpty() ) {
            System.out.println(ownerNickname + " has not inserted development cards yet.");
            return;
        }
        System.out.println(ownerNickname + "'s development cards inserted (all cards inserted):");
        for( DevelopmentCard card : allCardsInserted ) {

            String colour = getColour(card);
            String drawingColour = drawColour(card);

            System.out.println(drawingColour + "\u2554\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2557");

            System.out.println(drawingColour + "\u2551" +
                    colour +
                    "\u2551");

            System.out.println(drawingColour + "\u2551" +
                    "CARD COST:" +
                    "      " +
                    "\u2551");

            StringBuilder cost = getCost(card);

            System.out.println(drawingColour + "\u2551" +
                    cost +
                    "\u2551");

            System.out.println(drawingColour + "\u2551" +
                    "PRODUCTION COST:" +
                    "\u2551");

            StringBuilder prodCost = getProductionCost(card);

            System.out.println(drawingColour + "\u2551" +
                    prodCost +
                    "\u2551");

            System.out.println(drawingColour + "\u2551" +
                    "PRODUCTION GAIN:" +
                    "\u2551");

            StringBuilder gain = getEarnedResources(card);

            System.out.println(drawingColour + "\u2551" +
                    gain +
                    "\u2551");

            System.out.println(drawingColour + "\u2551" +
                    "VICTORY POINTS:" +
                    " " +
                    "\u2551");

            String victory = getVictoryPoints(card);

            System.out.println(drawingColour + "\u2551" +
                    victory +
                    "\u2551");

            System.out.println(drawingColour + "\u2551" +
                    "CARD LEVEL:" +
                    "     " +
                    "\u2551" );

            String level = getLevel(card);

            System.out.println(drawingColour + "\u2551" +
                    level +
                    "\u2551");

            System.out.println(drawingColour + "\u255a\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u255d"
                    + Colours.RESET + "\n");
        }
    }

    public int[] getFaithCards() {
        return faithCards;
    }

    public WareHouseDepot getWareHouseDepot() {
        return wareHouseDepot;
    }

    public Map<ResourceType, Integer> getStrongbox() {
        return strongbox;
    }

    public DevelopmentCard[] getDevelopmentCards() {
        return developmentCards;
    }

    public DevelopmentCard[] getLevel1cards() {
        return level1cards;
    }

    public DevelopmentCard[] getLevel2cards() {
        return level2cards;
    }

    public DevelopmentCard[] getLevel3cards() {
        return level3cards;
    }
}
