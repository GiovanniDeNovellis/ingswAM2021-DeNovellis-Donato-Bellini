package it.polimi.ingsw.View.Printers;

import it.polimi.ingsw.Colour;
import it.polimi.ingsw.Marble;
import it.polimi.ingsw.ResourceType;
import it.polimi.ingsw.View.Colours;

import java.util.HashMap;
import java.util.Map;

public class MarketBoardPrinter implements Printable {
    private String[][] marbleGrid;
    private String marbleOut;
    private Map<ResourceType,Integer> temporaryResources = new HashMap<>();
    private boolean built=false;
    private final Map<String,Colours> marbleColours = new HashMap<>();
    private final Map<ResourceType, Colours> resourceTypeColoursMap = new HashMap<>();

    public MarketBoardPrinter() {
        //marbleColours.put("white",Colours.ANSI_WHITE);
        marbleColours.put("blue",Colours.ANSI_BLUE);
        marbleColours.put("grey",Colours.ANSI_WHITE);
        marbleColours.put("yellow",Colours.ANSI_YELLOW);
        marbleColours.put("purple",Colours.ANSI_PURPLE);
        marbleColours.put("red", Colours.ANSI_RED);
        resourceTypeColoursMap.put(ResourceType.COINS,Colours.ANSI_YELLOW);
        resourceTypeColoursMap.put(ResourceType.SERVANTS, Colours.ANSI_PURPLE);
        resourceTypeColoursMap.put(ResourceType.SHIELDS,Colours.ANSI_CYAN);
        resourceTypeColoursMap.put(ResourceType.STONES,Colours.ANSI_WHITE);
    }

    @Override
    public void print(String whatIHaveToPrint) {
        switch (whatIHaveToPrint){
            case "marketboard":
                printMarketBoard();
                break;
            case "temporaryResources":
                printTempResources();
                break;
        }
    }

    public void setMarbleGrid(String[][] marbleGrid) {
        if(!built)
            built=true;
        this.marbleGrid = marbleGrid;
    }

    public void setMarbleOut(String marbleOut) {
        if(!built)
            built=true;
        this.marbleOut = marbleOut;
    }

    public void setTemporaryResources(Map<ResourceType, Integer> temporaryResources) {
        this.temporaryResources = temporaryResources;
    }

    public Map<ResourceType, Integer> getTemporaryResources() {
        return temporaryResources;
    }

    private void printMarketBoard(){
        if(marbleGrid==null){
            System.out.println("MarketBoard not present");
            return;
        }
        System.out.println("MarketBoard:");
        int j;
        for(int i=0; i<3; i++){
            System.out.println("\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550 \u2550");
            for( j=0; j<4; j++){
                String marble = marbleGrid[i][j];
                for( int h = marble.length(); h<6; h++ ){
                    marble += " ";
                }
                if(marbleGrid[i][j].equals("white"))
                    System.out.print(Colours.RESET + "\u2551" + Colours.RESET + marble + Colours.RESET);
                else
                    System.out.print(Colours.RESET + "\u2551" + marbleColours.get(marbleGrid[i][j]).escape() + marble + Colours.RESET);
            }
            System.out.print(Colours.RESET + "\u2551");
            System.out.println("\t(" + i +"," + j + ")");
        }
        System.out.println("\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550 \u2550");
        System.out.println(" \u2551   \u2551  \u2551   \u2551  \u2551   \u2551  \u2551   \u2551  ");
        System.out.println(" (3,0)  (3,1)  (3,2)  (3,3)");
        if(marbleOut.equals("white"))
            System.out.print("Out: " + Colours.RESET + marbleOut + Colours.RESET + "\n");
        else
            System.out.print("Out: " + marbleColours.get(marbleOut).escape() + marbleOut + Colours.RESET + "\n");
    }

    private void printTempResources(){
        System.out.println("Temporary resources: ");
        for(ResourceType r : temporaryResources.keySet()){
            System.out.println(resourceTypeColoursMap.get(r).escape() + "\u2550\u2550\u2550 " + r + ":" + temporaryResources.get(r) + Colours.RESET);
        }
    }
}
