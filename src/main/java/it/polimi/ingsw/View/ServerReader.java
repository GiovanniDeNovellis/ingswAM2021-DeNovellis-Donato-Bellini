package it.polimi.ingsw.View;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.SocketTimeoutException;

public class ServerReader implements Runnable {
    private final BufferedReader in;

    public ServerReader(BufferedReader in) {
        this.in = in;
    }

    @Override
    public void run() {
        String serverOutput;
        try {
            while ((serverOutput = in.readLine()) != null) {
                if (serverOutput.equals("Ping")) {
                } else {
                    System.out.println(serverOutput);
                }
            }
        } catch (SocketTimeoutException e) {
            System.out.println("Il server non risponde");
            System.exit(1);
        } catch (IOException e) {
            {
                System.err.println("Couldn't get I/O for the connection");
                System.exit(1);

            }
        }
    }
}
