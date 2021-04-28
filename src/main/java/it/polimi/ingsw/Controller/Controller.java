package it.polimi.ingsw.Controller;

import com.google.gson.Gson;
import it.polimi.ingsw.Controller.Messages.AddPlayerMessage;
import it.polimi.ingsw.Controller.Messages.Message;
import it.polimi.ingsw.Controller.Messages.SimpleMessage;
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
            AddPlayerMessage addMessage = gson.fromJson(jsonContent,AddPlayerMessage.class);
            if( game.addPlayer(addMessage.getSenderNickname()) ){
                SimpleMessage mex = new SimpleMessage();
                mex.setMessageContent("Player added to the game.");
                AddPlayerMessage notification = new AddPlayerMessage();
                notification.setMessageType("AddPlayerNotificationForEveryone");
                notification.setSenderNickname(addMessage.getSenderNickname());
                for( ClientHandler clientHandler: connectedClients ){
                    clientHandler.notifyInterface(gson.toJson(notification));
                }
                return gson.toJson(mex);
            } else {
                SimpleMessage mex = new SimpleMessage();
                mex.setMessageContent("Error: invalid nickname or game already started");
                return gson.toJson(mex);
            }

        }
        else if(message.getMessageType().equals("startSinglePlayer")){
            if(game.startSinglePlayer()){
            }
            else{

            }
        }
        return "cacca";
    }

    public void addClientHandler(ClientHandler clientHandler){
        connectedClients.add(clientHandler);
    }
}
