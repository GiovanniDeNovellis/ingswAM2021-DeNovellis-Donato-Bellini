package it.polimi.ingsw.View.MessageBuilders;

import com.google.gson.Gson;
import it.polimi.ingsw.Controller.Messages.TakeResourceFromMarketMessage;

import java.util.InputMismatchException;
import java.util.Scanner;

public class TakeResourceFromMarketMessageBuilder extends MessageBuilder {
    private final String nickname;

    public TakeResourceFromMarketMessageBuilder(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public String buildMessage() {
        Gson gson = new Gson();
        Scanner input = new Scanner(System.in);
        TakeResourceFromMarketMessage message = new TakeResourceFromMarketMessage();
        message.setMessageType("TakeResourcesFromMarket");
        message.setSenderNickname(nickname);
        int x,y;
        int[] marketindex={0,0};

        System.out.println("Correct configurations:\n \"row:3,col:0\",\t\"row:3,col:1\",\n\"row:3,col:2\",\t\"row:3,col:3\"" +
                "\n\"row:0,col:4\",\t\"row:1,col:4\",\n\"row:2,col:4\"");
        do {
            try {
                System.out.println("Write the row of the grid where you want to take the resource: [\"0\",\"1\",\"2\",\"3\"]");
                x = input.nextInt();
            }
            catch(InputMismatchException e){
                System.out.println("Please write a number");
                x=4;
                input.nextLine();
            }
        } while (x != 0 && x != 1 && x != 2 && x != 3);
        marketindex[0]=x;
        do {
            try {
                System.out.println("Write the column of the grid where you want to take the resource: [\"0\",\"1\",\"2\",\"3\",\"4\"]");
                y = input.nextInt();
            }
            catch (InputMismatchException e){
                System.out.println("Please write a number");
                y=5;
                input.nextLine();
            }
        } while (y != 0 && y != 1 && y != 2 && y != 3 && y != 4);
        marketindex[1]=y;
        message.setMarketIndex(marketindex);

        return gson.toJson(message);
    }
}