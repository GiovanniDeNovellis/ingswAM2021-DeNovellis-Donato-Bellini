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
            System.out.println("Write resource you want to obtain through initial distribution: [\"SHIELDS\",\"COINS\",\"SERVANTS\",\"STONES\"]");
            resourceType = input.nextLine();
        }while(!(resourceType.equals("SHIELDS") || resourceType.equals("COINS") || resourceType.equals("SERVANTS") || resourceType.equals("STONES")) );

        message.setResourceToDistribute(ResourceType.valueOf(resourceType));

        return gson.toJson(message);
    }

}
