package it.polimi.ingsw.View.MessageBuilders;

import com.google.gson.Gson;
import it.polimi.ingsw.Controller.Messages.Message;
import it.polimi.ingsw.Controller.Messages.StartMultiPlayerMessage;

/**
 * Used to build a start multi player request
 */
public class StartMultiPlayerMessageBuilder extends MessageBuilder{
    @Override
    public String buildMessage() {
        Gson gson = new Gson();
        StartMultiPlayerMessage message = new StartMultiPlayerMessage();
        message.setMessageType("StartMultiPlayer");
        return gson.toJson(message);
    }
}
