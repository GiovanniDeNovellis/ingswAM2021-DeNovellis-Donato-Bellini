package it.polimi.ingsw.View;

import com.google.gson.Gson;
import it.polimi.ingsw.Controller.Messages.Message;

public class NotificationManager {
    public NotificationManager(VirtualView virtualView){
        this.virtualView = virtualView;
    }

    VirtualView virtualView;

    public void manageNotification(String notification){
        Gson gson = new Gson();
        Message toRead = gson.fromJson(notification, Message.class);
        String messageType = toRead.getMessageType();

        switch (messageType){
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
