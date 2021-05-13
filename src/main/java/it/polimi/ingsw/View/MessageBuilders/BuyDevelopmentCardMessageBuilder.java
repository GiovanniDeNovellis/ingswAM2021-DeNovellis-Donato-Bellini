package it.polimi.ingsw.View.MessageBuilders;

import com.google.gson.Gson;
import it.polimi.ingsw.Colour;
import it.polimi.ingsw.Controller.Messages.BuyDevelopmentCardMessage;

import java.util.Scanner;

public class BuyDevelopmentCardMessageBuilder extends MessageBuilder {

    private final String nickname;
    public BuyDevelopmentCardMessageBuilder(String nickname){
        this.nickname = nickname;
    }

    @Override
    public String buildMessage() {
        Gson gson = new Gson();
        Scanner input = new Scanner(System.in);
        BuyDevelopmentCardMessage message = new BuyDevelopmentCardMessage();

        int level, slot, extradep1,extradep2;
        String colour;
        message.setMessageType("BuyDevelopmentCard");
        message.setSenderNickname(nickname);

        do{
            System.out.println("Write (in CAPS LOCK) the colour of the Development Card you want to buy: [\"PURPLE\",\"GREEN\",\"BLUE\",\"YELLOW\"]");
            colour = input.nextLine();
        }while (!(colour.equals("PURPLE") || colour.equals("GREEN") || colour.equals("BLUE") || colour.equals("YELLOW")) );
        message.setColour(Colour.valueOf(colour));


        do{
            System.out.println("Write the level of the Development Card you want to buy: [\"1\",\"2\",\"3\"]");
            level = input.nextInt();
        }while(level !=1 && level !=2 && level !=3);
        message.setLevel(level);

        do{
            System.out.println("Write the slot of the Development Card you want to buy: [\"0\",\"1\",\"2\"]");
            slot = input.nextInt();
        }while(slot !=0 && slot !=1 && slot !=2);
        message.setSlot(slot);

        do{
            System.out.println("Write how many resources from the extra deposit1 you want to use: [\"0\",\"1\",\"2\"]");
             extradep1 = input.nextInt();
        }while(extradep1 !=0 && extradep1 !=1 && extradep1 !=2);
        do{
            System.out.println("Write how many resources from the extra deposit2 you want to use: [\"0\",\"1\",\"2\"]");
            extradep2 = input.nextInt();
        }while(extradep2!=0 && extradep2 !=1 && extradep2 !=2);
        message.setPayUsingExtraDeposit(extradep1,extradep2);

        return gson.toJson(message);
    }
}
