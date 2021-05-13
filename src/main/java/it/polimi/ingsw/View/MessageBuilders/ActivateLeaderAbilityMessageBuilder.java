package it.polimi.ingsw.View.MessageBuilders;

import com.google.gson.Gson;
import it.polimi.ingsw.Controller.Messages.ActivateLeaderAbilityMessage;

import java.util.Scanner;

public class ActivateLeaderAbilityMessageBuilder extends MessageBuilder{
    private String nickname;

    public ActivateLeaderAbilityMessageBuilder(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public String buildMessage() {
        Gson gson = new Gson();
        Scanner input = new Scanner(System.in);
        ActivateLeaderAbilityMessage message = new ActivateLeaderAbilityMessage();
        message.setMessageType("ActivateLeaderAbility");
        message.setSenderNickname(nickname);
        int position;
        do {
            System.out.println("Write position of Leader card which ability you want to activate: [\"0\",\"1\"]");
            position = input.nextInt();
        }while(position!=0&&position!=1);
        message.setPosition(position);
        return gson.toJson(message);
    }
}
