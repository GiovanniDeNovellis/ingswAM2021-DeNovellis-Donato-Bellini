package it.polimi.ingsw;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LorenzoTest {

    @Test
    void addFaithPointsTest(){
        Lorenzo l = new Lorenzo ();
        l.addFaithPoints(10);
        assertEquals(10,l.getBlackFaithPoints());
    }
}