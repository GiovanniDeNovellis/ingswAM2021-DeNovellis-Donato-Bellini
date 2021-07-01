package it.polimi.ingsw.View.MessageBuilders;

import com.google.gson.Gson;
import it.polimi.ingsw.Controller.Messages.Message;

/**
 * Used to build an action card activation request
 */
public class ActivateActionCardMessageBuilder extends MessageBuilder{
    @Override
    public String buildMessage() {
        Gson gson = new Gson();
        Message message = new Message();
        message.setMessageType("ActionCardActivation");
        return gson.toJson(message);
    }
}
