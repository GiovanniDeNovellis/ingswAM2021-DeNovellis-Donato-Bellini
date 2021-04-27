package it.polimi.ingsw;

import java.util.HashMap;
import java.util.Map;

/**
 * The Development card can activate the "production power" for a resource,
 * each card has a cost, a level and gives you victory points.
 */
public class DevelopmentCard {
    /** Attribute level is referred to developmentCard's level */
    private int level;

    /**
     * The three HashMaps are used to keep in memory ,respectively, the necessary resources to buy the development card,
     * cost expressed in resources to activate the developmentCard's production and resources obtained throw the production
     */
    private Map<ResourceType, Integer> cost = new HashMap<>();
    private Map<ResourceType, Integer> earnedResources = new HashMap<>();
    private Map<ResourceType, Integer> productionCost = new HashMap<>();

    /** Attribute colour is referred to developmentCard's colour */
    private Colour type;

    /** Attribute victoryPoints represents victoryPoints given by a developmentCard when game ends */
    private int victoryPoints;

    /**
     * Public method. Returns the colour of the card.
     * @return Color
     */
    public Colour getColour(){return this.type;}


    /**
     * Public method.
     * Getter of the level.
     * @return the level of the card
     */
    public int getLevel(){
        return level;
    }

    /**
     * Public method.
     * Getter of the victoryPoints.
     * @return the victoryPoints of the card
     */
    public int getVictoryPoints(){
        return victoryPoints;
    }

    /**
     * Public method.
     * Getter of the ProductionCost
     * @return the resourceType of the productionCost
     */
    public int getProductionCost(ResourceType resourceType) {
        return productionCost.get(resourceType);
    }

    // --  uncomment this constructor to execute DeckTest --
    /*
    public DevelopmentCard(int level, Colour type, int victoryPoints){
        this.level = level;
        this.type = type;
        this.victoryPoints = victoryPoints;
    }*/


    /**
     * Public method.
     * ActivateProduction method is called by PersonalBoard when the player wants tho activate production from a
     * DevelopmentCard in his own PersonalBoard. This method checks if the player has the required resources in his
     * warehouse to activate the production through the boolean returned by the personalBoard.takeResourcesFromWarehouse
     * method, invoked by this method. If the player can pay the production cost, this method invokes personalBoard.addResourceToStrongbox
     * @param personalBoard references the player's board where the card is placed
     */

