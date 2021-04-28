package it.polimi.ingsw.Controller;

import com.google.gson.Gson;
import it.polimi.ingsw.Controller.Messages.Message;
import it.polimi.ingsw.Controller.Messages.SimpleMessage;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class ClientHandler implements Runnable {
    private Controller controller;
    private Socket socket;
    private int numClient;
    private PrintWriter out;

    public ClientHandler(Socket socket, Controller controller, int numClient) {
        this.socket = socket;
        this.controller = controller;
        this.numClient = numClient;
    }

    @Override
    public void run() {
        try {
            Scanner in = new Scanner(socket.getInputStream());
            out = new PrintWriter(socket.getOutputStream());
            // Leggo e scrivo nella connessione finche' non ricevo "quit"
            if (numClient > 4) {
                SimpleMessage mex = new SimpleMessage();
                mex.setMessageContent("Connection refused: too many clients connected.");
                Gson gson = new Gson();
                String s = gson.toJson(mex);
                out.println(s);
                out.flush();
                out.close();
                socket.close();
                return;
            }
            SimpleMessage mex = new SimpleMessage();
            mex.setMessageContent("Connection accepted, please login with your nickname.");
            controller.addClientHandler(this);
            Gson gson = new Gson();
            String s = gson.toJson(mex);
            out.println(s);
            out.flush();
            while (true) {
                    String line = in.nextLine();
                if (line.equals("quit") ) {
                    break;
                } else {
                    out.println(controller.startAction(line));
                    out.flush();
                }
            }
            // Chiudo lo stream e i socket
            in.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }


    public void notifyInterface(String notification) {
        out.println(notification);
        out.flush();
    }
}