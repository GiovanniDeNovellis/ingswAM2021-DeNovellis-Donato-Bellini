package it.polimi.ingsw;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    @Test
    void singlePlayerTest() throws FileNotFoundException{
        Game game = new Game();
        assertFalse(game.startSingleplayer());
        game.addPlayer("Player 1");
        assertTrue(game.startSingleplayer());
        assertEquals(1,game.getPlayers().size());
        game.addPlayer("Player 1");
        assertFalse(game.startSingleplayer());
        game.addPlayer("Player 2");
        assertFalse(game.startSingleplayer());
    }

    @Test
    void multiPlayerTest() throws FileNotFoundException{
        Game game = new Game();
        assertFalse(game.startMultiplayer());
        assertTrue(game.addPlayer("Player 1"));
        assertTrue(game.addPlayer("Player 2"));
        assertTrue(game.addPlayer("Player 3"));
        assertTrue(game.addPlayer("Player 4"));
        assertTrue(game.startMultiplayer());
        //Player 1 turn
        assertEquals(0,game.getCurrentPlayer().getFaithPoints());
        game.chooseLeaderCards(1,2);
        assertTrue(game.endTurn());
        //Player 2 turn
        assertEquals(0,game.getCurrentPlayer().getFaithPoints());
        game.distributionResourceSecondThird(ResourceType.SHIELDS);
        game.chooseLeaderCards(1,2);
        assertTrue(game.endTurn());
        //Player 3 turn
        assertEquals(1,game.getCurrentPlayer().getFaithPoints());
        game.distributionResourceSecondThird(ResourceType.SHIELDS);
        game.chooseLeaderCards(1,2);
        assertTrue(game.endTurn());
        //Player 4 turn
        assertEquals(1,game.getCurrentPlayer().getFaithPoints());
        game.distributionResourceFourthPlayer(ResourceType.SHIELDS,ResourceType.COINS);
        game.chooseLeaderCards(1,2);
        assertTrue(game.endTurn());

        assertFalse(game.addPlayer("Player 5"));
        assertFalse(game.startMultiplayer());
    }

    @Test
    void insertResourcesIntoWarehouse () throws FileNotFoundException{
        Game game = new Game();
        game.addPlayer("Player 1");
        game.addPlayer("Player 2");
        game.addPlayer("Player 3");
        game.startMultiplayer();
        game.chooseLeaderCards(1,2);
        game.endTurn();
        game.distributionResourceSecondThird(ResourceType.SHIELDS);
        game.chooseLeaderCards(1,2);
        game.endTurn();
        game.distributionResourceSecondThird(ResourceType.SHIELDS);
        game.chooseLeaderCards(1,2);
        game.endTurn();
        assertTrue(game.takeResourcesFromMarket(3,1));
        ResourceType res = game.getMarketBoard().getTemporaryResources().firstKey();
        int numOfThatRes = game.getMarketBoard().getTemporaryResources().get(res);
        assertTrue(game.insertResourcesIntoWarehouse(res,numOfThatRes));
        assertFalse(game.insertResourcesIntoWarehouse(res,numOfThatRes));
        assertFalse(game.insertResourcesIntoWarehouse(res,numOfThatRes+10));
        assertFalse(game.insertResourcesIntoWarehouse(res,numOfThatRes-10));
    }


    @Test
    void addPlayer() throws FileNotFoundException {
        Game game = new Game();
        assertTrue( game.addPlayer("FirstPlayer"));
        assertTrue( game.addPlayer("SecondPlayer"));
        assertTrue( game.addPlayer("ThirdPlayer"));
        assertTrue( game.addPlayer("FourthPlayer"));
        assertFalse( game.addPlayer("FifthPlayer"));
        assertEquals( "FirstPlayer", game.getPlayers().get(0).getNickname() );
        assertEquals( "SecondPlayer", game.getPlayers().get(1).getNickname() );
        assertEquals( "ThirdPlayer", game.getPlayers().get(2).getNickname() );
        assertEquals( "FourthPlayer", game.getPlayers().get(3).getNickname() );
        assertEquals(4, game.getPlayers().size());
    }

    @Test
    void firstAudience() throws FileNotFoundException {
        Game game = new Game();
        assertTrue( game.addPlayer("FirstPlayer"));
        assertTrue( game.addPlayer("SecondPlayer"));
        assertTrue( game.addPlayer("ThirdPlayer"));
        assertTrue( game.addPlayer("FourthPlayer"));
        game.getPlayers().get(0).addFaithPointsWithoutCallingAudience(4);
        game.getPlayers().get(1).addFaithPointsWithoutCallingAudience(2);
        game.getPlayers().get(2).addFaithPointsWithoutCallingAudience(5);
        game.getPlayers().get(3).addFaithPointsWithoutCallingAudience(8);
        assertTrue(game.firstAudience());
        assertEquals(4, game.getPlayers().get(0).getFaithPoints());
        assertEquals(2, game.getPlayers().get(1).getFaithPoints());
        assertEquals(5, game.getPlayers().get(2).getFaithPoints());
        assertEquals(8, game.getPlayers().get(3).getFaithPoints());
        assertEquals(0, game.getPlayers().get(0).getVictoryPoints());
        assertEquals(0, game.getPlayers().get(1).getVictoryPoints());
        assertEquals(2, game.getPlayers().get(2).getVictoryPoints());
        assertEquals(2, game.getPlayers().get(3).getVictoryPoints());
        assertFalse(game.firstAudience());
    }

    @Test
    void secondAudience() throws FileNotFoundException {
        Game game = new Game();
        assertTrue( game.addPlayer("FirstPlayer"));
        assertTrue( game.addPlayer("SecondPlayer"));
        assertTrue( game.addPlayer("ThirdPlayer"));
        assertTrue( game.addPlayer("FourthPlayer"));
        game.getPlayers().get(0).addFaithPointsWithoutCallingAudience(12);
        game.getPlayers().get(1).addFaithPointsWithoutCallingAudience(14);
        game.getPlayers().get(2).addFaithPointsWithoutCallingAudience(16);
        game.getPlayers().get(3).addFaithPointsWithoutCallingAudience(8);
        assertTrue(game.secondAudience());
        assertEquals(3, game.getPlayers().get(0).getVictoryPoints());
        assertEquals(3, game.getPlayers().get(1).getVictoryPoints());
        assertEquals(3, game.getPlayers().get(2).getVictoryPoints());
        assertEquals(0, game.getPlayers().get(3).getVictoryPoints());
        assertFalse(game.secondAudience());
        assertFalse(game.secondAudience());
    }

    @Test
    void thirdAudience() throws FileNotFoundException {
        Game game = new Game();
        assertTrue( game.addPlayer("FirstPlayer"));
        assertTrue( game.addPlayer("SecondPlayer"));
        assertTrue( game.addPlayer("ThirdPlayer"));
        assertTrue( game.addPlayer("FourthPlayer"));
        game.getPlayers().get(0).addFaithPointsWithoutCallingAudience(19);
        game.getPlayers().get(1).addFaithPointsWithoutCallingAudience(21);
        game.getPlayers().get(2).addFaithPointsWithoutCallingAudience(24);
        game.getPlayers().get(3).addFaithPointsWithoutCallingAudience(18);
        assertTrue(game.thirdAudience());
        assertEquals(4, game.getPlayers().get(0).getVictoryPoints());
        assertEquals(4, game.getPlayers().get(1).getVictoryPoints());
        assertEquals(4, game.getPlayers().get(2).getVictoryPoints());
        assertEquals(0, game.getPlayers().get(3).getVictoryPoints());
        assertFalse(game.thirdAudience());
        assertFalse(game.thirdAudience());
    }

    @Test
    public void buyDevTest() throws FileNotFoundException {
        Game game = new Game();
        assertTrue( game.addPlayer("FirstPlayer"));
        assertTrue( game.addPlayer("SecondPlayer"));
        game.startMultiplayer();
        game.getCurrentPlayer().insertResources(ResourceType.SHIELDS,2,2);
        assertEquals(ResourceType.SHIELDS,game.getCurrentPlayer().getPersonalBoard().getWarehouseDepot().getLevel(2).getResourceType());
        assertEquals(2,game.getCurrentPlayer().getPersonalBoard().getWarehouseDepot().getLevel(2).getCurrNumResources());
        game.chooseLeaderCards(1,2);
        assertTrue(game.endTurn());
        game.distributionResourceSecondThird(ResourceType.COINS);
        game.getCurrentPlayer().setCanEndTurn(true);
        game.chooseLeaderCards(1,2);
        assertTrue(game.endTurn());
        if(!game.buyDevelopmentCard(1,Colour.GREEN,1)){
            assertEquals(ResourceType.SHIELDS,game.getCurrentPlayer().getPersonalBoard().getWarehouseDepot().getLevel(2).getResourceType());
            assertEquals(2,game.getCurrentPlayer().getPersonalBoard().getWarehouseDepot().getLevel(2).getCurrNumResources());
            assertEquals(2,game.getCurrentPlayer().getPersonalBoard().getWarehouseDepot().getLevel(2).getMaxNumResources());
            System.out.println("Carta non comprata");
        }
        else{
            assertNull(game.getCurrentPlayer().getPersonalBoard().getWarehouseDepot().getLevel(2).getResourceType());
            assertEquals(0,game.getCurrentPlayer().getPersonalBoard().getWarehouseDepot().getLevel(2).getCurrNumResources());
            System.out.println("Carta comprata");
        }
    }

    @Test
    public void distributionSecondThirdTest() throws FileNotFoundException {
        Game game = new Game();
        game.addPlayer("First player");
        game.addPlayer("Second player");
        game.addPlayer("Third player");
        assertTrue(game.startMultiplayer());
        game.chooseLeaderCards(1,2);
        assertTrue(game.endTurn());
        game.chooseLeaderCards(1,2);
        assertFalse(game.endTurn());
        assertFalse(game.distributionResourceFourthPlayer(ResourceType.COINS,ResourceType.COINS));
        assertTrue(game.distributionResourceSecondThird(ResourceType.SHIELDS));
        assertEquals(ResourceType.SHIELDS,game.getCurrentPlayer().getPersonalBoard().getWarehouseDepot().getLevel(1).getResourceType());
        assertEquals(1,game.getCurrentPlayer().getPersonalBoard().getWarehouseDepot().getLevel(1).getCurrNumResources());
        assertEquals(1,game.getCurrentPlayer().getPersonalBoard().getWarehouseDepot().getLevel(1).getMaxNumResources());
        game.chooseLeaderCards(1,2);
        assertTrue(game.endTurn());
        assertTrue(game.distributionResourceSecondThird(ResourceType.STONES));
        game.chooseLeaderCards(1,2);
        assertTrue(game.endTurn());
        assertFalse(game.distributionResourceSecondThird(ResourceType.SERVANTS));
    }

    @Test
    public void distributionFourthDiffTest() throws FileNotFoundException {
        Game game = new Game();
        game.addPlayer("First player");
        game.addPlayer("Second player");
        game.addPlayer("Third player");
        game.addPlayer("Fourth player");
        assertTrue(game.startMultiplayer());
        game.chooseLeaderCards(1,2);
        assertTrue(game.endTurn());
        assertTrue(game.distributionResourceSecondThird(ResourceType.STONES));
        game.chooseLeaderCards(1,2);
        assertTrue(game.endTurn());
        assertTrue(game.distributionResourceSecondThird(ResourceType.STONES));
        game.chooseLeaderCards(1,2);
        assertTrue(game.endTurn());
        assertEquals(4,game.getCurrentPlayer().getPlayerNumber());
        game.chooseLeaderCards(1,2);
        assertFalse(game.endTurn());
        assertFalse(game.distributionResourceSecondThird(ResourceType.SERVANTS));
        assertTrue(game.distributionResourceFourthPlayer(ResourceType.STONES,ResourceType.SHIELDS));
        assertEquals(ResourceType.STONES,game.getCurrentPlayer().getPersonalBoard().getWarehouseDepot().getLevel(1).getResourceType());
        assertEquals(1,game.getCurrentPlayer().getPersonalBoard().getWarehouseDepot().getLevel(1).getCurrNumResources());
        assertEquals(1,game.getCurrentPlayer().getPersonalBoard().getWarehouseDepot().getLevel(1).getMaxNumResources());
        assertEquals(ResourceType.SHIELDS,game.getCurrentPlayer().getPersonalBoard().getWarehouseDepot().getLevel(2).getResourceType());
        assertEquals(1,game.getCurrentPlayer().getPersonalBoard().getWarehouseDepot().getLevel(2).getCurrNumResources());
        assertEquals(2,game.getCurrentPlayer().getPersonalBoard().getWarehouseDepot().getLevel(2).getMaxNumResources());
        assertTrue(game.endTurn());
        assertEquals(1,game.getCurrentPlayer().getPlayerNumber());
        game.getCurrentPlayer().setCanEndTurn(true);
        assertTrue(game.endTurn());
        assertFalse(game.distributionResourceSecondThird(ResourceType.SERVANTS));
        assertFalse(game.endTurn());
    }

    @Test
    public void endGameDiffVictoryPointsTest() throws FileNotFoundException {
        Game game = new Game();
        game.addPlayer("First player");
        game.addPlayer("Second player");
        game.addPlayer("Third player");
        game.addPlayer("Fourth player");
        assertTrue(game.startMultiplayer());
        game.getCurrentPlayer().addVictoryPoints(20);
        game.chooseLeaderCards(1,2);
        assertTrue(game.endTurn());
        assertTrue(game.distributionResourceSecondThird(ResourceType.COINS));
        game.chooseLeaderCards(1,2);
        assertTrue(game.endTurn());
        assertTrue(game.distributionResourceSecondThird(ResourceType.COINS));
        game.chooseLeaderCards(1,2);
        assertTrue(game.endTurn());
        assertFalse(game.distributionResourceSecondThird(ResourceType.COINS));
        assertTrue(game.distributionResourceFourthPlayer(ResourceType.STONES,ResourceType.SHIELDS));
        assertNull(game.getWinnerPlayer());
        game.setEndGame(true);
        game.chooseLeaderCards(1,2);
        assertTrue(game.endTurn());
        assertEquals(1,game.getCurrentPlayer().getPlayerNumber());
        assertNotNull(game.getWinnerPlayer());
        assertEquals(1,game.getWinnerPlayerNumber());
        assertEquals(1,game.getWinnerPlayer().getPlayerNumber());
    }

    @Test
    public void endGameSameVictoryPointsTest() throws FileNotFoundException {
        Game game = new Game();
        game.addPlayer("First player");
        game.addPlayer("Second player");
        game.addPlayer("Third player");
        assertTrue(game.startMultiplayer());
        game.chooseLeaderCards(1,2);
        assertTrue(game.endTurn());
        assertFalse(game.endTurn());
        assertTrue(game.distributionResourceSecondThird(ResourceType.STONES));
        assertFalse(game.distributionResourceSecondThird(ResourceType.SHIELDS));
        assertEquals(ResourceType.STONES, game.getCurrentPlayer().getPersonalBoard().getWarehouseDepot().getLevel(1).getResourceType());
        assertEquals(1, game.getCurrentPlayer().getPersonalBoard().getWarehouseDepot().getLevel(1).getCurrNumResources());
        assertEquals(1, game.getCurrentPlayer().getPersonalBoard().getWarehouseDepot().getLevel(1).getMaxNumResources());
        assertNull(game.getWinnerPlayer());
        game.chooseLeaderCards(1,2);
        assertTrue(game.endTurn());
        game.distributionResourceSecondThird(ResourceType.STONES);
        game.setEndGame(true);
        assertEquals(3,game.getCurrentPlayer().getPlayerNumber());
        game.chooseLeaderCards(1,2);
        assertTrue(game.endTurn());
        assertEquals(1,game.getCurrentPlayer().getPlayerNumber());
        assertEquals(2,game.getWinnerPlayerNumber());
        assertNotNull(game.getWinnerPlayer());
    }

    @Test
    public void activateProductionPersonalBoardTest() throws FileNotFoundException {
        Game game = new Game();
        game.addPlayer("First player");
        game.addPlayer("Second player");
        game.startMultiplayer();
        game.chooseLeaderCards(1,2);
        assertTrue(game.endTurn());
        assertTrue(game.distributionResourceSecondThird(ResourceType.COINS));
        game.chooseLeaderCards(1,2);
        assertTrue(game.endTurn());
        game.getCurrentPlayer().insertResources(ResourceType.STONES,2,2);
        boolean[] dev  = {false,false,false};
        boolean[] lead  = {false,false};
        ResourceType[] obtainedFromLeader = {null, null};
        assertTrue(game.activateProduction(dev, true, lead,ResourceType.STONES,ResourceType.STONES,ResourceType.SHIELDS, obtainedFromLeader ));
        assertEquals(1,game.getCurrentPlayer().getPersonalBoard().getStrongbox().getNumOf(ResourceType.SHIELDS));
        assertNull(game.getCurrentPlayer().getPersonalBoard().getWarehouseDepot().getLevel(2).getResourceType());
        assertEquals(0,game.getCurrentPlayer().getPersonalBoard().getWarehouseDepot().getLevel(2).getCurrNumResources());
        assertEquals(2,game.getCurrentPlayer().getPersonalBoard().getWarehouseDepot().getLevel(2).getMaxNumResources());
        assertFalse(game.activateProduction(dev, true, lead,ResourceType.STONES,ResourceType.STONES,ResourceType.SHIELDS, obtainedFromLeader ));
        assertTrue(game.endTurn());
        boolean[] dev1  = {false,false,false};
        boolean[] lead1  = {false,false};
        assertTrue(game.activateProduction(dev1,true,lead1,ResourceType.COINS,ResourceType.STONES,ResourceType.SERVANTS, obtainedFromLeader));
        assertEquals(0,game.getCurrentPlayer().getPersonalBoard().getStrongbox().getNumOf(ResourceType.SERVANTS));
        assertEquals(1,game.getCurrentPlayer().getPersonalBoard().getWarehouseDepot().getLevel(1).getCurrNumResources());
        assertEquals(ResourceType.COINS,game.getCurrentPlayer().getPersonalBoard().getWarehouseDepot().getLevel(1).getResourceType());
    }
}