package it.polimi.ingsw.View.MessageBuilders;

import com.google.gson.Gson;
import it.polimi.ingsw.Controller.Messages.TakeResourceFromMarketMessage;

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
        message.setMessageType("TakeResourceFromMarket");
        message.setSenderNickname(nickname);
        int x,y;
        int[] marketindex={0,0};

        do {
            System.out.println("Write the row of the grid where you want to take the resource: [\"0\",\"1\",\"2\"]");
            x = input.nextInt();
        } while (x != 0 && x != 1 && x != 2);
        marketindex[0]=x;
        do {
            System.out.println("Write the column of the grid where you want to take the resource: [\"0\",\"1\",\"2\",\"3\"]");
            y = input.nextInt();
        } while (y != 0 && y != 1 && y != 2 && y != 3);
        marketindex[1]=y;
        message.setMarketIndex(marketindex);

        return gson.toJson(message);
    }
}