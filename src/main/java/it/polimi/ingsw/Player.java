package it.polimi.ingsw;

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

    private Game game;

    public void setGame(Game game){
        this.game = game;
    }

    public Game getGame() {
        return game;
    }
    //Todo( private LeaderCard[] choosableLeaderCards = new LeaderCard[](); )


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
        //Todo( a fine partita guarda i victory points delle leader cards rimaste al player e sommali ai victory points del player)
        int depotsPoints;
        int positionPoints = 0;
        int numOfResTopDevCards = 0;
        int numOfResWarehouse = 0;
        int numOfResStrongbox = personalBoard.getStrongbox().getVictoryPoints();
        WareHouseDepot warehouse = personalBoard.getWarehouseDepot();
        for( int i=1; i<4; i++ ){
            numOfResWarehouse += warehouse.getLevel(i).getCurrNumResources();
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

        int toSet = numOfResTopDevCards + depotsPoints + positionPoints; //Todo( + leaderCardsPoints )
        this.addVictoryPoints(toSet);
    }



}
