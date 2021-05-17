package it.polimi.ingsw.Controller.RequestManagers;

import com.google.gson.Gson;
import it.polimi.ingsw.Controller.ClientHandler;
import it.polimi.ingsw.Controller.Controller;
import it.polimi.ingsw.Controller.Messages.Message;
import it.polimi.ingsw.Controller.Messages.SinglePlayerCreationMessage;

public class StartSinglePlayerManager implements Manageable{
    private final Controller controller;

    public StartSinglePlayerManager(Controller controller) {
        this.controller = controller;
    }

    @Override
    public String manageRequest(String jsonContent) {
        Gson gson = new Gson();
        boolean ans=controller.getGame().startSinglePlayer();
        Message message = new Message();
        if (ans) {
            SinglePlayerCreationMessage mex = new SinglePlayerCreationMessage();
            mex.setMessageType("SinglePlayerCreationMessage");
            mex.setChoosableLeaderCardsNumbers(controller.getGame().getCurrentPlayer().getChoosableLeadercardsNumber());
            mex.setDeckgridConfiguration(controller.getGame().getDeckgrid());
            mex.setMarbleGridConfiguration(controller.getGame().getMarketBoard().getMarketboardColours());
            mex.setMarbleOut(controller.getGame().getMarketBoard().getMarbleOut().getColour());
            message.setMessageType("SinglePLayerCreationOkNotification");
            for(ClientHandler c: controller.getConnectedClients()){
                c.notifyInterface(gson.toJson(mex));
            }
        } else {
            message.setMessageType("SinglePLayerCreationFailedNotification");
        }
        return gson.toJson(message);
    }
}
