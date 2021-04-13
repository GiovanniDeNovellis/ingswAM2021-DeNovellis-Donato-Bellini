package it.polimi.ingsw;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.TreeMap;

public class Game {

    //Todo( IMPLEMENTARE RESILIENZA )
    private MarketBoard marketBoard = new MarketBoard(this);
    private ArrayList<Player> players = new ArrayList<>();
    private int numPlayers = 0;
    private Player currentPlayer;
    private Deckgrid deckgrid = new Deckgrid();
    private ActionCardStack actionCardStack;
    private LorenzoSingleton lorenzo;
    private boolean gameStarted = false;
    private int WinnerPlayerNumber;
    private Player winnerPlayer;
    private boolean isEndGame = false;
    private boolean actionCardDone = false;
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



    public boolean startMultiplayer(){
        if( players.size()<=1 || players.size()>4 || gameStarted ) {
            return false;
        }
        //Todo( distribuire 4 leaderCards a testa e ogni player ne sceglie 2 )
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

    public boolean switchLevels(int maxSlotsFirst, int maxSlotsSecond){
        return currentPlayer.switchLevels(maxSlotsFirst, maxSlotsSecond);
    }

    public boolean buyDevelopmentCard( int level, Colour colour, int slot ){
        if( !currentPlayer.isInitialDistribution() || currentPlayer.isCanEndTurn() || !currentPlayer.hasChosenLeaderCards())
            return false;
        boolean canBuy = false;
        if( deckgrid.readCard(level, colour) == null )
            return false;
        canBuy = ( currentPlayer.insertCard(deckgrid.readCard(level, colour), slot) );
        if( canBuy ){
            deckgrid.removeCard(level, colour);
            currentPlayer.setCanEndTurn(true);
            return true;
        }
        return false;
    }


    public boolean activateProduction(boolean[] whichDevCardSlot, boolean fromPersonalBoard, boolean[] whichLeaderCard,
                                      ResourceType resourceType1, ResourceType resourceType2, ResourceType obtainedResource){

        if( !currentPlayer.isInitialDistribution() || currentPlayer.isCanEndTurn() || !currentPlayer.hasChosenLeaderCards()  )
            return false;
        for( int i=0; i<3; i++ ){
            if( whichDevCardSlot[i] ){
                currentPlayer.getPersonalBoard().activateProductionFromDevCard(i);
            }
        }
        if( fromPersonalBoard && resourceType1!=null && resourceType2!=null &&
                obtainedResource!=null && obtainedResource!=ResourceType.FAITHPOINTS ){
            currentPlayer.getPersonalBoard().activateProductionFromPersonalBoard(resourceType1, resourceType2, obtainedResource);
        }
        currentPlayer.getPersonalBoard().fromStrongboxTempToStrongbox();
        //TODO( WHICHlEADERcARDS.....
        currentPlayer.setCanEndTurn(true);
        return true;
    }

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

    public boolean insertResourcesIntoWarehouse( ResourceType resourceType, int quantityToAdd ){
        if(marketBoard.getTemporaryResources().get(resourceType)==null) return false;
        int maxAddable = marketBoard.getTemporaryResources().get(resourceType);
        if( quantityToAdd > maxAddable || quantityToAdd <= 0 )
            return false;
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

    public boolean activateActionCard(){
        if(players.size()!=1 || !currentPlayer.isCanEndTurn()) return false;
        actionCardStack.activateCard();
        actionCardDone=true;
        return true;
    }

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

    public boolean activateLeaderCard(int pos){
        if(currentPlayer.isLeaderActionDone())
            return false;
        return currentPlayer.activateLeaderCard(pos);
    }

    public boolean activateLeaderAbility(int whichLeaderCard){
        return currentPlayer.activateLeaderAbility(whichLeaderCard);
    }
}
