package it.polimi.ingsw.View;


public class CommandManager {
    private VirtualView virtualView;

    public CommandManager(VirtualView virtualView) {
        this.virtualView = virtualView;
    }

    public String Manage(String userInput){
        switch (userInput) {
   //Help to show all commands
            case "help":

//Logging in
            case "login":

//Activating production
            case "ActivateProduction":

//Activating leader card(s)
            case "ActivateLeaderCard":

//Activating leader ability
            case "ActivateLeaderAbility":

//Activating action card
            case "ActionCardActivation":

//Activating SwitchLevels
            case "SwitchLevels":

//Activating TakeResourceFromMarket
            case "TakeResourcesFromMarket":

//Activating InsertResourceIntoWarehouse
            case "InsertResourcesIntoWarehouse":

//Activating BuyDevelopmentCard
            case "BuyDevelopmentCard":

//Starting single player mode
            case "StartSinglePlayer":

//Starting a multi player game
            case "StartMultiPlayer":

//Activating an initial resource distribution for the second or the third player
            case "DistributionSecondThird":

//Activating an initial resource distribution for the fourth player
            case "DistributionFourth":

//Selecting the leader cards from the 4 choosable cards
            case "LeaderCardSelection":

// Ending turn
            case "EndTurnRequest":

        }
        return "Invalid command";
    }

}
