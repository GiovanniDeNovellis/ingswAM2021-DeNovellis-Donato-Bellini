package it.polimi.ingsw.Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientHandler implements Runnable {
    private Controller controller;
    private Socket socket;

    public ClientHandler(Socket socket, Controller controller) {
        this.socket = socket;
        this.controller=controller;
    }

    @Override
    public void run() {
        try {
            Scanner in = new Scanner(socket.getInputStream());
            PrintWriter out = new PrintWriter(socket.getOutputStream());
            // Leggo e scrivo nella connessione finche' non ricevo "quit"
            while (true) {
                String line = in.nextLine();
                if (line.equals("quit")) {
                    break;
                } else {
                    controller.startAction(line);
                    out.println("Received: " + line);
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
}