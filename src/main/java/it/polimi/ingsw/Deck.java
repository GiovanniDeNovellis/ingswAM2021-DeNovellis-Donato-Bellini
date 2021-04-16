package it.polimi.ingsw;

import java.util.ArrayList;
import java.util.Collections;

/**
 * The Deck of the DevelopmentCards
 */
public class Deck {

    /**
     * The reference of the DevelopmentCard's ArrayList
     */
    private final ArrayList<DevelopmentCard> cards;


    /**
     * Public method.
     * Constructor of deck.
     */
    public Deck(){
        this.cards = new ArrayList<>();
    }

    /**
     * Public method.
     * Method used by the DeckGrid to insert a Card into
     * the deck when initiating it.
     * @param card Card to be added.
     */
    public void Add(DevelopmentCard card){cards.add(card);}

    /**
     * Public method.
     * Method used by the DeckGrid to shuffle the
     * deck when initiating it.
     */
    public void shuffle(){ Collections.shuffle(cards);}

    /**
     * Public method.
     * Returns the first card of the Deck.NULL
     * if the Deck is empty.
     * @return Card returned.
     */
    public DevelopmentCard removeCard(){
        try {
            DevelopmentCard d = cards.get(0);
            cards.remove(0);
            return d;
        }
        catch(IndexOutOfBoundsException e){return null;}
    }

    /**
     * Public method.
     * Read the card
     */
    public DevelopmentCard readCard(){
        try {
            return cards.get(0);
        }
        catch(IndexOutOfBoundsException e){return null;}
    }

    /**
     * Public method.
     * Used for whitebox testing.
     * @return Arraylist returned for testing.
     */
    public ArrayList<DevelopmentCard> getAllCards() {
        return cards;
    }

    // To printing the deck and testing the shuffle
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