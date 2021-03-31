package it.polimi.ingsw;


import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    private final ArrayList<DevelopmentCard> cards;

    public Deck(){
        this.cards = new ArrayList<>();
    }

    /**
     * Method used by the DeckGrid to insert a Card into
     * the deck when initiating it.
     * @param card Card to be added.
     */
    public void Add(DevelopmentCard card){cards.add(card);}

    /**
     * Method used by the DeckGrid to shuffle the
     * deck when initiating it.
     */
    public void shuffle(){ Collections.shuffle(cards);}

    /**
     * Returns the first card of the Deck.NULL
     * if the Deck is empty.
     * @return Card returned.
     */
    public DevelopmentCard getCard(){
        try {
            DevelopmentCard d = cards.get(0);
            cards.remove(0);
            return d;
        }
        catch(IndexOutOfBoundsException e){return null;}
    }

    /**
     * Method used for whitebox testing.
     * @return Arraylist returned for testing.
     */
    public ArrayList<DevelopmentCard> getAllCards() {
        return cards;
    }

    // For printing the deck and testing the shuffle
    /*
    public static void main(String[] args) {
        Deck d = new Deck();
        d.Add(new DevelopmentCard(1,Colour.GREEN,2));
        d.Add(new DevelopmentCard(1,Colour.YELLOW,2));
        d.Add(new DevelopmentCard(1,Colour.BLUE,2));
        d.Add(new DevelopmentCard(1,Colour.PURPLE,2));
        for(DevelopmentCard dev: d.getAllCards()){
            dev.printCards();
        }
        d.shuffle();
        System.out.println("Fine deck 1\n");
        for(DevelopmentCard dev: d.getAllCards()){
            dev.printCards();
        }
    }*/
}
