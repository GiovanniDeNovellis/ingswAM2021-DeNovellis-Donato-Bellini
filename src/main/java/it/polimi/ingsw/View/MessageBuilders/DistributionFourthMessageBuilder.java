package it.polimi.ingsw.View.MessageBuilders;

import com.google.gson.Gson;
import it.polimi.ingsw.Controller.Messages.DistributionFourthMessage;
import it.polimi.ingsw.Controller.Messages.DistributionSecondThirdMessage;
import it.polimi.ingsw.ResourceType;

import java.util.Scanner;

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
        message.setMessageType("DistributionFourthMessage");
        message.setSenderNickname(nickname);

        do {
            System.out.println("Write the first resource you want to obtain through initial distribution: [\"shields\",\"coins\",\"servants\",\"stones\"]");
            resourceType = input.nextLine();
        }while (!(resourceType.equals("shields") || resourceType.equals("coins") || resourceType.equals("servants") || resourceType.equals("stones")) );

        setResourceType(resourceType, message);

        do {
            System.out.println("Write the second resource you want to obtain through initial distribution: [\"shields\",\"coins\",\"servants\",\"stones\"]");
            resourceType = input.nextLine();
        }while (!(resourceType.equals("shields") || resourceType.equals("coins") || resourceType.equals("servants") || resourceType.equals("stones")) );

        setSecondResourceType(resourceType, message);

        return gson.toJson(message);
    }

    private void setResourceType(String resourceType, DistributionFourthMessage message){
        switch (resourceType) {
            case "shields":
                message.setResourceToDistribute(ResourceType.SHIELDS);
                break;
            case "coins":
                message.setResourceToDistribute(ResourceType.COINS);
                break;
            case "servants":
                message.setResourceToDistribute(ResourceType.SERVANTS);
                break;
            case "stones":
                message.setResourceToDistribute(ResourceType.STONES);
                break;
        }
    }

    private void setSecondResourceType(String resourceType, DistributionFourthMessage message){
        switch (resourceType) {
            case "shields":
                message.setSecondResourceToDistribute(ResourceType.SHIELDS);
                break;
            case "coins":
                message.setSecondResourceToDistribute(ResourceType.COINS);
                break;
            case "servants":
                message.setSecondResourceToDistribute(ResourceType.SERVANTS);
                break;
            case "stones":
                message.setSecondResourceToDistribute(ResourceType.STONES);
                break;
        }
    }
}
