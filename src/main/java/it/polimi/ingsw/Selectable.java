package it.polimi.ingsw;

/**
 * Interface for the onSelection method,
 * the method used by all the marbles when
 * they are selected by a player.
 */
public interface Selectable {

    /**
     * Public method.
     * Reacts to the selection of the marble.
     * @param marketBoard The marketBoard where all the marbles are stored.
     */
    public void onSelection(MarketBoard marketBoard);
}

