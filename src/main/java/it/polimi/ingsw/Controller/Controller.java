package it.polimi.ingsw.Controller;

import com.google.gson.Gson;
import it.polimi.ingsw.Controller.Messages.Message;
import it.polimi.ingsw.Controller.RequestManagers.*;
import it.polimi.ingsw.Game;

import java.util.ArrayList;

public class Controller {
    private Game game;
    private ArrayList<ClientHandler> connectedClients = new ArrayList<>();
    private int[] vaticanReport;

    public int[] getVaticanReport() {
        vaticanReport = game.getCurrentPlayer().getFaithCards();
        return vaticanReport;
    }


    public Controller(Game game) {
        this.game = game;
    }

    public String startAction(String jsonContent){
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
            case "SwitchLevel":
                Manageable switchLevel = new SwitchLevelsManager(this);
                return switchLevel.manageRequest(jsonContent);
//Activating TakeResourceFromMarket
            case "TakeResourceFromMarket":
                Manageable takeResourceFromMarket = new TakeResourcesFromMarketManager(this);
                return takeResourceFromMarket.manageRequest(jsonContent);
//Activating InsertResourceIntoWarehouse
            case "InsertResourceIntoWarehouse":
                Manageable insertResourceIntoWarehouse = new InsertResourcesIntoWarehouseManager(this);
                return insertResourceIntoWarehouse.manageRequest(jsonContent);
//Activating BuyDevelopmentCard
            case "BuyDevelopmentCard":
                Manageable buyDevelopmentCard = new BuyDevelopmentCardManager(this);
                return buyDevelopmentCard.manageRequest(jsonContent);
//Starting single player mode
            case "startSinglePlayer":
                if (game.startSinglePlayer()) {
                } else {

                }
                break;
        }
        return "end";
    }

    public void addClientHandler(ClientHandler clientHandler){
        connectedClients.add(clientHandler);
    }

    public Game getGame() {
        return game;
    }

    public ArrayList<ClientHandler> getConnectedClients() {
        return connectedClients;
    }


}