    public boolean activateProduction(PersonalBoard personalBoard){
        boolean doneWarehouse;
        boolean doneStrongbox;
        boolean canPay = false;
        int quantityExtraDep1 = 0;
        ResourceType resTypeExtraDep1 = null;
        int quantityExtraDep2 = 0;
        ResourceType resTypeExtraDep2 = null;
        int secondQuantityExtraDep1 = 0;
        ResourceType secondResTypeExtraDep1 = null;
        int secondQuantityExtraDep2 = 0;
        ResourceType secondResTypeExtraDep2 = null;
        int oldQuantity1 = 0;
        int oldQuantity2 = 0;
        boolean doneExtra = false;

        if( personalBoard.getPayUsingExtraDep1()!=0 && personalBoard.getExtraDeposit1()!=null ){
            quantityExtraDep1 = personalBoard.getPayUsingExtraDep1();
            oldQuantity1 = personalBoard.getPayUsingExtraDep1();
            resTypeExtraDep1 = personalBoard.getExtraDeposit1().getResourceType();
        }
        if( personalBoard.getPayUsingExtraDep2()!=0 && personalBoard.getExtraDeposit2()!=null ){
            quantityExtraDep2 = personalBoard.getPayUsingExtraDep2();
            oldQuantity2 = personalBoard.getPayUsingExtraDep2();
            resTypeExtraDep2 = personalBoard.getExtraDeposit2().getResourceType();
        }

        for( ResourceType resource : productionCost.keySet()) {
            int value = productionCost.get(resource);
            if( resTypeExtraDep1!= null && quantityExtraDep1!=0 && resTypeExtraDep1==resource ){
                doneExtra = true;
                boolean allUsed = value == quantityExtraDep1;
                value-=quantityExtraDep1;
                if( value<0 ){
                    quantityExtraDep1+=value;
                    value=0;
                }
                if( allUsed=true ){
                    quantityExtraDep1=0;
                }
                if( value!=0 ){
                    doneExtra=false;
                }
            }
            if( resTypeExtraDep2!= null && quantityExtraDep2!=0 && resTypeExtraDep2==resource ){
                doneExtra = true;
                boolean allUsed2 = value == quantityExtraDep2;
                value-=quantityExtraDep2;
                if( value<0 ){
                    quantityExtraDep2+=value;
                    value=0;
                }
                if( allUsed2=true ){
                    quantityExtraDep2=0;
                }
                if( value!=0 ){
                    doneExtra=false;
                }
            }
            if( doneExtra ){
                canPay = true;
            }
            doneWarehouse = personalBoard.checkResourcesIntoWarehouse(resource, value);
            if( doneWarehouse )
                canPay = true;
            else {
                int missing = personalBoard.missingResourcesIntoWarehouseWithoutRemove(resource, value);
                doneStrongbox = personalBoard.checkResourcesIntoStrongbox(resource, missing);
                if (!doneStrongbox) {
                    canPay = false;
                    break;
                } else
                    canPay = true;
            }
        }

        if( canPay ) {
            if( personalBoard.getPayUsingExtraDep1()!=0 && personalBoard.getExtraDeposit1()!=null ){
                secondQuantityExtraDep1 = personalBoard.getPayUsingExtraDep1();
                secondResTypeExtraDep1 = personalBoard.getExtraDeposit1().getResourceType();
            }
            if( personalBoard.getPayUsingExtraDep2()!=0 && personalBoard.getExtraDeposit2()!=null ){
                secondQuantityExtraDep2 = personalBoard.getPayUsingExtraDep2();
                secondResTypeExtraDep2 = personalBoard.getExtraDeposit2().getResourceType();
            }
            personalBoard.payFromExtraDep(1, (oldQuantity1-quantityExtraDep1) );
            personalBoard.payFromExtraDep(2, (oldQuantity2-quantityExtraDep2) );
            personalBoard.setPayUsingExtraDep1(quantityExtraDep1);
            personalBoard.setPayUsingExtraDep2(quantityExtraDep2);

            if( doneExtra ){
                for (ResourceType resource : earnedResources.keySet()) {
                    personalBoard.addResourceToStrongboxTemp(resource, earnedResources.get(resource));
                }
                return true;
            }

            for( ResourceType resource : productionCost.keySet()) {
                int value = productionCost.get(resource);
                if( secondResTypeExtraDep1!= null && secondQuantityExtraDep1!=0 && secondResTypeExtraDep1==resource ){
                    value-=secondQuantityExtraDep1;
                    if( value<0 ){
                        secondQuantityExtraDep1+=value;
                        value=0;
                    }
                }
                if( secondResTypeExtraDep2!= null && secondQuantityExtraDep2!=0 && secondResTypeExtraDep2==resource ){
                    value-=quantityExtraDep2;
                    if( value<0 ){
                        secondQuantityExtraDep2+=value;
                        value=0;
                    }
                }
                doneWarehouse = personalBoard.takeResourcesFromWarehouse(resource, value);
                if( !doneWarehouse ) {
                    int missing = personalBoard.missingResourcesIntoWarehouse(resource, value);
                    doneStrongbox = personalBoard.takeResourcesFromStrongbox(resource, missing);
                    if (!doneStrongbox) {
                        return false;
                    }
                }
            }
            for (ResourceType resource : earnedResources.keySet()) {
                personalBoard.addResourceToStrongboxTemp(resource, earnedResources.get(resource));
            }
            return true;
        }
        return false;
    }


    /**
     * Public method.
     * Give a representation of developmentCards and their attributes
     */
    public void printCards(){
        System.out.println("Colour: " + type);
        System.out.println("Level: " + level);
        for (ResourceType resource : cost.keySet()) {
            System.out.println("Costo della carta: " + resource + " ,Quantità: " + cost.get(resource));
        }
        for (ResourceType resource : earnedResources.keySet()) {
            System.out.println("Risorse guadagnate: " + resource + " ,Quantità: " + earnedResources.get(resource));
        }
        for (ResourceType resource : productionCost.keySet()) {
            System.out.println("Costo di produzione: " + resource + " ,Quantità: " + productionCost.get(resource));
        }
        System.out.println("\n");
    }


    /**
     * Public method.
     * Call personalBoard.payDevelopmentCard method for each resource necessary to buy the card
     * @param personalBoard is the personal board where the card will be added
     * @return true if player can add that card
     */
    public boolean buyCard(PersonalBoard personalBoard){

        return personalBoard.payDevelopmentCard(cost);
    }
}