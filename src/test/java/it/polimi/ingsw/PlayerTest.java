package it.polimi.ingsw;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {


    @Test
    void addFaithPointsAndCallAudience() throws FileNotFoundException {
        Game game = new Game();
        assertTrue( game.addPlayer("FirstPlayer"));
        assertTrue( game.addPlayer("SecondPlayer"));
        assertTrue( game.addPlayer("ThirdPlayer"));
        assertTrue( game.addPlayer("FourthPlayer"));
        game.getPlayers().get(0).addFaithPointsWithoutCallingAudience(3);
        game.getPlayers().get(1).addFaithPointsWithoutCallingAudience(2);
        game.getPlayers().get(2).addFaithPointsWithoutCallingAudience(5);
        game.getPlayers().get(3).addFaithPointsWithoutCallingAudience(7);
        assertFalse( game.getPlayers().get(0).addFaithPointsAndCallAudience(1) );
        assertTrue( game.getPlayers().get(3).addFaithPointsAndCallAudience(1) );
        assertFalse( game.getPlayers().get(2).addFaithPointsAndCallAudience(3) );
        assertEquals(0, game.getPlayers().get(0).getVictoryPoints());
        assertEquals(0, game.getPlayers().get(1).getVictoryPoints());
        assertEquals(2, game.getPlayers().get(2).getVictoryPoints());
        assertEquals(2, game.getPlayers().get(3).getVictoryPoints());

    }

    @Test
    void callAudience() throws FileNotFoundException {
        Game game = new Game();
        assertTrue( game.addPlayer("FirstPlayer"));
        assertTrue( game.addPlayer("SecondPlayer"));
        assertTrue( game.addPlayer("ThirdPlayer"));
        assertFalse( game.getPlayers().get(0).callAudience() );
        game.getPlayers().get(0).addFaithPointsWithoutCallingAudience(6);
        assertFalse( game.getPlayers().get(0).callAudience() );
        game.getPlayers().get(0).addFaithPointsWithoutCallingAudience(2);
        assertTrue( game.getPlayers().get(0).callAudience() );
        assertEquals(2, game.getPlayers().get(0).getVictoryPoints() );
        assertFalse( game.getPlayers().get(1).callAudience() );
        game.getPlayers().get(1).addFaithPointsWithoutCallingAudience(16);
        assertTrue( game.getPlayers().get(1).callAudience() );
        assertEquals(3, game.getPlayers().get(1).getVictoryPoints() );
        assertFalse( game.getPlayers().get(1).callAudience() );
        assertFalse( game.getPlayers().get(0).callAudience() );
        assertFalse( game.getPlayers().get(2).callAudience() );
        game.getPlayers().get(2).addFaithPointsWithoutCallingAudience(24);
        assertTrue( game.getPlayers().get(2).callAudience() );
        assertEquals(4, game.getPlayers().get(2).getVictoryPoints() );
        assertFalse( game.getPlayers().get(1).callAudience() );
        assertFalse( game.getPlayers().get(0).callAudience() );
        assertFalse( game.getPlayers().get(2).callAudience() );


    }

    @Test
    void calculateVictoryPoints() throws FileNotFoundException {
        Player playerWithZeroPoints = new Player();
        playerWithZeroPoints.getPersonalBoard().insertResources(ResourceType.COINS, 2, 2);
        playerWithZeroPoints.getPersonalBoard().getStrongbox().addResources(ResourceType.SHIELDS, 2);
        playerWithZeroPoints.calculateVictoryPoints();
        assertEquals(0, playerWithZeroPoints.getVictoryPoints() );

        Player playerWithThreePoints = new Player();
        playerWithThreePoints.addFaithPointsWithoutCallingAudience(7);
        playerWithThreePoints.getPersonalBoard().insertResources(ResourceType.COINS, 2, 2);
        playerWithThreePoints.getPersonalBoard().getStrongbox().addResources(ResourceType.SHIELDS, 3);
        playerWithThreePoints.calculateVictoryPoints();
        assertEquals(3, playerWithThreePoints.getVictoryPoints() );

        Player playerWithDevCardsToo = new Player();
        Deckgrid deckgrid = new Deckgrid();
        playerWithDevCardsToo.getPersonalBoard().insertResources(ResourceType.STONES,3,3);
        assertTrue( playerWithDevCardsToo.getPersonalBoard().insertCard(deckgrid.getCards().get(11),1) );
        playerWithDevCardsToo.addFaithPointsWithoutCallingAudience(15);
        playerWithDevCardsToo.getPersonalBoard().getStrongbox().addResources(ResourceType.SHIELDS,1);
        playerWithDevCardsToo.getPersonalBoard().getStrongbox().addResources(ResourceType.COINS,1);
        playerWithDevCardsToo.getPersonalBoard().getStrongbox().addResources(ResourceType.SERVANTS,1);
        playerWithDevCardsToo.getPersonalBoard().getStrongbox().addResources(ResourceType.STONES,1);
        playerWithDevCardsToo.calculateVictoryPoints();
        assertEquals(12, playerWithDevCardsToo.getVictoryPoints() );

        Player playerWithDevCardsLevelTwo = new Player();
        playerWithDevCardsLevelTwo.getPersonalBoard().insertResources(ResourceType.STONES,3,3);
        playerWithDevCardsLevelTwo.getPersonalBoard().getStrongbox().addResources(ResourceType.COINS,6);
        assertTrue( playerWithDevCardsLevelTwo.getPersonalBoard().insertCard(deckgrid.getCards().get(11),1) );
        assertTrue( playerWithDevCardsLevelTwo.getPersonalBoard().insertCard(deckgrid.getCards().get(26),1) );
        playerWithDevCardsLevelTwo.addFaithPointsWithoutCallingAudience(15);
        playerWithDevCardsLevelTwo.getPersonalBoard().getStrongbox().addResources(ResourceType.SHIELDS,1);
        playerWithDevCardsLevelTwo.getPersonalBoard().getStrongbox().addResources(ResourceType.COINS,1);
        playerWithDevCardsLevelTwo.getPersonalBoard().getStrongbox().addResources(ResourceType.SERVANTS,1);
        playerWithDevCardsLevelTwo.getPersonalBoard().getStrongbox().addResources(ResourceType.STONES,1);
        playerWithDevCardsLevelTwo.calculateVictoryPoints();
        assertEquals(20, playerWithDevCardsLevelTwo.getVictoryPoints() );

    }
}