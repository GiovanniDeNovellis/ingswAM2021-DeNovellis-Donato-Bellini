package it.polimi.ingsw.View;

import com.google.gson.Gson;
import it.polimi.ingsw.Controller.Messages.LoginOkNotificationMessage;
import it.polimi.ingsw.Controller.Messages.Message;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.SocketTimeoutException;

public class ServerReader implements Runnable {
    private final BufferedReader in;
    private CLI cli;
    private GUI gui;
    private final ModelPrinter modelPrinter = new ModelPrinter();

    public ServerReader(BufferedReader in) {
        this.in = in;
    }

    @Override
    public void run() {
        Gson gson = new Gson();
        String serverOutput;
        try {
            while ((serverOutput = in.readLine()) != null) {
                if(serverOutput.equals("end")){
                    System.err.println("Hai mandato al server un messaggio non riconosciuto");
                }
                else if (!serverOutput.equals("Ping")) {
                    System.out.println(serverOutput);
                    Message mex=gson.fromJson(serverOutput,Message.class);
                    if(mex.getMessageType()==null){
                        System.err.println("Ricevuto messaggio senza tipo!");
                    }
                    else if(mex.getMessageType().equals("LoginOkNotification") && cli!=null ){
                        LoginOkNotificationMessage log = gson.fromJson(serverOutput,LoginOkNotificationMessage.class);
                        cli.setNickname(log.getSenderNickname());
                        NotificationManager notificationManager = new NotificationManager(modelPrinter,true);
                        notificationManager.manageNotification(serverOutput);
                    }
                    else if(cli!=null){
                        NotificationManager notificationManager = new NotificationManager(modelPrinter,true);
                        notificationManager.manageNotification(serverOutput);
                    }
                    //SONO IN MODALITA' GUI
                    else if(mex.getMessageType().equals("LoginOkNotification")){
                        LoginOkNotificationMessage log = gson.fromJson(serverOutput,LoginOkNotificationMessage.class);
                        GUI.setClientNickname(log.getSenderNickname());
                        NotificationManager notificationManager = new NotificationManager(modelPrinter,false);
                        notificationManager.manageNotification(serverOutput);
                    }
                    else if(gui!=null){
                        NotificationManager notificationManager = new NotificationManager(modelPrinter,false);
                        notificationManager.manageNotification(serverOutput);
                    }
                    else{
                        System.err.println("GUI o CLI non trovati");
                    }
                }
            }
            System.err.println("Il server è crashato, la partita termina.");
            System.exit(1);
        }
         catch (SocketTimeoutException e) {
                System.err.println("Il server non risponde, la partita termina.");
                System.exit(1);
        } catch (IOException e) {
                System.err.println("Il server è crashato, la partita termina.");
                System.exit(1);
        }
    }

    public void setCli(CLI cli) {
        this.cli = cli;
        cli.setModelPrinter(modelPrinter);
    }

    public void setGui(GUI gui) {
        this.gui = gui;
    }
}
