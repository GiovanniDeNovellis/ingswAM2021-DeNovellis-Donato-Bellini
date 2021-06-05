package it.polimi.ingsw;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * The class representing the player, contains all his attributes
 * and his game objects such as the personal board.
 */
public class Player {
    /**
     * The personal board of the player
     */
    private final PersonalBoard personalBoard = new PersonalBoard(this);
    /**
     * The player's position on the faith track.
     */
    private int faithPoints = 0;
    /**
     * The array of the faith cards. A faith card's value
     * is 0 after the corresponding audience is done.
     */
    private final int[] faithCards = {2, 3, 4};
    /**
     * The inkwell is true for the first player
     * of the round.
     */
    private boolean inkwell = false;
    /**
     * The player's number represent his
     * position in the turn.
     */
    private int playerNumber;
    /**
     * The player's amount of victory points used to decide the winner.
     */
    private int victoryPoints = 0;
    /**
     * Nickname of the player
     */
    private String nickname;
    /**
     * If true has to skip the player's turn.
     */
    private boolean isAFK = false;
    /**
     * True if the player is allowed to finish is turn.
     */
    private boolean canEndTurn = false;
    /**
     * True if the player has chosen his first resources.
     */
    private boolean initialDistribution = false;
    /**
     * Total number of resources owned by the player.
     */
    private int totNumOfRes = 0;
    /**
     * The 4 cards in which the player has to choose
     * the 2 leader cards.
     */
    private final ArrayList<LeaderCard> choosableLeaderCards = new ArrayList<>();
    /**
     * The 2 leader cards chosed by the player.
     */
    private final ArrayList<LeaderCard> choosedLeaderCards = new ArrayList<>();
    /**
     * True if the player has chosen the 2 leader cards.
     */
    private boolean chosenLeaderCards = false;
    /**
     * The game class reference.
     */
    private Game game;
    /**
     * True if the player has to choose how to transform his white marbles
     */
    private int numTransformationAbility=0;


    /**
     * Public method.
     * Getter of ChoosedLeaderCards
     * @return the reference of the choosedLeaderCards
     */
    public ArrayList<LeaderCard> getChoosedLeaderCards() {
        return choosedLeaderCards;
    }

    /**
     * Public method.
     * Used to choose the 2 leader cards from the
     * 4 available.
     * @param pos1 Position of the first leader card.
     * @param pos2 Position of the second leader card.
     * @return True if the operation has been done successfully
     */
    public boolean chooseLeaderCards (int pos1, int pos2){
        if (pos1<0 || pos1>=4 || pos2<0 || pos2>=4){
            return false;
        }
        choosedLeaderCards.add(choosableLeaderCards.get(pos1));
        choosedLeaderCards.add(choosableLeaderCards.get(pos2));
        choosedLeaderCards.get(0).setOwner(this);
        choosedLeaderCards.get(1).setOwner(this);
        return true;
    }

    /**
     * Public method.
     * Used to set if the player has chosen the leader cards.
     * @param chosenLeaderCards True if the player has chosen the leader cards.
     */
    public void setChosenLeaderCards(boolean chosenLeaderCards) {
        this.chosenLeaderCards = chosenLeaderCards;
    }

    /**
     * Public method.
     * @return True if the player has chosen the leader cards.
     */
    public boolean hasChosenLeaderCards() {
        return chosenLeaderCards;
    }

    /**
     * Public method.
     * Setter for the game reference.
     * @param game reference of the game class.
     */
    public void setGame(Game game){
        this.game = game;
    }

    /**
     * Public method.
     * Getter for the game reference.
     * @return the reference of the game class.
     */
    public Game getGame() {
        return game;
    }

    /**
     * Public method.
     * Used to distribute the 4 leader cards to the player
     * that will choose 2 of them.
     * @param leaderCard The leader card to add.
     */
    public void addChoosableLeaderCards(LeaderCard leaderCard){
        this.choosableLeaderCards.add(leaderCard);
    }

