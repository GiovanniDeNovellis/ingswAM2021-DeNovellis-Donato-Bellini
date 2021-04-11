package it.polimi.ingsw;

public class LorenzoSingleton {
    private static LorenzoSingleton Lorenzo = null;
    private int blackFaithPoints;

    /** Inizialize the Black Faith Points to 0 */
    private LorenzoSingleton (){
        blackFaithPoints = 0;
    }
    public static LorenzoSingleton getLorenzo(){
        if(Lorenzo == null)
            Lorenzo = new LorenzoSingleton();
        return Lorenzo;
    }
    /** Method that add a number of faith point to Lorenzo black cross */
    public void addFaithPoints(int number) {
        Lorenzo.blackFaithPoints += number;
    }
    public int getBlackFaithPoints() {
        return blackFaithPoints;
    }

}
