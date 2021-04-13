package it.polimi.ingsw;

import it.polimi.ingsw.MarbleTypes.FaithMarbleType;
import it.polimi.ingsw.MarbleTypes.WareHouseMarbleType;
import it.polimi.ingsw.MarbleTypes.WhiteMarbleType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeMap;

/**
 * The board where all the Marbles are stored.
 * The player can get the resources giving a row or
 * a column and then has to choose which ones to store
 * or discard.
 */
public class MarketBoard {

    /**
     * The grid of the marbles.
     */
    private Marble[][] marketGrid = new Marble[3][4];
    /**
     * The marble which is out of the grid. Will be inserted
     * into the grid when taking resources again.
     */
    private Marble marbleOut;
    /**
     * A map containing the resources chosen by the player.
     * The player can store them or discard them. If he chooses to discard
     * a resource, all the other players will get one FaithPoint.
     */
    private TreeMap<ResourceType, Integer> temporaryResources = new TreeMap<>();
    private Game game;
    private int whiteMarblesSelected=0;

    /**
     * Builds the marketBoard with all the Marbles
     * in a random position.
     */
    public MarketBoard(Game game){
        ArrayList<Marble> marbles = createMarbles();
        for(int i=0; i<3; i++){
            for(int j=0; j<4; j++){
                marketGrid[i][j] = marbles.get(0);
                marbles.remove(0);
            }
        }
        this.game=game;
        marbleOut = marbles.get(0);
    }

    /**
     * @return An ArrayList of marbles in a random position.
     */
    private ArrayList<Marble> createMarbles(){
        ArrayList<Marble> marbles = new ArrayList<>();
        marbles.add(new Marble(new WhiteMarbleType(), "white"));
        marbles.add(new Marble(new WhiteMarbleType(), "white"));
        marbles.add(new Marble(new WhiteMarbleType(), "white"));
        marbles.add(new Marble(new WhiteMarbleType(), "white"));
        marbles.add(new Marble(new WareHouseMarbleType(ResourceType.SHIELDS), "blue"));
        marbles.add(new Marble(new WareHouseMarbleType(ResourceType.SHIELDS), "blue"));
        marbles.add(new Marble(new WareHouseMarbleType(ResourceType.STONES), "grey"));
        marbles.add(new Marble(new WareHouseMarbleType(ResourceType.STONES), "grey"));
        marbles.add(new Marble(new WareHouseMarbleType(ResourceType.COINS), "yellow"));
        marbles.add(new Marble(new WareHouseMarbleType(ResourceType.COINS), "yellow"));
        marbles.add(new Marble(new WareHouseMarbleType(ResourceType.SERVANTS), "purple"));
        marbles.add(new Marble(new WareHouseMarbleType(ResourceType.SERVANTS), "purple"));
        marbles.add(new Marble(new FaithMarbleType(), "red"));
        Collections.shuffle(marbles);
        return marbles;
    }

    /**
     * Method used to take resources from the market. Calls the onSelection
     * method for every marble in the selected row or column and shifts all the marbles.
     * @param startrow The selected row.
     * @param startcol The selected column.
     * @return False if the coordinates given are invalid.
     */
    public boolean getResourcesFromMarket(int startrow, int startcol){
        whiteMarblesSelected=0;
        temporaryResources.clear();
        if(startrow!=3 && startcol!=4) return false;
        else if(startrow==3 && startcol==4) return false;
        else if(startrow>3 || startrow<0 || startcol >4 || startcol<0) return false;
        else if(startrow==3){
            for(int i=startrow-1; i>=0; i--){
                marketGrid[i][startcol].onSelection(this);
            }
            Marble temp = marbleOut;
            marbleOut=marketGrid[0][startcol];
            marketGrid[0][startcol]=marketGrid[1][startcol];
            marketGrid[1][startcol]=marketGrid[2][startcol];
            marketGrid[2][startcol]=temp;
        }
        else {
            for(int i=startcol-1; i>=0; i--){
                marketGrid[startrow][i].onSelection(this);
            }
            Marble temp = marbleOut;
            marbleOut=marketGrid[startrow][0];
            marketGrid[startrow][0]=marketGrid[startrow][1];
            marketGrid[startrow][1]=marketGrid[startrow][2];
            marketGrid[startrow][2]=marketGrid[startrow][3];
            marketGrid[startrow][3]=temp;
        }
        return true;
    }

    /**
     * @return The list of the resources selected by the player.
     */
    public TreeMap<ResourceType, Integer> getTemporaryResources() {
        return temporaryResources;
    }

    /*
    /**
     * Discards the resources left in the TemporaryResources list.

    public void discardTemporaryResources(){
        for(ResourceType r: temporaryResources.keySet()){
            //TODO AGGIUNGI PUNTI FEDE A TUTTI I GIOCATORI TRANNE IL CORRENTE
        }
        temporaryResources.clear();
    }
    */

    /**
     * Testing method used for printing the MarketGrid.
     */
    public void printMarketGrid(){
        for(int i=0;i<3;i++){
            for(int j=0;j<4;j++){
                System.out.print(marketGrid[i][j].getColour() + "  ");
            }
            System.out.println("\n");
        }
        System.out.println("Out: " + marbleOut.getColour());
    }

    public Game getGame() {
        return game;
    }

    public int getWhiteMarblesSelected() {
        return whiteMarblesSelected;
    }

    public void setWhiteMarblesSelected(int whiteMarblesSelected) {
        this.whiteMarblesSelected = whiteMarblesSelected;
    }

    //
}
