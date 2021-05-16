package it.polimi.ingsw.View;


import it.polimi.ingsw.View.MessageBuilders.*;

public class CommandManager {
    private ModelPrinter modelPrinter;
    private String nickname;

    public CommandManager(ModelPrinter modelPrinter, String nickname) {
        this.modelPrinter = modelPrinter;
        this.nickname = nickname;
    }

    public String manage(String userInput){
        MessageBuilder toBuild;
        switch (userInput) {
            //TODO( AGGIUNGERE I COMANDI SHOW )
   //Help to show all commands
            case "help":
                toBuild = new HelpMessageBuilder();
                return toBuild.buildMessage();
//Logging in
            case "login":
                toBuild = new LoginMessageBuilder();
                return toBuild.buildMessage();
//Activating production
            case "ActivateProduction":
                toBuild = new ActivateProductionMessageBuilder(nickname);
                return toBuild.buildMessage();
//Activating leader card(s)
            case "ActivateLeaderCard":
                toBuild = new ActivateLeaderCardMessageBuilder(nickname);
                return toBuild.buildMessage();
//Activating leader ability
            case "ActivateLeaderAbility":
                toBuild = new ActivateLeaderAbilityMessageBuilder(nickname);
                return toBuild.buildMessage();
//Activating action card
            case "ActionCardActivation":
                toBuild = new ActivateActionCardMessageBuilder();
                return toBuild.buildMessage();
//Activating SwitchLevels
            case "SwitchLevels":
                toBuild = new SwitchLevelMessageBuilder(nickname);
                return toBuild.buildMessage();
//Activating TakeResourceFromMarket
            case "TakeResourcesFromMarket":
                toBuild = new TakeResourceFromMarketMessageBuilder(nickname);
                return toBuild.buildMessage();
//Activating InsertResourceIntoWarehouse
            case "InsertResourcesIntoWarehouse":
                toBuild = new InsertResourcesIntoWarehouseMessageBuilder(nickname);
                return toBuild.buildMessage();
//Activating BuyDevelopmentCard
            case "BuyDevelopmentCard":
                toBuild = new BuyDevelopmentCardMessageBuilder(nickname);
                return toBuild.buildMessage();
//Starting single player mode
            case "StartSinglePlayer":
                toBuild = new StartSinglePlayerMessageBuilder();
                return toBuild.buildMessage();
//Starting a multi player game
            case "StartMultiPlayer":
                toBuild = new StartMultiPlayerMessageBuilder();
                return toBuild.buildMessage();
//Activating an initial resource distribution for the second or the third player
            case "DistributionSecondThird":
                toBuild = new DistributionSecondThirdMessageBuilder(nickname);
                return toBuild.buildMessage();
//Activating an initial resource distribution for the fourth player
            case "DistributionFourth":
                toBuild = new DistributionFourthMessageBuilder(nickname);
                return toBuild.buildMessage();
//Selecting the leader cards from the 4 choosable cards
            case "LeaderCardSelection":
                toBuild = new LeaderCardSelectionMessageBuilder(nickname);
                return toBuild.buildMessage();
// Ending turn
            case "EndTurnRequest":
                toBuild = new EndTurnRequestMessageBuilder(nickname);
                return toBuild.buildMessage();
        }
        return "Invalid command.";

    }

}
