package it.polimi.ingsw.View.MessageBuilders;

import com.google.gson.Gson;
import it.polimi.ingsw.Controller.Messages.SwitchLevelMessage;

import java.util.Scanner;

public class SwitchLevelMessageBuilder extends MessageBuilder {
    private final String nickname;
    public SwitchLevelMessageBuilder(String nickname) {
        this.nickname = nickname;
    }

    @Override
        public String buildMessage() {
        int[] levelToSwitch={0,0};
        int firstLevel, secondLevel;
        Gson gson = new Gson();
        Scanner input = new Scanner(System.in);
        SwitchLevelMessage message = new SwitchLevelMessage();
        message.setMessageType("SwitchLevel");
        message.setSenderNickname(nickname);
        do {
            System.out.println("Write the first level you want to switch: [\"1\",\"2\",\"3\"]");
            firstLevel = input.nextInt();
        } while (firstLevel != 1 && firstLevel != 2 && firstLevel != 3);
        levelToSwitch[0]=firstLevel;
        do {
            System.out.println("Write the second level you want to switch: [\"1\",\"2\",\"3\"]");
            secondLevel = input.nextInt();
        } while (secondLevel != 1 && secondLevel != 2 && secondLevel != 3);
        levelToSwitch[1]=secondLevel;

        message.setLevelsToSwitch(levelToSwitch);
        return gson.toJson(message);
    }
}
