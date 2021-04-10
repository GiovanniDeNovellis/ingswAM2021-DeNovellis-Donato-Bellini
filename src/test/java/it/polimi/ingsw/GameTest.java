package it.polimi.ingsw;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {


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
}