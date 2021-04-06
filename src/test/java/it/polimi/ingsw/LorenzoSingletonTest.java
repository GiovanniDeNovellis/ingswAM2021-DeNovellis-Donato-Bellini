package it.polimi.ingsw;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LorenzoSingletonTest {

    @Test
    void addFaithPointsTest() {
        int x = LorenzoSingleton.getLorenzo().getBlackFaithPoints();
        LorenzoSingleton.getLorenzo().addFaithPoints(2);
        assertEquals(x+2,LorenzoSingleton.getLorenzo().getBlackFaithPoints());
    }
}