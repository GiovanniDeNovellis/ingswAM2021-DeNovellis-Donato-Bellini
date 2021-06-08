package it.polimi.ingsw.View.LocalNetwork;

import com.google.gson.Gson;
import it.polimi.ingsw.Controller.Messages.LoginOkNotificationMessage;
import it.polimi.ingsw.Controller.Messages.Message;
import it.polimi.ingsw.View.CLI;
import it.polimi.ingsw.View.GUI;
import it.polimi.ingsw.View.ModelPrinter;
import it.polimi.ingsw.View.UInotifiers.UI;
import it.polimi.ingsw.View.UInotifiers.CLIInterface;
import it.polimi.ingsw.View.UInotifiers.GUIInterface;

public class LocalServerReader {
    private CLI cli;
    private GUI gui;
    private final ModelPrinter modelPrinter = new ModelPrinter();
    private final UI interfaceToNotify;

    public LocalServerReader(CLI cli, GUI gui) {
        if(cli!=null){
            this.cli=cli;
            cli.setModelPrinter(modelPrinter);
            interfaceToNotify=new CLIInterface(modelPrinter);
        }
        else{
            this.gui=gui;
            interfaceToNotify=new GUIInterface(modelPrinter);
        }
    }

    public void notifyClient(String serverOutput){
        Gson gson = new Gson();
        if (serverOutput.equals("end"))
            System.err.println("Hai mandato al server un messaggio non riconosciuto");
        else {
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
}
