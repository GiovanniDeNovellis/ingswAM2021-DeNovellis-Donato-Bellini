package it.polimi.ingsw.Controller;

import com.google.gson.Gson;
import it.polimi.ingsw.Controller.Messages.AddPlayerMessage;
import it.polimi.ingsw.Controller.Messages.Message;
import it.polimi.ingsw.Game;

public class Controller {
    private Game game;

    public Controller(Game game) {
        this.game = game;
    }

    public void startAction(String jsonContent){
        Gson gson = new Gson();
        Message message = gson.fromJson(jsonContent,Message.class);
        if(message.getMessageType().equals("addPlayer")){
            AddPlayerMessage addMessage = gson.fromJson(jsonContent,AddPlayerMessage.class);
            //TODO GUARDA IL SEQUENCE DIAGRAM CORRISPONDENTE E CHIAMA I METODI SU GAME
        }
        else if(message.getMessageType().equals("startSinglePlayer")){
            if(game.startSinglePlayer()){

            }
            else{

            }
        }
    }
}
