package it.polimi.ingsw.View.LocalNetwork;

import com.google.gson.Gson;
import it.polimi.ingsw.Controller.Controller;
import it.polimi.ingsw.Controller.Messages.AddPlayerMessage;
import it.polimi.ingsw.Controller.Messages.ConnectedPlayersMessage;
import it.polimi.ingsw.Controller.Messages.LoginOkNotificationMessage;
import it.polimi.ingsw.Controller.Messages.Message;
import it.polimi.ingsw.Controller.NotifiableHandler;
import it.polimi.ingsw.Game;
import java.io.FileNotFoundException;

public class LocalClientHandler implements NotifiableHandler {
    private final Controller controller;
    private  LocalServerReader localServerReader;

    public LocalClientHandler() throws FileNotFoundException {
        this.controller= new Controller(new Game());
        this.controller.addClientHandler(this);

    }

    public void submitCommand(String notification){
        Gson gson = new Gson();
        Message message = gson.fromJson(notification, Message.class);
        if(message.getMessageType().equals("AddPlayer")){
            String response = controller.startAction(notification);
            Message resp = gson.fromJson(response, Message.class);
            AddPlayerMessage addMessage = gson.fromJson(notification,AddPlayerMessage.class);
            String tempNickname = addMessage.getSenderNickname();
            if(resp.getMessageType().equals("PlayerAddedNotification")){
                LoginOkNotificationMessage m = new LoginOkNotificationMessage();
                m.setMessageType("LoginOkNotification");
                m.setSenderNickname(tempNickname);
                localServerReader.notifyClient(gson.toJson(m));
            }
        }
        else
            localServerReader.notifyClient(controller.startAction(notification));
    }

    @Override
    public void notifyInterface(String notification) {
        localServerReader.notifyClient(notification);
    }

    public void setLocalServerReader(LocalServerReader localServerReader) {
        this.localServerReader = localServerReader;
    }
}
