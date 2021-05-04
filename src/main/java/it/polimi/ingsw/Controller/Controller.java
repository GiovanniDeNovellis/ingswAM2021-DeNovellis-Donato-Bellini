package it.polimi.ingsw.Controller;

import com.google.gson.Gson;
import it.polimi.ingsw.Controller.Messages.Message;
import it.polimi.ingsw.Controller.RequestManagers.*;
import it.polimi.ingsw.Game;

import java.util.ArrayList;

public class Controller {
    private final Game game;
    private final ArrayList<ClientHandler> connectedClients = new ArrayList<>();
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
            case "startSinglePlayer":
                StartSinglePlayerManager startSinglePlayerManager = new StartSinglePlayerManager(this);
                return startSinglePlayerManager.manageRequest(jsonContent);
            case "startMultiPlayer":
                Manager startMultiPlayerManager = new StartMultiPlayerManager(this);
                return startMultiPlayerManager.manageRequest(jsonContent);
            case "distributionSecondThird":
                Manager distributionSecondThirdManager = new DistributionSecondThirdManager(this);
                return distributionSecondThirdManager.manageRequest(jsonContent);
            case "distributionFourth":
                Manager distributionFourthManager = new DistributionFourthManager(this);
                return distributionFourthManager.manageRequest(jsonContent);
            case "leaderCardSelection":
                Manager leaderCardSelectionManager = new LeaderCardSelectionManager(this);
                return leaderCardSelectionManager.manageRequest(jsonContent);
            case "endTurnRequest":
                Manager endTurnManager = new EndTurnManager(this);
                return endTurnManager.manageRequest(jsonContent);
        }
        return "end";
    }

    public void addClientHandler(ClientHandler clientHandler){
        synchronized (connectedClients) {
            connectedClients.add(clientHandler);
        }
    }

    public Game getGame() {
        return game;
    }

    public ArrayList<ClientHandler> getConnectedClients() {
        return connectedClients;
    }
}
