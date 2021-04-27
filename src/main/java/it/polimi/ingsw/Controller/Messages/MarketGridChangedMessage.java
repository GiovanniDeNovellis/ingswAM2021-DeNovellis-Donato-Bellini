package it.polimi.ingsw.Controller.Messages;

import it.polimi.ingsw.Marble;

public class MarketGridChangedMessage extends Message{
    private Marble[][] marketGridConfiguration;

    public Marble[][] getMarketGridConfiguration() {
        return marketGridConfiguration;
    }

    public void setMarketGridConfiguration(Marble[][] marketGridConfiguration) {
        this.marketGridConfiguration = marketGridConfiguration;
    }
}
