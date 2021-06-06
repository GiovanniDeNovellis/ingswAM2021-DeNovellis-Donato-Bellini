package it.polimi.ingsw.View.UInotifiers;

import com.google.gson.Gson;
import it.polimi.ingsw.Controller.Messages.DiscardSuccessMessage;
import it.polimi.ingsw.Controller.Messages.Message;
import it.polimi.ingsw.View.CLINotifiers.*;
import it.polimi.ingsw.View.GUI;
import it.polimi.ingsw.View.GUINotifiers.*;
import it.polimi.ingsw.View.ModelPrinter;
import it.polimi.ingsw.View.UI;
import javafx.application.Platform;

import java.io.IOException;

public class GUIInterface implements UI {
    private final ModelPrinter modelPrinter;

    public GUIInterface(ModelPrinter modelPrinter) {
        this.modelPrinter = modelPrinter;
    }

    @Override
    public void notify(String notification) {
        Gson gson = new Gson();
        Message toRead = gson.fromJson(notification, Message.class);
        String messageType = toRead.getMessageType();
        CLINotifier reader;
        GUINotifier notifier;
        switch (messageType){
            case "LoginOkNotification":
                try {
                    GUI.setRoot("lobby_scene");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case "ReconnectOkNotification":
                System.out.println("Successfully reconnected");
                notifier = new ReconnectOkGUINotifier(modelPrinter);
                notifier.notifyGui(notification);
                break;
            case "InvalidLoginNotification":
                notifier = new LoginErrorGUINotifier();
                if(modelPrinter.isMultiplayerGameStarted())
                    notifier.notifyGui("game started");
                else
                    notifier.notifyGui(null);
                break;
            case"ConnectionAcceptedPleaseLoginNotification":
                break;
            case"PlayerInNotification":
                notifier = new PlayerReconnectedGUINotifier(modelPrinter);
                notifier.notifyGui(notification);
                break;
            case"ConnectedPlayersMessage":
                notifier = new ConnectedPlayersGUINotifier(modelPrinter);
                notifier.notifyGui(notification);
                break;
            case"ExpectedLoginRequestNotification":
                break;
            case"DiscardLeaderCardSuccessNotification":
                notifier = new DiscardLeaderSuccessGUINotifier(modelPrinter);
                notifier.notifyGui(notification);
                break;
            case"DiscardLeaderCardFailureNotification":
                notifier = new DiscardLeadercCardFailureGUINotifier(modelPrinter);
                notifier.notifyGui(notification);
                break;
            case"NotifyDiscardLeaderCard":
                notifier = new DiscardLeaderGUINotifier(modelPrinter);
                notifier.notifyGui(notification);
                break;
            case"ReconnectConfigurationMessage":
                notifier = new ReconnectConfigurationGUINotifier(modelPrinter);
                notifier.notifyGui(notification);
                break;
            case"PlayerOutNotification":
                notifier = new PlayerOutGUINotifier(modelPrinter);
                notifier.notifyGui(notification);
                break;
            case"GameNotStartedNotification":
                break;
            case"MoveLorenzo":
                notifier = new LorenzoMovedGUINotifier(modelPrinter);
                notifier.notifyGui(notification);
                break;
            case"MoveAndShuffle":
                notifier = new MovedAndShuffledGUINotifier(modelPrinter);
                notifier.notifyGui(notification);
                break;
            case"NotifyDeckgridChanged":
                notifier = new DeckgridChangedGUINotifier(modelPrinter);
                notifier.notifyGui(notification);
                break;
            case"ActionCardActivationFailureNotification":
                break;
            case"ActivateLeaderAbilitySuccessNotification":
                break;
            case"ActivateLeaderAbilityDiscount":
                notifier = new ActivateDiscountGUINotifier(modelPrinter);
                notifier.notifyGui(notification);
                break;
            case"ActivateLeaderAbilityDeposit":
                notifier = new ActivateDepositGUINotifier(modelPrinter);
                notifier.notifyGui(notification);
                break;
            case"ActivateLeaderAbilityProduction":
                notifier = new ActivateAbilityProductionGUINotifier(modelPrinter);
                notifier.notifyGui(notification);
                break;
            case"ActivateLeaderAbilityTransformation":
                notifier = new ActivateTransformationGUINotifier(modelPrinter);
                notifier.notifyGui(notification);
                break;
            case"ActivateLeaderAbilityFailureNotification":
                Platform.runLater(()->{
                    GUI.getMainSceneController().getNotificationLabel().setText("This leader ability can't be activated more than once.");
                    GUI.getMainSceneController().getNotificationLabel().setVisible(true);
                });
                break;
            case"NotYourTurnNotification":
                notifier = new NotYourTurnGUINotifier(modelPrinter);
                notifier.notifyGui(null);
                break;
            case"ActivateLeaderCardSuccessNotification":
                notifier = new ActivateLeaderSuccessGUINotifier(modelPrinter);
                notifier.notifyGui(null);
                break;
            case"NotifyActivateLeaderCard":
                notifier = new LeaderActivatedGUINotifier(modelPrinter);
                notifier.notifyGui(notification);
                break;
            case"ActivateLeaderCardFailureNotification":
                Platform.runLater(()->{
                    GUI.getMainSceneController().getNotificationLabel().setText("(\"You don't have the requirements to activate this leader card now.\")");
                    GUI.getMainSceneController().getNotificationLabel().setVisible(true);
                });
                break;
            case"ActivateProductionSuccessNotification":
                notifier = new ProductionSuccessGUINotifier(modelPrinter);
                notifier.notifyGui(null);
                break;
            case"NotifyActivateProductionMessage":
                notifier = new ProductionActivatedGUINotifier(modelPrinter);
                notifier.notifyGui(notification);
                break;
            case"VaticanReportMessage":
                //TODO CHANGE FOR GUI
                reader = new VaticanReportMessageReader(modelPrinter);
                reader.notifyCLI(notification);
                break;
            case"ActivateProductionFailureNotification":
                notifier = new ProductionFailedGUINotifier();
                notifier.notifyGui(null);
                break;
            case"PlayerAddedNotification":
                break;
            case"AddPlayerNotificationForEveryone":
                notifier = new PlayerConnectionGUINotifier(modelPrinter);
                notifier.notifyGui(notification);
                break;
            case"InvalidPlayerAddNotification":
                notifier = new MaxPlayerNumberExceededGUINotifier();
                notifier.notifyGui(null);
                break;
            case"BuyDevelopmentCardSuccessNotification":
                notifier = new CardBoughtSuccessfullyGUINotifier(modelPrinter);
                notifier.notifyGui(null);
                break;
            case"DevelopmentCardBought":
                notifier = new CardBoughtGUINotifier(modelPrinter);
                notifier.notifyGui(notification);
                break;
            case"BuyDevelopmentCardFailureNotification":
                notifier = new BuyCardFailGUINotifier(modelPrinter);
                notifier.notifyGui(null);
                break;
            case"DistributionOkNotification":
                break;
            case"NotifyWareHouseChangedMessage":
                notifier = new WarehouseChangedGUINotifier(modelPrinter);
                notifier.notifyGui(notification);
                break;
            case"NotRightToDistributionNotification":
                break;
            case"EndTurnNotificationMessage":
                notifier = new EndTurnGuiNotifier(modelPrinter);
                notifier.notifyGui(notification);
                break;
            case"InsertedResourcesSuccessNotification":
                break;
            case"InsertedResourceChanged":
                notifier = new InsertedResGUINotifier(modelPrinter);
                notifier.notifyGui(notification);
                break;
            case"ChosenLeaderCardsMessage":
                reader = new ChosenLeaderCards(modelPrinter);
                reader.notifyCLI(notification);
                break;
            case"LeaderCardSelectionOkNotification":
                break;
            case"NotRightToLeaderCardSelectionNotification":
                break;
            case"MultiPlayerCreationMessage":
                notifier = new MultiPlayerCreationGUINotifier(modelPrinter);
                notifier.notifyGui(notification);
                break;
            case"MultiPlayerCreationOkNotification":
                break;
            case"MultiPlayerCreationErrorNotification":
                break;
            case"SinglePLayerCreationOkNotification":
                break;
            case"SinglePlayerCreationMessage":
                notifier = new SinglePlayerCreationGUINotifier(modelPrinter);
                notifier.notifyGui(notification);
                break;
            case"SinglePLayerCreationFailedNotification":
                break;
            case"SwitchLevelsSuccessNotification":
                notifier = new SwitchSuccessGUINotifier(modelPrinter);
                notifier.notifyGui(null);
                break;
            case"SwitchLevelsFailureNotification":
                notifier = new SwitchLevelsFailGUINotifier();
                notifier.notifyGui(null);
                break;
            case"InsertedResourcesFailureNotification":
                notifier = new InsertedFailGUINotifier();
                notifier.notifyGui(null);
                break;
            case"TakeResourceFromMarketSuccessNotification":
                break;
            case"TemporaryResourcesChanged":
                notifier = new TempResChangeGUINotifier(modelPrinter);
                notifier.notifyGui(notification);
                break;
            case"TakeResourceFromMarketFailureNotification":
                notifier = new TakeResFailGUINotifier();
                notifier.notifyGui(null);
                break;
            case "MarketGridChangedMessage":
                notifier = new MarketChangeGUINotifier(modelPrinter);
                notifier.notifyGui(notification);
                break;
            case "EndTurnOkNotification":
                break;
            case "NotRightToEndTurnNotification":
                notifier = new EndTurnFailGUINotifier();
                notifier.notifyGui(null);
                break;
            default:
                System.out.println("Received wrong input message!");
                break;
        }
    }
}
