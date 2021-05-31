package it.polimi.ingsw.View.GUIControllers;

import com.google.gson.Gson;
import it.polimi.ingsw.Controller.Messages.InsertResourceMessage;
import it.polimi.ingsw.Controller.Messages.SwitchLevelMessage;
import it.polimi.ingsw.Controller.Messages.TakeResourceFromMarketMessage;
import it.polimi.ingsw.ResourceType;
import it.polimi.ingsw.View.GUI;
import it.polimi.ingsw.View.ModelPrinter;
import it.polimi.ingsw.View.PrinterSingleton;
import it.polimi.ingsw.View.Printers.PersonalBoardPrinter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class MarketSceneController implements Initializable {
    private final ArrayList<Integer> selectedLevels = new ArrayList<>();
    private final Map<ResourceType, String> resourceImagesMap = new HashMap<>();
    private final Map<String, String> marbleImagesMap = new HashMap<>();
    private final ArrayList<ResourceType> temporaryResources = new ArrayList<>();
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
    @FXML
    private Button first_level_button;
    @FXML
    private Button second_level_button;
    @FXML
    private Button third_level_button;
    @FXML
    private Button switch_levels_button;
    @FXML
    private Label notificationLabel;
    @FXML
    private Button changementButton;
    private String lastChangedNickname;
    @FXML
    private ImageView tempRes1;
    @FXML
    private ImageView tempRes2;
    @FXML
    private ImageView tempRes3;
    @FXML
    private ImageView tempRes4;
    @FXML
    private Button buttonRes1;
    @FXML
    private Button buttonRes2;
    @FXML
    private Button buttonRes3;
    @FXML
    private Button buttonRes4;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        GUI.setMarketSceneController(this);
        resourceImagesMap.put(ResourceType.STONES, "Images/stone.png");
        resourceImagesMap.put(ResourceType.COINS, "Images/coin.png");
        resourceImagesMap.put(ResourceType.SERVANTS, "Images/servant.png");
        resourceImagesMap.put(ResourceType.SHIELDS, "Images/shield.png");
        marketBoard.setDisable(true);
        marbleImagesMap.put("red", "Images/red_marble.png");
        marbleImagesMap.put("purple", "Images/purple_marble.png");
        marbleImagesMap.put("white", "Images/white_marble.png");
        marbleImagesMap.put("yellow", "Images/yellow_marble.png");
        marbleImagesMap.put("blue", "Images/lightblue_marble.png");
        marbleImagesMap.put("grey", "Images/grey_marble.png");
    }

    public void viewPlayer1(ActionEvent actionEvent) {
        try {
            GUI.setRoot("personalBoard_scene");
        } catch (IOException e) {
            e.printStackTrace();
        }
        GUI.setStatus("Main");
        GUI.getMainSceneController().setModelPrinter(modelPrinter);
        GUI.getMainSceneController().viewPlayer1(actionEvent);
    }

    public void viewPlayer2(ActionEvent actionEvent) {
        try {
            GUI.setRoot("personalBoard_scene");
        } catch (IOException e) {
            e.printStackTrace();
        }
        GUI.setStatus("Main");
        GUI.getMainSceneController().setModelPrinter(modelPrinter);
        GUI.getMainSceneController().viewPlayer2(actionEvent);
    }

    public void viewPlayer3(ActionEvent actionEvent) {
        try {
            GUI.setRoot("personalBoard_scene");
        } catch (IOException e) {
            e.printStackTrace();
        }
        GUI.setStatus("Main");
        GUI.getMainSceneController().setModelPrinter(modelPrinter);
        GUI.getMainSceneController().viewPlayer3(actionEvent);
    }

    public void viewPlayer4(ActionEvent actionEvent) {
        try {
            GUI.setRoot("personalBoard_scene");
        } catch (IOException e) {
            e.printStackTrace();
        }
        GUI.setStatus("Main");
        GUI.getMainSceneController().setModelPrinter(modelPrinter);
        GUI.getMainSceneController().viewPlayer4(actionEvent);

    }

    public void viewMarketBoard(ActionEvent actionEvent) {
    }

    public void viewDeckGrid(ActionEvent actionEvent) {
        try {
            GUI.setRoot("deckgrid_scene");
            GUI.setStatus("Deck");
            GUI.getDeckgridSceneController().printScene(modelPrinter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printScene(ModelPrinter modelPrinter) {
        tempRes1.setVisible(false);
        tempRes2.setVisible(false);
        tempRes3.setVisible(false);
        tempRes4.setVisible(false);
        selectedLevels.clear();
        GUI.setStatus("Market");
        changementButton.setDisable(true);
        changementButton.setVisible(false);
        changementButton.setCursor(Cursor.DEFAULT);
        notificationLabel.setVisible(false);
        first_level_button.setDisable(true);
        second_level_button.setDisable(true);
        third_level_button.setDisable(true);
        first_level_button.setOpacity(0);
        second_level_button.setOpacity(0);
        third_level_button.setOpacity(0);
        first_level_button.setCursor(Cursor.DEFAULT);
        second_level_button.setCursor(Cursor.DEFAULT);
        third_level_button.setCursor(Cursor.DEFAULT);
        switch_levels_button.setText("SWITCH TWO LEVELS");
        buttonRes1.setDisable(true);
        buttonRes2.setDisable(true);
        buttonRes3.setDisable(true);
        buttonRes4.setDisable(true);
        this.modelPrinter = modelPrinter;
        printPlayerButtons();
        for (PersonalBoardPrinter p : modelPrinter.getPersonalBoards()) {
            if (p.getOwnerNickname().equals(GUI.getClientNickname()))
                printWarehouse(p);
        }
        printMarbleGrid();
        printTemporaryResources();
    }

    private void printMarbleGrid() {
        for (Node n : marbles_grid_pane.getChildren()) {
            int x = GridPane.getRowIndex(n);
            int y = GridPane.getColumnIndex(n);
            ImageView im = (ImageView) n;
            im.setImage(new Image(marbleImagesMap.get(modelPrinter.getMarketBoardPrinter().getMarbleGrid()[x][y])));
        }
        marbleOut.setImage(new Image(marbleImagesMap.get(modelPrinter.getMarketBoardPrinter().getMarbleOut())));
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
            } else {
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
            } else if (p.getWareHouseDepot().getLevel(3).getCurrNumResources() == 2) {
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

    public void switchFirstLevel(ActionEvent actionEvent) {
        addSelectedLevel(1, first_level_button);
    }

    public void switchSecondLevel(ActionEvent actionEvent) {
        addSelectedLevel(2, second_level_button);
    }

    public void switchThirdLevel(ActionEvent actionEvent) {
        addSelectedLevel(3, third_level_button);
    }

    private void addSelectedLevel(int level, Button button) {
        if (selectedLevels.size() == 2) {
            if (selectedLevels.get(0) == level) {
                selectedLevels.remove(0);
                button.setOpacity(0);
                switch_levels_button.setDisable(true);
            } else if (selectedLevels.get(1) == level) {
                selectedLevels.remove(1);
                button.setOpacity(0);
                switch_levels_button.setDisable(true);
            }
        } else if (selectedLevels.size() == 1) {
            if (selectedLevels.get(0) == level) {
                selectedLevels.remove(0);
                button.setOpacity(0);
            } else {
                selectedLevels.add(level);
                button.setOpacity(0.3);
                switch_levels_button.setDisable(false);
            }
        } else if (selectedLevels.isEmpty()) {
            selectedLevels.add(level);
            button.setOpacity(0.3);
        } else {
            System.err.println("SwitchLevelsGUIBug " + level);
        }
    }

    public void switchLevels(ActionEvent actionEvent) {
        if (switch_levels_button.getText().equals("SWITCH TWO LEVELS")) {
            first_level_button.setDisable(false);
            second_level_button.setDisable(false);
            third_level_button.setDisable(false);
            first_level_button.setCursor(Cursor.HAND);
            second_level_button.setCursor(Cursor.HAND);
            third_level_button.setCursor(Cursor.HAND);
            switch_levels_button.setText("CONFIRM");
            switch_levels_button.setDisable(true);
        } else {
            //DEVO MANDARE IL MESSAGGIO E RISETTARE IL TASTO
            Gson gson = new Gson();
            int[] levelToSwitch = new int[2];
            SwitchLevelMessage message = new SwitchLevelMessage();
            message.setMessageType("SwitchLevels");
            message.setSenderNickname(GUI.getClientNickname());
            levelToSwitch[0] = selectedLevels.get(0);
            levelToSwitch[1] = selectedLevels.get(1);
            message.setLevelsToSwitch(levelToSwitch);
            PrinterSingleton.getPrinterSingleton().sendMessage(gson.toJson(message));
        }
    }

    public void firstBottomIndex(ActionEvent actionEvent) {
        Gson gson = new Gson();
        TakeResourceFromMarketMessage message = new TakeResourceFromMarketMessage();
        message.setMessageType("TakeResourcesFromMarket");
        message.setSenderNickname(GUI.getClientNickname());
        int[] marketindex = {3, 0};
        message.setMarketIndex(marketindex);
        PrinterSingleton.getPrinterSingleton().sendMessage(gson.toJson(message));
    }

    public void secondBottomIndex(ActionEvent actionEvent) {
        Gson gson = new Gson();
        TakeResourceFromMarketMessage message = new TakeResourceFromMarketMessage();
        message.setMessageType("TakeResourcesFromMarket");
        message.setSenderNickname(GUI.getClientNickname());
        int[] marketindex = {3, 1};
        message.setMarketIndex(marketindex);
        PrinterSingleton.getPrinterSingleton().sendMessage(gson.toJson(message));
    }

    public void thirdBottomIndex(ActionEvent actionEvent) {
        Gson gson = new Gson();
        TakeResourceFromMarketMessage message = new TakeResourceFromMarketMessage();
        message.setMessageType("TakeResourcesFromMarket");
        message.setSenderNickname(GUI.getClientNickname());
        int[] marketindex = {3, 2};
        message.setMarketIndex(marketindex);
        PrinterSingleton.getPrinterSingleton().sendMessage(gson.toJson(message));
    }

    public void fourthBottomIndex(ActionEvent actionEvent) {
        Gson gson = new Gson();
        TakeResourceFromMarketMessage message = new TakeResourceFromMarketMessage();
        message.setMessageType("TakeResourcesFromMarket");
        message.setSenderNickname(GUI.getClientNickname());
        int[] marketindex = {3, 3};
        message.setMarketIndex(marketindex);
        PrinterSingleton.getPrinterSingleton().sendMessage(gson.toJson(message));
    }

    public void firstRightIndex(ActionEvent actionEvent) {
        Gson gson = new Gson();
        TakeResourceFromMarketMessage message = new TakeResourceFromMarketMessage();
        message.setMessageType("TakeResourcesFromMarket");
        message.setSenderNickname(GUI.getClientNickname());
        int[] marketindex = {0, 4};
        message.setMarketIndex(marketindex);
        PrinterSingleton.getPrinterSingleton().sendMessage(gson.toJson(message));
    }

    public void secondRightIndex(ActionEvent actionEvent) {
        Gson gson = new Gson();
        TakeResourceFromMarketMessage message = new TakeResourceFromMarketMessage();
        message.setMessageType("TakeResourcesFromMarket");
        message.setSenderNickname(GUI.getClientNickname());
        int[] marketindex = {1, 4};
        message.setMarketIndex(marketindex);
        PrinterSingleton.getPrinterSingleton().sendMessage(gson.toJson(message));
    }

    public void thirdRightIndex(ActionEvent actionEvent) {
        Gson gson = new Gson();
        TakeResourceFromMarketMessage message = new TakeResourceFromMarketMessage();
        message.setMessageType("TakeResourcesFromMarket");
        message.setSenderNickname(GUI.getClientNickname());
        int[] marketindex = {2, 4};
        message.setMarketIndex(marketindex);
        PrinterSingleton.getPrinterSingleton().sendMessage(gson.toJson(message));
    }

    public void insertRes1(ActionEvent actionEvent) {
        Gson gson = new Gson();
        InsertResourceMessage message = new InsertResourceMessage();
        message.setMessageType("InsertResourcesIntoWarehouse");
        message.setSenderNickname(GUI.getClientNickname());
        message.setResourceToInsert(temporaryResources.get(0));
        message.setQuantityToInsert(1);
        message.setIntoExtraDeposit(false);
        PrinterSingleton.getPrinterSingleton().sendMessage(gson.toJson(message));
    }

    public void insertRes2(ActionEvent actionEvent) {
        Gson gson = new Gson();
        InsertResourceMessage message = new InsertResourceMessage();
        message.setMessageType("InsertResourcesIntoWarehouse");
        message.setSenderNickname(GUI.getClientNickname());
        message.setResourceToInsert(temporaryResources.get(1));
        message.setQuantityToInsert(1);
        message.setIntoExtraDeposit(false);
        PrinterSingleton.getPrinterSingleton().sendMessage(gson.toJson(message));
    }

    public void insertRes3(ActionEvent actionEvent) {
        Gson gson = new Gson();
        InsertResourceMessage message = new InsertResourceMessage();
        message.setMessageType("InsertResourcesIntoWarehouse");
        message.setSenderNickname(GUI.getClientNickname());
        message.setResourceToInsert(temporaryResources.get(2));
        message.setQuantityToInsert(1);
        message.setIntoExtraDeposit(false);
        PrinterSingleton.getPrinterSingleton().sendMessage(gson.toJson(message));
    }

    public void insertRes4(ActionEvent actionEvent) {
        Gson gson = new Gson();
        InsertResourceMessage message = new InsertResourceMessage();
        message.setMessageType("InsertResourcesIntoWarehouse");
        message.setSenderNickname(GUI.getClientNickname());
        message.setResourceToInsert(temporaryResources.get(3));
        message.setQuantityToInsert(1);
        message.setIntoExtraDeposit(false);
        PrinterSingleton.getPrinterSingleton().sendMessage(gson.toJson(message));
    }

    public void notifyChangement(String textToShow, String nickname) {
        notificationLabel.setText("Player " + nickname + " " + textToShow);
        notificationLabel.setVisible(true);
        changementButton.setDisable(false);
        changementButton.setVisible(true);
        changementButton.setOpacity(1);
        changementButton.setCursor(Cursor.HAND);
        lastChangedNickname = nickname;
    }

    public void printChangedBoard(ActionEvent actionEvent) {
        int numToShow = -1;
        for (PersonalBoardPrinter p : modelPrinter.getPersonalBoards()) {
            if (p.getOwnerNickname().equals(lastChangedNickname)) {
                numToShow = p.getPlayerNumber();
            }
        }
        if (numToShow == 1 || numToShow == 0)
            viewPlayer1(actionEvent);
        else if (numToShow == 2)
            viewPlayer2(actionEvent);
        else if (numToShow == 3)
            viewPlayer3(actionEvent);
        else if (numToShow == 4)
            viewPlayer4(actionEvent);
        else
            System.err.println("Unknown number");
    }

    public Label getNotificationLabel() {
        return notificationLabel;
    }

    private void printTemporaryResources() {
        temporaryResources.clear();
        for (ResourceType r : modelPrinter.getMarketBoardPrinter().getTemporaryResources().keySet()) {
            addRes(r, modelPrinter.getMarketBoardPrinter().getTemporaryResources().get(r));
        }
        printRes();
    }

    private void addRes(ResourceType r, int number) {
        for (; number > 0; number--) {
            temporaryResources.add(r);
        }
    }

    private void printRes() {
        int counter = 0;
        for (ResourceType r : temporaryResources) {
            if (counter == 0) {
                tempRes1.setImage(new Image(resourceImagesMap.get(r)));
                tempRes1.setVisible(true);
                buttonRes1.setDisable(false);
            }
            else if(counter==1){
                tempRes2.setImage(new Image(resourceImagesMap.get(r)));
                tempRes2.setVisible(true);
                buttonRes2.setDisable(false);
            }
            else if(counter==2){
                tempRes3.setImage(new Image(resourceImagesMap.get(r)));
                tempRes3.setVisible(true);
                buttonRes3.setDisable(false);
            }
            else if(counter==3){
                tempRes4.setImage(new Image(resourceImagesMap.get(r)));
                tempRes4.setVisible(true);
                buttonRes4.setDisable(false);
            }
            counter++;
        }
    }
}
