package it.polimi.ingsw.View.GUIControllers;

import com.google.gson.Gson;
import it.polimi.ingsw.Controller.Messages.ActivateProductionMessage;
import it.polimi.ingsw.Controller.Messages.EndTurnRequestMessage;
import it.polimi.ingsw.ResourceType;
import it.polimi.ingsw.View.GUI;
import it.polimi.ingsw.View.ModelPrinter;
import it.polimi.ingsw.View.PrinterSingleton;
import it.polimi.ingsw.View.Printers.LeaderCardsPrinter;
import it.polimi.ingsw.View.Printers.PersonalBoardPrinter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class MainSceneController implements Initializable {
    private final ArrayList<String> leaderCardsIndex = new ArrayList<>();
    private final Map<ResourceType, String> resourceImagesMap = new HashMap<>();
    private final ArrayList<String> developmentCardsIndex = new ArrayList<>();
    private ModelPrinter modelPrinter;

    private boolean[] whichDevCardSlot = new boolean[]{false,false,false};
    private boolean fromPersonalBoard = false;
    private boolean[] whichLeaderCard = new boolean[]{false,false};
    private ResourceType[] resourceBaseProduction = new ResourceType[]{null,null,null};
    private ResourceType[] resourceFromLeader = null;
    private int[] payUsingExtraDeposit = new int[]{0,0};
    private boolean[] clicked = new boolean[]{false,false,false,false,false,false};

    @FXML
    private ImageView personalBoard;
    @FXML
    private ImageView chosenLeadCard1;
    @FXML
    private ImageView chosenLeadCard2;
    @FXML
    private Button discard1;
    @FXML
    private Button discard2;
    @FXML
    private Button activateLeadCard1;
    @FXML
    private Button activateLeadCard2;
    @FXML
    private Button activateAbility1;
    @FXML
    private Button activateAbility2;
    @FXML
    private Label errorLabel;
    @FXML
    private Button player1;
    @FXML
    private Button player2;
    @FXML
    private Button player3;
    @FXML
    private Button player4;
    @FXML
    private ImageView res1;
    @FXML
    private ImageView res2;
    @FXML
    private ImageView res3;
    @FXML
    private ImageView res4;
    @FXML
    private ImageView res5;
    @FXML
    private ImageView res6;
    @FXML
    private Label stonesLabel;
    @FXML
    private Label shieldsLabel;
    @FXML
    private Label servantsLabel;
    @FXML
    private Label coinsLabel;
    @FXML
    private ImageView devCardImage1lvl1;
    @FXML
    private ImageView devCardImage2lvl1;
    @FXML
    private ImageView devCardImage3lvl1;
    @FXML
    private ImageView devCardImage1lvl2;
    @FXML
    private ImageView devCardImage2lvl2;
    @FXML
    private ImageView devCardImage3lvl2;
    @FXML
    private ImageView devCardImage1lvl3;
    @FXML
    private ImageView devCardImage2lvl3;
    @FXML
    private ImageView devCardImage3lvl3;
    @FXML
    private ImageView redCross;
    @FXML
    private ImageView faithCard1;
    @FXML
    private ImageView faithCard2;
    @FXML
    private ImageView faithCard3;
    @FXML
    private Button devCard0;
    @FXML
    private Button devCard1;
    @FXML
    private Button devCard2;
    @FXML
    private Button production;
    @FXML
    private Label notificationLabel;
    @FXML
    private Button changementButton;
    private String lastChangedNickname;
    @FXML
    private Button endTurnButton;
    @FXML
    private Label currentPlayerLabel;
    @FXML
    private Button basePayButton1;
    @FXML
    private Button basePayButton2;
    @FXML
    private Button basePayButton3;
    @FXML
    private Button basePayButton4;
    @FXML
    private Button basePayButton5;
    @FXML
    private Button basePayButton6;
    @FXML
    Button baseObtainServantButton;
    @FXML
    Button baseObtainShieldButton;
    @FXML
    Button baseObtainStoneButton;
    @FXML
    Button baseObtainCoinButton;
    @FXML
    Button baseProd;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        GUI.setMainSceneController(this);
        personalBoard.setImage(new Image("Images/personalBoard.png"));
        leaderCardsIndex.add("Images/LeaderCardImages/leader1.png");
        leaderCardsIndex.add("Images/LeaderCardImages/leader2.png");
        leaderCardsIndex.add("Images/LeaderCardImages/leader3.png");
        leaderCardsIndex.add("Images/LeaderCardImages/leader4.png");
        leaderCardsIndex.add("Images/LeaderCardImages/leader5.png");
        leaderCardsIndex.add("Images/LeaderCardImages/leader6.png");
        leaderCardsIndex.add("Images/LeaderCardImages/leader7.png");
        leaderCardsIndex.add("Images/LeaderCardImages/leader8.png");
        leaderCardsIndex.add("Images/LeaderCardImages/leader9.png");
        leaderCardsIndex.add("Images/LeaderCardImages/leader10.png");
        leaderCardsIndex.add("Images/LeaderCardImages/leader11.png");
        leaderCardsIndex.add("Images/LeaderCardImages/leader12.png");
        leaderCardsIndex.add("Images/LeaderCardImages/leader13.png");
        leaderCardsIndex.add("Images/LeaderCardImages/leader14.png");
        leaderCardsIndex.add("Images/LeaderCardImages/leader15.png");
        leaderCardsIndex.add("Images/LeaderCardImages/leader16.png");
        resourceImagesMap.put(ResourceType.STONES, "Images/stone.png");
        resourceImagesMap.put(ResourceType.COINS, "Images/coin.png");
        resourceImagesMap.put(ResourceType.SERVANTS, "Images/servant.png");
        resourceImagesMap.put(ResourceType.SHIELDS, "Images/shield.png");
        developmentCardsIndex.add("Images/DevelopmentCardImages/Masters of Renaissance_Cards_FRONT_3mmBleed_1-1-1.png");
        developmentCardsIndex.add("Images/DevelopmentCardImages/Masters of Renaissance_Cards_FRONT_3mmBleed_1-2-1.png");
        developmentCardsIndex.add("Images/DevelopmentCardImages/Masters of Renaissance_Cards_FRONT_3mmBleed_1-3-1.png");
        developmentCardsIndex.add("Images/DevelopmentCardImages/Masters of Renaissance_Cards_FRONT_3mmBleed_1-4-1.png");
        developmentCardsIndex.add("Images/DevelopmentCardImages/Masters of Renaissance_Cards_FRONT_3mmBleed_1-5-1.png");
        developmentCardsIndex.add("Images/DevelopmentCardImages/Masters of Renaissance_Cards_FRONT_3mmBleed_1-6-1.png");
        developmentCardsIndex.add("Images/DevelopmentCardImages/Masters of Renaissance_Cards_FRONT_3mmBleed_1-7-1.png");
        developmentCardsIndex.add("Images/DevelopmentCardImages/Masters of Renaissance_Cards_FRONT_3mmBleed_1-8-1.png");
        developmentCardsIndex.add("Images/DevelopmentCardImages/Masters of Renaissance_Cards_FRONT_3mmBleed_1-9-1.png");
        developmentCardsIndex.add("Images/DevelopmentCardImages/Masters of Renaissance_Cards_FRONT_3mmBleed_1-10-1.png");
        developmentCardsIndex.add("Images/DevelopmentCardImages/Masters of Renaissance_Cards_FRONT_3mmBleed_1-11-1.png");
        developmentCardsIndex.add("Images/DevelopmentCardImages/Masters of Renaissance_Cards_FRONT_3mmBleed_1-12-1.png");
        developmentCardsIndex.add("Images/DevelopmentCardImages/Masters of Renaissance_Cards_FRONT_3mmBleed_1-13-1.png");
        developmentCardsIndex.add("Images/DevelopmentCardImages/Masters of Renaissance_Cards_FRONT_3mmBleed_1-14-1.png");
        developmentCardsIndex.add("Images/DevelopmentCardImages/Masters of Renaissance_Cards_FRONT_3mmBleed_1-15-1.png");
        developmentCardsIndex.add("Images/DevelopmentCardImages/Masters of Renaissance_Cards_FRONT_3mmBleed_1-16-1.png");
        developmentCardsIndex.add("Images/DevelopmentCardImages/Masters of Renaissance_Cards_FRONT_3mmBleed_1-17-1.png");
        developmentCardsIndex.add("Images/DevelopmentCardImages/Masters of Renaissance_Cards_FRONT_3mmBleed_1-18-1.png");
        developmentCardsIndex.add("Images/DevelopmentCardImages/Masters of Renaissance_Cards_FRONT_3mmBleed_1-19-1.png");
        developmentCardsIndex.add("Images/DevelopmentCardImages/Masters of Renaissance_Cards_FRONT_3mmBleed_1-20-1.png");
        developmentCardsIndex.add("Images/DevelopmentCardImages/Masters of Renaissance_Cards_FRONT_3mmBleed_1-21-1.png");
        developmentCardsIndex.add("Images/DevelopmentCardImages/Masters of Renaissance_Cards_FRONT_3mmBleed_1-22-1.png");
        developmentCardsIndex.add("Images/DevelopmentCardImages/Masters of Renaissance_Cards_FRONT_3mmBleed_1-23-1.png");
        developmentCardsIndex.add("Images/DevelopmentCardImages/Masters of Renaissance_Cards_FRONT_3mmBleed_1-24-1.png");
        developmentCardsIndex.add("Images/DevelopmentCardImages/Masters of Renaissance_Cards_FRONT_3mmBleed_1-25-1.png");
        developmentCardsIndex.add("Images/DevelopmentCardImages/Masters of Renaissance_Cards_FRONT_3mmBleed_1-26-1.png");
        developmentCardsIndex.add("Images/DevelopmentCardImages/Masters of Renaissance_Cards_FRONT_3mmBleed_1-27-1.png");
        developmentCardsIndex.add("Images/DevelopmentCardImages/Masters of Renaissance_Cards_FRONT_3mmBleed_1-28-1.png");
        developmentCardsIndex.add("Images/DevelopmentCardImages/Masters of Renaissance_Cards_FRONT_3mmBleed_1-29-1.png");
        developmentCardsIndex.add("Images/DevelopmentCardImages/Masters of Renaissance_Cards_FRONT_3mmBleed_1-30-1.png");
        developmentCardsIndex.add("Images/DevelopmentCardImages/Masters of Renaissance_Cards_FRONT_3mmBleed_1-31-1.png");
        developmentCardsIndex.add("Images/DevelopmentCardImages/Masters of Renaissance_Cards_FRONT_3mmBleed_1-32-1.png");
        developmentCardsIndex.add("Images/DevelopmentCardImages/Masters of Renaissance_Cards_FRONT_3mmBleed_1-33-1.png");
        developmentCardsIndex.add("Images/DevelopmentCardImages/Masters of Renaissance_Cards_FRONT_3mmBleed_1-34-1.png");
        developmentCardsIndex.add("Images/DevelopmentCardImages/Masters of Renaissance_Cards_FRONT_3mmBleed_1-35-1.png");
        developmentCardsIndex.add("Images/DevelopmentCardImages/Masters of Renaissance_Cards_FRONT_3mmBleed_1-36-1.png");
        developmentCardsIndex.add("Images/DevelopmentCardImages/Masters of Renaissance_Cards_FRONT_3mmBleed_1-37-1.png");
        developmentCardsIndex.add("Images/DevelopmentCardImages/Masters of Renaissance_Cards_FRONT_3mmBleed_1-38-1.png");
        developmentCardsIndex.add("Images/DevelopmentCardImages/Masters of Renaissance_Cards_FRONT_3mmBleed_1-39-1.png");
        developmentCardsIndex.add("Images/DevelopmentCardImages/Masters of Renaissance_Cards_FRONT_3mmBleed_1-40-1.png");
        developmentCardsIndex.add("Images/DevelopmentCardImages/Masters of Renaissance_Cards_FRONT_3mmBleed_1-41-1.png");
        developmentCardsIndex.add("Images/DevelopmentCardImages/Masters of Renaissance_Cards_FRONT_3mmBleed_1-42-1.png");
        developmentCardsIndex.add("Images/DevelopmentCardImages/Masters of Renaissance_Cards_FRONT_3mmBleed_1-43-1.png");
        developmentCardsIndex.add("Images/DevelopmentCardImages/Masters of Renaissance_Cards_FRONT_3mmBleed_1-44-1.png");
        developmentCardsIndex.add("Images/DevelopmentCardImages/Masters of Renaissance_Cards_FRONT_3mmBleed_1-45-1.png");
        developmentCardsIndex.add("Images/DevelopmentCardImages/Masters of Renaissance_Cards_FRONT_3mmBleed_1-46-1.png");
        developmentCardsIndex.add("Images/DevelopmentCardImages/Masters of Renaissance_Cards_FRONT_3mmBleed_1-47-1.png");
        developmentCardsIndex.add("Images/DevelopmentCardImages/Masters of Renaissance_Cards_FRONT_3mmBleed_1-48-1.png");
    }

    public void viewDeckGrid(ActionEvent actionEvent) {
        try {
            GUI.setRoot("deckgrid_scene");
            GUI.getDeckgridSceneController().printScene(modelPrinter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void viewMarketBoard(ActionEvent actionEvent) {
        try {
            GUI.setRoot("market_scene");
            GUI.getMarketSceneController().printScene(modelPrinter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void viewPlayersNumber(ActionEvent actionEvent) {
    }

    public void viewPlayer1(ActionEvent actionEvent) {
        viewPlayer(1);
    }

    public void viewPlayer2(ActionEvent actionEvent) {
        viewPlayer(2);
    }

    public void viewPlayer3(ActionEvent actionEvent) {
        viewPlayer(3);
    }

    public void viewPlayer4(ActionEvent actionEvent) {
        viewPlayer(4);
    }

    private void viewPlayer(int number){
        for(PersonalBoardPrinter p: modelPrinter.getPersonalBoards()){
            if(p.getPlayerNumber()==number) {
                if (p.getOwnerNickname().equals(GUI.getClientNickname()))
                    printClientPlayer(modelPrinter);
                else
                    printOtherPlayer(p.getOwnerNickname());
            }
        }
    }

    public void activateLeadCard1(ActionEvent actionEvent) {
    }

    public void activateLeadCard2(ActionEvent actionEvent) {
    }

    public void discardLeadCard1(ActionEvent actionEvent) {
    }

    public void discardLeadCard2(ActionEvent actionEvent) {
    }

    public void activateProduction(ActionEvent actionEvent) {
        if( production.getText().equals("ACTIVATE PRODUCTION")){
            production.setDisable(true);
            production.setCursor(Cursor.DEFAULT);
            production.setText("CONFIRM");
            baseProd.setDisable(false);
            baseProd.setCursor(Cursor.HAND);
            devCard0.setDisable(false);
            devCard0.setCursor(Cursor.HAND);
            devCard1.setDisable(false);
            devCard1.setCursor(Cursor.HAND);
            devCard2.setDisable(false);
            devCard2.setCursor(Cursor.HAND);

        } else if( production.getText().equals("CONFIRM")){
            PrinterSingleton.getPrinterSingleton().sendMessage(parseMessage());
        }
    }

    private String parseMessage(){
        Gson gson = new Gson();
        ActivateProductionMessage message = new ActivateProductionMessage();
        message.setMessageType("ActivateProduction");
        message.setSenderNickname(GUI.getClientNickname());
        message.setFromPersonalBoard(fromPersonalBoard);
        message.setPayUsingExtraDeposit(payUsingExtraDeposit);
        message.setResourceBaseProduction(resourceBaseProduction);
        message.setResourceFromLeader(resourceFromLeader);
        message.setWhichDevCardSlot(whichDevCardSlot);
        message.setWhichLeaderCard(whichLeaderCard);
        return gson.toJson(message);
    }

    public void baseProdTrue(ActionEvent actionEvent) {
        if(!fromPersonalBoard){
            fromPersonalBoard=true;
            activateBaseProdButtons();
            baseProd.setOpacity(0.3);
            baseProd.setDisable(false);
            baseProd.setCursor(Cursor.HAND);
        } else {
            fromPersonalBoard=false;
            deactivateBaseProdButtons();
            baseProd.setOpacity(0);
            baseProd.setDisable(false);
            baseProd.setCursor(Cursor.HAND);
        }
    }

    private void activateBaseProdButtons(){
        //Risorse da ottenere
        baseObtainStoneButton.setDisable(false);
        baseObtainStoneButton.setCursor(Cursor.HAND);
        baseObtainShieldButton.setDisable(false);
        baseObtainShieldButton.setCursor(Cursor.HAND);
        baseObtainServantButton.setDisable(false);
        baseObtainServantButton.setCursor(Cursor.HAND);
        baseObtainCoinButton.setDisable(false);
        baseObtainCoinButton.setCursor(Cursor.HAND);
        //Risorse da pagare
        if( res1.getImage()!=null ) {
            basePayButton1.setDisable(false);
            basePayButton1.setCursor(Cursor.HAND);
        }
        if( res2.getImage()!=null ) {
            basePayButton2.setDisable(false);
            basePayButton2.setCursor(Cursor.HAND);
        }
        if( res3.getImage()!=null ) {
            basePayButton3.setDisable(false);
            basePayButton3.setCursor(Cursor.HAND);
        }
        if( res4.getImage()!=null ) {
            basePayButton4.setDisable(false);
            basePayButton4.setCursor(Cursor.HAND);
        }
        if( res5.getImage()!=null ) {
            basePayButton5.setDisable(false);
            basePayButton5.setCursor(Cursor.HAND);
        }
        if( res6.getImage()!=null ) {
            basePayButton6.setDisable(false);
            basePayButton6.setCursor(Cursor.HAND);
        }
    }

    private void deactivateBaseProdButtons(){
        //Risorse da ottenere
        baseObtainStoneButton.setDisable(true);
        baseObtainStoneButton.setCursor(Cursor.DEFAULT);
        baseObtainStoneButton.setOpacity(0);
        baseObtainShieldButton.setDisable(true);
        baseObtainShieldButton.setCursor(Cursor.DEFAULT);
        baseObtainShieldButton.setOpacity(0);
        baseObtainServantButton.setDisable(true);
        baseObtainServantButton.setCursor(Cursor.DEFAULT);
        baseObtainServantButton.setOpacity(0);
        baseObtainCoinButton.setDisable(true);
        baseObtainCoinButton.setCursor(Cursor.DEFAULT);
        baseObtainCoinButton.setOpacity(0);
        //Risorse da pagare
        basePayButton1.setDisable(true);
        basePayButton1.setCursor(Cursor.DEFAULT);
        basePayButton1.setOpacity(0);
        basePayButton2.setDisable(true);
        basePayButton2.setCursor(Cursor.DEFAULT);
        basePayButton2.setOpacity(0);
        basePayButton3.setDisable(true);
        basePayButton3.setCursor(Cursor.DEFAULT);
        basePayButton3.setOpacity(0);
        basePayButton4.setDisable(true);
        basePayButton4.setCursor(Cursor.DEFAULT);
        basePayButton4.setOpacity(0);
        basePayButton5.setDisable(true);
        basePayButton5.setCursor(Cursor.DEFAULT);
        basePayButton5.setOpacity(0);
        basePayButton6.setDisable(true);
        basePayButton6.setCursor(Cursor.DEFAULT);
        basePayButton6.setOpacity(0);
        //svuoto array di risorse
        for( int i=0; i<3; i++ )
            resourceBaseProduction[i] = null;
    }

    private void selectDevCard(Button devCard, int slot ){
        if( !whichDevCardSlot[slot] ){
            devCard.setOpacity(0.3);
            whichDevCardSlot[slot] = true;
        } else {
            devCard.setOpacity(0);
            whichDevCardSlot[slot] = false;
        }
    }

    public void selectDevCard0(ActionEvent actionEvent) {
        selectDevCard(devCard0,0);
        checkConfirmButton();
    }

    public void selectDevCard1(ActionEvent actionEvent) {
        selectDevCard(devCard1,1);
        checkConfirmButton();
    }

    public void selectDevCard2(ActionEvent actionEvent) {
        selectDevCard(devCard2,2);
        checkConfirmButton();
    }

    public void activateLeaderAbility1(ActionEvent actionEvent) {
    }

    public void activateLeaderAbility2(ActionEvent actionEvent) {
    }

    public void printClientPlayer(ModelPrinter modelPrinter) {
        GUI.setStatus("Main");
        endTurnButton.setDisable(false);
        endTurnButton.setVisible(true);
        endTurnButton.setCursor(Cursor.HAND);
        notificationLabel.setVisible(false);
        changementButton.setDisable(true);
        changementButton.setVisible(false);
        changementButton.setCursor(Cursor.DEFAULT);
        production.setVisible(true);
        production.setDisable(false);
        devCardImage1lvl1.setVisible(false);
        devCardImage1lvl2.setVisible(false);
        devCardImage1lvl3.setVisible(false);
        devCardImage2lvl1.setVisible(false);
        devCardImage2lvl2.setVisible(false);
        devCardImage2lvl3.setVisible(false);
        devCardImage3lvl1.setVisible(false);
        devCardImage3lvl2.setVisible(false);
        devCardImage3lvl3.setVisible(false);
        currentPlayerLabel.setText(modelPrinter.getCurrentPlayerNickname());
        this.modelPrinter=modelPrinter;
        LeaderCardsPrinter leadToPrint = null;
        PersonalBoardPrinter personalToPrint = null;
        for (LeaderCardsPrinter l : modelPrinter.getLeaderCardsPrinters()) {
            if (l.getOwnerNickname().equals(GUI.getClientNickname())) {
                leadToPrint = l;
            }
        }
        for (PersonalBoardPrinter p : modelPrinter.getPersonalBoards()) {
            if (p.getOwnerNickname().equals(GUI.getClientNickname())) {
                personalToPrint = p;
            }
        }
        assert leadToPrint != null;
        printLeadersMainPlayer(leadToPrint);
        assert personalToPrint != null;
        printPlayerButtons(modelPrinter,personalToPrint.getPlayerNumber());
        printWarehouse(personalToPrint);
        printStrongBox(personalToPrint);
        printDevelopmentCards(personalToPrint);
        printFaithTrack(personalToPrint);
        deactivateBaseProdButtons();
        devCard0.setDisable(true);
        devCard0.setOpacity(0);
        devCard1.setDisable(true);
        devCard1.setOpacity(0);
        devCard2.setDisable(true);
        devCard2.setOpacity(0);
        baseProd.setOpacity(0);
        baseProd.setDisable(true);
        production.setText("ACTIVATE PRODUCTION");
    }

    public void printOtherPlayer(String playerNickname) {
        GUI.setStatus("Main");
        endTurnButton.setDisable(true);
        endTurnButton.setVisible(false);
        endTurnButton.setCursor(Cursor.DEFAULT);
        notificationLabel.setVisible(false);
        changementButton.setDisable(true);
        changementButton.setVisible(false);
        devCardImage1lvl1.setVisible(false);
        devCardImage1lvl2.setVisible(false);
        devCardImage1lvl3.setVisible(false);
        devCardImage2lvl1.setVisible(false);
        devCardImage2lvl2.setVisible(false);
        devCardImage2lvl3.setVisible(false);
        devCardImage3lvl1.setVisible(false);
        devCardImage3lvl2.setVisible(false);
        devCardImage3lvl3.setVisible(false);
        currentPlayerLabel.setText(modelPrinter.getCurrentPlayerNickname());
        changementButton.setCursor(Cursor.DEFAULT);
        LeaderCardsPrinter leadToPrint = null;
        PersonalBoardPrinter personalToPrint = null;
        for (LeaderCardsPrinter l : modelPrinter.getLeaderCardsPrinters()) {
            if (l.getOwnerNickname().equals(playerNickname)) {
                leadToPrint = l;
            }
        }
        for (PersonalBoardPrinter p : modelPrinter.getPersonalBoards()) {
            if (p.getOwnerNickname().equals(playerNickname)) {
                personalToPrint = p;
            }
        }
        disableButtons();
        assert leadToPrint != null;
        printLeadersOtherPlayer(leadToPrint);
        assert personalToPrint != null;
        printPlayerButtons(modelPrinter,personalToPrint.getPlayerNumber());
        printWarehouse(personalToPrint);
        printStrongBox(personalToPrint);
        printDevelopmentCards(personalToPrint);
        printFaithTrack(personalToPrint);
        deactivateBaseProdButtons();
    }

    private void disableButtons(){
        activateLeadCard1.setDisable(true);
        activateLeadCard1.setVisible(false);
        activateLeadCard2.setDisable(true);
        activateLeadCard2.setVisible(false);
        discard1.setDisable(true);
        discard1.setVisible(false);
        discard2.setDisable(true);
        discard2.setVisible(false);
        activateAbility1.setDisable(true);
        activateAbility1.setVisible(false);
        activateAbility2.setDisable(true);
        activateAbility2.setVisible(false);
        devCard0.setDisable(true);
        devCard0.setOpacity(0);
        devCard1.setDisable(true);
        devCard1.setOpacity(0);
        devCard2.setDisable(true);
        devCard2.setOpacity(0);
        production.setDisable(true);
        production.setVisible(false);
        baseProd.setOpacity(0);
        baseProd.setDisable(true);
        production.setText("ACTIVATE PRODUCTION");
    }

    private void printLeadersOtherPlayer(LeaderCardsPrinter leadToPrint){
        chosenLeadCard1.setVisible(false);
        chosenLeadCard2.setVisible(false);
        if(leadToPrint.getChosenLeaderCards()[0]!=0 && leadToPrint.getActivatedLeaderCards()[0]) {
            //La prima carta esiste ed è stata attivata
            chosenLeadCard1.setImage(new Image(leaderCardsIndex.get(leadToPrint.getChosenLeaderCards()[0] - 1)));
            chosenLeadCard1.setVisible(true);
        }
        if(leadToPrint.getChosenLeaderCards()[1]!=0 && leadToPrint.getActivatedLeaderCards()[1]) {
            //La seconda carta esiste ed è stata attivata
            chosenLeadCard2.setImage(new Image(leaderCardsIndex.get(leadToPrint.getChosenLeaderCards()[1] - 1)));
            chosenLeadCard2.setVisible(true);
        }
    }

    private void printLeadersMainPlayer(LeaderCardsPrinter leadToPrint) {
        chosenLeadCard1.setVisible(true);
        chosenLeadCard2.setVisible(true);
        activateLeadCard1.setDisable(false);
        activateLeadCard1.setVisible(true);
        activateLeadCard2.setDisable(false);
        activateLeadCard2.setVisible(true);
        discard1.setDisable(false);
        discard1.setVisible(true);
        discard2.setDisable(false);
        discard2.setVisible(true);
        activateAbility1.setDisable(false);
        activateAbility1.setVisible(true);
        activateAbility2.setDisable(false);
        activateAbility2.setVisible(true);
        if (leadToPrint.getChosenLeaderCards()[0] != 0) {
            //La carta esiste
            chosenLeadCard1.setImage(new Image(leaderCardsIndex.get(leadToPrint.getChosenLeaderCards()[0] - 1)));
            if (leadToPrint.getActivatedLeaderCards()[0]) {
                //La carta esiste ed è stata attivata
                discard1.setDisable(true);
                activateLeadCard1.setDisable(true);
                discard1.setOpacity(0.3);
                activateLeadCard1.setOpacity(0.3);
                discard1.setCursor(Cursor.DEFAULT);
                activateLeadCard1.setCursor(Cursor.DEFAULT);
            } else {
                //La carta esiste ma non è stata attivata
                activateAbility1.setDisable(true);
                activateAbility1.setOpacity(0.3);
                activateAbility1.setCursor(Cursor.DEFAULT);
            }
        } else {
            //La carta non c'è (scartata)
            discard1.setDisable(true);
            activateLeadCard1.setDisable(true);
            activateAbility1.setDisable(true);
            discard1.setOpacity(0.3);
            activateLeadCard1.setOpacity(0.3);
            activateAbility1.setOpacity(0.3);
            discard1.setCursor(Cursor.DEFAULT);
            activateLeadCard1.setCursor(Cursor.DEFAULT);
            activateAbility1.setCursor(Cursor.DEFAULT);
        }
        if (leadToPrint.getChosenLeaderCards()[1] != 0) {
            //La carta esiste
            chosenLeadCard2.setImage(new Image(leaderCardsIndex.get(leadToPrint.getChosenLeaderCards()[1] - 1)));
            if (leadToPrint.getActivatedLeaderCards()[1]) {
                //La carta esiste ed è stata attivata
                discard2.setDisable(true);
                activateLeadCard2.setDisable(true);
                discard2.setOpacity(0.3);
                activateLeadCard2.setOpacity(0.3);
                discard2.setCursor(Cursor.DEFAULT);
                activateLeadCard2.setCursor(Cursor.DEFAULT);
            } else {
                //La carta esiste ma non è stata attivata
                activateAbility2.setDisable(true);
                activateAbility2.setOpacity(0.3);
                activateAbility2.setCursor(Cursor.DEFAULT);
            }
        } else {
            //La carta non c'è (scartata)
            discard2.setDisable(true);
            activateLeadCard2.setDisable(true);
            activateAbility2.setDisable(true);
            discard2.setOpacity(0.3);
            activateLeadCard2.setOpacity(0.3);
            activateAbility2.setOpacity(0.3);
            discard2.setCursor(Cursor.DEFAULT);
            activateLeadCard2.setCursor(Cursor.DEFAULT);
            activateAbility2.setCursor(Cursor.DEFAULT);
        }
    }

    private void printPlayerButtons(ModelPrinter modelPrinter, int numberToDisable) {
        for (PersonalBoardPrinter p : modelPrinter.getPersonalBoards()) {
            if (p.getPlayerNumber() == 1) {
                player1.setText("Player 1: " + p.getOwnerNickname());
                if (numberToDisable == 1) {
                    player1.setDisable(true);
                    player1.setOpacity(0.3);
                    player1.setCursor(Cursor.DEFAULT);
                } else {
                    player1.setDisable(false);
                    player1.setOpacity(1);
                    player1.setCursor(Cursor.HAND);
                }
            } else if (p.getPlayerNumber() == 2) {
                player2.setText("Player 2: " + p.getOwnerNickname());
                if (numberToDisable == 2) {
                    player2.setDisable(true);
                    player2.setOpacity(0.3);
                    player2.setCursor(Cursor.DEFAULT);
                } else {
                    player2.setDisable(false);
                    player2.setOpacity(1);
                    player2.setCursor(Cursor.HAND);
                }
            } else if (p.getPlayerNumber() == 3) {
                player3.setText("Player 3: " + p.getOwnerNickname());
                if (numberToDisable == 3) {
                    player3.setDisable(true);
                    player3.setOpacity(0.3);
                    player3.setCursor(Cursor.DEFAULT);
                } else {
                    player3.setDisable(false);
                    player3.setOpacity(1);
                    player3.setCursor(Cursor.HAND);
                }
            } else {
                player4.setText("Player 4: " + p.getOwnerNickname());
                if (numberToDisable == 4) {
                    player4.setDisable(true);
                    player4.setOpacity(0.3);
                    player4.setCursor(Cursor.DEFAULT);
                } else {
                    player4.setDisable(false);
                    player4.setOpacity(1);
                    player4.setCursor(Cursor.HAND);
                }
            }
        }

        if (modelPrinter.getPersonalBoards().size() == 1) {
            player1.setText(modelPrinter.getPersonalBoards().get(0).getOwnerNickname());
            player2.setText("Lorenzo");
            player3.setDisable(true);
            player3.setOpacity(0);
            player3.setCursor(Cursor.DEFAULT);
            player4.setDisable(true);
            player4.setOpacity(0);
            player4.setCursor(Cursor.DEFAULT);
        } else if (modelPrinter.getPersonalBoards().size() == 2) {
            player3.setDisable(true);
            player3.setOpacity(0);
            player3.setCursor(Cursor.DEFAULT);
            player4.setDisable(true);
            player4.setOpacity(0);
            player4.setCursor(Cursor.DEFAULT);
        } else if (modelPrinter.getPersonalBoards().size() == 3) {
            player4.setDisable(true);
            player4.setOpacity(0);
            player4.setCursor(Cursor.DEFAULT);
        }
    }

    //USE THIS TO SET ERRORS
    public void setErrorLabelText(String text) {
        errorLabel.setText(text);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        errorLabel.setText("");
    }

    private void printWarehouse(PersonalBoardPrinter p) {
        res1.setVisible(false);
        res2.setVisible(false);
        res3.setVisible(false);
        res4.setVisible(false);
        res5.setVisible(false);
        res6.setVisible(false);
        if (p.getWareHouseDepot().getLevel(1).getResourceType() != null) {
            res1.setImage(new Image(resourceImagesMap.get(p.getWareHouseDepot().getLevel(1).getResourceType())));
            res1.setVisible(true);
        }
        if (p.getWareHouseDepot().getLevel(2).getResourceType() != null) {
            if (p.getWareHouseDepot().getLevel(2).getCurrNumResources() == 1) {
                res2.setImage(new Image(resourceImagesMap.get(p.getWareHouseDepot().getLevel(2).getResourceType())));
                res2.setVisible(true);
            }
            else {
                res2.setImage(new Image(resourceImagesMap.get(p.getWareHouseDepot().getLevel(2).getResourceType())));
                res3.setImage(new Image(resourceImagesMap.get(p.getWareHouseDepot().getLevel(2).getResourceType())));
                res2.setVisible(true);
                res3.setVisible(true);
            }
        }
        if (p.getWareHouseDepot().getLevel(3).getResourceType() != null) {
            if (p.getWareHouseDepot().getLevel(3).getCurrNumResources() == 1) {
                res4.setImage(new Image(resourceImagesMap.get(p.getWareHouseDepot().getLevel(3).getResourceType())));
                res4.setVisible(true);
            }
            else if (p.getWareHouseDepot().getLevel(3).getCurrNumResources() == 2) {
                res4.setImage(new Image(resourceImagesMap.get(p.getWareHouseDepot().getLevel(3).getResourceType())));
                res5.setImage(new Image(resourceImagesMap.get(p.getWareHouseDepot().getLevel(3).getResourceType())));
                res4.setVisible(true);
                res5.setVisible(true);
            } else {
                res4.setImage(new Image(resourceImagesMap.get(p.getWareHouseDepot().getLevel(3).getResourceType())));
                res5.setImage(new Image(resourceImagesMap.get(p.getWareHouseDepot().getLevel(3).getResourceType())));
                res6.setImage(new Image(resourceImagesMap.get(p.getWareHouseDepot().getLevel(3).getResourceType())));
                res4.setVisible(true);
                res5.setVisible(true);
                res6.setVisible(true);
            }
        }
    }

    private void printStrongBox(PersonalBoardPrinter p) {
        for(ResourceType r: p.getStrongbox().keySet()){
            if(r==ResourceType.STONES)
                stonesLabel.setText("x" + p.getStrongbox().get(r));
            else if(r==ResourceType.SHIELDS)
                shieldsLabel.setText("x" + p.getStrongbox().get(r));
            else if(r==ResourceType.SERVANTS)
                servantsLabel.setText("x" + p.getStrongbox().get(r));
            else if(r==ResourceType.COINS)
                coinsLabel.setText("x" + p.getStrongbox().get(r));
        }
    }

    private void printDevelopmentCards(PersonalBoardPrinter p){
        //LVL 1 CARDS
        for( int i=0; i<3; i++ ){
            if( i==0 && p.getLevel1cards()[i]!=null) {
                devCardImage1lvl1.setImage(new Image(developmentCardsIndex.get(p.getLevel1cards()[i].getNumber() - 1)));
                devCardImage1lvl1.setVisible(true);
            }
            if( i==1 && p.getLevel1cards()[i]!=null) {
                devCardImage2lvl1.setImage(new Image(developmentCardsIndex.get(p.getLevel1cards()[i].getNumber() - 1)));
                devCardImage2lvl1.setVisible(true);
            }
            if( i==2 && p.getLevel1cards()[i]!=null) {
                devCardImage3lvl1.setImage(new Image(developmentCardsIndex.get(p.getLevel1cards()[i].getNumber() - 1)));
                devCardImage3lvl1.setVisible(true);
            }
        }
        //LVL 2 CARDS
        for( int i=0; i<3; i++ ){
            if( i==0 && p.getLevel2cards()[i]!=null) {
                devCardImage1lvl2.setImage(new Image(developmentCardsIndex.get(p.getLevel2cards()[i].getNumber() - 1)));
                devCardImage1lvl2.setVisible(true);
            }
            if( i==1 && p.getLevel2cards()[i]!=null) {
                devCardImage2lvl2.setImage(new Image(developmentCardsIndex.get(p.getLevel2cards()[i].getNumber() - 1)));
                devCardImage2lvl2.setVisible(true);
            }
            if( i==2 && p.getLevel2cards()[i]!=null){
                devCardImage3lvl2.setImage(new Image(developmentCardsIndex.get(p.getLevel2cards()[i].getNumber() -1 )));
                devCardImage3lvl2.setVisible(true);
            }
        }
        //LVL 3 CARDS
        for( int i=0; i<3; i++ ){
            if( i==0 && p.getLevel3cards()[i]!=null) {
                devCardImage1lvl3.setImage(new Image(developmentCardsIndex.get(p.getLevel3cards()[i].getNumber() - 1)));
                devCardImage1lvl3.setVisible(true);
            }
            if( i==1 && p.getLevel3cards()[i]!=null) {
                devCardImage2lvl3.setImage(new Image(developmentCardsIndex.get(p.getLevel3cards()[i].getNumber() - 1)));
                devCardImage2lvl3.setVisible(true);
            }
            if( i==2 && p.getLevel3cards()[i]!=null) {
                devCardImage3lvl3.setImage(new Image(developmentCardsIndex.get(p.getLevel3cards()[i].getNumber() - 1)));
                devCardImage3lvl3.setVisible(true);
            }
        }
    }

    private void printFaithTrack(PersonalBoardPrinter p){
        if(p.getFaithCards()[0]!=0)
            faithCard1.setImage(new Image("Images/FaithCards/quadratoGiallo.png"));
        if(p.getFaithCards()[1]!=0)
            faithCard2.setImage(new Image("Images/FaithCards/quadratoArancione.png"));
        if(p.getFaithCards()[2]!=0)
            faithCard3.setImage(new Image("Images/FaithCards/quadratoRosso.png"));
        redCross.setLayoutX(calcX(p.getFaithPoints()));
        redCross.setLayoutY(calcY(p.getFaithPoints()));
    }

    private int calcX(int position){
        int delay,startX=488,offsetX=74;
        if(position>=0&&position<=2)
            delay=0;
        else if(position == 3)
            delay=1;
        else if(position >=4&& position <= 9 )
            delay=2;
        else if(position==10)
            delay=3;
        else if(position>=11&&position<=16)
            delay=4;
        else if(position==17)
            delay=5;
        else
            delay=5;
        return startX + offsetX*position-offsetX*delay;
    }

    private int calcY(int position){
        int delay,startY=215,offsetY=75;
        if((position>=0&&position<=2)||(position>=11&&position<=16))
            delay=0;
        else if(position==3||position==10||position==17)
            delay=1;
        else
            delay=2;
        return startY -offsetY*delay;
    }

    public void setModelPrinter(ModelPrinter modelPrinter) {
        this.modelPrinter = modelPrinter;
    }

    public void notifyChangement(String textToShow, String nickname){
        notificationLabel.setText("Player " + nickname + " " + textToShow);
        notificationLabel.setVisible(true);
        changementButton.setDisable(false);
        changementButton.setVisible(true);
        changementButton.setOpacity(1);
        changementButton.setCursor(Cursor.HAND);
        lastChangedNickname=nickname;
    }

    public void printChangedBoard(ActionEvent actionEvent) {
        int numToShow=-1;
        for(PersonalBoardPrinter p: modelPrinter.getPersonalBoards()){
            if(p.getOwnerNickname().equals(lastChangedNickname)){
                numToShow=p.getPlayerNumber();
            }
        }
        if(numToShow==1||numToShow==0)
            viewPlayer1(actionEvent);
        else if(numToShow==2)
            viewPlayer2(actionEvent);
        else if(numToShow==3)
            viewPlayer3(actionEvent);
        else if(numToShow==4)
            viewPlayer4(actionEvent);
        else
            System.err.println("Unknown number");
    }

    public Label getNotificationLabel() {
        return notificationLabel;
    }

    public void endTurn(ActionEvent actionEvent) {
        Gson gson = new Gson();
        EndTurnRequestMessage message = new EndTurnRequestMessage();
        message.setSenderNickname(GUI.getClientNickname());
        message.setMessageType("EndTurnRequest");
        PrinterSingleton.getPrinterSingleton().sendMessage(gson.toJson(message));
    }

    public void baseObtainsCoin(ActionEvent actionEvent) {
        if( resourceBaseProduction[2]== null ) {
            baseObtainCoinButton.setOpacity(0.3);
            resourceBaseProduction[2] = ResourceType.COINS;
        } else if( resourceBaseProduction[2] == ResourceType.COINS ){
            baseObtainCoinButton.setOpacity(0);
            resourceBaseProduction[2] = null;
        }
        checkConfirmButton();
    }

    public void baseObtainsStone(ActionEvent actionEvent) {
        if( resourceBaseProduction[2]== null ) {
            baseObtainStoneButton.setOpacity(0.3);
            resourceBaseProduction[2] = ResourceType.STONES;
        } else if( resourceBaseProduction[2] == ResourceType.STONES ){
            baseObtainStoneButton.setOpacity(0);
            resourceBaseProduction[2] = null;
        }
        checkConfirmButton();
    }

    public void baseObtainsShield(ActionEvent actionEvent) {
        if( resourceBaseProduction[2]== null ) {
            baseObtainShieldButton.setOpacity(0.3);
            resourceBaseProduction[2] = ResourceType.SHIELDS;
        } else if( resourceBaseProduction[2] == ResourceType.SHIELDS ){
            baseObtainShieldButton.setOpacity(0);
            resourceBaseProduction[2] = null;
        }
        checkConfirmButton();
    }

    public void baseObtainsServant(ActionEvent actionEvent) {
        if( resourceBaseProduction[2]== null ) {
            baseObtainServantButton.setOpacity(0.3);
            resourceBaseProduction[2] = ResourceType.SERVANTS;
        } else if( resourceBaseProduction[2] == ResourceType.SERVANTS ){
            baseObtainServantButton.setOpacity(0);
            resourceBaseProduction[2] = null;
        }
        checkConfirmButton();
    }

    public void baseSetPayRes1(ActionEvent actionEvent) {
        baseSetPayRes(1, 1, 0, basePayButton1);
        checkConfirmButton();
    }
    public void baseSetPayRes2(ActionEvent actionEvent) {
        baseSetPayRes(2, 1, 1, basePayButton2);
        checkConfirmButton();
    }
    public void baseSetPayRes3(ActionEvent actionEvent) {
        baseSetPayRes(2, 2, 2, basePayButton3);
        checkConfirmButton();
    }
    public void baseSetPayRes4(ActionEvent actionEvent) {
        baseSetPayRes(3, 1, 3, basePayButton4);
        checkConfirmButton();
    }
    public void baseSetPayRes5(ActionEvent actionEvent) {
        baseSetPayRes(3, 2, 4, basePayButton5);
        checkConfirmButton();
    }
    public void baseSetPayRes6(ActionEvent actionEvent) {
        baseSetPayRes(3, 3, 5, basePayButton6);
        checkConfirmButton();
    }

    private void baseSetPayRes(int level, int levelPos, int button, Button resButton ){
        ResourceType res = getResFromWarehouse(level, levelPos);
        if( !clicked[button] &&( resourceBaseProduction[0]==null || resourceBaseProduction[1]==null ) ) {
            clicked[button] = true;
            if( res != null ){
                if (resourceBaseProduction[0] == null)
                    resourceBaseProduction[0] = res;
                else if (resourceBaseProduction[1] == null)
                    resourceBaseProduction[1] = res;
                resButton.setOpacity(0.3);
            }
        } else if( clicked[button] ){
            clicked[button] = false;
            resButton.setOpacity(0);
            if( resourceBaseProduction[0]==res )
                resourceBaseProduction[0] = null;
            else if( resourceBaseProduction[1]==res )
                resourceBaseProduction[1] = null;
        }
    }

    private void checkConfirmButton(){
        if( fromPersonalBoard &&( resourceBaseProduction[0]==null || resourceBaseProduction[1]==null || resourceBaseProduction[2]==null)){
            production.setDisable(true);
            production.setCursor(Cursor.DEFAULT);
        } else if ( fromPersonalBoard ){
            production.setDisable(false);
            production.setCursor(Cursor.HAND);
        } else if( whichDevCardSlot[0] || whichDevCardSlot[1] || whichDevCardSlot[2] ){
            production.setDisable(false);
            production.setCursor(Cursor.HAND);
        }
    }

    private ResourceType getResFromWarehouse(int level, int levelPos){
        for( PersonalBoardPrinter p: modelPrinter.getPersonalBoards() ) {
            if( p.getOwnerNickname().equals(GUI.getClientNickname())) {
                if( p.getWareHouseDepot().getLevel(level).getCurrNumResources()>=levelPos )
                    return p.getWareHouseDepot().getLevel(level).getResourceType();
            }
        }
        return null;
    }



}
