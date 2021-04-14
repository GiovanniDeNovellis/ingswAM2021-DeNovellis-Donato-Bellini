package it.polimi.ingsw;

/**
 * This class represent the single player mode of the game
 */
public class LorenzoSingleton {
    private static LorenzoSingleton Lorenzo = null;
    /** Black Faith points it's a counter of the Lorenzo's Cross */
    private int blackFaithPoints;
    private boolean lorenzoWinner=false;

    /** Inizialize the Black Faith Points to 0 */
    private LorenzoSingleton (){
        blackFaithPoints = 0;
    }

    public static LorenzoSingleton getLorenzo(){
        if(Lorenzo == null)
            Lorenzo = new LorenzoSingleton();
        return Lorenzo;
    }

    /** Method that add faith point to Lorenzo black cross
     * @param number indicate the number of faith points to add
     */
    public void addFaithPoints(int number) {
        Lorenzo.blackFaithPoints += number;
    }
    public int getBlackFaithPoints() {
        return blackFaithPoints;
    }

    public void setLorenzoWinner(boolean lorenzoWinner) {
        this.lorenzoWinner = lorenzoWinner;
    }

    public boolean isLorenzoWinner() {
        return lorenzoWinner;
    }
}
