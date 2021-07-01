package it.polimi.ingsw.View.MessageBuilders;

import com.google.gson.Gson;
import it.polimi.ingsw.Colour;
import it.polimi.ingsw.Controller.Messages.BuyDevelopmentCardMessage;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Used to build a development card purchase request
 */
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
            System.out.println("Write the colour of the Development Card you want to buy: [\"PURPLE\",\"GREEN\",\"BLUE\",\"YELLOW\"]");
            colour = input.nextLine();
        }while (!(colour.equals("PURPLE") || colour.equals("GREEN") || colour.equals("BLUE") || colour.equals("YELLOW")) );
        message.setColour(Colour.valueOf(colour));


        do{
            try {
                System.out.println("Write the level of the Development Card you want to buy: [\"1\",\"2\",\"3\"]");
                level = input.nextInt();
            }
            catch (InputMismatchException e){
                System.out.println("Please write a number");
                level=4;
                input.nextLine();
            }
        }while(level !=1 && level !=2 && level !=3);
        message.setLevel(level);

        do{
            try {
                System.out.println("Write the slot number where you want to place your development card: [\"0\",\"1\",\"2\"]");
                slot = input.nextInt();
            }
            catch (InputMismatchException e){
                System.out.println("Please write a number");
                slot=4;
                input.nextLine();
            }
        }while(slot !=0 && slot !=1 && slot !=2);
        message.setSlot(slot);

        do{
            try {
                System.out.println("Write how many resources from the extra deposit1 you want to use: [\"0\",\"1\",\"2\"]");
                extradep1 = input.nextInt();
            }
            catch (InputMismatchException e){
                System.out.println("Please write a number");
                extradep1=4;
                input.nextLine();
            }
        }while(extradep1 !=0 && extradep1 !=1 && extradep1 !=2);

        do{
            try {
                System.out.println("Write how many resources from the extra deposit2 you want to use: [\"0\",\"1\",\"2\"]");
                extradep2 = input.nextInt();
            }
            catch (InputMismatchException e){
                System.out.println("Please write a number");
                extradep2=4;
                input.nextLine();
            }
        }while(extradep2!=0 && extradep2 !=1 && extradep2 !=2);
        message.setPayUsingExtraDeposit(extradep1,extradep2);

        return gson.toJson(message);
    }
}
