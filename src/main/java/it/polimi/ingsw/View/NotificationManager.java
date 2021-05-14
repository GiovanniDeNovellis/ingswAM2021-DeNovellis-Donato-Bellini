package it.polimi.ingsw.View;

import com.google.gson.Gson;
import it.polimi.ingsw.Controller.Messages.Message;
import it.polimi.ingsw.Controller.Messages.NotifyInsertedOkMessage;
import it.polimi.ingsw.View.NotificationReaders.InsertedResourceChanged;
import it.polimi.ingsw.View.NotificationReaders.NotificationReader;

public class NotificationManager {
    public NotificationManager(VirtualView virtualView){
        this.virtualView = virtualView;
    }

    VirtualView virtualView;

    public void manageNotification(String notification){
        Gson gson = new Gson();
        Message toRead = gson.fromJson(notification, Message.class);
        String messageType = toRead.getMessageType();
        NotificationReader reader;

        switch (messageType){
            case "LoginOkNotification":
                System.out.println("Logged in.");
            case "InvalidLoginNotification":
                System.out.println("Nickname already used. Choose another nickname.");
                break;
            case"ConnectionAcceptedPleaseLoginNotification":
                System.out.println("Connection accepted. Please log in.");
                break;
            case"PlayerInNotification":
                break;
            case"ExpectedLoginRequestNotification":
                System.out.println("Expected login. Please log in before doing this action.");
                break;
            case"PlayerOutNotification":
                break;


            case"GameNotStartedNotification":
                break;
            case"MoveLorenzo":
                break;
            case"MoveAndShuffle":
                break;
            case"NotifyDeckgridChanged":
                break;
            case"ActionCardActivationFailureNotification":
                break;
            case"ActivateLeaderAbilitySuccessNotification":
                break;
            case"ActivateLeaderAbilityDiscount":
                break;
            case"ActivateLeaderAbilityDeposit":
                break;
            case"ActivateLeaderAbilityProduction":
                break;
            case"ActivateLeaderAbilityTransformation":
                break;
            case"ActivateLeaderAbilityFailureNotification":
                break;
            case"NotYourTurnNotification":
                break;
            case"ActivateLeaderCardSuccessNotification":
                break;
            case"NotifyActivateLeaderCard":
                break;
            case"ActivateLeaderCardFailureNotification":
                break;
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
                reader = new InsertedResourceChanged(virtualView);
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
