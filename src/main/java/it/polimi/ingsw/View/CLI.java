package it.polimi.ingsw.View;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CLI implements Runnable{
    private String userInput=null;
    private  ModelPrinter modelPrinter;
    private String nickname;

    public void run(){
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        String userInput;
        while(true){
            try {
                if((userInput=stdIn.readLine())!=null){
                    synchronized (modelPrinter) {
                        CommandManager commandManager = new CommandManager(modelPrinter, nickname);
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

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public ModelPrinter getModelPrinter() {
        return modelPrinter;
    }

    public void setModelPrinter(ModelPrinter modelPrinter) {
        this.modelPrinter = modelPrinter;
    }
}
