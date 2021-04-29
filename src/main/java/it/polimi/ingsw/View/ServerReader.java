package it.polimi.ingsw.View;

import java.io.BufferedReader;
import java.io.IOException;

public class ServerReader implements Runnable {
    private BufferedReader in;

    public ServerReader(BufferedReader in) {
        this.in = in;
    }

    @Override
    public void run() {
        String serverOutput;
        try {
            while ((serverOutput = in.readLine()) != null) {
                System.out.println(serverOutput);
            }
        } catch (IOException e) {
            {
                System.err.println("Couldn't get I/O for the connection");
                System.exit(1);

            }
        }
    }
}
