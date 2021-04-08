package it.polimi.ingsw;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class ActionCardTest{
    @Test
    void activateTest() throws FileNotFoundException {
        int x = LorenzoSingleton.getLorenzo().getBlackFaithPoints();
        // MoveTest
        Move m = new Move();
        Deckgrid d = new Deckgrid();
        m.activate();
        assertEquals(x+2, LorenzoSingleton.getLorenzo().getBlackFaithPoints());
        // MoveAndShuffle Test
        ActionCardStack c = new ActionCardStack(d);
        MoveAndShuffle ms = new MoveAndShuffle(c);
        ms.activate();
        assertEquals(x+3,LorenzoSingleton.getLorenzo().getBlackFaithPoints());
        // RemoveDevCard Test
        RemoveDevCard rd = new RemoveDevCard(Colour.PURPLE,d);
        rd.activate();
        rd.activate();
        assertNull(d.removeCard(1,Colour.PURPLE));
        rd.activate();
        rd.activate();
        assertNull(d.removeCard(2,Colour.PURPLE));
        rd.activate();
        rd.activate();
        assertNull(d.removeCard(3,Colour.PURPLE));

        rd = new RemoveDevCard(Colour.GREEN,d);
        rd.activate();
        rd.activate();
        assertNull(d.removeCard(1,Colour.GREEN));
        rd.activate();
        rd.activate();
        assertNull(d.removeCard(2,Colour.GREEN));
        rd.activate();
        rd.activate();
        assertNull(d.removeCard(3,Colour.GREEN));

        rd = new RemoveDevCard(Colour.BLUE,d);
        rd.activate();
        rd.activate();
        assertNull(d.removeCard(1,Colour.BLUE));
        rd.activate();
        rd.activate();
        assertNull(d.removeCard(2,Colour.BLUE));
        rd.activate();
        rd.activate();
        assertNull(d.removeCard(3,Colour.BLUE));

        rd = new RemoveDevCard(Colour.YELLOW,d);
        rd.activate();
        assertEquals(Colour.YELLOW,d.removeCard(1,Colour.YELLOW).getColour());
        assertEquals(Colour.YELLOW,d.removeCard(1,Colour.YELLOW).getColour());
        assertNull(d.removeCard(1,Colour.YELLOW));
        rd.activate();
        rd.activate();
        assertNull(d.removeCard(2,Colour.YELLOW));
        rd.activate();
        rd.activate();
        assertNull(d.removeCard(3,Colour.YELLOW));
    }
}