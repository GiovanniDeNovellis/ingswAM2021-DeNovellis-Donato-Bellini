package it.polimi.ingsw.Controller;

import java.io.PrintWriter;

import static java.lang.Thread.sleep;

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
