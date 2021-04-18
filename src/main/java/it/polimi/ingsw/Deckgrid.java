package it.polimi.ingsw;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/** It's the grid of DevelopmentCards, organized by level and color. */
public class Deckgrid {

    /** The array indicate of many slot has the DeckGrid */
    private final Deck[] decks = new Deck[12];

    /** Create the HasheMap of DevelopmentCards ordinated by colour */
    private final Map<Colour, Integer> deckColumn = new HashMap<>();

    /** Reference of the DevelopmentCard's ArrayList */
    private ArrayList<DevelopmentCard> cards = new ArrayList<>();

    /**
     * Public method.
     * The constructor of the DeckGrid, upload the card into the deck with
     * the specified colour for every columns.
     * @throws FileNotFoundException check if the card is in the .json
     */
    public Deckgrid() throws FileNotFoundException {
        uploadCards();
        deckColumn.put(Colour.GREEN,0);
        deckColumn.put(Colour.BLUE,3);
        deckColumn.put(Colour.YELLOW,6);
        deckColumn.put(Colour.PURPLE,9);
        for (int i = 0; i < 12; i++) {
            decks[i] = new Deck();
        }
        for(DevelopmentCard card: cards){
            decks[deckColumn.get(card.getColour())+card.getLevel()-1].Add(card);
        }
        for(Deck d: decks){
            d.shuffle();
        }
    }

    //for testing activateProductionFromDevCard() in PersonalBoardTest class.
    /**
     * Public method.
     * Getter of the card.
     * @return the card
     */
    public ArrayList<DevelopmentCard> getCards() {
        return cards;
    }

    /**
     * Private metod used by the DeckGrid to read all the Development
     * Cards from the json file and store them into the ArrayList cards.
     * @throws FileNotFoundException If the file is not present.
     */
    private void uploadCards() throws FileNotFoundException {
        Gson gson = new GsonBuilder().serializeNulls().create();
        Type Devcards = new TypeToken<ArrayList<DevelopmentCard>>() {}.getType();
        File f = new File("src/main/java/it/polimi/ingsw/DevelopmentCards.json");
        BufferedReader Reader = new BufferedReader(new FileReader(f));
        this.cards = gson.fromJson(Reader, Devcards);
    }

    /**
     * Public method.
     * Return a card from the DeckGrid of the level and the colour asked
     * @param level Level of the card.
     * @param colour Colour of the card.
     * @return The card selected.
     */
    public DevelopmentCard removeCard(int level, Colour colour){
        return decks[deckColumn.get(colour) + level-1].removeCard();
    }

    /*
    //uncomment to test parsing with json file..
    public static void main(String[] args) throws FileNotFoundException {
        Deckgrid d = new Deckgrid();
        for( DevelopmentCard c : d.cards)
            c.printCards();
    }*/


    /**
     * Public method.
     * Read the card of the deck
     * @param level indicate the level needed to be read
     * @param colour indicate the level needed to be read
     * @return the card from the deck
     */
    public DevelopmentCard readCard(int level, Colour colour){
        return decks[deckColumn.get(colour) + level-1].readCard();
    }
}