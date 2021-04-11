package it.polimi.ingsw;

/** Abstracted class that represent the three different types of ActionCard */
public abstract class ActionCard {
    abstract void activate();
}

/** Move the Black Cross token forward by 2 spaces. */
class Move extends ActionCard {
    @Override
    void activate() {
        LorenzoSingleton.getLorenzo().addFaithPoints(2);
    }
}

/** Move the Black Cross token forward by 1 space. Then, shuffle all the Solo Action tokens and create a new stack. */
class MoveAndShuffle extends ActionCard {
    private ActionCardStack cards;

    public MoveAndShuffle(ActionCardStack cards){
        this.cards=cards;
    }

    @Override
    void activate() {
        LorenzoSingleton.getLorenzo().addFaithPoints(1);
        this.cards.Shuffle();
    }
}

/** Discard 2 Development Cards of the indicated type from the bottom of the grid, from the lowest level to the highest */
class RemoveDevCard extends ActionCard {
    private Colour colour;
    private Deckgrid deck;

    public RemoveDevCard (Colour colour, Deckgrid deck) {
        this.colour = colour;
        this.deck = deck;
    }

    @Override
    void activate() {
            int counter=0;
            int l = 1;
            while (counter<2 && l<4){
                if(deck.removeCard(l,colour)!=null) {
                    counter++;
                }else{
                    l++;
                }
            }
            //if (l==4){
                //TODO = implementare eccezione del fine partita

            //}
    }
}
