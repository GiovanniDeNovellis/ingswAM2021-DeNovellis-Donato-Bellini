package it.polimi.ingsw;

/** This class represent the single player mode of the game */
public class LorenzoSingleton {
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
}
