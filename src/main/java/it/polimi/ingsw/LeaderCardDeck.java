package it.polimi.ingsw;

import java.util.ArrayList;
import java.util.Collections;


/** This class represent a deck of ActionCards */
public class LeaderCardDeck {


    /**
     * The ArrayList contains multiple LeaderCard's objects
     */
    private final ArrayList<LeaderCard> leaderCards = new ArrayList<>();

    /**
     * Public method.
     * Build the deck with the cards contained in the game,
     * there are 4 cards for each types, for a total of 12 cards.
     * The method add a slot in the array list of the deck that contains four Leader Card of each type
     * with different parameters.
     */
    public LeaderCardDeck() {
        leaderCards.add(new LeaderCardDiscount(Colour.YELLOW,Colour.GREEN,ResourceType.SERVANTS,1,2));
        leaderCards.add(new LeaderCardDiscount(Colour.BLUE,Colour.PURPLE,ResourceType.SHIELDS,2,2));
        leaderCards.add(new LeaderCardDiscount(Colour.GREEN,Colour.BLUE,ResourceType.STONES,3,2));
        leaderCards.add(new LeaderCardDiscount(Colour.YELLOW,Colour.PURPLE,ResourceType.COINS,4,2));
        leaderCards.add(new LeaderCardDeposit(ResourceType.COINS,ResourceType.STONES,5,3));
        leaderCards.add(new LeaderCardDeposit(ResourceType.STONES,ResourceType.SERVANTS,6,3));
        leaderCards.add(new LeaderCardDeposit(ResourceType.SERVANTS,ResourceType.SHIELDS,7,3));
        leaderCards.add(new LeaderCardDeposit(ResourceType.SHIELDS,ResourceType.COINS,8,3));
        leaderCards.add(new LeaderCardTransformation(Colour.YELLOW,Colour.BLUE,ResourceType.SERVANTS,9,5));
        leaderCards.add(new LeaderCardTransformation(Colour.GREEN,Colour.PURPLE,ResourceType.SHIELDS,10,5));
        leaderCards.add(new LeaderCardTransformation(Colour.BLUE,Colour.YELLOW,ResourceType.STONES,11,5));
        leaderCards.add(new LeaderCardTransformation(Colour.PURPLE,Colour.GREEN,ResourceType.COINS,12,5));
        leaderCards.add(new LeaderCardProduction(Colour.YELLOW,ResourceType.SHIELDS,13,4));
        leaderCards.add(new LeaderCardProduction(Colour.BLUE,ResourceType.SERVANTS,14,4));
        leaderCards.add(new LeaderCardProduction(Colour.PURPLE,ResourceType.STONES,15,4));
        leaderCards.add(new LeaderCardProduction(Colour.GREEN,ResourceType.COINS,16,4));
        //have to comment "Collections.shuffle(leaderCards);" to execute LeaderAbility tests in GameTest class.
        Collections.shuffle(leaderCards);
    }

    /**
     * Public method.
     * Used by the deck to distribute the cards to the players
     * @param player is the reference of who receive the cards
     */
    public void randomDistribute(Player player){
       for (int i=0;i<4;i++){
           player.addChoosableLeaderCards(leaderCards.get(0));
           leaderCards.remove(0);
       }
    }
    public ArrayList<LeaderCard> getLeaderCards(){
        return this.leaderCards;
    }
}
