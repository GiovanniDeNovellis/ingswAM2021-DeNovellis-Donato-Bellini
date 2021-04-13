package it.polimi.ingsw;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class MarketBoardTest {
    @Test
    public void columnMarketGetTest() throws FileNotFoundException {
        System.out.println("--COLUMN TEST START--");
        Game g = new Game();
        g.addPlayer("player 1");
        g.addPlayer("player 2");
        g.startMultiplayer();
        MarketBoard marketBoard = new MarketBoard(g);
        assertFalse(marketBoard.getResourcesFromMarket(1,2));
        marketBoard.printMarketGrid();
        System.out.println("\nTaking res from second column\n");
        assertTrue(marketBoard.getResourcesFromMarket(3,1));
        System.out.println(marketBoard.getTemporaryResources().toString());
        marketBoard.printMarketGrid();
        System.out.println("\nTaking res from second column\n");
        assertTrue(marketBoard.getResourcesFromMarket(3,1));
        System.out.println(marketBoard.getTemporaryResources().toString());
        marketBoard.printMarketGrid();
        System.out.println("--COLUMN TEST END--");
    }

    @Test
    public void rowMarketGetTest() throws FileNotFoundException {
        System.out.println("--ROW TEST START--");
        Game g = new Game();
        g.addPlayer("player 1");
        g.addPlayer("player 2");
        g.startMultiplayer();
        MarketBoard marketBoard = new MarketBoard(g);
        assertFalse(marketBoard.getResourcesFromMarket(3,4));
        marketBoard.printMarketGrid();
        System.out.println("\nTaking res from first row\n");
        assertTrue(marketBoard.getResourcesFromMarket(0,4));
        System.out.println(marketBoard.getTemporaryResources().toString());
        marketBoard.printMarketGrid();
        System.out.println("\nTaking res from second row\n");
        assertTrue(marketBoard.getResourcesFromMarket(1,4));
        System.out.println(marketBoard.getTemporaryResources().toString());
        marketBoard.printMarketGrid();
        System.out.println("--ROW TEST END--");
    }

    @Test
    public void mixedTest() throws FileNotFoundException {
        System.out.println("--MIXED TEST START--");
        Game g = new Game();
        g.addPlayer("player 1");
        g.addPlayer("player 2");
        g.startMultiplayer();
        MarketBoard marketBoard = new MarketBoard(g);
        marketBoard.printMarketGrid();
        System.out.println("\nTaking res from third row\n");
        assertTrue(marketBoard.getResourcesFromMarket(2,4));
        System.out.println(marketBoard.getTemporaryResources().toString());
        marketBoard.printMarketGrid();
        System.out.println("\nTaking res from second column\n");
        assertTrue(marketBoard.getResourcesFromMarket(3, 1));
        System.out.println(marketBoard.getTemporaryResources().toString());
        marketBoard.printMarketGrid();
        System.out.println("--MIXED TEST END--");
    }
}