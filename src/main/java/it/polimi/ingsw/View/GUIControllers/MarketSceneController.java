package it.polimi.ingsw.View.GUIControllers;

import it.polimi.ingsw.ResourceType;
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
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class MarketSceneController implements Initializable {
    private final Map<ResourceType, String> resourceImagesMap = new HashMap<>();
    private final Map<String, String> marbleImagesMap = new HashMap<>();
    private ModelPrinter modelPrinter;
    @FXML
    private Button player1;
    @FXML
    private Button player2;
    @FXML
    private Button player3;
    @FXML
    private Button player4;
    @FXML
    private GridPane marbles_grid_pane;
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
    private Button marketBoard;
    @FXML
    private ImageView marbleOut;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        GUI.setMarketSceneController(this);
        resourceImagesMap.put(ResourceType.STONES, "Images/stone.png");
        resourceImagesMap.put(ResourceType.COINS, "Images/coin.png");
        resourceImagesMap.put(ResourceType.SERVANTS, "Images/servant.png");
        resourceImagesMap.put(ResourceType.SHIELDS, "Images/shield.png");
        marketBoard.setDisable(true);
        marbleImagesMap.put("red","Images/red_marble.png");
        marbleImagesMap.put("purple","Images/purple_marble.png");
        marbleImagesMap.put("white","Images/white_marble.png");
        marbleImagesMap.put("yellow","Images/yellow_marble.png");
        marbleImagesMap.put("blue","Images/lightblue_marble.png");
        marbleImagesMap.put("grey","Images/grey_marble.png");
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

    public void viewMarketBoard(ActionEvent actionEvent) {
    }

    public void viewDeckGrid(ActionEvent actionEvent) {
        try {
            GUI.setRoot("deckgrid_scene");
            GUI.getDeckgridSceneController().printScene(modelPrinter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printScene(ModelPrinter modelPrinter){
        this.modelPrinter=modelPrinter;
        printPlayerButtons();
        for(PersonalBoardPrinter p: modelPrinter.getPersonalBoards()){
            if(p.getOwnerNickname().equals(GUI.getClientNickname()))
                printWarehouse(p);
        }
        printMarbleGrid();
    }

    private void printMarbleGrid(){
        for(Node n: marbles_grid_pane.getChildren()){
            int x= GridPane.getRowIndex(n);
            int y= GridPane.getColumnIndex(n);
            ImageView im = (ImageView) n;
            im.setImage(new Image(marbleImagesMap.get(modelPrinter.getMarketBoardPrinter().getMarbleGrid()[x][y])));
        }
        marbleOut.setImage(new Image(marbleImagesMap.get(modelPrinter.getMarketBoardPrinter().getMarbleOut())));
    }

    private void printPlayerButtons(){
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

    private void printWarehouse(PersonalBoardPrinter p){
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

    public void firstRightIndex(ActionEvent actionEvent) {
    }

    public void switchFirstLevel(ActionEvent actionEvent) {
    }

    public void switchSecondLevel(ActionEvent actionEvent) {
    }

    public void switchThirdLevel(ActionEvent actionEvent) {
    }

    public void switchLevels(ActionEvent actionEvent) {
    }

    public void firstBottomIndex(ActionEvent actionEvent) {
    }

    public void secondBottomIndex(ActionEvent actionEvent) {
    }

    public void thirdBottomIndex(ActionEvent actionEvent) {
    }

    public void fourthBottomIndex(ActionEvent actionEvent) {
    }

    public void secondRightIndex(ActionEvent actionEvent) {
    }

    public void thirdRightIndex(ActionEvent actionEvent) {
    }

    public void insertRes1(ActionEvent actionEvent) {
    }

    public void insertRes2(ActionEvent actionEvent) {
    }

    public void insertRes3(ActionEvent actionEvent) {
    }

    public void insertRes4(ActionEvent actionEvent) {
    }
}
