package it.polimi.ingsw;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeMap;


/**
 * Main class: Users actions are managed by Game class.
 */
public class Game {

    //Todo( IMPLEMENTARE RESILIENZA )
    /**
     * Reference to market board, unique in the game and shared by all players.
     */
    private MarketBoard marketBoard = new MarketBoard(this);
    /**
     * Contains all the Game's players.
     */
    private ArrayList<Player> players = new ArrayList<>();
    /**
     * It represents the player who is playing in this turn.
     */
    private Player currentPlayer;
    /**
     * Represents the 12 decks of development cards.
     */
    private Deckgrid deckgrid = new Deckgrid();
    /**
     * Represents the deck of Solo Action tokens, used in single player mode.
     */
    private ActionCardStack actionCardStack;
    /**
     * Represents Lorenzo il Magnifico: the virtual opponent in single player mode.
     */
    private LorenzoSingleton lorenzo;
    /**
     * True if game has already started.
     */
    private boolean gameStarted = false;
    /**
     * Represents through the player number(first player, second player.. ordered by tourn order) which of all
     * players is the winner of the game.
     */
    private int WinnerPlayerNumber;
    /**
     * The Player who wins the game.
     */
    private Player winnerPlayer;
    /**
     * It is true if a player reaches the end of faith track or buy the 7'th development cards.
     */
    private boolean isEndGame = false;
    /**
     * True if the player has already done one of the necessary actions to end the turn.
     */
    private boolean actionCardDone = false;
    /**
     * Represent the initial leader card's deck that contains all the leader cards of the game.
     */
    private LeaderCardDeck leaderCardDeck = new LeaderCardDeck();


    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public Game getGame() {
        return this;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setEndGame(boolean endGame) {
        isEndGame = endGame;
    }

    public MarketBoard getMarketBoard() {
        return marketBoard;
    }

    public Game() throws FileNotFoundException {
    }

    /**
     * Add a player in the game, if game has less then 4 players.
     * @param nickname is the player's nickname in the game
     * @return true if has correctly added a player in the game
     */
    public boolean addPlayer( String nickname ){
        if( players.size() >= 4 || gameStarted ) {
            return false;
        }
        Player p = new Player();
        p.setNickname(nickname);
        p.setGame(this);
        players.add(p);
        return true;
    }

    /**
     * Starts the single player mode, only if there is 1 player in game.
     * @return true if game correctly starts in single player mode.
     */
    public boolean startSingleplayer(){
        //Todo( distribuire 4 leaderCards a testa e ogni player ne sceglie 2 )
        if( players.size()!=1 || gameStarted ) {
            return false;
        }
        leaderCardDeck.randomDistribute(players.get(0));
        this.actionCardStack = new ActionCardStack(deckgrid);
        this.lorenzo = LorenzoSingleton.getLorenzo();
        gameStarted = true;
        players.get(0).setInitialDistribution(true);
        return true;
    }


    /**
     * Starts the multiplayer mode, only if there at least 2 players in the game(maximum 4 players).
     * @return true if the game correctly starts in multiplayer mode.
     */
    public boolean startMultiplayer(){
        if( players.size()<=1 || players.size()>4 || gameStarted ) {
            return false;
        }
        Collections.shuffle(players);
        players.get(0).setInkwell(true);
        currentPlayer = players.get(0);
        for( int i = 0; i<players.size(); i++ ){
            players.get(i).setPlayerNumber(i+1);
            leaderCardDeck.randomDistribute(players.get(i));
            if( i==0 ){
                players.get(i).setInitialDistribution(true);
                players.get(i).setCanEndTurn(true);
            }
            if( i==2){
                players.get(i).addFaithPointsAndCallAudience(1);
            }
            else if( i==3 ){
                players.get(i).addFaithPointsAndCallAudience(1);
            }
        }
        gameStarted = true;
        return true;
    }

    /**
     * Makes turn ends for the current player and starts the turn for the next player.
     * @return true if the player can end the turn.
     */
    public boolean endTurn(){
        if( !currentPlayer.isInitialDistribution() || !currentPlayer.isCanEndTurn() || !currentPlayer.hasChosenLeaderCards()){
            return false;
        }
        if(currentPlayer.getHasTrasformationAbility()&&marketBoard.getWhiteMarblesSelected()>0) return false;
        marketBoard.setWhiteMarblesSelected(0);
        if(players.size()==1&&!actionCardDone) return false;
        actionCardDone=false;
        if( !marketBoard.getTemporaryResources().isEmpty() ){
            discardRemainingResources();
        }
        if( currentPlayer.getPlayerNumber()==players.size() ){
            if( isEndGame )
                endGame();
            currentPlayer = players.get(0);
        }
        else {
            int nextPlayer;
            nextPlayer = currentPlayer.getPlayerNumber();
            currentPlayer = players.get(nextPlayer);
        }
        currentPlayer.setLeaderActionDone(false);
        currentPlayer.setCanEndTurn(false);
        return true;
    }


    /**
     * Does the initial distribution of one resource for the second and third players.
     * @param resourceType is the resource type chosen by the player.
     * @return true if it is called on the second or third player.
     */
    public boolean distributionResourceSecondThird( ResourceType resourceType ){
        if( (currentPlayer.getPlayerNumber() == 2 || currentPlayer.getPlayerNumber() == 3)
            && !currentPlayer.isInitialDistribution()) {
            currentPlayer.insertResources(resourceType, 1, 1);
            currentPlayer.setInitialDistribution(true);
            currentPlayer.setCanEndTurn(true);
            return true;
        }
        return false;
    }

    /** Does the initial distribution of two resources for the fourth player.
     * @param resourceType1 is the first resource type chosen by the player.
     * @param resourceType2 is the second resource type chosen by the player.
     * @return true if it is called on the fourth player.
     */
    public boolean distributionResourceFourthPlayer( ResourceType resourceType1, ResourceType resourceType2 ){
        if( currentPlayer.getPlayerNumber() == 4 && !currentPlayer.isInitialDistribution()) {
            if( resourceType1.equals(resourceType2) ) {
                currentPlayer.insertResources(resourceType1, 2, 2);
            }
            else {
                currentPlayer.insertResources(resourceType1, 1, 1);
                currentPlayer.insertResources(resourceType2, 2, 1);
            }
            currentPlayer.setInitialDistribution(true);
            currentPlayer.setCanEndTurn(true);
            return true;
        }
        return false;
    }

    /**
     * Founds the winner player on base of victory points of all players and sets the winner player.
     * @return true when has found the winner player.
     */
    public boolean endGame(){
        int maxPoints = 0;
        int WinnerPlayerNumber = 1;

        for( int i=0; i<players.size(); i++ ){
            players.get(i).calculateVictoryPoints();
            if( players.get(i).getVictoryPoints() == maxPoints ) {
                if(players.get(WinnerPlayerNumber-1).getTotNumOfRes() < players.get(i).getTotNumOfRes()) {
                    WinnerPlayerNumber = players.get(i).getPlayerNumber();
                    this.WinnerPlayerNumber=WinnerPlayerNumber;
                    winnerPlayer=players.get(WinnerPlayerNumber-1);
                }
            }
            if ( players.get(i).getVictoryPoints() > maxPoints ) {
                maxPoints = players.get(i).getVictoryPoints();
                WinnerPlayerNumber = players.get(i).getPlayerNumber();
                this.WinnerPlayerNumber=WinnerPlayerNumber;
                winnerPlayer=players.get(WinnerPlayerNumber-1);
            }
        }
        return true;
    }

    /**
     * Allows the player to switch resources through warehouse's levels, if it is permitted by the rules.
     * @param maxSlotsFirst represents the first level to switch with the second.
     * @param maxSlotsSecond represents the second level to switch with the first.
     * @return true if the level switch is legal.
     */
    public boolean switchLevels(int maxSlotsFirst, int maxSlotsSecond){
        return currentPlayer.switchLevels(maxSlotsFirst, maxSlotsSecond);
    }

    /**
     * Allows the player to buy a development card from the deckgrid and insert that card into one of his personal board's slots.
     * @param level is the level of the card wanted by the player.
     * @param colour is the colour of the card wanted by the player.
     * @param slot is the personal board's slot where te player wants to insert the card
     * @return true if the player can buy that card and if the player can insert that card into the chosen slot
     */
    public boolean buyDevelopmentCard( int level, Colour colour, int slot , int payUsingExtraDep1, int payUsingExtraDep2 ) {
        if (!currentPlayer.isInitialDistribution() || currentPlayer.isCanEndTurn() || !currentPlayer.hasChosenLeaderCards())
            return false;
        if (payUsingExtraDep1 < 0 || payUsingExtraDep1 > 2 || (payUsingExtraDep1 > 0 && currentPlayer.getPersonalBoard().getExtraDeposit1() == null))
            return false;
        if (payUsingExtraDep2 < 0 || payUsingExtraDep2 > 2 || (payUsingExtraDep2 > 0 && currentPlayer.getPersonalBoard().getExtraDeposit2() == null))
            return false;

        if (payUsingExtraDep1 != 0 || payUsingExtraDep2 != 0) {
            currentPlayer.getPersonalBoard().setPayUsingExtraDep1(payUsingExtraDep1);
            currentPlayer.getPersonalBoard().setPayUsingExtraDep2(payUsingExtraDep2);
        }

        boolean canBuy;
        if (deckgrid.readCard(level, colour) == null)
            return false;
        canBuy = (currentPlayer.insertCard(deckgrid.readCard(level, colour), slot));
        if (canBuy) {
            deckgrid.removeCard(level, colour);
            currentPlayer.setCanEndTurn(true);
            return true;
        }
        return false;
    }


    /**
     * Allows the player to activate production from development cards, base production power, leader cards. The player
     * can choose all the possible productions or only some of those.
     * @param whichDevCardSlot says from which development cards the player wants to activate the production.
     * @param fromPersonalBoard says if the player wants to activate production from personal board.
     * @param whichLeaderCard says from which leader cards the player wants to activate the production.
     * @param resourceType1 is the first resource type of resource the player wants to pay to activate the base production power.
     *                      It is null if the player doesn't want to use the base production power.
     * @param resourceType2 is the second resource type of resource the player wants to pay to activate the base production power.
     *      *                      It is null if the player doesn't want to use the base production power.
     * @param obtainedResource is the resource type of resource received by base production power.
     *                         It is null if the player doesn't want to use the base production power.
     * @param resourceObtainedFromLeader is the resource type of resources received by leader card's production power.
     *      *                         It is null if the player doesn't want to use any leader card's production power or
     *                                   if the player hasn't got any leader card with a production ability
     * @return true if the productions can be done.
     */
    public boolean activateProduction(boolean[] whichDevCardSlot, boolean fromPersonalBoard, boolean[] whichLeaderCard,
                                      ResourceType resourceType1, ResourceType resourceType2, ResourceType obtainedResource,
                                      ResourceType[] resourceObtainedFromLeader, int payUsingExtraDep1, int payUsingExtraDep2 ){

        if( !currentPlayer.isInitialDistribution() || currentPlayer.isCanEndTurn() || !currentPlayer.hasChosenLeaderCards()  )
            return false;

        if (payUsingExtraDep1 < 0 || payUsingExtraDep1 > 2 || (payUsingExtraDep1 > 0 && currentPlayer.getPersonalBoard().getExtraDeposit1() == null))
            return false;
        if (payUsingExtraDep2 < 0 || payUsingExtraDep2 > 2 || (payUsingExtraDep2 > 0 && currentPlayer.getPersonalBoard().getExtraDeposit2() == null))
            return false;

        if (payUsingExtraDep1 != 0 || payUsingExtraDep2 != 0) {
            currentPlayer.getPersonalBoard().setPayUsingExtraDep1(payUsingExtraDep1);
            currentPlayer.getPersonalBoard().setPayUsingExtraDep2(payUsingExtraDep2);
        }

        //DEV CARDS
        for( int i=0; i<3; i++ ){
            if( whichDevCardSlot[i] ){
                currentPlayer.getPersonalBoard().activateProductionFromDevCard(i);
            }
        }

        //BASE PRODUCTION
        if( fromPersonalBoard && resourceType1!=null && resourceType2!=null &&
                obtainedResource!=null && obtainedResource!=ResourceType.FAITHPOINTS ){
            currentPlayer.getPersonalBoard().activateProductionFromPersonalBoard(resourceType1, resourceType2, obtainedResource);
        }
        currentPlayer.getPersonalBoard().fromStrongboxTempToStrongbox();

        //LEADER CARDS
        for( int j=0; j<2; j++ ){
            if( whichLeaderCard[j] && currentPlayer.getChoosedLeaderCards().get(j).isActive() && resourceObtainedFromLeader[j]!=null ){
                currentPlayer.getPersonalBoard().activateProductionFromLeaderCard(resourceObtainedFromLeader[j]);
            }
        }
        currentPlayer.setCanEndTurn(true);

        currentPlayer.getPersonalBoard().setPayUsingExtraDep1(0);
        currentPlayer.getPersonalBoard().setPayUsingExtraDep2(0);
        return true;
    }

    /**
     * Allows the player to select a row or a column in the resource market to take the resources contained in that row or column.
     * @param row index of selection for row.
     * @param column index of selection for column.
     * @return true if player selects a correct row or column.
     */
    public boolean takeResourcesFromMarket( int row, int column ){
        if( !currentPlayer.isInitialDistribution() || currentPlayer.isCanEndTurn() || !currentPlayer.hasChosenLeaderCards())
            return false;
        if( marketBoard.getResourcesFromMarket(row, column) ) {
            if(currentPlayer.getHasTrasformationAbility())
                return true;
            currentPlayer.setCanEndTurn(true);
            return true;
        }
        else
            return false;
    }

    /**
     * Allows the player to insert into his warehouse the resources he wants to insert, once he toke those resources from market.
     * @param resourceType is the resource type of resource that the player wants to insert
     * @param quantityToAdd is the quantity of resource that the player wants to insert.
     * @return true if the player can effectively insert than number of that type of resource.
     */
    public boolean insertResourcesIntoWarehouse( ResourceType resourceType, int quantityToAdd, boolean intoExtraDeposit ){
        if(marketBoard.getTemporaryResources().get(resourceType)==null) return false;
        int maxAddable = marketBoard.getTemporaryResources().get(resourceType);
        if( quantityToAdd > maxAddable || quantityToAdd <= 0 )
            return false;
        if (intoExtraDeposit){
            if (currentPlayer.getPersonalBoard().addToExtraDeposit1(resourceType,quantityToAdd)){
                return true;
            }
            else return currentPlayer.getPersonalBoard().addToExtraDeposit2(resourceType, quantityToAdd);
        }
        if( currentPlayer.getPersonalBoard().insertResources(resourceType, 1, quantityToAdd) ) {
            marketBoard.getTemporaryResources().put(resourceType, maxAddable - quantityToAdd);
            return true;
        }
        else if( currentPlayer.getPersonalBoard().insertResources(resourceType, 2, quantityToAdd) ){
            marketBoard.getTemporaryResources().put(resourceType, maxAddable - quantityToAdd);
            return true;
        }
        else if( currentPlayer.getPersonalBoard().insertResources(resourceType, 3, quantityToAdd) ){
            marketBoard.getTemporaryResources().put(resourceType, maxAddable - quantityToAdd);
            return true;
        }
        return false;
    }


    /**
     * After the insertion of resources into warehouse, the player has to discard all the resources taken from market
     * that he has not inserted into his warehouse. For every discarded resource, the other players earn one faith point.
     */
    private void discardRemainingResources(){
        TreeMap<ResourceType, Integer>map;
        boolean doneAudience = false;
        map = marketBoard.getTemporaryResources();
        for( ResourceType r: map.keySet() ){
            int pointsToAdd = map.get(r);
            if(players.size()==1){
                lorenzo.addFaithPoints(pointsToAdd);
            }
            else {
                for (Player player : players) {
                    if (player.getPlayerNumber() != currentPlayer.getPlayerNumber()) {
                        player.addFaithPointsWithoutCallingAudience(pointsToAdd);
                    }
                }
                for (Player player : players) {
                    if (player.getPlayerNumber() != currentPlayer.getPlayerNumber()) {
                        player.endGameFaithPoints();
                        if (!doneAudience) {
                            doneAudience = player.callAudience();
                        }
                    }
                }
            }
        }
        map.clear();
    }


    /**
     * When a Faith Marker reaches (or goes beyond) a Pope space, a Vatican Report occurs. All the players in that Pope space
     * earn 2 victory points.
     * @return false and it does nothing if firsAudience() method was already called.
     */
    public boolean firstAudience(){
        for(Player player: players){
            if( player.getFaithCards()[0] == 0 )
                return false;
            if (player.getFaithPoints() >= 5) {
                player.addVictoryPoints(2);
            }
            player.setFaithCardsZero(0);
        }
        return true;
    }

    /**
     * When a Faith Marker reaches (or goes beyond) a Pope space, a Vatican Report occurs. All the players in that Pope space
     * earn 3 victory points.
     * @return false and it does nothing if secondAudience() method was already called.
     */
    public boolean secondAudience(){
        for(Player player: players){
            if( player.getFaithCards()[1] == 0 )
                return false;
            if (player.getFaithPoints() >= 12) {
                player.addVictoryPoints(3);
            }
            player.setFaithCardsZero(1);
        }
        return true;
    }

    /**
     * When a Faith Marker reaches (or goes beyond) a Pope space, a Vatican Report occurs. All the players in that Pope space
     * earn 3 victory points.
     * @return false and it does nothing if thirdAudience() method was already called.
     */
    public boolean thirdAudience() {
        for (Player player : players) {
            if (player.getFaithCards()[2] == 0)
                return false;
            if (player.getFaithPoints() >= 19) {
                player.addVictoryPoints(4);
            }
            player.setFaithCardsZero(2);
        }
        return true;
    }

    public Player getWinnerPlayer() {
        return winnerPlayer;
    }

    public int getWinnerPlayerNumber() {
        return WinnerPlayerNumber;
    }

    /**
     * In single player mode, it takes the first action token from action card deck and activates the effect.
     * @return true if it can be done.
     */
    public boolean activateActionCard(){
        if(players.size()!=1 || !currentPlayer.isCanEndTurn()) return false;
        actionCardStack.activateCard();
        actionCardDone=true;
        return true;
    }

    /**
     * Make the player choose 2 leader cards from the 4 leader cards he got when game starts.
     * @param pos1
     * @param pos2
     * @return false if the current player has already chosen the two leader cards
     */
    public boolean chooseLeaderCards(int pos1, int pos2){
        if(currentPlayer.hasChosenLeaderCards())
            return false;
        if(currentPlayer.chooseLeaderCards(pos1, pos2)){
            currentPlayer.setChosenLeaderCards(true);
            return true;
        }
        return false;
    }

    public boolean discardLeaderCard(int pos){
        if(currentPlayer.isLeaderActionDone())
            return false;
        return currentPlayer.discardLeaderCard(pos);
    }

    /**
     * Allows the player to activate the leader card's special ability.
     * @param pos //TODO
     * @return false if the player has already done his move in this turn.
     */
    //TODO( il player può attivare entrambe le leader cards, osì invece può attivarne una sola )
    public boolean activateLeaderCard(int pos){
        if(currentPlayer.isLeaderActionDone())
            return false;
        return currentPlayer.activateLeaderCard(pos);
    }

    public boolean activateLeaderAbility(int whichLeaderCard){
        return currentPlayer.activateLeaderAbility(whichLeaderCard);
    }
}


