package it.polimi.ingsw;

import java.util.ArrayList;
import java.util.TreeMap;

public class PersonalBoard {

    public PersonalBoard(Player player){
        this.player = player;
    }
    private Player player;
    private TreeMap<Colour,Integer> cardsColours = new TreeMap<>();
    private int totDevCards = 0;
    private ArrayList<DevelopmentCard> insertedDevelopmentCards = new ArrayList<>();
    private Strongbox strongbox = new Strongbox();
    private WareHouseDepot warehouseDepot = new WareHouseDepot();
    /**
     * developmentCard[] attribute represent the three slots located in the personal board where developmentCards
     * can be positioned
     */
    private DevelopmentCard[] developmentCard = new DevelopmentCard[3];

    /**
     * resourcesToAddToStrongbox TreeMap contains all resources obtained through production.
     */
    private TreeMap<ResourceType, Integer> resourcesToAddToStrongbox = new TreeMap<>();

    private ResourceType discount1;
    private ResourceType discount2;


    public ResourceType getDiscount1() {
        return discount1;
    }


    public ResourceType getDiscount2() {
        return discount2;
    }

    public void setDiscount1(ResourceType discount1) {
        this.discount1 = discount1;
    }

    public void setDiscount2(ResourceType discount2) {
        this.discount2 = discount2;
    }

    //for testing..
    public Strongbox getStrongbox() {
        return strongbox;
    }

    /**
     * insertCard method inserts developmentCards in their own slots, if it is permitted and if the player can pay the
     * card's cost from his warehouse, from his strongbox, or from both
     */
    public boolean insertCard(DevelopmentCard card, int position) {
            if (developmentCard[position] == null) {
                if (card.getLevel() == 1) {
                    if (card.buyCard(this)) {
                        developmentCard[position] = card;
                        totDevCards++;
                        insertedDevelopmentCards.add(card);
                        if(cardsColours.get(card.getColour())==null){
                            cardsColours.put(card.getColour(),1);
                        }
                        else{
                            int oldval=cardsColours.get(card.getColour());
                            cardsColours.put(card.getColour(),oldval+1);
                        }
                        if( totDevCards>=7 )
                            player.getGame().setEndGame(true);
                        return true;
                    }
                    else return false;
                }
            } else if (developmentCard[position].getLevel() == 1) {
                if (card.getLevel() == 2) {
                    if (card.buyCard(this)) {
                        player.addVictoryPoints( developmentCard[position].getVictoryPoints() );
                        developmentCard[position] = card;
                        totDevCards++;
                        insertedDevelopmentCards.add(card);
                        if(cardsColours.get(card.getColour())==null){
                            cardsColours.put(card.getColour(),1);
                        }
                        else{
                            int oldval=cardsColours.get(card.getColour());
                            cardsColours.put(card.getColour(),oldval+1);
                        }
                        if( totDevCards>=7 )
                            player.getGame().setEndGame(true);
                        return true;
                    }
                    else return false;
                }
            } else if (developmentCard[position].getLevel() == 2) {
                if (card.getLevel() == 3) {
                    if (card.buyCard(this)) {
                        player.addVictoryPoints( developmentCard[position].getVictoryPoints() );
                        developmentCard[position] = card;
                        totDevCards++;
                        insertedDevelopmentCards.add(card);
                        if(cardsColours.get(card.getColour())==null){
                            cardsColours.put(card.getColour(),1);
                        }
                        else{
                            int oldval=cardsColours.get(card.getColour());
                            cardsColours.put(card.getColour(),oldval+1);
                        }
                        if( totDevCards>=7 )
                            player.getGame().setEndGame(true);
                        return true;
                    }
                    else return false;
                }
            }
            return false;
    }



