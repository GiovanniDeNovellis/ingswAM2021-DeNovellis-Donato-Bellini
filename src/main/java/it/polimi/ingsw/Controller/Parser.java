package it.polimi.ingsw.Controller;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Parser {
    public String readHostFromJson() throws FileNotFoundException {
        Gson gson = new Gson();
        File f = new File("src/main/java/it/polimi/ingsw/Controller/prova.json");
        BufferedReader Reader = new BufferedReader(new FileReader(f));
        Server s = gson.fromJson(Reader, Server.class);
        //System.out.println(s.hostName);
        return s.getHostName();
    }

    public int readPortNumberFromJson() throws FileNotFoundException {
        Gson gson = new Gson();
        File f = new File("src/main/java/it/polimi/ingsw/Controller/prova.json");
        BufferedReader Reader = new BufferedReader(new FileReader(f));
        Server s = gson.fromJson(Reader, Server.class);
        //System.out.println(s.hostName);
        return s.getPortNumber();
    }
}
