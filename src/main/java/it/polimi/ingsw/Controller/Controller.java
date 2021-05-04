package it.polimi.ingsw.Controller;

import com.google.gson.Gson;
import it.polimi.ingsw.Controller.Messages.Message;
import it.polimi.ingsw.Controller.RequestManagers.*;
import it.polimi.ingsw.Game;

import java.util.ArrayList;

public class Controller {
    private final Game game;
    private final ArrayList<ClientHandler> connectedClients = new ArrayList<>();


    public Controller(Game game) {
        this.game = game;
    }

    public String startAction(String jsonContent){
        Gson gson = new Gson();
        Message message = gson.fromJson(jsonContent,Message.class);
        switch (message.getMessageType()) {
            case "AddPlayer":
                AddPlayerManager addPlayerManager = new AddPlayerManager(this);
                return addPlayerManager.manageRequest(jsonContent);
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
