package it.polimi.ingsw.View;

import com.google.gson.Gson;
import it.polimi.ingsw.Controller.Messages.Message;
import it.polimi.ingsw.View.NotificationReaders.*;

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
                System.out.println("You have correctly inserted resource(s) into your warehouse");
                break;
            case"InsertedResourceChanged":
                reader = new InsertedResourceChanged(virtualView);
                reader.readNotification(notification);
                break;
            case"ChosenLeaderCardsMessage":
                reader = new ChosenLeaderCards(virtualView);
                reader.readNotification(notification);
                break;
            case"LeaderCardSelectionOkNotification":
                System.out.println("You have correctly chosen your Leader cards!");
                break;
            case"NotRightToLeaderCardSelectionNotification":
                System.out.println("You have already chosen your Leader cards! Please try another game action.");
                break;
            case"MultiPlayerCreationMessage":
                reader = new MultiPlayerCreation(virtualView);
                reader.readNotification(notification);
                break;
            case"MultiPlayerCreationOkNotification":
                System.out.println("You have just started the game in multiplayer mode!");
                break;
            case"MultiPlayerCreationErrorNotification":
                System.out.println("There is only one player logged in. You need at least another player to play" +
                        "multiplayer mode." +
                        "\nIf you want to play in single player mode write \"StartSinglePlayer\".");
                break;
            case"SinglePLayerCreationOkNotification":
                System.out.println("You have just started the game in single player mode!");
                break;
            case"SinglePLayerCreationFailedNotification":
                System.out.println("There too many players logged in. Single player mode needs exactly one player." +
                        "\nIf you want to play in multiplayer mode write \"StartMultiPlayer\".");
                break;
            case"SwitchLevelsSuccessNotification":
                System.out.println("You have correctly switched these two levels!");
                break;
            case"SwitchLevelsFailureNotification":
                System.out.println("You can't switch these two levels.");
                break;
            case"InsertedResourcesFailureNotification":
                System.out.println("Resource(s) insertion failed. Try again.");
                break;
            case"TakeResourceFromMarketSuccessNotification":
                System.out.println("You have correctly taken resources from market!Place them in your warehouse.");
                break;
            case"TemporaryResourcesChanged":
                reader = new TemporaryResourcesChanged(virtualView);
                reader.readNotification(notification);
                break;
            case"TakeResourceFromMarketFailureNotification":
                System.out.println("Wrong index(row,column) chosen. Try again.");
                break;
        }
    }
}
