package it.polimi.ingsw.View.LeaderCardsBuilder;

import it.polimi.ingsw.Colour;
import it.polimi.ingsw.ResourceType;

public class LeaderCardDiscount {
        ResourceType resourceType;
        Colour singleColour1;
        Colour singleColour2;
        String type;
        int leaderCardNumber;
        LeaderCardDiscount(Colour singleColour1,Colour singleColour2,ResourceType resourceType, int leaderCardNumber) {
                this.resourceType = resourceType;
                this.singleColour1 = singleColour1;
                this.singleColour2 = singleColour2;
                this.type = "Discount";
                this.leaderCardNumber=leaderCardNumber;
        }

        public ResourceType getResourceType() {
                return resourceType;
        }

        public Colour getSingleColour1() {
                return singleColour1;
        }

        public Colour getSingleColour2() {
                return singleColour2;
        }

        public String getType() {
                return type;
        }

        public int getLeaderCardNumber() {
                return leaderCardNumber;
        }
}