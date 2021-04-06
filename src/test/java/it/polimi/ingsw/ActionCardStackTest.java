package it.polimi.ingsw;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import javax.swing.*;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class ActionCardStackTest {

    @Test
    void fillTest() throws FileNotFoundException {
        Deckgrid d = new Deckgrid();
        ActionCardStack c = new ActionCardStack(d);
        assertTrue(c.getCards()[0] instanceof MoveAndShuffle);
        assertTrue(c.getCards()[1] instanceof Move);
        assertTrue(c.getCards()[2] instanceof Move);
        assertTrue(c.getCards()[3] instanceof RemoveDevCard);
        assertTrue(c.getCards()[4] instanceof RemoveDevCard);
        assertTrue(c.getCards()[5] instanceof RemoveDevCard);
        assertTrue(c.getCards()[6] instanceof RemoveDevCard);
        assertEquals(7,c.getCards().length);
    }

    @Test
    void shuffleTest() throws FileNotFoundException {
        Deckgrid d = new Deckgrid();
        ActionCardStack c = new ActionCardStack(d);
        c.Shuffle();
        assertEquals(7,c.getCards().length);
        c.Shuffle();
        assertEquals(7,c.getCards().length);
    }
}