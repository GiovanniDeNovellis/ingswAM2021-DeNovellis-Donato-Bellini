package it.polimi.ingsw;

/** Abstracted class that represent the three different types of ActionCard */
public abstract class ActionCard{
    /**
     * Public method.
     * Activate the ActionCard.
     */
    public abstract void activate();
}

/** Move the Black Cross token forward by 2 spaces. */
class Move extends ActionCard {
    /**
     * Type of the card.
     */
    protected String type="Move";
    /**
     * Public method overrode from Action card.
     * Add 2 faithPoints to Lorenzo.
     */
    @Override
    public void activate() {
        LorenzoSingleton.getLorenzo().addFaithPoints(2);
    }

    /**
     * Public method.
     * @return the type of the action card.
     */
    public String getType() {
        return type;
    }
}

/** Move the Black Cross token forward by 1 space. Then, shuffle all the Solo Action tokens and create a new stack. */
class MoveAndShuffle extends ActionCard {
    private ActionCardStack cards;

    /**
     * Type of the card
     */
    protected String type = "MoveAndShuffle";

    /**
     * Public method.
     * Constructor of the MoveAndShuffle cards.
     * @param cards is the reference for the deck that have to be shuffle.
     */
    public MoveAndShuffle(ActionCardStack cards){
        this.cards=cards;
    }

    /**
     * Public method overrode from Action card.
     * Add 1 faithPoints to Lorenzo and shuffle the ActionCardStack.
     */
    @Override
    public void activate() {
        LorenzoSingleton.getLorenzo().addFaithPoints(1);
        this.cards.Shuffle();
    }

    /**
     * Public method.
     * @return the type of the action card.
     */
    public String getType() {
        return type;
    }
}

/** Discard 2 Development Cards of the indicated type from the bottom of the grid, from the lowest level to the highest */
class RemoveDevCard extends ActionCard {

    /** The colour of the DevelopmentCard.*/
    private Colour colour;

    /**
     * The reference to the DevelopmentCard deck
     * that contains the DevelopmentCards.
     */
    private Deckgrid deck;

    /**
     * Type of the card
     */
    protected String type;

    /**
     * Public method.
     * The constructor of the RemoveDevCard
     * @param colour indicate the type of colour that have to be discarded
     * @param deck indicate the deck where is the card
     */
    public RemoveDevCard (Colour colour, Deckgrid deck) {
        this.colour = colour;
        this.deck = deck;
    }

    /** Public method overrode from Action card.
     * Check from the three level of the deck if it contains the cards,
     * otherwise game end and the player has lost.
     */
    @Override
    public void activate() {
            int counter=0;
            int l = 1;
            while (counter<2 && l<4){
                if(deck.removeCard(l,colour)!=null) {
                    counter++;
                }else{
                    l++;
                }
            }
            if (l==4){
                LorenzoSingleton.getLorenzo().setLorenzoWinner(true);
            }
    }

    /**
     * Public method.
     * @return the type of the action card.
     */
    public String getType() {
        return type;
    }
}