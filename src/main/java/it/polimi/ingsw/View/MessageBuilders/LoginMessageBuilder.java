package it.polimi.ingsw.View.MessageBuilders;

import com.google.gson.Gson;
import it.polimi.ingsw.Controller.Messages.AddPlayerMessage;

import java.util.Scanner;

/**
 * Used to build a login request
 */
public class LoginMessageBuilder extends MessageBuilder{
    @Override
    public String buildMessage() {
        String nickname;
        Gson gson = new Gson();
        AddPlayerMessage message = new AddPlayerMessage();
        message.setMessageType("AddPlayer");
        Scanner input = new Scanner(System.in);
        System.out.println("Please insert your nickname for the login");
        nickname= input.nextLine();
        message.setSenderNickname(nickname);
        return gson.toJson(message);
    }
}
