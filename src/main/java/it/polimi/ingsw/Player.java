package it.polimi.ingsw;


import java.util.ArrayList;
import java.util.TreeMap;

public class Player {

    private PersonalBoard personalBoard = new PersonalBoard(this);
    /**
     * 0<= faithPoints <= 24
     */
    private int faithPoints = 0;
    private int[] faithCards = {2, 3, 4};
    private boolean inkwell = false;
    private int playerNumber;
    private int victoryPoints = 0;
    private String nickname;
    private boolean isAFK = false;
    private boolean canEndTurn = false;
    private boolean initialDistribution = false;
    private int totNumOfRes = 0;
    private ArrayList<LeaderCard> choosableLeaderCards = new ArrayList<>();
    private ArrayList<LeaderCard> choosedLeaderCards = new ArrayList<>();
    private boolean chosenLeaderCards = false;
    private Game game;
    private boolean leaderActionDone=false;

    public void setLeaderActionDone(boolean leaderActionDone) {
        this.leaderActionDone = leaderActionDone;
    }

    public boolean isLeaderActionDone() {
        return leaderActionDone;
    }

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
    public void setChosenLeaderCards(boolean chosenLeaderCards) {
        this.chosenLeaderCards = chosenLeaderCards;
    }

    public boolean hasChosenLeaderCards() {
        return chosenLeaderCards;
    }

    public void setGame(Game game){
        this.game = game;
    }

    public Game getGame() {
        return game;
    }

    public void addChoosableLeaderCards(LeaderCard leaderCard){
        this.choosableLeaderCards.add(leaderCard);
    }

    public boolean isCanEndTurn() {
        return canEndTurn;
    }

    public void setCanEndTurn(boolean canEndTurn) {
        this.canEndTurn = canEndTurn;
    }

    public int getTotNumOfRes() {
        return totNumOfRes;
    }


    public void setPlayerNumber(int playerNumber) {
        this.playerNumber = playerNumber;
    }

    public int getPlayerNumber(){
        return playerNumber;
    }

    public void addVictoryPoints(int quantity){
        this.victoryPoints += quantity;
    }

    public int getVictoryPoints(){
        return victoryPoints;
    }

    public boolean isInitialDistribution() {
        return initialDistribution;
    }

    public void setInitialDistribution(boolean initialDistributionDone) {
        this.initialDistribution = initialDistributionDone;
    }


    public PersonalBoard getPersonalBoard() {
        return personalBoard;
    }

    public int[] getFaithCards() {
        return faithCards;
    }


    public void setFaithCardsZero(int position) {
        this.faithCards[position] = 0;
    }

    public void setInkwell(boolean inkwell) {
        this.inkwell = inkwell;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getNickname(){
        return nickname;
    }

    public int getFaithPoints(){
        return faithPoints;
    }

    public boolean insertCard(DevelopmentCard devCard, int position ){
        return ( personalBoard.insertCard(devCard, position ) );
    }

    //insert into warehouse
    public boolean insertResources(ResourceType resourceType, int level, int quantity ) {
        return (personalBoard.insertResources(resourceType, level, quantity));
    }

    public boolean switchLevels(int maxSlotsFirst, int maxSlotsSecond){
        return personalBoard.switchLevels(maxSlotsFirst, maxSlotsSecond);
    }

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

    public void addFaithPointsWithoutCallingAudience(int pointsToAdd ){
        faithPoints += pointsToAdd;
        if( faithPoints >= 24 ) {
            faithPoints = 24;
        }
    }
    public void endGameFaithPoints(){
        if( faithPoints >= 24 ){
            game.setEndGame(true);
        }
    }


    //STRONGBOX, DEVCARDS, LEADERCARDS, POSITION(TRACCIATO)
    public void calculateVictoryPoints(){
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
        //Todo( int numOfResSpecialDept = ... )
        numOfResTopDevCards += personalBoard.getTopCardsVictoryPoints();
        totNumOfRes = ( numOfResWarehouse + numOfResStrongbox ); //Todo(+ numOfResSpecialDept )
        depotsPoints =( numOfResWarehouse + numOfResStrongbox )/5; //Todo(+ numOfResSpecialDept )
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

    public boolean discardLeaderCard(int pos){
        if(pos<0 || pos>=choosedLeaderCards.size())
            return false;
        choosedLeaderCards.remove(pos);
        leaderActionDone = true;
        return true;
    }

    public boolean activateLeaderCard(int pos){
        if(pos<0 || pos>=choosedLeaderCards.size())
            return false;
        if(choosableLeaderCards.get(pos).isActive())
            return false;
        if(choosedLeaderCards.get(pos).setActive()){
            leaderActionDone=true;
            return true;
        }
        return false;
    }

    public TreeMap<Colour, Integer> getCardColours(){
        return this.personalBoard.getCardsColours();
    }

    public ArrayList<DevelopmentCard> getInsertedDevCards(){
        return personalBoard.getInsertedDevelopmentCards();
    }
}
