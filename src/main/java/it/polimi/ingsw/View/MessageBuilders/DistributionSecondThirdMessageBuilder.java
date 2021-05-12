package it.polimi.ingsw.View.MessageBuilders;

import com.google.gson.Gson;
import it.polimi.ingsw.Controller.Messages.DistributionSecondThirdMessage;
import it.polimi.ingsw.Controller.Messages.Message;
import it.polimi.ingsw.Controller.Messages.StartMultiPlayerMessage;
import it.polimi.ingsw.ResourceType;

import java.util.*;

public class DistributionSecondThirdMessageBuilder extends MessageBuilder{

    String nickname;
    String resourceType;

    public DistributionSecondThirdMessageBuilder(String nickname){
        this.nickname = nickname;
    }

    @Override
    public String buildMessage() {
        Scanner input = new Scanner(System.in);
        Gson gson = new Gson();
        DistributionSecondThirdMessage message = new DistributionSecondThirdMessage();
        message.setMessageType("DistributionSecondThirdMessage");
        message.setSenderNickname(nickname);

        do {
            System.out.println("Write the resource you want to obtain through initial distribution: [\"shield\",\"coins\",\"servants\",\"stones\"]");
            resourceType = input.nextLine();
        }while (!(resourceType.equals("shields") || resourceType.equals("coins") || resourceType.equals("servants") || resourceType.equals("stones")) );

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

        return gson.toJson(message);
    }


}
