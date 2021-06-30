package it.polimi.ingsw.View;

import java.io.PrintWriter;

import static java.lang.Thread.sleep;
/**
 * Every 5 seconds they send a keep alive ping message,
 * which takes advantage of the fact that client-side and server-side
 * sockets after 15 seconds without receiving, throw an exception
 */
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
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
