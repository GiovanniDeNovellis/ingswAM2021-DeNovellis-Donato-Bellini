package it.polimi.ingsw.View.MessageBuilders;

import com.google.gson.Gson;
import it.polimi.ingsw.Controller.Messages.LeaderCardSelectionMessage;

import java.util.Scanner;

public class LeaderCardSelectionMessageBuilder extends MessageBuilder{

    private final String nickname;

    public LeaderCardSelectionMessageBuilder(String nickname){
        this.nickname = nickname;
    }

    @Override
    public String buildMessage() {
        Gson gson = new Gson();
        Scanner input = new Scanner(System.in);
        LeaderCardSelectionMessage message = new LeaderCardSelectionMessage();

        message.setMessageType("LeaderCardSelection");
        message.setSenderNickname(nickname);
        int position;
        do{
            System.out.println("Write position of first Leader card you want to keep: [\"0\",\"1\",\"2\",\"3\"]");
            position = input.nextInt();
        }while(position !=0 && position !=1 && position !=2 && position !=3);
        message.setLeaderCardPosition1(position);

        do{
            System.out.println("Write position of second Leader card you want to keep: [\"0\",\"1\",\"2\",\"3\"]");
            position = input.nextInt();
        }while(position !=0 && position !=1 && position !=2 && position !=3);
        message.setLeaderCardPosition2(position);

        return gson.toJson(message);
    }
}
