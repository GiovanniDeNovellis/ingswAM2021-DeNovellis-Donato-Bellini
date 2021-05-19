package it.polimi.ingsw.View.MessageBuilders;

import com.google.gson.Gson;
import it.polimi.ingsw.Controller.Messages.InsertResourceMessage;
import it.polimi.ingsw.Controller.Messages.TakeResourceFromMarketMessage;
import it.polimi.ingsw.ResourceType;

import javax.naming.spi.ResolveResult;
import java.util.InputMismatchException;
import java.util.Scanner;

public class InsertResourcesIntoWarehouseMessageBuilder extends MessageBuilder {
    private final String nickname;
    public InsertResourcesIntoWarehouseMessageBuilder(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public String buildMessage() {
        Gson gson = new Gson();
        int quantity;
        String resourceType;
        String intoExtraDep;

        Scanner input = new Scanner(System.in);
        InsertResourceMessage message = new InsertResourceMessage();
        message.setMessageType("InsertResourcesIntoWarehouse");
        message.setSenderNickname(nickname);

        do {
            System.out.println("Write the type of resource you want to insert into warehouse: [\"SHIELDS\",\"COINS\",\"SERVANTS\",\"STONES\"]");
            resourceType = input.nextLine();
        } while (!(resourceType.equals("SHIELDS") || resourceType.equals("COINS") || resourceType.equals("SERVANTS") || resourceType.equals("STONES")));
        message.setResourceToInsert(ResourceType.valueOf(resourceType));

        do {
            System.out.println("Do you want to insert that resource into the extra deposit? [\"Y\",\"N\"]");
            intoExtraDep = input.nextLine();
        } while (!(intoExtraDep.equals("Y") || (intoExtraDep.equals("N"))));
        message.setIntoExtraDeposit(intoExtraDep.equals("Y"));

        do {
            try {
                System.out.println("How many of this resource do you want to insert? Write the number:\n");
                quantity = input.nextInt();
            }
            catch (InputMismatchException e){
                System.out.println("Please write a number");
                quantity=-1;
                input.nextLine();
            }
        } while (quantity<=0);
        message.setQuantityToInsert(quantity);

        return gson.toJson(message);
    }
}

