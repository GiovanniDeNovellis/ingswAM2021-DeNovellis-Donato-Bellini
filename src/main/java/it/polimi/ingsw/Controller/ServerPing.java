package it.polimi.ingsw.Controller;

import java.io.PrintWriter;

import static java.lang.Thread.sleep;

/**
 * Every 5 seconds they send a keep alive ping message,
 * which takes advantage of the fact that client-side and server-side
 * sockets after 15 seconds without receiving, throw an exception
 */
public class ServerPing implements Runnable{
    private final PrintWriter out;
    private boolean running = true;

    public ServerPing(PrintWriter out) {
        this.out = out;
    }

    @Override
    public void run() {
        while(true){
            try {
                synchronized (this){
                    if(!running)
                        return;
                }
                sleep(5000);
                out.println("Ping");
                out.flush();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void setRunning(boolean running) {
        this.running = running;
    }
}
