package it.polimi.ingsw.MarbleTypes;

import it.polimi.ingsw.MarketBoard;
import it.polimi.ingsw.Selectable;

/**
 * A type of marbles that does nothing. Can be trasformed
 * into another Marble with a certain leaderAbility.
 */
public class WhiteMarbleType implements Selectable {
    /**
     * Does nothing.
     * @param marketBoard The marketBoard where all the marbles are stored.
     */
    @Override
    public void onSelection(MarketBoard marketBoard) {
        int old = marketBoard.getWhiteMarblesSelected();
        marketBoard.setWhiteMarblesSelected(old+1);
    }
    //
}