    /**
     * Public method.
     * @return True if the player can end his turn.
     */
    public boolean canEndTurn() {
        return canEndTurn;
    }

    /**
     * Public method.
     * Setter of CanEndTurn.
     * @param canEndTurn Set if the player can end his turn.
     */
    public void setCanEndTurn(boolean canEndTurn) {
        this.canEndTurn = canEndTurn;
    }

    /**
     * Public method.
     * Getter of totNumOfRes.
     * @return The number of resources owned by the player.
     */
    public int getTotNumOfRes() {
        return totNumOfRes;
    }

    /**
     * Public method.
     * Set the position of the player in the turn.
     * @param playerNumber Number to associate to the player.
     */
    public void setPlayerNumber(int playerNumber) {
        this.playerNumber = playerNumber;
    }

    /**
     * Public method.
     * Getter of PLayerNumber
     * @return The player's number.
     */
    public int getPlayerNumber(){
        return playerNumber;
    }

    /**
     * Public method.
     * Used to add victory points to the player.
     * @param quantity Points to add.
     */
    public void addVictoryPoints(int quantity){
        this.victoryPoints += quantity;
    }

    /**
     * Public method.
     * Getter of VictoryPoints.
     * @return The victory points of the player.
     */
    public int getVictoryPoints(){
        return victoryPoints;
    }

    /**
     * Public method.
     * @return False if the player has done the initial distribution.
     */
    public boolean notDoneInitialDistribution() {
        return !initialDistribution;
    }

    /**
     * Public method.
     * Setter of the InitialDistribution
     * @param initialDistributionDone Set True when the player does the initial distribution.
     */
    public void setInitialDistribution(boolean initialDistributionDone) {
        this.initialDistribution = initialDistributionDone;
    }

    /**
     * Public method.
     * Getter of the PersonalBoard
     * @return The player's personal board.
     */
    public PersonalBoard getPersonalBoard() {
        return personalBoard;
    }

    /**
     * Public method.
     * Getter of the FaithCards.
     * @return The player's faith cards.
     */
    public int[] getFaithCards() {
        return faithCards;
    }

    /**
     * Public method.
     * Setter of the faith card to zero when it's read( to add it's points or to discard it)
     * @param position The position of the card.
     */
    public void setFaithCardsZero(int position) {
        this.faithCards[position] = 0;
    }

    /**
     * Public method.
     * Setter of the inkwell.
     * @param inkwell Set true to the first player.
     */
    public void setInkwell(boolean inkwell) {
        this.inkwell = inkwell;
    }

    /**
     * Public method.
     * Setter of the nickname of the player.
     * @param nickname Nickname of the player.
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * Public method.
     * Getter of the Nickname.
     * @return The nickname of the player.
     */
    public String getNickname(){
        return nickname;
    }

    /**
     * Public method.
     * Getter of FaithPoints.
     * @return The faith points of the player.
     */
    public int getFaithPoints(){
        return faithPoints;
    }

    /**
     * Public Method.
     * Used to insert a development card into the player's
     * personal board.
     * @param devCard The development card to insert.
     * @param position The position of the personal board where
     *                 the card has to be inserted.
     * @return True if the insertion is completed successfully.
     */
    public boolean insertCard(DevelopmentCard devCard, int position ){
        return ( personalBoard.insertCard(devCard, position ) );
    }

    /**
     * Public method.
     * Used to insert resources into the warehouse during the first distribution.
     * @param resourceType Type of resource.
     * @param level Level of the warehouse.
     * @param quantity Quantity to insert.
     * @return True if the insertion is completed successfully.
     */
    public boolean insertResourcesIntoWarehouse(ResourceType resourceType, int level, int quantity ) {
        return (personalBoard.insertResources(resourceType, level, quantity));
    }