    /**
     * payDevelopmentCard method is called by DevelopmentCard when a player wants to insert that DevelopmentCard in his
     * own personalBoard. This method checks if the player has got the necessary resources in the warehouse. If he has
     * enough resources in the warehouse to pay the card, this method takes all the necessary resources from the warehouse,
     * else it takes all the possible resources from the warehouse and it takes the remaining resources from the strongbox.
     * If the player hasn't got the necessary resources summing resources in his strongbox plus resources in his warehouse
     * to pay the card, the method returns false and it doesn't take any resources from warehouse and strongbox.
     * @param cost contains resource types to pay the card and relatives quantity
     * @return boolean
     */
    public boolean payDevelopmentCard( TreeMap<ResourceType, Integer> cost ) {
        boolean check = true;
        TreeMap<ResourceType, Integer> discountedCost = new TreeMap<>();
        discountedCost.putAll(cost);

        if( discount1!=null || discount2!= null ){
            for( ResourceType res: discountedCost.keySet() ) {
                if (res == discount1 || res == discount2) {
                    discountedCost.put(res, (discountedCost.get(res) - 1));
                }
            }
            discount1=null;
            discount2=null;
        }
        for( ResourceType resourceType: discountedCost.keySet() ) {
            check = checkResourcesIntoWarehouse(resourceType, discountedCost.get(resourceType));
            if (!check) {
                int missingQuantity = missingResourcesIntoWarehouseWithoutRemove(resourceType, discountedCost.get(resourceType));
                if (!checkResourcesIntoStrongbox(resourceType, missingQuantity)) {
                    return false;
                }
            }
        }
        for( ResourceType resourceType: discountedCost.keySet() ) {
            if (checkResourcesIntoWarehouse(resourceType, discountedCost.get(resourceType))) {
                takeResourcesFromWarehouse(resourceType, discountedCost.get(resourceType));
            } else {
                int missingQuantity = missingResourcesIntoWarehouseWithoutRemove(resourceType, discountedCost.get(resourceType));
                if (checkResourcesIntoStrongbox(resourceType, missingQuantity)) {
                    missingQuantity = missingResourcesIntoWarehouse(resourceType, discountedCost.get(resourceType));
                    takeResourcesFromStrongbox(resourceType, missingQuantity);
                }
            }
        }
        return true;
    }



    /**
     * insertResources method is called when a player takes resources form market: the player chooses what type of resource
     * and the quantity of that resource to add in the warehouse. The player chooses the level where to put those resources
     * and the method add the resources chosen into the level chosen by the player.
     */
    public boolean insertResources(ResourceType resource, int level, int quantity) {
        for(int i=1; i<=3; i++){
            if(warehouseDepot.getLevel(i).getResourceType()==null){}
            else if(i!=level && warehouseDepot.getLevel(i).getResourceType().equals(resource)) return false;
        }
        if(warehouseDepot.getLevel(level).getResourceType()!=resource && warehouseDepot.getLevel(level)
            .getResourceType()!=null) return false;
        int oldQuantity=warehouseDepot.getLevel(level).getCurrNumResources();
        return warehouseDepot.getLevel(level).addResources(resource, oldQuantity+quantity);
    }

    /**
     * activateProductionFromDevCard  method is called when a player activate a production form a DevelopmentCard in his
     * PersonalBoard. The method calls the production from the DevelopmentCard
     */
    public boolean activateProductionFromDevCard(int slot) {
        if( developmentCard[slot] != null )
            return (developmentCard[slot].activateProduction(this));
        else
            return false;
    }

    /**
     * activateProductionFromPersonalBoard represents the basic production power. The player choose two resources of any
     * type as payment and he obtains back one resource of any type(the player chooses the resource type).
     * @param resourceType1 is the first resource to activate production
     * @param resourceType2 is the second resource to activate production( can be the same type of first resource )
     * @param obtainedResource is the resource obtained after production
     * @return true if the player has got( in his warehouse or strongbox )the chosen resources to activate production
     */
    public boolean activateProductionFromPersonalBoard( ResourceType resourceType1, ResourceType resourceType2,
                                                        ResourceType obtainedResource ){
        if( obtainedResource == ResourceType.FAITHPOINTS )
            return false;
        boolean foundWare1=false,foundWare2=false;
        if( checkResourcesIntoWarehouse(resourceType1, 1) )foundWare1=true;
        else if( checkResourcesIntoStrongbox(resourceType1, 1) ) ;
        else return false;

        if( checkResourcesIntoWarehouse(resourceType2, 1) )foundWare2=true;
        else if( checkResourcesIntoStrongbox(resourceType2, 1) );
        else return false;

        if(foundWare1) takeResourcesFromWarehouse(resourceType1, 1);
        else takeResourcesFromStrongbox(resourceType1, 1);
        if(foundWare2) takeResourcesFromWarehouse(resourceType2, 1);
        else takeResourcesFromStrongbox(resourceType2, 1);

        addResourceToStrongboxTemp(obtainedResource, 1);
        return true;

    }

