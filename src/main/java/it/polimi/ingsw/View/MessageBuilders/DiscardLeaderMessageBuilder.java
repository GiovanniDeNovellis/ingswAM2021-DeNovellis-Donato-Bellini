package it.polimi.ingsw.View.MessageBuilders;

import com.google.gson.Gson;
import it.polimi.ingsw.Controller.Messages.DiscardLeaderMessage;
import it.polimi.ingsw.View.ModelPrinter;

import java.util.InputMismatchException;
import java.util.Scanner;

public class DiscardLeaderMessageBuilder extends MessageBuilder{
    private final String nickname;
    private final ModelPrinter modelPrinter;

    public DiscardLeaderMessageBuilder(String nickname, ModelPrinter modelPrinter) {
        this.nickname = nickname;
        this.modelPrinter=modelPrinter;
    }

    @Override
    public String buildMessage() {
        Gson gson = new Gson();
        Scanner input = new Scanner(System.in);
        DiscardLeaderMessage message = new DiscardLeaderMessage();
        message.setMessageType("DiscardLeaderCard");
        message.setSenderNickname(nickname);
        int position;
        do {
            try {
                System.out.println("Write position of Leader card you want to discard: [\"0\",\"1\"]");
                position = input.nextInt();
            }
            catch (InputMismatchException e ){
                System.out.println("Please write a number");
                position=4;
                input.nextLine();
            }
        }while(position!=0&&position!=1);
        if(modelPrinter.hasDiscardedFirst())
            message.setPosition(0);
        else
            message.setPosition(position);
        return gson.toJson(message);
    }
}