    /**
     * Public method.
     * Used to switch the resources of two levels.
     * @param maxSlotsFirst The first level's number.
     * @param maxSlotsSecond The second level's number.
     * @return True if the switch is completed successfully,
     */
    public boolean switchLevels(int maxSlotsFirst, int maxSlotsSecond){
        return personalBoard.switchLevels(maxSlotsFirst, maxSlotsSecond);
    }

    /**
     * Public method.
     * Add faith points to the player and calls an audition if needed.
     * @param pointsToAdd Quantity of points to add.
     * @return True if the addition is completed successfully.
     */
    public boolean addFaithPointsAndCallAudience(int pointsToAdd ){
        faithPoints += pointsToAdd;
        if( faithPoints >= 8 && faithPoints < 16 ){
            if( faithCards[0]!=0 ) {
                game.firstAudience();
                return true;
            }
        }
        if( faithPoints >= 16 && faithPoints < 24 ){
            if( faithCards[1]!=0 ) {
                game.secondAudience();
                return true;
            }
        }
        if( faithPoints >= 24 ) {
            faithPoints = 24;
            if( faithCards[2]!=0 ) {
                game.thirdAudience();
                return true;
            }
            game.setEndGame(true);
        }
        return false;
    }

    /**
     * Public method.
     * Used to call an audience if there are enough points.
     * @return True if a call has been completed.
     */
    public boolean callAudience(){
        if( faithPoints >= 8 && faithPoints < 16 ){
            if( faithCards[0]!=0 ) {
                game.firstAudience();
                return true;
            }
        }
        if( faithPoints >= 16 && faithPoints < 24 ){
            if( faithCards[1]!=0 ) {
                game.secondAudience();
                return true;
            }
        }
        if( faithPoints >= 24 ) {
            faithPoints = 24;
            if( faithCards[2]!=0 ) {
                game.thirdAudience();
                return true;
            }
            game.setEndGame(true);
        }
        return false;
    }

    /**
     * Public method.
     * Used to add faith points to the player without calling an audience if needed.
     * @param pointsToAdd Quantity of points to add.
     */
    public void addFaithPointsWithoutCallingAudience(int pointsToAdd ){
        faithPoints += pointsToAdd;
        if( faithPoints >= 24 ) {
            faithPoints = 24;
        }
    }

    /**
     * Public method.
     * Used to set the end game if a player has reached the
     * 24 faith points.
     */
    public void endGameFaithPoints(){
        if( faithPoints >= 24 ){
            game.setEndGame(true);
        }
    }


    //STRONGBOX, DEVCARDS, LEADERCARDS, POSITION(TRACCIATO)

    /**
     * Public method.
     * Used to calculate the victory points of
     * every player at the end of the game.
     */
    public void calculateVictoryPoints(){
        int numOfResSpecialDept=0;
        int leaderCardsPoints=0;
        int depotsPoints;
        int positionPoints = 0;
        int numOfResTopDevCards = 0;
        int numOfResWarehouse = 0;
        int numOfResStrongbox = personalBoard.getStrongbox().getVictoryPoints();
        WareHouseDepot warehouse = personalBoard.getWarehouseDepot();
        for( int i=1; i<4; i++ ){
            numOfResWarehouse += warehouse.getLevel(i).getCurrNumResources();
        }
        for(LeaderCard l: choosedLeaderCards){
            if(l.isActive())
                leaderCardsPoints+=l.getVictoryPoints();
        }
        if(personalBoard.getExtraDeposit1()!=null){
            numOfResSpecialDept+=personalBoard.getExtraDeposit1().getCurrentQuantity();
        }
        if(personalBoard.getExtraDeposit2()!=null){
            numOfResSpecialDept+=personalBoard.getExtraDeposit2().getCurrentQuantity();
        }
        numOfResTopDevCards += personalBoard.getTopCardsVictoryPoints();
        totNumOfRes = ( numOfResWarehouse + numOfResStrongbox + numOfResSpecialDept );
        depotsPoints =( numOfResWarehouse + numOfResStrongbox + numOfResSpecialDept )/5;
        if( faithPoints >= 3 && faithPoints < 6 )
            positionPoints = 1;
        else if( faithPoints >= 6 && faithPoints < 9 )
            positionPoints = 2;
        else if( faithPoints >= 9 && faithPoints < 12 )
            positionPoints = 4;
        else if( faithPoints >= 12 && faithPoints < 15 )
            positionPoints = 6;
        else if( faithPoints >= 15 && faithPoints < 18 )
            positionPoints = 9;
        else if( faithPoints >= 18 && faithPoints < 21 )
            positionPoints = 12;
        else if( faithPoints >= 21 && faithPoints < 24 )
            positionPoints = 16;
        else if( faithPoints >= 24 )
            positionPoints = 20;

        int toSet = numOfResTopDevCards + depotsPoints + positionPoints+ leaderCardsPoints;
        this.addVictoryPoints(toSet);
    }

