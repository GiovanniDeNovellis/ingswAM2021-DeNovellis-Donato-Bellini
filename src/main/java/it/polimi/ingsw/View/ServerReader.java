package it.polimi.ingsw.View;

import com.google.gson.Gson;
import it.polimi.ingsw.Controller.Messages.LoginOkNotificationMessage;
import it.polimi.ingsw.Controller.Messages.Message;
import it.polimi.ingsw.View.UInotifiers.CLIInterface;
import it.polimi.ingsw.View.UInotifiers.GUIInterface;
import it.polimi.ingsw.View.UInotifiers.UI;

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
        UI interfaceToNotify;
        if(cli!=null) //SONO IN CLI
            interfaceToNotify= new CLIInterface(modelPrinter);
        else //SONO IN GUI
            interfaceToNotify = new GUIInterface(modelPrinter);
        try {
            while ((serverOutput = in.readLine()) != null) {
                if (serverOutput.equals("end"))
                    System.err.println("Hai mandato al server un messaggio non riconosciuto");
                 else if (!serverOutput.equals("Ping")) {
                    //System.out.println(serverOutput);
                    Message mex = gson.fromJson(serverOutput, Message.class);
                    if (mex.getMessageType() == null)
                        System.err.println("Ricevuto messaggio senza tipo!");
                     else if (mex.getMessageType().equals("LoginOkNotification")) {
                        LoginOkNotificationMessage log = gson.fromJson(serverOutput, LoginOkNotificationMessage.class);
                        if (cli != null)
                            cli.setNickname(log.getSenderNickname());
                        else
                            GUI.setClientNickname(log.getSenderNickname());
                        interfaceToNotify.notify(serverOutput);
                    } else
                        interfaceToNotify.notify(serverOutput);
                }
            }
            System.err.println("Il server è crashato, la partita termina.");
            System.exit(1);
        }
         catch (SocketTimeoutException e) {
            if(cli!=null) {
                System.err.println("Il server non risponde, la partita termina.");
                System.exit(1);
            }
            else {
                try {
                    GUI.setRoot("server_crash_scene");
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        } catch (IOException e) {
            if(cli!=null) {
                System.err.println("Il server è crashato, la partita termina.");
                System.exit(1);
            }
            else {
                try {
                    GUI.setRoot("server_crash_scene");
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
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
