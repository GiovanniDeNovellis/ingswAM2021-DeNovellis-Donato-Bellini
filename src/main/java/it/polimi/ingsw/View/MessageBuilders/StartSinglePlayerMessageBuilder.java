package it.polimi.ingsw.View.MessageBuilders;

import com.google.gson.Gson;
import it.polimi.ingsw.Controller.Messages.StartSinglePlayerMessage;

public class StartSinglePlayerMessageBuilder extends MessageBuilder{
        @Override
        public String buildMessage() {
            Gson gson = new Gson();
            StartSinglePlayerMessage message = new StartSinglePlayerMessage();
            message.setMessageType("StartSinglePlayer");
            return gson.toJson(message);
        }
    }

