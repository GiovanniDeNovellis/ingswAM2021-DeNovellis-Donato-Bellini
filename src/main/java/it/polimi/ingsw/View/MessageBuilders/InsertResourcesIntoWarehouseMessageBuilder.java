package it.polimi.ingsw.View.MessageBuilders;

import com.google.gson.Gson;
import it.polimi.ingsw.Controller.Messages.InsertResourceMessage;
import it.polimi.ingsw.Controller.Messages.TakeResourceFromMarketMessage;
import it.polimi.ingsw.ResourceType;

import javax.naming.spi.ResolveResult;
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

        System.out.println("Write the quantity of resource that you want to insert into warehouse:");
        quantity = input.nextInt();
        message.setQuantityToInsert(quantity);

        do {
            System.out.println("Write the type of resource you want to that you want to insert into warehouse: [\"SHIELDS\",\"COINS\",\"SERVANTS\",\"STONES\"]");
            resourceType = input.nextLine();
        } while (!(resourceType.equals("SHIELDS") || resourceType.equals("COINS") || resourceType.equals("SERVANTS") || resourceType.equals("STONES")));
        message.setResourceToInsert(ResourceType.valueOf(resourceType));

        do {
            System.out.println("Do you want to insert that resource into the extra deposit? [\"Y\",\"N\"]");
            intoExtraDep = input.nextLine();
        } while (!(intoExtraDep.equals("Y") || (intoExtraDep.equals("N"))));
        if (intoExtraDep.equals("Y")) {
            message.setIntoExtraDeposit(true);
        }
        if (intoExtraDep.equals("N"))
            message.setIntoExtraDeposit(false);

        return gson.toJson(message);
    }
}

