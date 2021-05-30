package it.polimi.ingsw.View.GUIControllers;

import it.polimi.ingsw.ResourceType;
import it.polimi.ingsw.View.GUI;
import it.polimi.ingsw.View.ModelPrinter;
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
    private ImageView devCardImage1;
    @FXML
    private ImageView devCardImage2;
    @FXML
    private ImageView devCardImage3;
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
    }

    public void baseProdTrue(ActionEvent actionEvent) {
    }

    public void selectDevCard0(ActionEvent actionEvent) {
    }

    public void selectDevCard1(ActionEvent actionEvent) {
    }

    public void selectDevCard2(ActionEvent actionEvent) {
    }

    public void activateLeaderAbility1(ActionEvent actionEvent) {
    }

    public void activateLeaderAbility2(ActionEvent actionEvent) {
    }

    public void printClientPlayer(ModelPrinter modelPrinter) {
        production.setVisible(true);
        production.setDisable(false);
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
    }

    public void printOtherPlayer(String playerNickname) {
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
        devCard1.setDisable(true);
        devCard2.setDisable(true);
        production.setDisable(true);
        production.setVisible(false);
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
        if(p.getDevelopmentCards()[0]!=null)
            devCardImage1.setImage(new Image(developmentCardsIndex.get(p.getDevelopmentCards()[0].getNumber()-1)));
        if(p.getDevelopmentCards()[1]!=null)
            devCardImage2.setImage(new Image(developmentCardsIndex.get(p.getDevelopmentCards()[1].getNumber()-1)));
        if(p.getDevelopmentCards()[2]!=null)
            devCardImage3.setImage(new Image(developmentCardsIndex.get(p.getDevelopmentCards()[2].getNumber()-1)));
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
}
