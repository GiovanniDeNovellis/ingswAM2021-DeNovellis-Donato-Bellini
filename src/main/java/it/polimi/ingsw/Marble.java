package it.polimi.ingsw;

/**
 * This class represents a Marble. The marbles are stored
 * in the MarketBoard and, when selected, have a different behaviour
 * depending on their MarbleType.
 */
public class Marble {
    /**
     * The marbleType associated to the Marble.
     */
    private Selectable currentMarbleType;
    /**
     * Colour of the marble.
     */
    private String colour;

    /**
     * Builds a Marble of the given Type and colour.
     * @param currentMarbleType The type of the marble.
     * @param colour The colour of the marble.
     */
    public Marble(Selectable currentMarbleType, String colour) {
        this.currentMarbleType = currentMarbleType;
        this.colour = colour;
    }

    /**
     * @return The type of the marble.
     */
    public Selectable getCurrentMarbleType(){return currentMarbleType;}

    /**
     * Calls the onSelection associated to it's type.
     * @param marketBoard The board where all the Marbles are stored.
     */
    public void onSelection(MarketBoard marketBoard){
        currentMarbleType.onSelection(marketBoard);
    }

    public String getColour() {
        return colour;
    }
}
