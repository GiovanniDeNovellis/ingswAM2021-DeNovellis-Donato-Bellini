package it.polimi.ingsw.View.MessageBuilders;

import com.google.gson.Gson;
import it.polimi.ingsw.Controller.Messages.DistributionFourthMessage;
import it.polimi.ingsw.Controller.Messages.DistributionSecondThirdMessage;
import it.polimi.ingsw.ResourceType;

import java.util.Scanner;

import static it.polimi.ingsw.ResourceType.*;

/**
 * Used to build a free distribution request
 */
public class DistributionFourthMessageBuilder extends MessageBuilder{
    String nickname;
    String resourceType;

    public DistributionFourthMessageBuilder(String nickname){
        this.nickname = nickname;
    }

    @Override
    public String buildMessage() {
        Scanner input = new Scanner(System.in);
        Gson gson = new Gson();
        DistributionFourthMessage message = new DistributionFourthMessage();
        message.setMessageType("DistributionFourth");
        message.setSenderNickname(nickname);

        do {
            System.out.println("Write the first resource you want to obtain through initial distribution: [\"SHIELDS\",\"COINS\",\"SERVANTS\",\"STONES\"]");
            resourceType = input.nextLine();
        }while(!(resourceType.equals("SHIELDS") || resourceType.equals("COINS") || resourceType.equals("SERVANTS") || resourceType.equals("STONES")) );

        message.setResourceToDistribute(ResourceType.valueOf(resourceType));

        do {
            System.out.println("Write the second resource you want to obtain through initial distribution: [\"SHIELDS\",\"COINS\",\"SERVANTS\",\"STONES\"]");
            resourceType = input.nextLine();
        }while (!(resourceType.equals("SHIELDS") || resourceType.equals("COINS") || resourceType.equals("SERVANTS") || resourceType.equals("STONES")) );

        message.setSecondResourceToDistribute(ResourceType.valueOf(resourceType));

        return gson.toJson(message);
    }
}
