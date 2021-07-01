package it.polimi.ingsw.Controller;

import com.google.gson.Gson;

import java.io.*;

/**
 * This class parses the network parameters from a json file.
 */
public class Parser {
    /**
     * @return The host name.
     * @throws FileNotFoundException
     */
    public String readHostFromJson() throws FileNotFoundException {
        Gson gson = new Gson();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("ServerConfig.json");
        BufferedReader Reader = new BufferedReader(new InputStreamReader(inputStream));
        Server s = gson.fromJson(Reader, Server.class);
        return s.getHostName();
    }

    /**
     * @return The port number
     * @throws FileNotFoundException
     */
    public int readPortNumberFromJson() throws FileNotFoundException {
        Gson gson = new Gson();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("ServerConfig.json");
        BufferedReader Reader = new BufferedReader(new InputStreamReader(inputStream));
        Server s = gson.fromJson(Reader, Server.class);
        return s.getPortNumber();
    }
}
