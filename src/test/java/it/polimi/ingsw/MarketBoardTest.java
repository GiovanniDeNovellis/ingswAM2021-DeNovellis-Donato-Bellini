package it.polimi.ingsw;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MarketBoardTest {
    @Test
    public void columnMarketGetTest(){
        System.out.println("--COLUMN TEST START--");
        MarketBoard marketBoard = new MarketBoard();
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
    public void rowMarketGetTest(){
        System.out.println("--ROW TEST START--");
        MarketBoard marketBoard = new MarketBoard();
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
    public void mixedTest(){
        System.out.println("--MIXED TEST START--");
        MarketBoard marketBoard = new MarketBoard();
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