package it.polimi.ingsw.View;

import com.google.gson.Gson;
import it.polimi.ingsw.Controller.Messages.Message;
import it.polimi.ingsw.View.NotificationReaders.*;

import java.sql.SQLOutput;

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
                break;
            case "ReconnectOkNotification":
                System.out.println("Successfully reconnected");
                break;
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
                break;
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
                //inizio
            case"ActivateProductionSuccessNotification":
                System.out.println("Production successfully activated.");
                break;
            case"NotifyActivateProductionMessage":
                break;
            case"VaticanReportMessage":
                break;
            case"ActivateProductionFailureNotification":
                System.out.println("You can't activate the production now.");
                break;
            case"PlayerAddedNotification":
                System.out.println("Player successfully added to the game.");
                break;
            case"AddPlayerNotificationForEveryone":
                break;
            case"InvalidPlayerAddNotification":
                System.out.println("Can't add other player into this game.");
                break;
            case"BuyDevelopmentCardSuccessNotification":
                System.out.println("Card successfully bought.");
                break;
            case"DevelopmentCardBought":
                break;
            case"BuyDevelopmentCardFailureNotification":
                System.out.println("You can't buy the development card now.");
                break;
            case"DistributionOkNotification":
                System.out.println("Distribution successfully done, resource(s) received.");
                break;
            case"NotifyWareHouseChangedMessage":
                break;
            case"NotRightToDistributionNotification":
                System.out.println("You don't have right to a free resource now.");
                break;
            case"EndTurnNotificationMessage":
                break;

            //fine
            case"InsertedResourcesSuccessNotification":
                System.out.println("You have correctly inserted resource(s) into your warehouse");
                break;
            case"InsertedResourceChanged":
                reader = new InsertedResourceChanged(modelPrinter);
                reader.readNotification(notification);
                break;
            case"ChosenLeaderCardsMessage":
                reader = new ChosenLeaderCards(modelPrinter);
                reader.readNotification(notification);
                break;
            case"LeaderCardSelectionOkNotification":
                System.out.println("You have correctly chosen your Leader cards!");
                break;
            case"NotRightToLeaderCardSelectionNotification":
                System.out.println("You have already chosen your Leader cards! Please try another game action.");
                break;
            case"MultiPlayerCreationMessage":
                reader = new MultiPlayerCreation(modelPrinter);
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
                reader = new TemporaryResourcesChanged(modelPrinter);
                reader.readNotification(notification);
                break;
            case"TakeResourceFromMarketFailureNotification":
                System.out.println("Wrong index(row,column) chosen. Try again.");
                break;
            case "MarketGridChangedMessage":
                //TODO CLASS
                break;
            default:
                System.out.println("Received wrong input message!");
        }
    }
}
