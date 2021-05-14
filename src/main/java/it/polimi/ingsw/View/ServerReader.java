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
                    Message mex=gson.fromJson(serverOutput,Message.class);
                    if(mex.getMessageType()==null){
                        //TODO METTERE IL TIPO AI MESSAGGI CHE NON LO HANNO
                        System.err.println("Ricevuto messaggio senza tipo!");
                    }
                    else if(mex.getMessageType().equals("LoginOkNotification")){
                        LoginOkNotificationMessage log = gson.fromJson(serverOutput,LoginOkNotificationMessage.class);
                        cli.setNickname(log.getSenderNickname());
                    }
                    else if(cli!=null){
                        NotificationManager notificationManager = new NotificationManager(cli.getVirtualView());
                    }
                    //System.out.println(serverOutput);  DEBUG
                }
            }
        }
         catch (SocketTimeoutException e) {
                System.out.println("Il server non risponde");
                System.exit(1);
        } catch (IOException e) {
                System.err.println("Couldn't get I/O for the connection");
                System.exit(1);
        }
    }

    public void setCli(CLI cli) {
        this.cli = cli;
    }
}
