package it.polimi.ingsw.Controller;

import com.google.gson.Gson;

import java.io.*;

public class Parser {
    public String readHostFromJson() throws FileNotFoundException {
        Gson gson = new Gson();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("ServerConfig.json");
        BufferedReader Reader = new BufferedReader(new InputStreamReader(inputStream));
        Server s = gson.fromJson(Reader, Server.class);
        return s.getHostName();
    }

    public int readPortNumberFromJson() throws FileNotFoundException {
        Gson gson = new Gson();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("ServerConfig.json");
        BufferedReader Reader = new BufferedReader(new InputStreamReader(inputStream));
        Server s = gson.fromJson(Reader, Server.class);
        return s.getPortNumber();
    }
}
