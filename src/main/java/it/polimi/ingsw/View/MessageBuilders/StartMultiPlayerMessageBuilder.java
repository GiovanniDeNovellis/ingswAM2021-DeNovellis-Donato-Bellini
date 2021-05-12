package it.polimi.ingsw.View.MessageBuilders;

import com.google.gson.Gson;
import it.polimi.ingsw.Controller.Messages.Message;
import it.polimi.ingsw.Controller.Messages.StartMultiPlayerMessage;

public class StartMultiPlayerMessageBuilder extends MessageBuilder{
    @Override
    public String buildMessage() {
        Gson gson = new Gson();
        Message message = new StartMultiPlayerMessage();
        return gson.toJson(message);
    }
}