    /**
     * Public method.
     * Used to discard a leader card and add 1 faith points to the player.
     * @param pos Position of the card to discard.
     * @return True if the removal has been completed successfully.
     */
    public boolean discardLeaderCard(int pos){
        if(pos<0 || pos>=choosedLeaderCards.size()|| choosedLeaderCards.get(pos).isActive())
            return false;
        choosedLeaderCards.remove(pos);
        addFaithPointsAndCallAudience(1);
        return true;
    }

    /**
     * Public method.
     * Used to activate a leader card.
     * @param pos Position of the leader card.
     * @return True if the activation has been done successfully.
     */
    public boolean activateLeaderCard(int pos){
        if(pos<0 || pos>=choosedLeaderCards.size())
            return false;
        if(choosedLeaderCards.get(pos).isActive())
            return false;
        return choosedLeaderCards.get(pos).setActive();
    }

    /**
     * Public method.
     * Used to activate a leader ability.
     * @param whichLeaderCard Position of the leader card.
     * @return True if the ability has been activated successfully.
     */
    public boolean activateLeaderAbility(int whichLeaderCard){
        if( choosedLeaderCards.get(whichLeaderCard) == null )
            return false;
        if ( !choosedLeaderCards.get(whichLeaderCard).isActive() )
            return false;
        else
            return choosedLeaderCards.get(whichLeaderCard).activateAbility();
    }

    /**
     * Public method.
     * @return Map of the colours owned by the player.
     */
    public Map<Colour, Integer> getCardColours(){
        return this.personalBoard.getCardsColours();
    }

    /**
     * Public method.
     * @return All the development cards owned by the player.
     */
    public ArrayList<DevelopmentCard> getInsertedDevCards(){
        return personalBoard.getInsertedDevelopmentCards();
    }

    /**
     * Public method.
     * Getter of NumTransformationAbility.
     * @return The number of transformation ability cards.
     */
    public int getNumTransformationAbility() {
        return numTransformationAbility;
    }

    /**
     * Public method.
     * Increaser of NumTransformationAbility.
     */
    public void increaseNumTrasformationAbility() {
        this.numTransformationAbility++;
    }

    /**
     * Public method.
     * Check if the player is away from keyboard.
     * @return true if is AFK.
     */
    public boolean isAFK() {
        return isAFK;
    }

    /**
     * @param isAFK The new AFK status of the player.
     */
    public void setIsAFK(boolean isAFK) {
        this.isAFK = isAFK;
    }

    /**
     * @return The choosable Leader Cards of the player.
     */
    public ArrayList<LeaderCard> getChoosableLeaderCards() {
        return choosableLeaderCards;
    }

    public int[] getChoosableLeadercardsNumber(){
        int i = 0;
        int[] numbers = new int[4];
        for( LeaderCard l : choosableLeaderCards ) {
            numbers[i] = l.getLeaderCardNumber();
            i++;
        }
        return numbers;
    }
}