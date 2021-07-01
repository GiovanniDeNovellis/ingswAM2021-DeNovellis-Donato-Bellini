package it.polimi.ingsw.View.LeaderCardsBuilder;

import it.polimi.ingsw.Colour;
import it.polimi.ingsw.ResourceType;

/**
 * Used to print a discount leader card
 */
public class LeaderCardDiscount extends LeaderCards{
        ResourceType resourceType;
        Colour singleColour1;
        Colour singleColour2;
        String type;
        int leaderCardNumber;
        int victoryPoints;
        LeaderCardDiscount(Colour singleColour1,Colour singleColour2,ResourceType resourceType, int leaderCardNumber, int victoryPoints) {
                this.resourceType = resourceType;
                this.singleColour1 = singleColour1;
                this.singleColour2 = singleColour2;
                this.type = "Discount";
                this.leaderCardNumber=leaderCardNumber;
                this.victoryPoints=victoryPoints;
        }

        @Override
        public int getVictoryPoints() {
                return victoryPoints;
        }

        @Override
        public ResourceType getResourceType() {
                return resourceType;
        }

        @Override
        public ResourceType getResourceRequired() {
                return null;
        }

        @Override
        public String getType() {
                return type;
        }


        @Override
        public Colour getSingleColour1() {
                return singleColour1;
        }

        @Override
        public Colour getSingleColour2() {
                return singleColour2;
        }

        @Override
        public String getLevel2CardColour() {
                return null;
        }

        @Override
        public Colour getDoubleCardColour() {
                return null;
        }

        @Override
        public Colour getSingleCardColour() {
                return null;
        }
}