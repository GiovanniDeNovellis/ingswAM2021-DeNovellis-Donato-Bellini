package it.polimi.ingsw.View.NotificationReaders;

import com.google.gson.Gson;
import it.polimi.ingsw.Controller.Messages.ActivateLeaderAbilityTransformationMessage;
import it.polimi.ingsw.View.ModelPrinter;

public class ActivateLeaderAbilityTransformationReader extends NotificationReader{
    public ActivateLeaderAbilityTransformationReader(ModelPrinter modelPrinter) {
        super(modelPrinter);
    }

    @Override
    public void readNotification(String notification) {
        Gson gson = new Gson();
        ActivateLeaderAbilityTransformationMessage m = gson.fromJson(notification, ActivateLeaderAbilityTransformationMessage.class);
        modelPrinter.getMarketBoardPrinter().setTemporaryResources(m.getTemporaryResourcesConfiguration());
        System.out.println(m.getSenderNickname() + "has activated a white transformation Leader ability in position" + m.getPosition() +". Remaining white marbles: " + m.getRemainingWhiteMarbles());
    }
}
