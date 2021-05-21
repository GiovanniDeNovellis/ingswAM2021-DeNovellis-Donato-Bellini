package it.polimi.ingsw;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    @Test
    void singlePlayerTest() throws FileNotFoundException{
        Game game = new Game();
        assertFalse(game.startSinglePlayer());
        game.addPlayer("Player 1");
        assertTrue(game.startSinglePlayer());
        assertEquals(1,game.getPlayers().size());
        game.addPlayer("Player 1");
        assertFalse(game.startSinglePlayer());
        game.addPlayer("Player 2");
        assertFalse(game.startSinglePlayer());
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
        if(game.getMarketBoard().getTemporaryResources().isEmpty())
            return;
        ResourceType res = game.getMarketBoard().getTemporaryResources().keySet().stream().findFirst().get();
        int numOfThatRes = game.getMarketBoard().getTemporaryResources().get(res);
        assertTrue(game.insertResourcesIntoWarehouse(res,numOfThatRes,false));
        assertFalse(game.insertResourcesIntoWarehouse(res,numOfThatRes,false));
        assertFalse(game.insertResourcesIntoWarehouse(res,numOfThatRes+10,false));
        assertFalse(game.insertResourcesIntoWarehouse(res,numOfThatRes-10,false));
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
        game.getCurrentPlayer().insertResourcesIntoWarehouse(ResourceType.SHIELDS,2,2);
        assertEquals(ResourceType.SHIELDS,game.getCurrentPlayer().getPersonalBoard().getWarehouseDepot().getLevel(2).getResourceType());
        assertEquals(2,game.getCurrentPlayer().getPersonalBoard().getWarehouseDepot().getLevel(2).getCurrNumResources());
        game.chooseLeaderCards(1,2);
        assertTrue(game.endTurn());
        game.distributionResourceSecondThird(ResourceType.COINS);
        game.getCurrentPlayer().setCanEndTurn(true);
        game.chooseLeaderCards(1,2);
        assertTrue(game.endTurn());
        if(!game.buyDevelopmentCard(1,Colour.GREEN,1,0, 0)){
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
        game.getCurrentPlayer().insertResourcesIntoWarehouse(ResourceType.STONES,2,2);
        boolean[] dev  = {false,false,false};
        boolean[] lead  = {false,false};
        ResourceType[] obtainedFromLeader = {null, null};
        assertTrue(game.activateProduction(dev, true, lead,ResourceType.STONES,ResourceType.STONES,ResourceType.SHIELDS, obtainedFromLeader ,0,0));
        assertEquals(1,game.getCurrentPlayer().getPersonalBoard().getStrongbox().getNumOf(ResourceType.SHIELDS));
        assertNull(game.getCurrentPlayer().getPersonalBoard().getWarehouseDepot().getLevel(2).getResourceType());
        assertEquals(0,game.getCurrentPlayer().getPersonalBoard().getWarehouseDepot().getLevel(2).getCurrNumResources());
        assertEquals(2,game.getCurrentPlayer().getPersonalBoard().getWarehouseDepot().getLevel(2).getMaxNumResources());
        assertFalse(game.activateProduction(dev, true, lead,ResourceType.STONES,ResourceType.STONES,ResourceType.SHIELDS, obtainedFromLeader,0,0 ));
        assertTrue(game.endTurn());
        boolean[] dev1  = {false,false,false};
        boolean[] lead1  = {false,false};
        assertTrue(game.activateProduction(dev1,true,lead1,ResourceType.COINS,ResourceType.STONES,ResourceType.SERVANTS, obtainedFromLeader,0,0));
        assertEquals(0,game.getCurrentPlayer().getPersonalBoard().getStrongbox().getNumOf(ResourceType.SERVANTS));
        assertEquals(1,game.getCurrentPlayer().getPersonalBoard().getWarehouseDepot().getLevel(1).getCurrNumResources());
        assertEquals(ResourceType.COINS,game.getCurrentPlayer().getPersonalBoard().getWarehouseDepot().getLevel(1).getResourceType());
    }






    //                          --comment line 40 in LeaderCardDeck class to execute those test--
    @Test
    public void LeaderAbilityDiscountAndExtraDepositTest() throws FileNotFoundException {
        Game game = new Game();
        Deckgrid deckgrid = new Deckgrid();
        game.addPlayer("Player1");
        game.addPlayer("Player2");
        game.startMultiplayer();
        assertFalse( game.endTurn() );
        //disabled shuffle on leaderCardDeck for a better testing
        assertTrue( game.chooseLeaderCards(0,3) );
        assertTrue( game.endTurn() );
        assertTrue( game.chooseLeaderCards(0,1) );
        assertTrue( game.distributionResourceSecondThird(ResourceType.COINS));
        assertTrue( game.endTurn() );
        //player n°1 turn:
        assertEquals(1,game.getCurrentPlayer().getPlayerNumber() );
        //manually select of Development cards for a better testing
        //add necessary resources to buy two devCards (needed for playing the first LeaderCard )
        game.getCurrentPlayer().getPersonalBoard().getStrongbox().addResources(ResourceType.STONES, 2);
        game.getCurrentPlayer().getPersonalBoard().getStrongbox().addResources(ResourceType.SHIELDS, 2);
        //add Green DevCard
        assertTrue( game.getCurrentPlayer().insertCard(deckgrid.getCards().get(0),0) );
        //add Yellow DevCard
        assertTrue( game.getCurrentPlayer().insertCard(deckgrid.getCards().get(3),1) );
        //activate first leader card: it needs one yellow DevCard and one green DevCard
        assertTrue( game.activateLeaderCard(0) );
        //activate first leader card ability: discount on servants
        assertTrue( game.activateLeaderAbility(0) );
        //let's buy DevCard n° 2 with 1 servant discount (2 servants for buying that card without discount )
        game.getCurrentPlayer().getPersonalBoard().getStrongbox().addResources(ResourceType.SERVANTS,1);
        assertTrue( game.getCurrentPlayer().insertCard(deckgrid.getCards().get(1),2));
        //discount already used: let's try to buy development card n° 18 with one servant discount (4 needed). Should be false
        game.getCurrentPlayer().getPersonalBoard().getStrongbox().addResources(ResourceType.SERVANTS,3);
        assertFalse( game.getCurrentPlayer().insertCard(deckgrid.getCards().get(17),0));
        game.getCurrentPlayer().getPersonalBoard().getStrongbox().addResources(ResourceType.SERVANTS,1);
        //now should be true: player has all 4 servants resources needed
        assertTrue( game.getCurrentPlayer().insertCard(deckgrid.getCards().get(17),0));
        //manually setCanEndTurn: needed for testing
        game.getCurrentPlayer().setCanEndTurn(true);
        assertTrue( game.endTurn() );
        assertEquals(2,game.getCurrentPlayer().getPlayerNumber() );
        //players has no coins, leader card n° 5 can't be activated
        assertFalse( game.activateLeaderAbility(0) );
        assertFalse( game.activateLeaderCard(0) );
        //insert 5 coins, needed to activate leader card n°5
        game.getCurrentPlayer().getPersonalBoard().getStrongbox().addResources(ResourceType.COINS,5);
        assertTrue( game.activateLeaderCard(0) );
        //activate leader card n°5 ability: create extra dep for two stones
        assertTrue( game.activateLeaderAbility(0) );
        //manually adding resources to extraDep: some tests
        assertFalse( game.getCurrentPlayer().getPersonalBoard().addToExtraDeposit1(ResourceType.STONES, 3 ) );
        assertFalse( game.getCurrentPlayer().getPersonalBoard().addToExtraDeposit1(ResourceType.SHIELDS, 1 ) );
        assertTrue(game.getCurrentPlayer().getPersonalBoard().addToExtraDeposit1(ResourceType.STONES, 2 ));
        game.getCurrentPlayer().getPersonalBoard().setPayUsingExtraDep1(2);
        //trying to buy Development card n°4(it needs 2 stones) using stones located in extraDep
        assertTrue( game.getCurrentPlayer().getPersonalBoard().checkFromExtraDep(1,2));
        assertEquals( ResourceType.STONES, game.getCurrentPlayer().getPersonalBoard().getExtraDeposit1().getResourceType() );
        assertTrue(game.getCurrentPlayer().insertCard(deckgrid.getCards().get(3),1));
        assertEquals( ResourceType.STONES, game.getCurrentPlayer().getPersonalBoard().getExtraDeposit1().getResourceType() );
        assertEquals(0,  game.getCurrentPlayer().getPersonalBoard().getExtraDeposit1().getCurrentQuantity());
        //adding two stones to extra dep and trying base production power using these
        assertTrue(game.getCurrentPlayer().getPersonalBoard().addToExtraDeposit1(ResourceType.STONES, 2 ));
        game.getCurrentPlayer().getPersonalBoard().setPayUsingExtraDep1(2);
        assertTrue( game.getCurrentPlayer().getPersonalBoard().activateProductionFromPersonalBoard(ResourceType.STONES, ResourceType.STONES, ResourceType.SERVANTS));
        assertEquals( ResourceType.STONES, game.getCurrentPlayer().getPersonalBoard().getExtraDeposit1().getResourceType() );
        assertEquals(0,  game.getCurrentPlayer().getPersonalBoard().getExtraDeposit1().getCurrentQuantity());
        //adding one stone to extra dep and trying base production power using that stone and another stone located in warehouse
        assertTrue(game.getCurrentPlayer().getPersonalBoard().addToExtraDeposit1(ResourceType.STONES, 1 ));
        game.getCurrentPlayer().getPersonalBoard().setPayUsingExtraDep1(1);
        assertTrue( game.getCurrentPlayer().insertResourcesIntoWarehouse(ResourceType.STONES,2,1) );
        assertTrue( game.getCurrentPlayer().getPersonalBoard().activateProductionFromPersonalBoard(ResourceType.STONES, ResourceType.STONES, ResourceType.SERVANTS));
        assertEquals( ResourceType.STONES, game.getCurrentPlayer().getPersonalBoard().getExtraDeposit1().getResourceType() );
        assertEquals(0,  game.getCurrentPlayer().getPersonalBoard().getExtraDeposit1().getCurrentQuantity());
        //adding one stone to extra dep and trying base production power using that stone and servant located in warehouse
        assertTrue(game.getCurrentPlayer().getPersonalBoard().addToExtraDeposit1(ResourceType.STONES, 1 ));
        game.getCurrentPlayer().getPersonalBoard().setPayUsingExtraDep1(1);
        assertTrue( game.getCurrentPlayer().insertResourcesIntoWarehouse(ResourceType.SERVANTS,3,1) );
        assertTrue( game.getCurrentPlayer().getPersonalBoard().activateProductionFromPersonalBoard(ResourceType.STONES, ResourceType.SERVANTS, ResourceType.SERVANTS));
        assertEquals( ResourceType.STONES, game.getCurrentPlayer().getPersonalBoard().getExtraDeposit1().getResourceType() );
        assertEquals(0,  game.getCurrentPlayer().getPersonalBoard().getExtraDeposit1().getCurrentQuantity());
        //let's active second extra dep for player2 (it contains servants):
        //leader card n°6 needs 5 stones to be activated
        game.getCurrentPlayer().getPersonalBoard().getStrongbox().addResources(ResourceType.STONES,5);
        assertTrue( game.getCurrentPlayer().activateLeaderCard(1) );
        assertTrue( game.getCurrentPlayer().activateLeaderAbility(1));
        //let's add 2 servants into extradep2
        assertTrue( game.getCurrentPlayer().getPersonalBoard().addToExtraDeposit2(ResourceType.SERVANTS,2));
        game.getCurrentPlayer().getPersonalBoard().setPayUsingExtraDep2(2);
        //try base production using the 2 servants located into extradep2
        assertTrue( game.getCurrentPlayer().getPersonalBoard().activateProductionFromPersonalBoard(ResourceType.SERVANTS, ResourceType.SERVANTS, ResourceType.STONES));
        assertEquals( ResourceType.SERVANTS, game.getCurrentPlayer().getPersonalBoard().getExtraDeposit2().getResourceType() );
        assertEquals(0,  game.getCurrentPlayer().getPersonalBoard().getExtraDeposit2().getCurrentQuantity());
        //try base production power using extradep1 and extradep2
        assertTrue(game.getCurrentPlayer().getPersonalBoard().addToExtraDeposit1(ResourceType.STONES, 1 ));
        game.getCurrentPlayer().getPersonalBoard().setPayUsingExtraDep1(1);
        assertTrue( game.getCurrentPlayer().getPersonalBoard().addToExtraDeposit2(ResourceType.SERVANTS,1));
        game.getCurrentPlayer().getPersonalBoard().setPayUsingExtraDep2(1);
        assertTrue( game.getCurrentPlayer().getPersonalBoard().activateProductionFromPersonalBoard(ResourceType.SERVANTS, ResourceType.STONES, ResourceType.COINS));
        assertEquals( ResourceType.SERVANTS, game.getCurrentPlayer().getPersonalBoard().getExtraDeposit2().getResourceType() );
        assertEquals(0,  game.getCurrentPlayer().getPersonalBoard().getExtraDeposit2().getCurrentQuantity());
        assertEquals( ResourceType.STONES, game.getCurrentPlayer().getPersonalBoard().getExtraDeposit1().getResourceType() );
        assertEquals(0,  game.getCurrentPlayer().getPersonalBoard().getExtraDeposit1().getCurrentQuantity());
        assertEquals(0, game.getCurrentPlayer().getPersonalBoard().getPayUsingExtraDep1() );
        assertEquals(0, game.getCurrentPlayer().getPersonalBoard().getPayUsingExtraDep2() );
        //trying to activate production for dev card n°4(it needs one servant) using one servant located in extradep2
        assertTrue( game.getCurrentPlayer().getPersonalBoard().addToExtraDeposit2(ResourceType.SERVANTS,1));
        game.getCurrentPlayer().getPersonalBoard().setPayUsingExtraDep2(1);
        assertEquals( ResourceType.SERVANTS, game.getCurrentPlayer().getPersonalBoard().getExtraDeposit2().getResourceType() );
        assertTrue( game.getCurrentPlayer().getPersonalBoard().activateProductionFromDevCard(1));
        game.getCurrentPlayer().setCanEndTurn(true);
        assertTrue(game.endTurn());
        //player2 turn:
        //activating second leader card(leader card n° 4:it needs 1 yellow dev card and 1 purple dev card)
        assertTrue( game.activateLeaderCard(1) );
        //activating second leader card ability: discount on coins
        assertTrue( game.activateLeaderAbility(1) );
        //activating first leader card ability: discount on servants
        assertTrue( game.activateLeaderAbility(0) );
        //trying to buy development card n°22 with discount on both resources: it needs 3 servants and 2 coins without discount
        game.getCurrentPlayer().getPersonalBoard().getStrongbox().addResources(ResourceType.SERVANTS,2);
        game.getCurrentPlayer().getPersonalBoard().getStrongbox().addResources(ResourceType.COINS,1);
        assertTrue( game.getCurrentPlayer().insertCard(deckgrid.getCards().get(21),2));
    }

    @Test
    public void whiteTransformationTestSingle() throws FileNotFoundException {
        Game game = new Game();
        Deckgrid deckgrid = new Deckgrid();
        assertTrue(game.addPlayer("First player"));
        assertTrue(game.addPlayer("Second player"));
        assertTrue(game.addPlayer("Third player"));
        assertTrue(game.startMultiplayer());
        assertTrue(game.chooseLeaderCards(0,1));
        assertTrue(game.endTurn());//end 1
        assertTrue(game.chooseLeaderCards(0,1));
        assertTrue(game.distributionResourceSecondThird(ResourceType.STONES));
        assertTrue(game.endTurn());//end 2
        assertFalse(game.endTurn());
        assertTrue(game.chooseLeaderCards(0,1));
        assertFalse(game.chooseLeaderCards(0,1));
        assertTrue(game.distributionResourceSecondThird(ResourceType.COINS));
        assertTrue(game.endTurn()); //end 3
        assertTrue(game.takeResourcesFromMarket(3,1));
        assertTrue(game.endTurn()); //end 1
        assertTrue(game.takeResourcesFromMarket(3,1));
        assertTrue(game.endTurn()); //end 2
        assertTrue(game.getCurrentPlayer().insertResourcesIntoWarehouse(ResourceType.STONES,2,2));
        assertTrue(game.getCurrentPlayer().insertCard(deckgrid.getCards().get(3),0));
        assertTrue(game.getCurrentPlayer().insertResourcesIntoWarehouse(ResourceType.STONES,2,1));
        assertTrue(game.getCurrentPlayer().insertResourcesIntoWarehouse(ResourceType.SHIELDS,3,1));
        assertTrue(game.getCurrentPlayer().insertCard(deckgrid.getCards().get(7),1));
        assertTrue(game.getCurrentPlayer().insertResourcesIntoWarehouse(ResourceType.COINS,2,2));
        assertTrue(game.getCurrentPlayer().insertCard(deckgrid.getCards().get(2),2));
        assertTrue(game.activateLeaderCard(0));
        assertTrue(game.takeResourcesFromMarket(2,4));
        assertEquals(1,game.getCurrentPlayer().getNumTransformationAbility());
        assertTrue(game.getCurrentPlayer().canEndTurn());
        if(game.getMarketBoard().getWhiteMarblesSelected()>0){
            assertFalse(game.endTurn());
            while(game.getMarketBoard().getWhiteMarblesSelected()>0){
                assertFalse(game.endTurn());
                int oldvalue;
                if(game.getMarketBoard().getTemporaryResources().get(ResourceType.SERVANTS)==null) oldvalue=0;
                else oldvalue=game.getMarketBoard().getTemporaryResources().get(ResourceType.SERVANTS);
                System.out.println("Palline bianche rimaste: " + game.getMarketBoard().getWhiteMarblesSelected());
                int oldWhiteMarbles = game.getMarketBoard().getWhiteMarblesSelected();
                assertTrue(game.activateLeaderAbility(0));
                assertEquals((oldvalue+oldWhiteMarbles),game.getMarketBoard().getTemporaryResources().get(ResourceType.SERVANTS));
                assertEquals(0,game.getMarketBoard().getWhiteMarblesSelected());
            }
            System.out.println("Convertite tutte posso finire");
            assertTrue(game.endTurn());
        }
        else{
            assertTrue(game.endTurn());
            System.out.println("No palline bianche");
        }
    }

    @Test
    public void whiteTransformationTestDouble() throws FileNotFoundException {
        Game game = new Game();
        Deckgrid deckgrid = new Deckgrid();
        assertTrue(game.addPlayer("First player"));
        assertTrue(game.addPlayer("Second player"));
        assertTrue(game.addPlayer("Third player"));
        assertTrue(game.startMultiplayer());
        assertTrue(game.chooseLeaderCards(0,1));
        assertTrue(game.endTurn());//end 1
        assertTrue(game.chooseLeaderCards(0,1));
        assertTrue(game.distributionResourceSecondThird(ResourceType.STONES));
        assertTrue(game.endTurn());//end 2
        assertFalse(game.endTurn());
        assertTrue(game.chooseLeaderCards(0,2));
        assertFalse(game.chooseLeaderCards(0,1));
        assertTrue(game.distributionResourceSecondThird(ResourceType.COINS));
        assertTrue(game.endTurn()); //end 3
        assertTrue(game.takeResourcesFromMarket(3,1));
        assertTrue(game.endTurn()); //end 1
        assertTrue(game.takeResourcesFromMarket(3,1));
        assertTrue(game.endTurn()); //end 2
        assertTrue(game.getCurrentPlayer().insertResourcesIntoWarehouse(ResourceType.STONES,2,2));
        assertTrue(game.getCurrentPlayer().insertCard(deckgrid.getCards().get(3),0));
        assertTrue(game.getCurrentPlayer().insertResourcesIntoWarehouse(ResourceType.STONES,2,1));
        assertTrue(game.getCurrentPlayer().insertResourcesIntoWarehouse(ResourceType.SHIELDS,3,1));
        assertTrue(game.getCurrentPlayer().insertCard(deckgrid.getCards().get(7),1));
        assertTrue(game.getCurrentPlayer().insertResourcesIntoWarehouse(ResourceType.COINS,2,2));
        assertTrue(game.getCurrentPlayer().insertCard(deckgrid.getCards().get(2),2));
        assertTrue(game.activateLeaderCard(0));
        assertTrue(game.getCurrentPlayer().insertResourcesIntoWarehouse(ResourceType.COINS,3,3));
        game.getCurrentPlayer().getPersonalBoard().getStrongbox().addResources(ResourceType.COINS,1);
        assertTrue(game.getCurrentPlayer().insertCard(deckgrid.getCards().get(18),0));
        assertTrue(game.activateLeaderCard(1));
        assertFalse(game.activateLeaderCard(0));
        assertFalse(game.activateLeaderCard(1));
        assertTrue(game.takeResourcesFromMarket(2,4));
        assertEquals(2,game.getCurrentPlayer().getNumTransformationAbility());
        assertTrue(game.getCurrentPlayer().canEndTurn());
        if(game.getMarketBoard().getWhiteMarblesSelected()>0){
            int num=1;
            assertFalse(game.endTurn());
            while(game.getMarketBoard().getWhiteMarblesSelected()>0){
                assertFalse(game.endTurn());
                int oldvalue;
                if(num%2==0){
                    if(game.getMarketBoard().getTemporaryResources().get(ResourceType.STONES)==null) oldvalue=0;
                    else oldvalue=game.getMarketBoard().getTemporaryResources().get(ResourceType.STONES);
                    System.out.println("Palline bianche rimaste: " + game.getMarketBoard().getWhiteMarblesSelected());
                    assertTrue(game.activateLeaderAbility(1));
                    assertEquals(oldvalue+1,game.getMarketBoard().getTemporaryResources().get(ResourceType.STONES));
                    System.out.println("Converto in pietre");
                }
                else{
                    if(game.getMarketBoard().getTemporaryResources().get(ResourceType.SERVANTS)==null) oldvalue=0;
                    else oldvalue=game.getMarketBoard().getTemporaryResources().get(ResourceType.SERVANTS);
                    System.out.println("Palline bianche rimaste: " + game.getMarketBoard().getWhiteMarblesSelected());
                    assertTrue(game.activateLeaderAbility(0));
                    assertEquals(oldvalue+1,game.getMarketBoard().getTemporaryResources().get(ResourceType.SERVANTS));
                    System.out.println("Converto in servi");
                }
                num++;
            }
            System.out.println("Convertite tutte posso finire");
            assertTrue(game.endTurn());
        }
        else{
            assertTrue(game.endTurn());
            System.out.println("No palline bianche");
        }
    }

    @Test
    public void extraProductionAbilityTest() throws FileNotFoundException {
        Game game = new Game();
        Deckgrid deckgrid = new Deckgrid();
        assertTrue(game.addPlayer("First player"));
        assertTrue(game.addPlayer("Second player"));
        assertTrue(game.addPlayer("Third player"));
        assertTrue(game.addPlayer("Fourth player"));
        assertTrue(game.startMultiplayer());
        assertTrue(game.chooseLeaderCards(0,1));
        assertTrue(game.endTurn());//end 1
        assertTrue(game.chooseLeaderCards(0,1));
        assertTrue(game.distributionResourceSecondThird(ResourceType.STONES));
        assertTrue(game.endTurn());//end 2
        assertFalse(game.endTurn());
        assertTrue(game.chooseLeaderCards(0,2));
        assertFalse(game.chooseLeaderCards(0,1));
        assertTrue(game.distributionResourceSecondThird(ResourceType.COINS));
        assertTrue(game.endTurn()); //end 3
        assertTrue(game.chooseLeaderCards(0,3));
        assertTrue(game.distributionResourceFourthPlayer(ResourceType.SHIELDS,ResourceType.SHIELDS));
        assertTrue(game.endTurn()); // end 4
        game.getCurrentPlayer().setCanEndTurn(true);
        assertTrue(game.endTurn()); //end 1
        game.getCurrentPlayer().setCanEndTurn(true);
        assertTrue(game.endTurn()); //end 2
        game.getCurrentPlayer().setCanEndTurn(true);
        assertTrue(game.endTurn());//end 3
        assertTrue(game.getCurrentPlayer().insertCard(deckgrid.getCards().get(0),0));
        assertTrue(game.getCurrentPlayer().insertResourcesIntoWarehouse(ResourceType.SHIELDS,3,3));
        assertTrue(game.getCurrentPlayer().insertResourcesIntoWarehouse(ResourceType.SERVANTS,2,2));
        assertFalse(game.getCurrentPlayer().insertCard(deckgrid.getCards().get(0),0));
        assertTrue(game.getCurrentPlayer().insertCard(deckgrid.getCards().get(20),0));
        assertTrue(game.getCurrentPlayer().insertResourcesIntoWarehouse(ResourceType.COINS,1,1));
        assertTrue(game.activateLeaderCard(1));
        game.activateLeaderAbility(1);
        int oldFaith=game.getCurrentPlayer().getFaithPoints();
        boolean[] dev  = {false,false,false};
        boolean[] lead  = {false,true};
        ResourceType[] obtRes  = {null,ResourceType.STONES};
        assertTrue(game.activateProduction(dev,false,lead,null,null,null,obtRes,0,0));
        assertEquals(1,game.getCurrentPlayer().getPersonalBoard().getStrongbox().getNumOf(ResourceType.STONES));
        assertEquals(oldFaith+1,game.getCurrentPlayer().getFaithPoints());
    }

    @Test
    public void extraProductionAbilityTestDouble() throws FileNotFoundException {
        Game game = new Game();
        Deckgrid deckgrid = new Deckgrid();
        assertTrue(game.addPlayer("First player"));
        assertTrue(game.addPlayer("Second player"));
        assertTrue(game.addPlayer("Third player"));
        assertTrue(game.addPlayer("Fourth player"));
        assertTrue(game.startMultiplayer());
        assertTrue(game.chooseLeaderCards(0,1));
        assertTrue(game.endTurn());//end 1
        assertTrue(game.chooseLeaderCards(0,1));
        assertTrue(game.distributionResourceSecondThird(ResourceType.STONES));
        assertTrue(game.endTurn());//end 2
        assertFalse(game.endTurn());
        assertTrue(game.chooseLeaderCards(0,2));
        assertFalse(game.chooseLeaderCards(0,1));
        assertTrue(game.distributionResourceSecondThird(ResourceType.COINS));
        assertTrue(game.endTurn()); //end 3
        assertTrue(game.chooseLeaderCards(0,3));
        assertTrue(game.distributionResourceFourthPlayer(ResourceType.SHIELDS,ResourceType.SHIELDS));
        assertTrue(game.endTurn()); // end 4
        game.getCurrentPlayer().setCanEndTurn(true);
        assertTrue(game.endTurn()); //end 1
        game.getCurrentPlayer().setCanEndTurn(true);
        assertTrue(game.endTurn()); //end 2
        game.getCurrentPlayer().setCanEndTurn(true);
        assertTrue(game.endTurn());//end 3
        assertTrue(game.getCurrentPlayer().insertCard(deckgrid.getCards().get(0),0));
        assertTrue(game.getCurrentPlayer().insertResourcesIntoWarehouse(ResourceType.SHIELDS,3,3));
        assertTrue(game.getCurrentPlayer().insertResourcesIntoWarehouse(ResourceType.SERVANTS,2,2));
        assertFalse(game.getCurrentPlayer().insertCard(deckgrid.getCards().get(0),0));
        assertTrue(game.getCurrentPlayer().insertCard(deckgrid.getCards().get(20),0));
        assertTrue(game.activateLeaderCard(1));
        assertTrue(game.getCurrentPlayer().insertResourcesIntoWarehouse(ResourceType.SERVANTS,2,2));
        assertFalse(game.getCurrentPlayer().insertCard(deckgrid.getCards().get(1),0));
        assertTrue(game.getCurrentPlayer().insertCard(deckgrid.getCards().get(1),1));
        assertTrue(game.getCurrentPlayer().insertResourcesIntoWarehouse(ResourceType.STONES,3,3));
        game.getCurrentPlayer().getPersonalBoard().getStrongbox().addResources(ResourceType.STONES,1);
        assertTrue(game.getCurrentPlayer().insertCard(deckgrid.getCards().get(19),1));
        assertTrue(game.activateLeaderCard(0));
        assertTrue(game.activateLeaderAbility(0));
        assertTrue(game.activateLeaderAbility(1));
        assertTrue(game.getCurrentPlayer().insertResourcesIntoWarehouse(ResourceType.COINS,1,1));
        assertTrue(game.getCurrentPlayer().insertResourcesIntoWarehouse(ResourceType.SHIELDS,2,1));
        int oldFaith=game.getCurrentPlayer().getFaithPoints();
        boolean[] dev  = {false,false,false};
        boolean[] lead  = {true,true};
        ResourceType[] obtRes  = {ResourceType.COINS,ResourceType.STONES};
        assertTrue(game.activateProduction(dev,false,lead,null,null,null,obtRes,0,0));
        assertEquals(1,game.getCurrentPlayer().getPersonalBoard().getStrongbox().getNumOf(ResourceType.STONES));
        assertEquals(1,game.getCurrentPlayer().getPersonalBoard().getStrongbox().getNumOf(ResourceType.COINS));
        assertEquals(oldFaith+2,game.getCurrentPlayer().getFaithPoints());
    }

}