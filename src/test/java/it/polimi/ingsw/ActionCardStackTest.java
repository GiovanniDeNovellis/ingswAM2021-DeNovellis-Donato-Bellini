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
        assertTrue(c.getCards().get(0) instanceof MoveAndShuffle);
        assertTrue(c.getCards().get(1) instanceof Move);
        assertTrue(c.getCards().get(2) instanceof Move);
        assertTrue(c.getCards().get(3) instanceof RemoveDevCard);
        assertTrue(c.getCards().get(4) instanceof RemoveDevCard);
        assertTrue(c.getCards().get(5) instanceof RemoveDevCard);
        assertTrue(c.getCards().get(6) instanceof RemoveDevCard);
        assertEquals(7,c.getCards().size());
    }

    @Test
    void shuffleTest() throws FileNotFoundException {
        Deckgrid d = new Deckgrid();
        ActionCardStack c = new ActionCardStack(d);
        c.Shuffle();
        assertEquals(7,c.getCards().size());
        c.Shuffle();
        assertEquals(7,c.getCards().size());
    }
}