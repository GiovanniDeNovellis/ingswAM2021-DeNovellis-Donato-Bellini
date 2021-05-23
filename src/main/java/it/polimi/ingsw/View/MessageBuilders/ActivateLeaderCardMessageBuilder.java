package it.polimi.ingsw.View.MessageBuilders;

import com.google.gson.Gson;
import it.polimi.ingsw.Controller.Messages.ActivateLeaderCardMessage;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ActivateLeaderCardMessageBuilder extends MessageBuilder{
    String nickname;

    public ActivateLeaderCardMessageBuilder(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public String buildMessage() {
        Gson gson = new Gson();
        ActivateLeaderCardMessage message = new ActivateLeaderCardMessage();
        Scanner input = new Scanner(System.in);
        message.setSenderNickname(nickname);
        message.setMessageType("ActivateLeaderCard");
        int position;
        do {
            try {
                System.out.println("Write position of Leader card you want to activate: [\"1\",\"2\"]");
                position = input.nextInt();
            }
            catch (InputMismatchException e ){
                System.out.println("Please write a number");
                position=4;
                input.nextLine();
            }
        }while(position!=1&&position!=2);
        message.setPosition(position-1);
        return gson.toJson(message);
    }
}
