package it.polimi.ingsw;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeckTest {
    @Test
    public void AddTest(){
        Deck d = new Deck();
        d.Add(new DevelopmentCard(1,Colour.GREEN,2));
        d.Add(new DevelopmentCard(2,Colour.YELLOW,2));
        d.Add(new DevelopmentCard(3,Colour.BLUE,2));
        d.Add(new DevelopmentCard(3,Colour.PURPLE,2));
        assertEquals(Colour.GREEN,d.getAllCards().get(0).getColour());
        assertEquals(1,d.getAllCards().get(0).getLevel());
        assertEquals(Colour.YELLOW,d.getAllCards().get(1).getColour());
        assertEquals(2,d.getAllCards().get(1).getLevel());
        assertEquals(Colour.BLUE,d.getAllCards().get(2).getColour());
        assertEquals(3,d.getAllCards().get(2).getLevel());
        assertEquals(Colour.PURPLE,d.getAllCards().get(3).getColour());
        assertEquals(3,d.getAllCards().get(3).getLevel());
    }
    @Test
    public void getTest(){
        Deck d = new Deck();
        d.Add(new DevelopmentCard(1,Colour.GREEN,2));
        d.Add(new DevelopmentCard(2,Colour.YELLOW,2));
        d.Add(new DevelopmentCard(3,Colour.BLUE,2));
        d.Add(new DevelopmentCard(3,Colour.PURPLE,2));
        DevelopmentCard c = d.getCard();
        assertEquals(Colour.GREEN,c.getColour());
        assertEquals(1,c.getLevel());
        c = d.getCard();
        assertEquals(Colour.YELLOW,c.getColour());
        assertEquals(2,c.getLevel());
        c = d.getCard();
        assertEquals(Colour.BLUE,c.getColour());
        assertEquals(3,c.getLevel());
        c = d.getCard();
        assertEquals(Colour.PURPLE,c.getColour());
        assertEquals(3,c.getLevel());
    }

    @Test
    public void AddAndGetTest(){
        Deck d = new Deck();
        d.Add(new DevelopmentCard(1,Colour.GREEN,2));
        d.Add(new DevelopmentCard(2,Colour.YELLOW,2));
        d.Add(new DevelopmentCard(3,Colour.BLUE,2));
        d.Add(new DevelopmentCard(3,Colour.PURPLE,2));
        assertEquals(Colour.GREEN,d.getAllCards().get(0).getColour());
        assertEquals(1,d.getAllCards().get(0).getLevel());
        assertEquals(Colour.YELLOW,d.getAllCards().get(1).getColour());
        assertEquals(2,d.getAllCards().get(1).getLevel());
        assertEquals(Colour.BLUE,d.getAllCards().get(2).getColour());
        assertEquals(3,d.getAllCards().get(2).getLevel());
        assertEquals(Colour.PURPLE,d.getAllCards().get(3).getColour());
        assertEquals(3,d.getAllCards().get(3).getLevel());
        DevelopmentCard c = d.getCard();
        assertEquals(Colour.GREEN,c.getColour());
        assertEquals(1,c.getLevel());
        c = d.getCard();
        assertEquals(Colour.YELLOW,c.getColour());
        assertEquals(2,c.getLevel());
        c = d.getCard();
        assertEquals(Colour.BLUE,c.getColour());
        assertEquals(3,c.getLevel());
        c = d.getCard();
        assertEquals(Colour.PURPLE,c.getColour());
        assertEquals(3,c.getLevel());
    }

    @Test
    public void ShuffleIntegrityTest(){
        Deck d = new Deck();
        d.Add(new DevelopmentCard(1,Colour.GREEN,2));
        d.Add(new DevelopmentCard(2,Colour.YELLOW,2));
        d.Add(new DevelopmentCard(3,Colour.BLUE,2));
        d.Add(new DevelopmentCard(3,Colour.PURPLE,2));
        assertEquals(Colour.GREEN,d.getAllCards().get(0).getColour());
        assertEquals(1,d.getAllCards().get(0).getLevel());
        assertEquals(Colour.YELLOW,d.getAllCards().get(1).getColour());
        assertEquals(2,d.getAllCards().get(1).getLevel());
        assertEquals(Colour.BLUE,d.getAllCards().get(2).getColour());
        assertEquals(3,d.getAllCards().get(2).getLevel());
        assertEquals(Colour.PURPLE,d.getAllCards().get(3).getColour());
        assertEquals(3,d.getAllCards().get(3).getLevel());
        int l=d.getAllCards().size();
        d.shuffle();
        assertEquals(l,d.getAllCards().size());
        int g=0,y=0,b=0,p=0;
        for(DevelopmentCard c: d.getAllCards()){
            if(c.getColour().equals(Colour.GREEN)) g++;
            else if(c.getColour().equals(Colour.YELLOW)) y++;
            else if(c.getColour().equals(Colour.BLUE)) b++;
            else if(c.getColour().equals(Colour.PURPLE)) p++;
        }
        assertEquals(1,g);
        assertEquals(1,y);
        assertEquals(1,b);
        assertEquals(1,p);
    }
}