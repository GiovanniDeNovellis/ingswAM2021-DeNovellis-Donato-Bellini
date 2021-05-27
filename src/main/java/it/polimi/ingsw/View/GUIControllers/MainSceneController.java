package it.polimi.ingsw.View.GUIControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.net.URL;
import java.util.ResourceBundle;

public class MainSceneController implements Initializable {
    @FXML
    ImageView personalBoard;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        personalBoard.setImage(new Image("Images/personalBoard.png"));
    }

    public void viewDeckGrid(ActionEvent actionEvent) {
    }

    public void viewMarketBoard(ActionEvent actionEvent) {
    }

    public void viewPlayersNumber(ActionEvent actionEvent) {
    }

    public void viewPlayer1(ActionEvent actionEvent) {
    }

    public void viewPlayer2(ActionEvent actionEvent) {
    }

    public void viewPlayer3(ActionEvent actionEvent) {
    }

    public void viewPlayer4(ActionEvent actionEvent) {
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
}
