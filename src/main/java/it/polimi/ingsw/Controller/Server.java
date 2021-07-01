package it.polimi.ingsw.Controller;

import it.polimi.ingsw.Game;

import java.io.*;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * This class represent the server. It launches and contains a instance of the model and the controller of the game
 * and accepts all the connections.
 */
public class Server {

    private int portNumber;
    private String hostName;

    public int getPortNumber() {
        return portNumber;
    }

    public String getHostName() {
        return hostName;
    }

    public static void main(String[] args) throws FileNotFoundException {
        Game game = new Game();
        Parser p = new Parser();
        Controller controller = new Controller(game);
        int portNumber;
        String hostName;
        if(args!=null&&args.length==2) {
            hostName = args[0];
            portNumber = Integer.parseInt(args[1]);
            System.out.println("Letto correttamente da linea di comando\n");
            System.out.println("hostName : " + hostName);
            System.out.println("portNumber : " + portNumber);
        }
        else{
            System.out.println("Leggo da file json\n");
            hostName=p.readHostFromJson();
            System.out.println("hostName :" + hostName);
            portNumber = p.readPortNumberFromJson();
            System.out.println("portNumber :" + portNumber);
        }
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(portNumber);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Server started!");
        ExecutorService executor = Executors.newCachedThreadPool();
        System.out.println("Pronto ad accettare");
        int num=0;
        while(true){
            try {
                Socket socket = serverSocket.accept();
                executor.submit(new ClientHandler(socket, controller));
                System.out.println("Accettato la connessione numero " + ++num);

            }
            catch(IOException e) {
                break; // Entrerei qui se serverSocket venisse chiuso
            }
        }
        executor.shutdown();
    }

}
