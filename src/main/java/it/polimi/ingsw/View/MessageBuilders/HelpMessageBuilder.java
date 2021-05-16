package it.polimi.ingsw.View.MessageBuilders;

public class HelpMessageBuilder extends MessageBuilder{
    @Override
    public String buildMessage() {
        // TODO FARLO IN INGLESE
        System.out.print("Lista dei comandi possibili: \n" +
                "help - Per vedere tutti i comandi \n" +
                "login - Per effettuare il login alla partita \n" +
                "ActivateProduction - Per attivare una produzione \n" +
                "ActivateLeaderCard - Per attivare una leader card \n" +
                "ActionCardActivation - Per attivare un segnalino azione in single player \n" +
                "SwitchLevels - Per scambiare due livelli del warehouse \n" +
                "TakeResourcesFromMarket - Per prendere risorse dal mercato \n" +
                "InsertResourcesIntoWarehouse - Per inserire risorse prese dal mercato \n" +
                "BuyDevelopmentCard - Per comprare una carta sviluppo \n" +
                "StartSinglePLayer - Per iniziare una partita in singolo \n" +
                "StartMultiPlayer - Per iniziare una partita in multi player \n" +
                "DistributionSecondThird - Per scegliere una risorsa omaggio se sei il secondo o terzo giocatore \n" +
                "DistributionFourth - Per scegliere due risorse omaggio se sei il quarto giocatore \n" +
                "LeaderCardSelection - Per scegliere le due leader Cards \n" +
                "EndTurnRequest - Per terminare il turno \n");
        return "Show";
    }
}
