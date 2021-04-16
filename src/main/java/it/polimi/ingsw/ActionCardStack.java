package it.polimi.ingsw;
import java.util.Random;

/** This class represent a deck of ActionCards */
public class ActionCardStack {

    /** Array of seven ActionCard */
    private ActionCard[] cards = new ActionCard[7];

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
    public ActionCard[] getCards() { return cards; }

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
            cards [0] = new MoveAndShuffle (this);
            cards [1] = new Move ();
            cards [2] = new Move ();
            cards [3] = new RemoveDevCard (Colour.PURPLE,deck);
            cards [4] = new RemoveDevCard (Colour.BLUE, deck);
            cards [5] = new RemoveDevCard (Colour.GREEN,deck);
            cards [6] = new RemoveDevCard (Colour.YELLOW,deck);
    }

    /** Public method
     * Shuffle the index of the ActionCard array and randomize the drawing of the cards */
    public void Shuffle(){
        Random rnd = new Random();
        for (int i = this.cards.length - 1; i>0; i--){
            int index = rnd.nextInt(i+1);
            ActionCard temp = this.cards[index];
            this.cards[index] = this.cards[i];
            this.cards[i] = temp;
        }
    }

    /** Public method.
     * Activate the ActionCard */
    public void activateCard(){
        ActionCard c = cards[0];
        for(int i=0; i< cards.length-1; i++){
            cards[i]=cards[i+1];
        }
        cards[6]=c;
        c.activate();
    }
}