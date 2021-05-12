package it.polimi.ingsw.View;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CLI implements Runnable{
    private String userInput=null;
    private final VirtualView virtualView = new VirtualView();
    private String nickname;

    public void run(){
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        String userInput;
        while(true){
            try {
                if((userInput=stdIn.readLine())!=null){
                    synchronized (virtualView) {
                        CommandManager commandManager = new CommandManager(virtualView, nickname);
                        userInput=commandManager.manage(userInput);
                    }
                    setUserInput(userInput);
                }
            } catch (IOException e) {
                System.err.println("Crashato input da tastiera");
                System.exit(1);
            }
        }
    }

    public synchronized String getUserInput() {
        return userInput;
    }

    public synchronized void setUserInput(String userInput) {
        this.userInput = userInput;
    }
}
