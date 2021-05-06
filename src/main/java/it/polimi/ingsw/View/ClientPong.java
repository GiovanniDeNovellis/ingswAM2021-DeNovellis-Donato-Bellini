package it.polimi.ingsw.View;

import java.io.PrintWriter;

import static java.lang.Thread.sleep;

public class ClientPong implements Runnable{
    private final PrintWriter out;

    public ClientPong(PrintWriter out) {
        this.out = out;
    }

    @Override
    public void run() {
        while(true){
            try {
                sleep(5000);
                out.println("Pong");
                out.flush();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
