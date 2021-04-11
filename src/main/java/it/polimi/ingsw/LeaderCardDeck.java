package it.polimi.ingsw;

import java.util.ArrayList;
import java.util.Collections;

public class LeaderCardDeck {
    private ArrayList<LeaderCard> leaderCards = new ArrayList<>();

    public LeaderCardDeck() {
        leaderCards.add(new LeaderCardDiscount(Colour.YELLOW,Colour.GREEN,ResourceType.SERVANTS));
        leaderCards.add(new LeaderCardDiscount(Colour.BLUE,Colour.PURPLE,ResourceType.SHIELDS));
        leaderCards.add(new LeaderCardDiscount(Colour.GREEN,Colour.BLUE,ResourceType.STONES));
        leaderCards.add(new LeaderCardDiscount(Colour.YELLOW,Colour.PURPLE,ResourceType.COINS));
        leaderCards.add(new LeaderCardDeposit(ResourceType.COINS,ResourceType.STONES));
        leaderCards.add(new LeaderCardDeposit(ResourceType.STONES,ResourceType.SERVANTS));
        leaderCards.add(new LeaderCardDeposit(ResourceType.SERVANTS,ResourceType.SHIELDS));
        leaderCards.add(new LeaderCardDeposit(ResourceType.SHIELDS,ResourceType.COINS));
        leaderCards.add(new LeaderCardTransformation(Colour.YELLOW,Colour.BLUE,ResourceType.SERVANTS));
        leaderCards.add(new LeaderCardTransformation(Colour.GREEN,Colour.PURPLE,ResourceType.SHIELDS));
        leaderCards.add(new LeaderCardTransformation(Colour.BLUE,Colour.YELLOW,ResourceType.STONES));
        leaderCards.add(new LeaderCardTransformation(Colour.PURPLE,Colour.GREEN,ResourceType.COINS));
        leaderCards.add(new LeaderCardProduction(Colour.YELLOW,ResourceType.SHIELDS));
        leaderCards.add(new LeaderCardProduction(Colour.BLUE,ResourceType.SERVANTS));
        leaderCards.add(new LeaderCardProduction(Colour.PURPLE,ResourceType.STONES));
        leaderCards.add(new LeaderCardProduction(Colour.GREEN,ResourceType.COINS));
        Collections.shuffle(leaderCards);
    }
    public void randomDistribute(Player player){
       for (int i=0;i<4;i++){
           player.addChoosableLeaderCards(leaderCards.get(0));
           leaderCards.remove(0);
       }
    }
}
