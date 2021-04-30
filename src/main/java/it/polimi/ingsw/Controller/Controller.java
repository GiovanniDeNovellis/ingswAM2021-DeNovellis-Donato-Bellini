package it.polimi.ingsw.Controller;

import com.google.gson.Gson;
import it.polimi.ingsw.Controller.Messages.Message;
import it.polimi.ingsw.Controller.RequestManagers.AddPlayerManager;
import it.polimi.ingsw.Game;

import java.util.ArrayList;

public class Controller {
    private Game game;
    private ArrayList<ClientHandler> connectedClients = new ArrayList<>();


    public Controller(Game game) {
        this.game = game;
    }

    public String startAction(String jsonContent){
        Gson gson = new Gson();
        Message message = gson.fromJson(jsonContent,Message.class);
        if(message.getMessageType().equals("AddPlayer")){
            AddPlayerManager addPlayerManager = new AddPlayerManager(this);
            return addPlayerManager.manageRequest(jsonContent);
        }
        else if(message.getMessageType().equals("startSinglePlayer")){
            if(game.startSinglePlayer()){
            }
            else{

            }
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
