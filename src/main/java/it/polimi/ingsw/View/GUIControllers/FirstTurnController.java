package it.polimi.ingsw.View.GUIControllers;

import com.google.gson.Gson;
import it.polimi.ingsw.Controller.Messages.DistributionFourthMessage;
import it.polimi.ingsw.Controller.Messages.DistributionSecondThirdMessage;
import it.polimi.ingsw.Controller.Messages.EndTurnRequestMessage;
import it.polimi.ingsw.Controller.Messages.LeaderCardSelectionMessage;
import it.polimi.ingsw.ResourceType;
import it.polimi.ingsw.View.GUI;
import it.polimi.ingsw.View.ModelPrinter;
import it.polimi.ingsw.View.PrinterSingleton;
import it.polimi.ingsw.View.Printers.LeaderCardsPrinter;
import it.polimi.ingsw.View.Printers.PersonalBoardPrinter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class FirstTurnController implements Initializable {
    @FXML
    private Label selectResLabel;
    @FXML
    private Label waitLabel;
    @FXML
    private Label selectCardsLabel;
    @FXML
    private ImageView firstResImage;
    @FXML
    private ImageView secondResImage;
    @FXML
    private ImageView thirdResImage;
    @FXML
    private ImageView fourthResImage;
    @FXML
    private Button firstResButton;
    @FXML
    private Button secondResButton;
    @FXML
    private Button thirdResButton;
    @FXML
    private Button fourthResButton;
    @FXML
    private Button confirmButton;
    @FXML
    private ImageView firstLeaderImage;
    @FXML
    private ImageView secondLeaderImage;
    @FXML
    private ImageView thirdLeaderImage;
    @FXML
    private ImageView fourthLeaderImage;
    @FXML
    private Button firstLeaderButton;
    @FXML
    private Button secondLeaderButton;
    @FXML
    private Button thirdLeaderButton;
    @FXML
    private Button fourthLeaderButton;
    @FXML
    private Label errorLabel;
    private final ArrayList<String> cardIndex = new ArrayList<>();
    private final ArrayList<Integer> leaderCardsSelected = new ArrayList<>();
    private int playerNumber;
    private final ArrayList<ResourceType> resourcesSelected = new ArrayList<>();
    private String nickname;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Stage s = new Stage();
        s.setFullScreen(true);
        GUI.setFirstTurnController(this);
        cardIndex.add("Images/LeaderCardImages/leader1.png");
        cardIndex.add("Images/LeaderCardImages/leader2.png");
        cardIndex.add("Images/LeaderCardImages/leader3.png");
        cardIndex.add("Images/LeaderCardImages/leader4.png");
        cardIndex.add("Images/LeaderCardImages/leader5.png");
        cardIndex.add("Images/LeaderCardImages/leader6.png");
        cardIndex.add("Images/LeaderCardImages/leader7.png");
        cardIndex.add("Images/LeaderCardImages/leader8.png");
        cardIndex.add("Images/LeaderCardImages/leader9.png");
        cardIndex.add("Images/LeaderCardImages/leader10.png");
        cardIndex.add("Images/LeaderCardImages/leader11.png");
        cardIndex.add("Images/LeaderCardImages/leader12.png");
        cardIndex.add("Images/LeaderCardImages/leader13.png");
        cardIndex.add("Images/LeaderCardImages/leader14.png");
        cardIndex.add("Images/LeaderCardImages/leader15.png");
        cardIndex.add("Images/LeaderCardImages/leader16.png");

    }

    public void printScene(ModelPrinter modelPrinter, String clientNickname){
        nickname=clientNickname;
        printLeadersImages(modelPrinter,clientNickname);
        for(PersonalBoardPrinter p: modelPrinter.getPersonalBoards()){
            if(p.getOwnerNickname().equals(clientNickname)){
                playerNumber=p.getPlayerNumber();
                if(p.getPlayerNumber()==1){
                    printFirstPlayer(modelPrinter, clientNickname);
                }
                else if(p.getPlayerNumber()==4){
                    printFourthPlayer(modelPrinter,clientNickname);
                }
                else{
                    printSecondThirdPlayer(modelPrinter, clientNickname);
                }
            }
        }
    }

    public void selectFirst(ActionEvent actionEvent) {
        addSelectedLeader(0,firstLeaderButton);
    }

    public void selectSecond(ActionEvent actionEvent) {
        addSelectedLeader(1,secondLeaderButton);
    }

    public void selectThird(ActionEvent actionEvent) {
        addSelectedLeader(2,thirdLeaderButton);
    }

    public void selectFourth(ActionEvent actionEvent) {
        addSelectedLeader(3,fourthLeaderButton);
    }

    public void selectResAndLeaders(ActionEvent actionEvent) {
        if(playerNumber==1)
            generateMessageFirst();
        else if(playerNumber==4)
            generateMessageFourth();
        else
            generateMessageSecondThird();
    }

    public void addStones(ActionEvent actionEvent) {
        if(playerNumber==2||playerNumber==3)
            oneResDistribution(ResourceType.STONES,firstResButton);
        else
            twoResDistribution(ResourceType.STONES,firstResButton);
    }

    public void addCoins(ActionEvent actionEvent) {
        if(playerNumber==2||playerNumber==3)
            oneResDistribution(ResourceType.COINS,secondResButton);
        else
            twoResDistribution(ResourceType.COINS,secondResButton);
    }

    public void addShields(ActionEvent actionEvent) {
        if(playerNumber==2||playerNumber==3)
            oneResDistribution(ResourceType.SHIELDS,thirdResButton);
        else
            twoResDistribution(ResourceType.SHIELDS,thirdResButton);
    }

    public void addServants(ActionEvent actionEvent) {
        if(playerNumber==2||playerNumber==3)
            oneResDistribution(ResourceType.SERVANTS,fourthResButton);
        else
            twoResDistribution(ResourceType.SERVANTS,fourthResButton);
    }

    public void printFirstPlayer(ModelPrinter modelPrinter, String clientNickname){
        waitLabel.setVisible(false);
        selectResLabel.setVisible(false);
        firstResButton.setDisable(true);
        secondResButton.setDisable(true);
        thirdResButton.setDisable(true);
        fourthResButton.setDisable(true);
    }

    public void printSecondThirdPlayer(ModelPrinter modelPrinter, String clientNickname){
        confirmButton.setDisable(true);
        selectResLabel.setText("Select one resource: ");
        firstResButton.setDisable(false);
        secondResButton.setDisable(false);
        thirdResButton.setDisable(false);
        fourthResButton.setDisable(false);
        printResImages();
    }

    public void printFourthPlayer(ModelPrinter modelPrinter, String clientNickname){
        confirmButton.setDisable(true);
        selectResLabel.setText("Select two resources:");
        firstResButton.setDisable(false);
        secondResButton.setDisable(false);
        thirdResButton.setDisable(false);
        fourthResButton.setDisable(false);
        printResImages();
    }

    public void printResImages(){
        firstResImage.setImage(new Image("Images/stone.png"));
        secondResImage.setImage(new Image("Images/coin.png"));
        thirdResImage.setImage(new Image("Images/shield.png"));
        fourthResImage.setImage(new Image("Images/servant.png"));
    }

    public void printLeadersImages(ModelPrinter modelPrinter, String clientNickname){
        for(LeaderCardsPrinter l: modelPrinter.getLeaderCardsPrinters()){
            if(l.getOwnerNickname().equals(clientNickname)){
                firstLeaderImage.setImage(new Image(cardIndex.get(l.getChoosableLeaderCards()[0]-1)));
                secondLeaderImage.setImage(new Image(cardIndex.get(l.getChoosableLeaderCards()[1]-1)));
                thirdLeaderImage.setImage(new Image(cardIndex.get(l.getChoosableLeaderCards()[2]-1)));
                fourthLeaderImage.setImage(new Image(cardIndex.get(l.getChoosableLeaderCards()[3]-1)));
            }
        }
    }

    private void addSelectedLeader(int position, Button button){
        if(leaderCardsSelected.size()==2){
            if(leaderCardsSelected.get(0)==position){
                leaderCardsSelected.remove(0);
                button.setOpacity(0);
            }
            else if(leaderCardsSelected.get(1)==position){
                leaderCardsSelected.remove(1);
                button.setOpacity(0);
            }
        }
        else if(leaderCardsSelected.size()==1){
            if(leaderCardsSelected.get(0)==position){
                leaderCardsSelected.remove(0);
                button.setOpacity(0);
            }
            else{
                leaderCardsSelected.add(position);
                button.setOpacity(0.3);
            }
        }
        else if(leaderCardsSelected.isEmpty()){
            leaderCardsSelected.add(position);
            button.setOpacity(0.3);
        }
        else{
            System.err.println("FirstTurnControllerBug " + position);
        }
    }

    private void oneResDistribution(ResourceType resourceType, Button button){
        if(resourcesSelected.size()==1){
            if( resourceType==resourcesSelected.get(0)) {
                resourcesSelected.remove(0);
                button.setOpacity(0);
            }
        }
        else if(resourcesSelected.isEmpty()){
            resourcesSelected.add(resourceType);
            button.setOpacity(0.3);
            button.setText("x1");
        }
    }

    private void twoResDistribution(ResourceType resourceType, Button button){
        if(resourcesSelected.size()==2){
            if(resourcesSelected.get(0)==resourcesSelected.get(1) && resourcesSelected.get(0)==resourceType){
                resourcesSelected.remove(0);
                resourcesSelected.remove(0);
                button.setOpacity(0);
            }
            else if(resourcesSelected.get(0)==resourceType){
                resourcesSelected.remove(0);
                button.setOpacity(0);
            }
            else if(resourcesSelected.get(1)==resourceType){
                resourcesSelected.remove(1);
                button.setOpacity(0);
            }
        }
        else if(resourcesSelected.size()==1){
            if(resourcesSelected.get(0)==resourceType){
                button.setText("x2");
                resourcesSelected.add(resourceType);
            }
            else{
                resourcesSelected.add(resourceType);
                button.setText("x1");
                button.setOpacity(0.3);
            }
        }
        else if(resourcesSelected.isEmpty()){
            resourcesSelected.add(resourceType);
            button.setText("x1");
            button.setOpacity(0.3);
        }
    }

    public Button getConfirmButton() {
        return confirmButton;
    }

    private void generateMessageFirst(){
        if(leaderCardsSelected.size()==2){
            sendLeaderSelection();
            sendEndTurn();
        }
        else{
            errorLabel.setText("Select two leader cards before confirming.");
            errorLabel.setVisible(true);
        }
    }

    private void generateMessageSecondThird(){
        if(leaderCardsSelected.size()==2&&resourcesSelected.size()==1){
            Gson gson = new Gson();
            sendLeaderSelection();
            DistributionSecondThirdMessage message = new DistributionSecondThirdMessage();
            message.setSenderNickname(nickname);
            message.setResourceToDistribute(resourcesSelected.get(0));
            message.setMessageType("DistributionSecondThird");
            PrinterSingleton.getPrinterSingleton().sendMessage(gson.toJson(message));
            sendEndTurn();
        }
        else if(leaderCardsSelected.size()!=2&&resourcesSelected.isEmpty()){
            errorLabel.setText("Select two leader cards and one resource before confirming.");
            errorLabel.setVisible(true);
        }
        else if(leaderCardsSelected.size()!=2){
            errorLabel.setText("Select two leader cards before confirming.");
            errorLabel.setVisible(true);
        }
        else{
            errorLabel.setText("Select one resource before confirming.");
            errorLabel.setVisible(true);
        }
    }

    private void generateMessageFourth(){
        if(leaderCardsSelected.size()==2&&resourcesSelected.size()==2){
            Gson gson = new Gson();
            sendLeaderSelection();
            DistributionFourthMessage mex = new DistributionFourthMessage();
            mex.setMessageType("DistributionFourth");
            mex.setSenderNickname(nickname);
            mex.setResourceToDistribute(resourcesSelected.get(0));
            mex.setSecondResourceToDistribute(resourcesSelected.get(1));
            PrinterSingleton.getPrinterSingleton().sendMessage(gson.toJson(mex));
            sendEndTurn();
        }
        else if(leaderCardsSelected.size()!=2&&resourcesSelected.size()!=2){
            errorLabel.setText("Select two leader cards and two resource before confirming.");
            errorLabel.setVisible(true);
        }
        else if(leaderCardsSelected.size()!=2){
            errorLabel.setText("Select two leader cards before confirming.");
            errorLabel.setVisible(true);
        }
        else{
            errorLabel.setText("Select two resources before confirming.");
            errorLabel.setVisible(true);
        }
    }

    private void sendLeaderSelection(){
        Gson gson = new Gson();
        LeaderCardSelectionMessage mex = new LeaderCardSelectionMessage();
        mex.setMessageType("LeaderCardSelection");
        mex.setSenderNickname(nickname);
        mex.setLeaderCardPosition1(leaderCardsSelected.get(0));
        mex.setLeaderCardPosition2(leaderCardsSelected.get(1));
        PrinterSingleton.getPrinterSingleton().sendMessage(gson.toJson(mex));
    }

    private void sendEndTurn(){
        Gson gson = new Gson();
        EndTurnRequestMessage message = new EndTurnRequestMessage();
        message.setSenderNickname(nickname);
        message.setMessageType("EndTurnRequest");
        PrinterSingleton.getPrinterSingleton().sendMessage(gson.toJson(message));
    }

    public void notifyYourTurn(){
        waitLabel.setVisible(false);
        confirmButton.setDisable(false);
    }
}
