package it.polimi.ingsw;
import java.util.ArrayList;
import java.util.Collections;

/** This class represent a deck of ActionCards */
public class ActionCardStack {

    /** Array of seven ActionCard */
    private ArrayList<ActionCard> cards = new ArrayList<>();

    /**
     * The reference to the DevelopmentCard deck
     * that contains the DevelopmentCards.
     */
    private Deckgrid deck;

    /**
     * Public Method.
     * Getter of the ActionCard.
     * @return the card
     */
    public ArrayList<ActionCard> getCards() { return cards; }

    /**
     * Public method.
     * Constructor of ActionCardStack, doing the Fill and the Shuffle for the first time
     * @param deck indicate the deck where is the card
     */
    public ActionCardStack(Deckgrid deck) {
        this.deck = deck;
        this.Fill();
    }

    /**
     * Public method.
     * Fill the ActionCardStack with the cards that we know from the game
     */
    public void Fill(){
            cards.add(new MoveAndShuffle (this));
            cards.add(new Move ());
            cards.add(new Move ());
            cards.add(new RemoveDevCard (Colour.PURPLE,deck));
            cards.add(new RemoveDevCard (Colour.BLUE, deck));
            cards.add(new RemoveDevCard (Colour.GREEN,deck));
            cards.add(new RemoveDevCard (Colour.YELLOW,deck));
    }

    /** Public method
     * Shuffle the index of the ActionCard array and randomize the drawing of the cards */
    public void Shuffle(){
        Collections.shuffle(cards);
    }

    /** Public method.
     * Activate the ActionCard */
    public void activateCard(){
        ActionCard c = cards.remove(0);
        cards.add(c);
        c.activate();
    }
}