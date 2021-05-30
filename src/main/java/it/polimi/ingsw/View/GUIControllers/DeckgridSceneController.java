package it.polimi.ingsw.View.GUIControllers;

import it.polimi.ingsw.Colour;
import it.polimi.ingsw.View.GUI;
import it.polimi.ingsw.View.ModelPrinter;
import it.polimi.ingsw.View.Printers.PersonalBoardPrinter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class DeckgridSceneController implements Initializable {
    private ModelPrinter modelPrinter;
    private final ArrayList<String> developmentCardsIndex = new ArrayList<>();
    private final Map<Integer,Integer> cardLevelsMap = new HashMap<>();
    private final Map<Integer, Colour> cardColoursMap = new HashMap<>();
    @FXML
    private Button player1;
    @FXML
    private Button player2;
    @FXML
    private Button player3;
    @FXML
    private Button player4;
    @FXML
    private Button deckGrid;
    @FXML
    private GridPane devCardsPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        GUI.setDeckgridSceneController(this);
        deckGrid.setDisable(true);
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
        cardLevelsMap.put(0,3);
        cardLevelsMap.put(1,2);
        cardLevelsMap.put(2,1);
        cardColoursMap.put(0,Colour.GREEN);
        cardColoursMap.put(1,Colour.BLUE);
        cardColoursMap.put(2,Colour.YELLOW);
        cardColoursMap.put(3,Colour.PURPLE);
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

    public void viewPlayer1(ActionEvent actionEvent) {
        try {
            GUI.setRoot("personalBoard_scene");
        } catch (IOException e) {
            e.printStackTrace();
        }
        GUI.getMainSceneController().setModelPrinter(modelPrinter);
        GUI.getMainSceneController().viewPlayer1(actionEvent);
    }

    public void viewPlayer2(ActionEvent actionEvent) {
        try {
            GUI.setRoot("personalBoard_scene");
        } catch (IOException e) {
            e.printStackTrace();
        }
        GUI.getMainSceneController().setModelPrinter(modelPrinter);
        GUI.getMainSceneController().viewPlayer2(actionEvent);
    }

    public void viewPlayer3(ActionEvent actionEvent) {
        try {
            GUI.setRoot("personalBoard_scene");
        } catch (IOException e) {
            e.printStackTrace();
        }
        GUI.getMainSceneController().setModelPrinter(modelPrinter);
        GUI.getMainSceneController().viewPlayer3(actionEvent);
    }

    public void viewPlayer4(ActionEvent actionEvent) {
        try {
            GUI.setRoot("personalBoard_scene");
        } catch (IOException e) {
            e.printStackTrace();
        }
        GUI.getMainSceneController().setModelPrinter(modelPrinter);
        GUI.getMainSceneController().viewPlayer4(actionEvent);
    }

    public void printScene(ModelPrinter modelPrinter) {
        this.modelPrinter = modelPrinter;
        printPlayerButtons();
        printDeckgrid();
    }

    private void printPlayerButtons() {
        for (PersonalBoardPrinter p : modelPrinter.getPersonalBoards()) {
            if (p.getPlayerNumber() == 1) {
                player1.setText("Player 1: " + p.getOwnerNickname());
                player1.setDisable(false);
                player1.setOpacity(1);
                player1.setCursor(Cursor.HAND);
            } else if (p.getPlayerNumber() == 2) {
                player2.setText("Player 2: " + p.getOwnerNickname());
                player2.setDisable(false);
                player2.setOpacity(1);
                player2.setCursor(Cursor.HAND);
            } else if (p.getPlayerNumber() == 3) {
                player3.setText("Player 3: " + p.getOwnerNickname());
                player3.setDisable(false);
                player3.setOpacity(1);
                player3.setCursor(Cursor.HAND);
            } else {
                player4.setText("Player 4: " + p.getOwnerNickname());
                player4.setDisable(false);
                player4.setOpacity(1);
                player4.setCursor(Cursor.HAND);
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

    private void printDeckgrid(){
        for(Node n: devCardsPane.getChildren()){
            int x= GridPane.getColumnIndex(n);
            int y= GridPane.getRowIndex(n);
            ImageView im = (ImageView) n;
            im.setImage(new Image(developmentCardsIndex.get(modelPrinter.getDeckGridPrinter().getDeckgrid().readCard(cardLevelsMap.get(y),cardColoursMap.get(x)).getNumber()-1)));
        }
    }
}
