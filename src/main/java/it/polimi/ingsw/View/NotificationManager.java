package it.polimi.ingsw.View;

import com.google.gson.Gson;
import it.polimi.ingsw.Controller.Messages.Message;
import it.polimi.ingsw.View.CLINotifiers.*;
import it.polimi.ingsw.View.GUINotifiers.*;
import javafx.application.Platform;

import java.io.IOException;

public class NotificationManager {
    public NotificationManager(ModelPrinter modelPrinter, boolean isCli){
        this.isCli=isCli;
        this.modelPrinter = modelPrinter;
    }

    private final ModelPrinter modelPrinter;
    private final boolean isCli;


    public void manageNotification(String notification){
        Gson gson = new Gson();
        Message toRead = gson.fromJson(notification, Message.class);
        String messageType = toRead.getMessageType();
        CLINotifier reader;

        switch (messageType){
            case "LoginOkNotification":
                if(isCli)
                    System.out.println("Successfully logged in.");
                else {
                    try {
                        GUI.setRoot("lobby_scene");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case "ReconnectOkNotification":
                System.out.println("Successfully reconnected");
                break;
            case "InvalidLoginNotification":
                if(isCli)
                    System.out.println("Nickname already used. Choose another nickname.\n" +
                        "If you are reconnecting, you have chosen an invalid nickname. Please choose " +
                        "the nickname you were logged with.");
                else {
                    GUINotifier notifier = new LoginErrorGUINotifier();
                    if(modelPrinter.isMultiplayerGameStarted())
                        notifier.notifyGui("game started");
                    else
                        notifier.notifyGui(null);
                }
                break;
            case"ConnectionAcceptedPleaseLoginNotification":
                if(isCli)
                    System.out.println("Connection accepted. Please log in.");
                break;
            case"PlayerInNotification":
                reader = new PlayerInCLINotifier(modelPrinter);
                reader.notifyCLI(notification);
                break;
            case"ConnectedPlayersMessage":
                if(isCli) {
                    reader = new ConnectedPlayersMessageReader(modelPrinter);
                    reader.notifyCLI(notification);
                }
                else{
                    GUINotifier notifier = new ConnectedPlayersGUINotifier(modelPrinter);
                    notifier.notifyGui(notification);
                }
                break;
            case"ExpectedLoginRequestNotification":
                if(isCli)
                    System.out.println("Expected login. Please log in before doing this action.");
                break;
            case"DiscardLeaderCardSuccessNotification":
                if(isCli)
                    System.out.println("You successfully discarded the leader card.");
                break;
            case"DiscardLeaderCardFailureNotification":
                if(isCli)
                    System.out.println("You can't discard this leader card.");
                break;
            case"NotifyDiscardLeaderCard":
                reader = new NotifyDiscardLeaderCardReader(modelPrinter);
                reader.notifyCLI(notification);
                break;
            case"ReconnectConfigurationMessage":
                reader = new ReconnectConfigurationMessageReader(modelPrinter);
                reader.notifyCLI(notification);
                break;
            case"PlayerOutNotification":
                reader = new PlayerOutCLINotifier(modelPrinter);
                reader.notifyCLI(notification);
                break;
            case"GameNotStartedNotification":
                if(isCli)
                    System.out.println("You can't do this action because the game has not started.");
                break;
            case"MoveLorenzo":
                reader = new MoveLorenzoReader(modelPrinter);
                reader.notifyCLI(notification);
                break;
            case"MoveAndShuffle":
                reader = new MoveAndShuffleReader(modelPrinter);
                reader.notifyCLI(notification);
                break;
            case"NotifyDeckgridChanged":
                reader = new NotifyDeckgridChandedReader(modelPrinter);
                reader.notifyCLI(notification);
                break;
            case"ActionCardActivationFailureNotification":
                if (isCli)
                    System.out.println("You can't activate an action card at this moment.");
                break;
            case"ActivateLeaderAbilitySuccessNotification":
                if(isCli)
                    System.out.println("You activated the leader ability successfully.");
                break;
            case"ActivateLeaderAbilityDiscount":
                reader = new ActivateLeaderAbilityDiscountReader(modelPrinter);
                reader.notifyCLI(notification);
                break;
            case"ActivateLeaderAbilityDeposit":
                reader = new ActivateLeaderAbilityDepositReader(modelPrinter);
                reader.notifyCLI(notification);
                break;
            case"ActivateLeaderAbilityProduction":
                reader = new ActivateLeaderAbilityProductionReader(modelPrinter);
                reader.notifyCLI(notification);
                break;
            case"ActivateLeaderAbilityTransformation":
                reader = new ActivateLeaderAbilityTransformationReader(modelPrinter);
                reader.notifyCLI(notification);
                break;
            case"ActivateLeaderAbilityFailureNotification":
                if(isCli)
                    System.out.println("You can't activate this leader ability now.");
                else{
                    Platform.runLater(()->{
                        GUI.getMainSceneController().setErrorLabelText("This leader ability can't be activated more than once.");
                    });
                }
                break;
            case"NotYourTurnNotification":
                if(isCli)
                    System.out.println("You can't do the action because it's not your turn.");
                else{
                    GUINotifier notifier = new NotYourTurnGUINotifier(modelPrinter);
                    notifier.notifyGui(null);
                }
                break;
            case"ActivateLeaderCardSuccessNotification":
                if(isCli)
                    System.out.println("Leader card successfully activated.");
                break;
            case"NotifyActivateLeaderCard":
                reader = new NotifyActivateLeaderCardReader(modelPrinter);
                reader.notifyCLI(notification);
                break;
            case"ActivateLeaderCardFailureNotification":
                if(isCli)
                    System.out.println("You can't activate this leader card now.");
                else{
                    Platform.runLater(()->{
                        GUI.getMainSceneController().setErrorLabelText("You don't have the requirements to activate this leader card now.");
                    });
                }
                break;
            case"ActivateProductionSuccessNotification":
                if(isCli)
                    System.out.println("Production successfully activated.");
                break;
            case"NotifyActivateProductionMessage":
                reader = new NotifyActivateProductionReader(modelPrinter);
                reader.notifyCLI(notification);
                break;
            case"VaticanReportMessage":
                reader = new VaticanReportMessageReader(modelPrinter);
                reader.notifyCLI(notification);
                break;
            case"ActivateProductionFailureNotification":
                if(isCli)
                    System.out.println("You can't activate the production now.");
                break;
            case"PlayerAddedNotification":
                if(isCli)
                    System.out.println("You have been successfully added to the game.");
                break;
            case"AddPlayerNotificationForEveryone":
                if( isCli ) {
                    reader = new AddPlayerCLINotifier(modelPrinter);
                    reader.notifyCLI(notification);
                } else {
                    GUINotifier notifier = new PlayerConnectionGUINotifier(modelPrinter);
                    notifier.notifyGui(notification);
                }
                break;
            case"InvalidPlayerAddNotification":
                if(isCli)
                    System.out.println("You can't be added to the game.");
                break;
            case"BuyDevelopmentCardSuccessNotification":
                if(isCli)
                    System.out.println("Card successfully bought.");
                break;
            case"DevelopmentCardBought":
                reader = new DevelopmentCardBoughtReader(modelPrinter);
                reader.notifyCLI(notification);
                break;
            case"BuyDevelopmentCardFailureNotification":
                if(isCli)
                    System.out.println("You can't buy the development card now.");
                break;
            case"DistributionOkNotification":
                if(isCli)
                    System.out.println("Distribution successfully done, resource(s) received.");
                break;
            case"NotifyWareHouseChangedMessage":
                if(isCli) {
                    reader = new NotifyWarehouseChangedMessageReader(modelPrinter);
                    reader.notifyCLI(notification);
                }
                else{
                    GUINotifier notifier = new WarehouseChangedGUINotifier(modelPrinter);
                    notifier.notifyGui(notification);
                }
                break;
            case"NotRightToDistributionNotification":
                if(isCli)
                    System.out.println("You can't receive free resource now.");
                break;
            case"EndTurnNotificationMessage":
                if(isCli) {
                    reader = new EndTurnCLINotifier(modelPrinter);
                    reader.notifyCLI(notification);
                }
                else{
                    GUINotifier endTurnGUiNotifier = new EndTurnGuiNotifier(modelPrinter);
                    endTurnGUiNotifier.notifyGui(notification);
                }
                break;
            case"InsertedResourcesSuccessNotification":
                if(isCli)
                    System.out.println("You have correctly inserted resource(s) into your warehouse");
                break;
            case"InsertedResourceChanged":
                if(isCli) {
                    reader = new InsertedResourceChanged(modelPrinter);
                    reader.notifyCLI(notification);
                }
                else{
                    GUINotifier notifier = new InsertedResGUINotifier(modelPrinter);
                    notifier.notifyGui(notification);
                }
                break;
            case"ChosenLeaderCardsMessage":
                reader = new ChosenLeaderCards(modelPrinter);
                reader.notifyCLI(notification);
                break;
            case"LeaderCardSelectionOkNotification":
                if(isCli)
                    System.out.println("You have correctly chosen your Leader cards!");
                break;
            case"NotRightToLeaderCardSelectionNotification":
                if(isCli)
                    System.out.println("You have already chosen your Leader cards! Please try another game action.");
                break;
            case"MultiPlayerCreationMessage":
                if (isCli) {
                    reader = new MultiPlayerCreation(modelPrinter);
                    reader.notifyCLI(notification);
                }
                else{
                    GUINotifier notifier = new MultiPlayerCreationGUINotifier(modelPrinter);
                    notifier.notifyGui(notification);
                }
                break;
            case"MultiPlayerCreationOkNotification":
                if(isCli)
                    System.out.println("You have just started the game in multiplayer mode!");
                break;
            case"MultiPlayerCreationErrorNotification":
                if(isCli) {
                    if (modelPrinter.isMultiplayerGameStarted())
                        System.out.println("Game already started in multiplayer mode.");
                    else
                        System.out.println("There is only one player logged in. You need at least another player to play" +
                                "multiplayer mode." +
                                "\nIf you want to play in single player mode write \"StartSinglePlayer\".");
                }
                break;
            case"SinglePLayerCreationOkNotification":
                if(isCli)
                    System.out.println("You have just started the game in single player mode!");
                break;
            case"SinglePlayerCreationMessage":
                if(isCli) {
                    reader = new SinglePlayerCreation(modelPrinter);
                    reader.notifyCLI(notification);
                }
                else{
                GUINotifier notifier = new SinglePlayerCreationGUINotifier(modelPrinter);
                notifier.notifyGui(notification);
            }
                break;
            case"SinglePLayerCreationFailedNotification":
                if(isCli) {
                    if (modelPrinter.isMultiplayerGameStarted())
                        System.out.println("Game already started in multiplayer mode.");
                    else if (modelPrinter.getPersonalBoards().size() == 1)
                        System.out.println("Game already started in single player mode");
                    else if (modelPrinter.getPersonalBoards().size() > 1)
                        System.out.println("There too many players logged in. Single player mode needs exactly one player." +
                                "\nIf you want to play in multiplayer mode write \"StartMultiPlayer\".");
                }
                break;
            case"SwitchLevelsSuccessNotification":
                if(isCli)
                    System.out.println("You have correctly switched these two levels!");
                else{
                    GUINotifier notifier = new SwitchSuccessGUINotifier(modelPrinter);
                    notifier.notifyGui(null);
                }
                break;
            case"SwitchLevelsFailureNotification":
                if(isCli)
                    System.out.println("You can't switch these two levels.");
                else{
                    GUINotifier notifier = new SwitchLevelsFailGUINotifier();
                    notifier.notifyGui(null);
                }
                break;
            case"InsertedResourcesFailureNotification":
                if(isCli)
                    System.out.println("Resource(s) insertion failed. Try again.");
                else{
                    GUINotifier notifier = new InsertedFailGUINotifier();
                    notifier.notifyGui(null);
                }
                break;
            case"TakeResourceFromMarketSuccessNotification":
                if(isCli)
                    System.out.println("You have correctly taken resources from market!Place them in your warehouse.");
                break;
            case"TemporaryResourcesChanged":
                if(isCli) {
                    reader = new TemporaryResourcesChanged(modelPrinter);
                    reader.notifyCLI(notification);
                }
                else{
                    GUINotifier notifier = new TempResChangeGUINotifier(modelPrinter);
                    notifier.notifyGui(notification);
                }
                break;
            case"TakeResourceFromMarketFailureNotification":
                if(isCli)
                    System.out.println("Wrong index(row,column) chosen. Try again.\n" +
                            "If you have chosen a correct index, it means you can't take resources now.");
                else{
                    GUINotifier notifier = new TakeResFailGUINotifier();
                    notifier.notifyGui(null);
                }
                break;
            case "MarketGridChangedMessage":
                if(isCli) {
                    reader = new MarketGridChanged(modelPrinter);
                    reader.notifyCLI(notification);
                }
                else{
                    GUINotifier notifier = new MarketChangeGUINotifier(modelPrinter);
                    notifier.notifyGui(notification);
                }
                break;
            case "EndTurnOkNotification":
                break;
            case "NotRightToEndTurnNotification":
                if(isCli)
                    System.out.println("You have to do a basic action before ending your turn.");
                else{
                    GUINotifier notifier = new EndTurnFailGUINotifier();
                    notifier.notifyGui(null);
                }
                break;
            default:
                System.out.println("Received wrong input message!");
                break;
        }
    }
}