    /**
     * takeResourcesFromWarehouse method is called by the DevelopmentCard when the player activate production on that
     * DevelopmentCard. This method checks if the player has enough resources in his warehouse to activate
     * production and takes resources from warehouse if he has it. This method returns a boolean: if the boolean is true
     * it means the player has enough resources to activate production.
     */
    public boolean takeResourcesFromWarehouse(ResourceType resource, int quantity) {
        if (resource == warehouseDepot.getLevel(1).getResourceType()) {
            if (warehouseDepot.getLevel(1).removeResources(quantity)) {
                return true;
            }
        } else if (resource == warehouseDepot.getLevel(2).getResourceType()) {
            if (warehouseDepot.getLevel(2).removeResources(quantity)) {
                return true;
            }
        } else if (resource == warehouseDepot.getLevel(3).getResourceType()) {
            if (warehouseDepot.getLevel(3).removeResources(quantity)) {
                return true;
            }
        }
        return false;
    }

    /**
     * checkResourcesIntoWarehouse method checks if the warehouse contains a certain quantity of a resource type
     * @param resource says what resource type has to check
     * @param quantity says how many resources warehouse has to contains
     * @return true if warehouse contains at least that quantity of that resource
     */
    public boolean checkResourcesIntoWarehouse(ResourceType resource, int quantity) {
        if (resource == warehouseDepot.getLevel(1).getResourceType()) {
            if (quantity <= warehouseDepot.getLevel(1).getCurrNumResources()) {
                return true;
            }
        } else if (resource == warehouseDepot.getLevel(2).getResourceType()) {
            if (quantity <= warehouseDepot.getLevel(2).getCurrNumResources()) {
                return true;
            }
        } else if (resource == warehouseDepot.getLevel(3).getResourceType()) {
            if (quantity <= warehouseDepot.getLevel(3).getCurrNumResources()) {
                return true;
            }
        }
        return false;
    }


    /**
     * missingResourcesIntoWarehouse method checks how many resources are missing in the warehouse to reach a certain
     * quantity of that resource type and remove them.
     * @param resource represents the resource type to check
     * @param requestedQuantity is the quantity to check
     * @return how many resources misses
     */
    public int missingResourcesIntoWarehouse(ResourceType resource, int requestedQuantity) {
        int missing;
        if (resource == warehouseDepot.getLevel(1).getResourceType()) {
            if (requestedQuantity > warehouseDepot.getLevel(1).getCurrNumResources()) {
                missing = (requestedQuantity - warehouseDepot.getLevel(1).getCurrNumResources());
                takeResourcesFromWarehouse(resource, warehouseDepot.getLevel(1).getCurrNumResources());
                return missing;
            }
            else if (requestedQuantity <= warehouseDepot.getLevel(1).getCurrNumResources()) {
                takeResourcesFromWarehouse(resource, requestedQuantity);
                return 0;
            }
        } else if (resource == warehouseDepot.getLevel(2).getResourceType()) {
            if (requestedQuantity > warehouseDepot.getLevel(2).getCurrNumResources()) {
                missing = (requestedQuantity - warehouseDepot.getLevel(2).getCurrNumResources());
                takeResourcesFromWarehouse(resource, warehouseDepot.getLevel(2).getCurrNumResources());
                return missing;
            }
            else if (requestedQuantity <= warehouseDepot.getLevel(2).getCurrNumResources()) {
                takeResourcesFromWarehouse(resource, requestedQuantity);
                return 0;
            }
        } else if (resource == warehouseDepot.getLevel(3).getResourceType()) {
            if (requestedQuantity > warehouseDepot.getLevel(3).getCurrNumResources()) {
                missing = (requestedQuantity - warehouseDepot.getLevel(3).getCurrNumResources());
                takeResourcesFromWarehouse(resource, warehouseDepot.getLevel(3).getCurrNumResources());
                return missing;
            }
            else if (requestedQuantity <= warehouseDepot.getLevel(3).getCurrNumResources()) {
                takeResourcesFromWarehouse(resource, requestedQuantity);
                return 0;
            }
        }
            return requestedQuantity;
    }


