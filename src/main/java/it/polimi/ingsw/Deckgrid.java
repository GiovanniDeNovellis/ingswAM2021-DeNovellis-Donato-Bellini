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

import java.util.ArrayList;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Type;

public class Deckgrid {

    public ArrayList<DevelopmentCard> cards = new ArrayList<>();
    public Deckgrid() throws FileNotFoundException {
        uploadCards();
    }
    private void uploadCards() throws FileNotFoundException {
        Gson gson = new GsonBuilder().serializeNulls().create();
        Type devcards = new TypeToken<ArrayList<DevelopmentCard>>() {}.getType();
        File f = new File("src/main/java/it/polimi/ingsw/DevelopmentCards.json");
        BufferedReader rder = new BufferedReader(new FileReader(f));
        this.cards = gson.fromJson(rder, devcards);
    }

    //testing parsing with json file..
    public static void main(String[] args) throws FileNotFoundException {
        Deckgrid d = new Deckgrid();
        for( DevelopmentCard c : d.cards)
            c.printCards();
    }
}
