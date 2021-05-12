package it.polimi.ingsw.View.MessageBuilders;

import com.google.gson.Gson;
import it.polimi.ingsw.Controller.Messages.EndTurnRequestMessage;

import java.util.Scanner;

public class EndTurnRequestMessageBuilder extends MessageBuilder{
    private final String nickname;

    public EndTurnRequestMessageBuilder(String nickname){
        this.nickname = nickname;
    }

    @Override
    public String buildMessage() {
        Gson gson = new Gson();
        EndTurnRequestMessage message = new EndTurnRequestMessage();

        message.setSenderNickname(nickname);
        message.setMessageType("EndTurnRequest");

        return gson.toJson(message);
    }
}
