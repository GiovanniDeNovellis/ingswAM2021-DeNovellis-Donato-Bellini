package it.polimi.ingsw.Controller.Messages;

/**
 * Message used to communicate the change of the market grid
 */
public class MarketGridChangedMessage extends Message{
    private String[][] marketGridConfiguration;

    public String getMarbleout() {
        return marbleout;
    }

    private String marbleout;
    public String[][] getMarketGridConfiguration() {
        return marketGridConfiguration;
    }

    public void setMarketGridConfiguration(String[][] marketGridConfiguration,String marbleout) {
        this.marketGridConfiguration = marketGridConfiguration;
        this.marbleout = marbleout;
    }
}
