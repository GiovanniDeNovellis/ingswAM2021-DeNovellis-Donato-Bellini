package it.polimi.ingsw.View;

import com.google.gson.Gson;
import it.polimi.ingsw.Controller.Messages.Message;
import it.polimi.ingsw.Controller.Messages.PlayerOutNotification;
import it.polimi.ingsw.View.NotificationReaders.*;

public class NotificationManager {
    public NotificationManager(ModelPrinter modelPrinter){
        this.modelPrinter = modelPrinter;
    }

    ModelPrinter modelPrinter;

    public void manageNotification(String notification){
        Gson gson = new Gson();
        Message toRead = gson.fromJson(notification, Message.class);
        String messageType = toRead.getMessageType();
        NotificationReader reader;

        switch (messageType){
            case "LoginOkNotification":
                reader = new LoginOKNotificationReader(modelPrinter);
                reader.readNotification(notification);
            case "ReconnectOkNotification":
                System.out.println("Successfully reconnected");
            case "InvalidLoginNotification":
                System.out.println("Nickname already used. Choose another nickname.");
                break;
            case"ConnectionAcceptedPleaseLoginNotification":
                System.out.println("Connection accepted. Please log in.");
                break;
            case"PlayerInNotification":
                reader = new PlayerInNotificationReader(modelPrinter);
                reader.readNotification(notification);
                break;
            case"ConnectedPlayersMessage":
                reader = new ConnectedPlayersMessageReader(modelPrinter);
                reader.readNotification(notification);
            case"ExpectedLoginRequestNotification":
                System.out.println("Expected login. Please log in before doing this action.");
                break;
            case"ReconnectConfigurationMessage":
                reader = new ReconnectConfigurationMessageReader(modelPrinter);
                reader.readNotification(notification);
                break;
            case"PlayerOutNotification":
                reader = new PlayerOutNotificationReader(modelPrinter);
                reader.readNotification(notification);
                break;
            case"GameNotStartedNotification":
                System.out.println("You can't do this action because the game has not started.");
                break;
            case"MoveLorenzo":
                reader = new MoveLorenzoReader(modelPrinter);
                reader.readNotification(notification);
                break;
            case"MoveAndShuffle":
                reader = new MoveAndShuffleReader(modelPrinter);
                reader.readNotification(notification);
                break;
            case"NotifyDeckgridChanged":
                reader = new NotifyDeckgridChandedReader(modelPrinter);
                reader.readNotification(notification);
                break;
            case"ActionCardActivationFailureNotification":
                System.out.println("You can't activate an action card at this moment.");
                break;
            case"ActivateLeaderAbilitySuccessNotification":
                System.out.println("You activated the leader ability successfully.");
                break;
            case"ActivateLeaderAbilityDiscount":
                reader = new ActivateLeaderAbilityDiscountReader(modelPrinter);
                reader.readNotification(notification);
                break;
            case"ActivateLeaderAbilityDeposit":
                reader = new ActivateLeaderAbilityDepositReader(modelPrinter);
                reader.readNotification(notification);
                break;
            case"ActivateLeaderAbilityProduction":
                reader = new ActivateLeaderAbilityProductionReader(modelPrinter);
                reader.readNotification(notification);
                break;
            case"ActivateLeaderAbilityTransformation":
                reader = new ActivateLeaderAbilityTransformationReader(modelPrinter);
                reader.readNotification(notification);
                break;
            case"ActivateLeaderAbilityFailureNotification":
                System.out.println("You can't activate this leader ability now.");
                break;
            case"NotYourTurnNotification":
                System.out.println("You can't do the action because it's not your turn.");
                break;
            case"ActivateLeaderCardSuccessNotification":
                System.out.println("Leader card successfully activated.");
                break;
            case"NotifyActivateLeaderCard":
                reader = new NotifyActivateLeaderCardReader(modelPrinter);
                reader.readNotification(notification);
                break;
            case"ActivateLeaderCardFailureNotification":
                System.out.println("You can't activate this leader card now.");
                break;
                // ---FINE MIA PARTE
            case"ActivateProductionSuccessNotification":
                break;
            case"NotifyActivateProductionMessage":
                break;
            case"VaticanReportMessage":
                break;
            case"ActivateProductionFailureNotification":
                break;
            case"PlayerAddedNotification":
                break;
            case"AddPlayerNotificationForEveryone":
                break;
            case"InvalidPlayerAddNotification":
                break;
            case"BuyDevelopmentCardSuccessNotification":
                break;
            case"DevelopmentCardBought":
                break;
            case"BuyDevelopmentCardFailureNotification":
                break;
            case"DistributionOkNotification":
                break;
            case"NotifyWareHouseChangedMessage":
                break;
            case"NotRightToDistributionNotification":
                break;
            case"EndTurnNotificationMessage":
                break;
            case"InsertedResourcesSuccessNotification":
                break;
            case"InsertedResourceChanged":
                reader = new InsertedResourceChanged(modelPrinter);
                reader.readNotification(notification);
                break;
            case"ChoosedLeaderCardsMessage":
                break;
            case"LeaderCardSelectionOkNotification":
                break;
            case"NotRightToLeaderCardSelectionNotification":
                break;
            case"MultiPlayerCreationMessage":
                break;
            case"MultiPlayerCreationOkNotification":
                break;
            case"MultiPlayerCreationErrorNotification":
                break;
            case"SinglePLayerCreationOkNotification":
                break;
            case"SinglePLayerCreationFailedNotification":
                break;
            case"SwitchLevelsSuccessNotification":
                break;
            case"SwitchLevelsFailureNotification":
                break;
            case"InsertedResourcesFailureNotification":
                System.out.println("Resource(s) insertion failed. Try again.");
                break;
            case"TakeResourceFromMarketSuccessNotification":
                break;
            case"TemporaryResourcesChanged":
                break;
            case"TakeResourceFromMarketFailureNotification":
                break;
        }
    }
}
