package it.polimi.ingsw.View.LeaderCardsBuilder;

import it.polimi.ingsw.Colour;
import it.polimi.ingsw.ResourceType;

import java.util.ArrayList;


public class LeaderCardsDeck {

    ArrayList<LeaderCards> leaderCards = new ArrayList<>();

    public LeaderCardsDeck(){
            leaderCards.add(new LeaderCardDiscount(Colour.YELLOW,Colour.GREEN,ResourceType.SERVANTS,1));
            leaderCards.add(new LeaderCardDiscount(Colour.BLUE,Colour.PURPLE,ResourceType.SHIELDS,2));
            leaderCards.add(new LeaderCardDiscount(Colour.GREEN,Colour.BLUE,ResourceType.STONES,3));
            leaderCards.add(new LeaderCardDiscount(Colour.YELLOW,Colour.PURPLE,ResourceType.COINS,4));
            leaderCards.add(new LeaderCardDeposit(ResourceType.COINS,ResourceType.STONES,5));
            leaderCards.add(new LeaderCardDeposit(ResourceType.STONES,ResourceType.SERVANTS,6));
            leaderCards.add(new LeaderCardDeposit(ResourceType.SERVANTS,ResourceType.SHIELDS,7));
            leaderCards.add(new LeaderCardDeposit(ResourceType.SHIELDS,ResourceType.COINS,8));
            leaderCards.add(new LeaderCardTransformation(Colour.YELLOW,Colour.BLUE,ResourceType.SERVANTS,9));
            leaderCards.add(new LeaderCardTransformation(Colour.GREEN,Colour.PURPLE,ResourceType.SHIELDS,10));
            leaderCards.add(new LeaderCardTransformation(Colour.BLUE,Colour.YELLOW,ResourceType.STONES,11));
            leaderCards.add(new LeaderCardTransformation(Colour.PURPLE,Colour.GREEN,ResourceType.COINS,12));
            leaderCards.add(new LeaderCardProduction(Colour.YELLOW,ResourceType.SHIELDS,13));
            leaderCards.add(new LeaderCardProduction(Colour.BLUE,ResourceType.SERVANTS,14));
            leaderCards.add(new LeaderCardProduction(Colour.PURPLE,ResourceType.STONES,15));
            leaderCards.add(new LeaderCardProduction(Colour.GREEN,ResourceType.COINS,16));
    }

    public ArrayList<LeaderCards> getLeaderCards() {
        return leaderCards;
    }
}
