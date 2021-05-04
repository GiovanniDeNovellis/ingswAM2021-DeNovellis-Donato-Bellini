package it.polimi.ingsw.Controller.Messages;

import it.polimi.ingsw.Marble;

public class MarketGridChangedMessage extends Message{
    private Marble[][] marketGridConfiguration;

    public Marble getMarbleout() {
        return marbleout;
    }

    private Marble marbleout;
    public Marble[][] getMarketGridConfiguration() {
        return marketGridConfiguration;
    }

    public void setMarketGridConfiguration(Marble[][] marketGridConfiguration,Marble marbleout) {
        this.marketGridConfiguration = marketGridConfiguration;
        this.marbleout = marbleout;
    }
}
