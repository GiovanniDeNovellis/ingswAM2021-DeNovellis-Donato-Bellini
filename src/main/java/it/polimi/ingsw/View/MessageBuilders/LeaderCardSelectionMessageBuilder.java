package it.polimi.ingsw.View.MessageBuilders;

import com.google.gson.Gson;
import it.polimi.ingsw.Controller.Messages.LeaderCardSelectionMessage;

import java.util.InputMismatchException;
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
        int position1;
        do{
            try {
                System.out.println("Write position of first Leader card you want to keep: [\"0\",\"1\",\"2\",\"3\"]");
                position1 = input.nextInt();
            }
            catch(InputMismatchException e){
                System.out.println("Please write a number");
                position1=4;
                input.nextLine();
            }
        }while(position1 !=0 && position1 !=1 && position1 !=2 && position1 !=3);
        message.setLeaderCardPosition1(position1);

        do {
            setPos2(message, input);
        }while(message.getLeaderCardPosition1()== message.getLeaderCardPosition2());


        return gson.toJson(message);
    }

    private void setPos2(LeaderCardSelectionMessage message, Scanner input){
        int position2;
        do{
            try {
                System.out.println("Write position of second Leader card you want to keep: [\"0\",\"1\",\"2\",\"3\"]");
                position2 = input.nextInt();
            }
            catch (InputMismatchException e){
                System.out.println("Please write a number");
                position2=4;
                input.nextLine();
            }
        }while( position2 !=0 && position2 !=1 && position2 !=2 && position2 !=3);
        message.setLeaderCardPosition2(position2);
    }
}