    /**
     *missingResourcesIntoWarehouse method checks how many resources are missing in the warehouse to reach a certain
     *quantity of that resource type but it doesn't remove them.
     *@param resource represents the resource type to check
     *@param requestedQuantity is the quantity to check
     *@return how many resources misses
     */
    public int missingResourcesIntoWarehouseWithoutRemove(ResourceType resource, int requestedQuantity) {
        int missing;
        if (resource == warehouseDepot.getLevel(1).getResourceType()) {
            if (requestedQuantity > warehouseDepot.getLevel(1).getCurrNumResources()) {
                missing = (requestedQuantity - warehouseDepot.getLevel(1).getCurrNumResources());
                return missing;
            }
            else if (requestedQuantity <= warehouseDepot.getLevel(1).getCurrNumResources()) {
                return 0;
            }
        } else if (resource == warehouseDepot.getLevel(2).getResourceType()) {
            if (requestedQuantity > warehouseDepot.getLevel(2).getCurrNumResources()) {
                missing = (requestedQuantity - warehouseDepot.getLevel(2).getCurrNumResources());
                return missing;
            }
            else if (requestedQuantity <= warehouseDepot.getLevel(2).getCurrNumResources()) {
                return 0;
            }
        } else if (resource == warehouseDepot.getLevel(3).getResourceType()) {
            if (requestedQuantity > warehouseDepot.getLevel(3).getCurrNumResources()) {
                missing = (requestedQuantity - warehouseDepot.getLevel(3).getCurrNumResources());
                return missing;
            }
            else if (requestedQuantity <= warehouseDepot.getLevel(3).getCurrNumResources()) {
                return 0;
            }
        }
        return requestedQuantity;
    }


    /**
     * checkResourcesIntoStrongbox method check if strongbox contains "quantity" resources of "resource" resource type
     * @param resource is the resource type to check
     * @param quantity is the quantity of that resource type
     * @return true if it contains at least that resources
     */
    public boolean checkResourcesIntoStrongbox(ResourceType resource, int quantity) {
        return (quantity <= strongbox.getNumOf(resource));
    }

    public boolean takeResourcesFromStrongbox(ResourceType resource, int quantity) {
        return (strongbox.removeResources(resource, quantity));
    }

    /**
     * method addResourceToStrongboxTemp is called if takeResourcesFromWarehouse method returns true: this method adds
     * resources into the temporary strongbox "resourcesToAddToStrongbox"(the resources earned from production, written in the DevelopmentCard
     * activated from the player).
     */
    public void addResourceToStrongboxTemp(ResourceType resource, int quantity) {
        if (quantity < 0)
            quantity = 0;
        if (resourcesToAddToStrongbox.get(resource) != null && resource != ResourceType.FAITHPOINTS)
            quantity += resourcesToAddToStrongbox.get(resource);
        resourcesToAddToStrongbox.put(resource, quantity);
    }


    /**
     * fromStrongboxTempToStrongbox method puts into the strongbox's player all the resources earned with all the
     * productions activated by the player in this turn
     */
    public void fromStrongboxTempToStrongbox(){
        for (ResourceType resource : resourcesToAddToStrongbox.keySet()) {
            if( resource!= ResourceType.FAITHPOINTS )
                strongbox.addResources(resource, resourcesToAddToStrongbox.get(resource));
            else
                player.addFaithPointsAndCallAudience(resourcesToAddToStrongbox.get(resource));
        }
        resourcesToAddToStrongbox.clear();
    }

    /**
     * Method called to swap the resources from two levels.
     * @param maxSlotsFirst The first level to swap.
     * @param maxSlotsSecond The second level to swap.
     * @return True if the swap is possible, false if not.
     */
    public boolean switchLevels(int maxSlotsFirst, int maxSlotsSecond){
        return this.warehouseDepot.switchLevels(maxSlotsFirst, maxSlotsSecond);
    }

    /**
     * For whitebox testing
     * @return the board's warehouse depot
     */
    public WareHouseDepot getWarehouseDepot() {
        return warehouseDepot;
    }

    public int getTopCardsVictoryPoints(){
        int tot = 0;
        for( int i=0; i<3; i++ ) {
            if( developmentCard[i] != null ) {
               tot += developmentCard[i].getVictoryPoints();
            }
        }
        return tot;
    }

    public TreeMap<Colour, Integer> getCardsColours(){
        return cardsColours;
    }

    public ArrayList<DevelopmentCard> getInsertedDevelopmentCards() {
        return insertedDevelopmentCards;
    }
}


