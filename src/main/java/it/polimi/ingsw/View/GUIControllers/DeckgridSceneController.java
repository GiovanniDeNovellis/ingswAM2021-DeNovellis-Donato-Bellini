package it.polimi.ingsw.View.GUIControllers;

import com.google.gson.Gson;
import it.polimi.ingsw.Colour;
import it.polimi.ingsw.Controller.Messages.BuyDevelopmentCardMessage;
import it.polimi.ingsw.Controller.Server;
import it.polimi.ingsw.DevelopmentCard;
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
import javafx.scene.text.Font;

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
    private String lastChangedNickname;
    private final int[] payUsingExtraDeps = new int[]{0,0};
    private Colour cardColour = null;
    private int cardLevel = 4;
    private int cardSlot = 3;

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
    @FXML
    private Label notificationLabel;
    @FXML
    private Button changementButton;
    @FXML
    private ImageView res1dep1;
    @FXML
    private ImageView res2dep1;
    @FXML
    private ImageView res1dep2;
    @FXML
    private ImageView res2dep2;
    @FXML
    private Button confirmButtonDep1;
    @FXML
    private Button confirmButtonDep2;
    @FXML
    private Button button1dep1;
    @FXML
    private Button button2dep1;
    @FXML
    private Button button1dep2;
    @FXML
    private Button button2dep2;
    @FXML
    private Button cardButton00;
    @FXML
    private Button cardButton01;
    @FXML
    private Button cardButton02;
    @FXML
    private Button cardButton03;
    @FXML
    private Button cardButton10;
    @FXML
    private Button cardButton11;
    @FXML
    private Button cardButton12;
    @FXML
    private Button cardButton13;
    @FXML
    private Button cardButton20;
    @FXML
    private Button cardButton21;
    @FXML
    private Button cardButton22;
    @FXML
    private Button cardButton23;
    @FXML
    private Button buyButton;
    @FXML
    private ImageView imageSlot0;
    @FXML
    private ImageView imageSlot1;
    @FXML
    private ImageView imageSlot2;
    @FXML
    private Button buttonSlot0;
    @FXML
    private Button buttonSlot1;
    @FXML
    private Button buttonSlot2;


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
            GUI.setStatus("Market");
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

    public void printScene(ModelPrinter modelPrinter) {
        GUI.setStatus("Deck");
        GUI.getDeckgridSceneController().getNotificationLabel().setFont(Font.font(15.0));
        notificationLabel.setVisible(false);
        changementButton.setDisable(true);
        changementButton.setVisible(false);
        changementButton.setCursor(Cursor.DEFAULT);
        this.modelPrinter = modelPrinter;
        printCards();
        printPlayerButtons();
        printDeckgrid();
    }

    private void printCards(){
        buttonSlot0.setDisable(true);
        buttonSlot0.setCursor(Cursor.DEFAULT);
        buttonSlot1.setDisable(true);
        buttonSlot1.setCursor(Cursor.DEFAULT);
        buttonSlot2.setDisable(true);
        buttonSlot2.setCursor(Cursor.DEFAULT);
        String nickname = GUI.getClientNickname();
        for( PersonalBoardPrinter p: modelPrinter.getPersonalBoards() ){
            if( nickname.equals(p.getOwnerNickname())){
                DevelopmentCard[] cards = p.getDevelopmentCards();

                if(cards[0]!=null)
                    imageSlot0.setImage(new Image(developmentCardsIndex.get(cards[0].getNumber()-1)));

                if (cards[1]!=null)
                    imageSlot1.setImage(new Image(developmentCardsIndex.get(cards[1].getNumber()-1)));

                if (cards[2]!=null)
                    imageSlot2.setImage(new Image(developmentCardsIndex.get(cards[2].getNumber()-1)));

            }
        }
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
        for(Node n: devCardsPane.getChildren()) {
            if (n instanceof ImageView) {
                int x = GridPane.getColumnIndex(n);
                int y = GridPane.getRowIndex(n);
                ImageView im = (ImageView) n;
                im.setImage(new Image(developmentCardsIndex.get(modelPrinter.getDeckGridPrinter().getDeckgrid().readCard(cardLevelsMap.get(y), cardColoursMap.get(x)).getNumber() - 1)));
            }
        }
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

    public void confirmPayFromExtra1(ActionEvent actionEvent) {
    }

    public void confirmPayFromExtra2(ActionEvent actionEvent) {
    }

    private void buyCard(int cardLevel, Colour cardColour, Button cardButton){
        if( this.cardLevel==4 ){
            this.cardLevel = cardLevel;
            this.cardColour = cardColour;
            cardButton.setOpacity(0.3);
            if( cardSlot!=3 ) {
                buyButton.setDisable(false);
                buyButton.setCursor(Cursor.HAND);
            }
        }else if( this.cardLevel==cardLevel && this.cardColour==cardColour ){
            this.cardLevel=4;
            this.cardColour=null;
            cardButton.setOpacity(0);
            buyButton.setDisable(true);
            buyButton.setCursor(Cursor.DEFAULT);
        }
    }


    public void buyCard2G(ActionEvent actionEvent) {
        buyCard(2, Colour.GREEN, cardButton10);
    }

    public void buyCard3P(ActionEvent actionEvent) {
        buyCard(3, Colour.PURPLE, cardButton03);
    }

    public void buyCard3Y(ActionEvent actionEvent) {
        buyCard(3, Colour.YELLOW, cardButton02);
    }

    public void buyCard3B(ActionEvent actionEvent) {
        buyCard(3, Colour.BLUE, cardButton01);
    }

    public void buyCard2P(ActionEvent actionEvent) {
        buyCard(2, Colour.PURPLE, cardButton13);
    }

    public void buyCard2Y(ActionEvent actionEvent) {
        buyCard(2, Colour.YELLOW, cardButton12);
    }

    public void buyCard2B(ActionEvent actionEvent) {
        buyCard(2, Colour.BLUE, cardButton11);
    }

    public void buyCard1P(ActionEvent actionEvent) {
        buyCard(1, Colour.PURPLE, cardButton23);
    }

    public void buyCard1Y(ActionEvent actionEvent) {
        buyCard(1, Colour.YELLOW, cardButton22);
    }

    public void buyCard1B(ActionEvent actionEvent) {
        buyCard(1, Colour.BLUE, cardButton21);
    }

    public void buyCard1G(ActionEvent actionEvent) {
        buyCard(1, Colour.GREEN, cardButton20);
    }

    public void buyCard3G(ActionEvent actionEvent) {
        buyCard(3, Colour.GREEN, cardButton00);
    }

    public void selectRes1dep1(ActionEvent actionEvent) {

    }

    public void selectRes2dep1(ActionEvent actionEvent) {
    }

    public void selectRes1dep2(ActionEvent actionEvent) {
    }

    public void selectRes2dep2(ActionEvent actionEvent) {
    }

    public void tryBuyCard(ActionEvent actionEvent) {
        if(buyButton.getText().equals("Buy development card")) {
            buyButton.setDisable(true);
            buyButton.setText("Confirm");
            buyButton.setCursor(Cursor.DEFAULT);
            buttonSlot0.setDisable(false);
            buttonSlot0.setCursor(Cursor.HAND);
            buttonSlot1.setDisable(false);
            buttonSlot1.setCursor(Cursor.HAND);
            buttonSlot2.setDisable(false);
            buttonSlot2.setCursor(Cursor.HAND);
            for(Node n: devCardsPane.getChildren()) {
                if (n instanceof Button) {
                    Button cardButton = (Button) n;
                    cardButton.setCursor(Cursor.HAND);
                    cardButton.setDisable(false);
                }
            }
        } else if(buyButton.getText().equals("Confirm")) {
            String message = parseMessage();
            PrinterSingleton.getPrinterSingleton().sendMessage(message);
        }
    }

    private String parseMessage(){
        Gson gson = new Gson();
        BuyDevelopmentCardMessage m = new BuyDevelopmentCardMessage();
        m.setMessageType("BuyDevelopmentCard");
        m.setSenderNickname(GUI.getClientNickname());
        m.setColour(cardColour);
        m.setLevel(cardLevel);
        m.setSlot(cardSlot);
        m.setPayUsingExtraDeposit(payUsingExtraDeps[0],payUsingExtraDeps[1] );
        return gson.toJson(m);
    }

    private void selectSlot( int slot, Button slotButton ){
        if( cardSlot==slot ){
            slotButton.setOpacity(0.1);
            buyButton.setDisable(true);
            buyButton.setCursor(Cursor.DEFAULT);
            cardSlot=3;
        } else if( cardSlot==3 ){
            cardSlot=slot;
            slotButton.setOpacity(0.3);
            if( cardLevel!=4 ){
                buyButton.setDisable(false);
                buyButton.setCursor(Cursor.HAND);
            }
        }
    }

    public void selectSlot2(ActionEvent actionEvent) {
        selectSlot(2,buttonSlot2);
    }

    public void selectSlot1(ActionEvent actionEvent) {
        selectSlot(1,buttonSlot1);
    }

    public void selectSlot0(ActionEvent actionEvent) {
        selectSlot(0,buttonSlot0);
    }

    //TODO EXTRA DEPOSITS
}
