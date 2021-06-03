package it.polimi.ingsw.View.MessageBuilders;

import com.google.gson.Gson;
import it.polimi.ingsw.Controller.Messages.ActivateLeaderAbilityMessage;
import it.polimi.ingsw.View.ModelPrinter;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ActivateLeaderAbilityMessageBuilder extends MessageBuilder{
    private String nickname;
    private final ModelPrinter modelPrinter;

    public ActivateLeaderAbilityMessageBuilder(String nickname, ModelPrinter modelPrinter) {
        this.nickname = nickname;
        this.modelPrinter=modelPrinter;
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
            try {
                System.out.println("Write position of Leader card which ability you want to activate: [\"0\",\"1\"]");
                position = input.nextInt();
            }
            catch(InputMismatchException e){
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
