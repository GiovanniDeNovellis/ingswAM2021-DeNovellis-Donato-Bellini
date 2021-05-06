package it.polimi.ingsw.Controller;

import com.google.gson.Gson;
import it.polimi.ingsw.Controller.Messages.Message;
import it.polimi.ingsw.Controller.RequestManagers.*;
import it.polimi.ingsw.Game;

import java.util.ArrayList;

public class Controller {
    private final Game game;
    private final ArrayList<ClientHandler> connectedClients = new ArrayList<>();

    public int[] getVaticanReport() {
        return  game.getCurrentPlayer().getFaithCards();
    }


    public Controller(Game game) {
        this.game = game;
    }

    public synchronized String startAction(String jsonContent){
        Gson gson = new Gson();
        Message message = gson.fromJson(jsonContent,Message.class);
        switch (message.getMessageType()) {
//Adding a player
            case "AddPlayer":
                Manageable addPlayerManageable = new AddPlayerManager(this);
                return addPlayerManageable.manageRequest(jsonContent);
//Activating production
            case "ActivateProduction":
                Manageable activateProduction = new ActivateProductionManager(this);
                return activateProduction.manageRequest(jsonContent);
//Activating leader card(s)
            case "ActivateLeaderCard":
                Manageable activateLeaderCard = new ActivateLeaderCardManager(this);
                return activateLeaderCard.manageRequest(jsonContent);
//Activating leader ability
            case "ActivateLeaderAbility":
                Manageable activateLeaderAbility = new ActivateLeaderAbilityManager(this);
                return activateLeaderAbility.manageRequest(jsonContent);
//Activating action card
            case "ActionCardActivation":
                Manageable actionCardActivation = new ActionCardActivationManager(this);
                return actionCardActivation.manageRequest(jsonContent);
//Activating SwitchLevels
            case "SwitchLevels":
                Manageable switchLevels = new SwitchLevelsManager(this);
                return switchLevels.manageRequest(jsonContent);
//Activating TakeResourceFromMarket
            case "TakeResourcesFromMarket":
                Manageable takeResourceFromMarket = new TakeResourcesFromMarketManager(this);
                return takeResourceFromMarket.manageRequest(jsonContent);
//Activating InsertResourceIntoWarehouse
            case "InsertResourcesIntoWarehouse":
                Manageable insertResourceIntoWarehouse = new InsertResourcesIntoWarehouseManager(this);
                return insertResourceIntoWarehouse.manageRequest(jsonContent);
//Activating BuyDevelopmentCard
            case "BuyDevelopmentCard":
                Manageable buyDevelopmentCard = new BuyDevelopmentCardManager(this);
                return buyDevelopmentCard.manageRequest(jsonContent);
//Starting single player mode
            case "StartSinglePlayer":
                StartSinglePlayerManager startSinglePlayerManager = new StartSinglePlayerManager(this);
                return startSinglePlayerManager.manageRequest(jsonContent);
//Starting a multi player game
            case "StartMultiPlayer":
                Manageable startMultiPlayerManager = new StartMultiPlayerManager(this);
                return startMultiPlayerManager.manageRequest(jsonContent);
//Activating an initial resource distribution for the second or the third player
            case "DistributionSecondThird":
                Manageable distributionSecondThirdManager = new DistributionSecondThirdManager(this);
                return distributionSecondThirdManager.manageRequest(jsonContent);
//Activating an initial resource distribution for the fourth player
            case "DistributionFourth":
                Manageable distributionFourthManager = new DistributionFourthManager(this);
                return distributionFourthManager.manageRequest(jsonContent);
//Selecting the leader cards from the 4 choosable cards
            case "LeaderCardSelection":
                Manageable leaderCardSelectionManager = new LeaderCardSelectionManager(this);
                return leaderCardSelectionManager.manageRequest(jsonContent);
// Ending turn
            case "EndTurnRequest":
                Manageable endTurnManager = new EndTurnManager(this);
                return endTurnManager.manageRequest(jsonContent);
        }
        return "end";
    }

    public synchronized void addClientHandler(ClientHandler clientHandler){
            connectedClients.add(clientHandler);
    }

    public Game getGame() {
        return game;
    }

    public ArrayList<ClientHandler> getConnectedClients() {
        return connectedClients;
    }
}
