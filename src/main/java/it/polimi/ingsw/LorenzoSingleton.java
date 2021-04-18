package it.polimi.ingsw;

/** This class represent the single player mode of the game */
public class LorenzoSingleton {
    /** The reference for the game class to call audiences */
    private Game game;
    /** Initialize the attribute to create a Singleton */
    private static LorenzoSingleton Lorenzo = null;

    /** Black Faith points it's a counter of the Lorenzo's Cross */
    private int blackFaithPoints;

    /** Initialized NPC looser at the beginning */
    private boolean lorenzoWinner=false;

    /** Inizialize the Black Faith Points to 0 */
    private LorenzoSingleton (){
        blackFaithPoints = 0;
    }

    /**
     *  Public method.
     * Getter of Lorenzo.
     * @return the reference to LorenzoSingleton
     */
    public static LorenzoSingleton getLorenzo(){
        if(Lorenzo == null)
            Lorenzo = new LorenzoSingleton();
        return Lorenzo;
    }

    /**
     * Public method.
     * Method that add faith point to Lorenzo black cross
     * @param number indicate the number of faith points to add
     */
    public void addFaithPoints(int number) {
        Lorenzo.blackFaithPoints += number;
        if( blackFaithPoints >= 8 && blackFaithPoints < 16 ){
            game.firstAudience();
        }
        else if( blackFaithPoints >= 16 && blackFaithPoints < 24 ){
            game.secondAudience();
        }
        if( blackFaithPoints >= 24 ) {
            blackFaithPoints = 24;
            setLorenzoWinner(true);
        }
    }

    /**
     * Public method.
     * Getter of Lorenzo.
     * @return the blackFaithPoints
     */
    public int getBlackFaithPoints() {
        return blackFaithPoints;
    }

    /**
     * Public method.
     * Setter of LorenzoWinner.
     * @param lorenzoWinner is the boolean that indicate if the NPC won
     */
    public void setLorenzoWinner(boolean lorenzoWinner) {
        this.lorenzoWinner = lorenzoWinner;
    }

    /**
     * Public method.
     * Check if the NPC won the game.
     * @return true if the NPC is the winner
     */
    public boolean isLorenzoWinner() {
        return lorenzoWinner;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}
