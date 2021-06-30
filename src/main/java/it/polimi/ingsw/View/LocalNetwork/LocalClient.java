package it.polimi.ingsw.View.LocalNetwork;

import com.google.gson.Gson;
import it.polimi.ingsw.Controller.Messages.AddPlayerMessage;
import it.polimi.ingsw.View.CLI;
import it.polimi.ingsw.View.GUI;
import it.polimi.ingsw.View.PrinterSingleton;

import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * This class represents the main part to manages the
 * local game (advanced functionality). So the server
 * is not "online" and the game start in local.
 */

public class LocalClient {
    public static void main(String[] args) {
        LocalClientHandler localClientHandler = null;
        try {
            localClientHandler= new LocalClientHandler();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String type = args[0];
        LocalServerReader local;
        if (type.equals("CLI")) {
            CLI cli = new CLI();
            local = new LocalServerReader(cli,null);
            localClientHandler.setLocalServerReader(local);
            System.out.println("Welcome to a local single player game");
            localClientHandler.submitCommand(buildLogin());
            String userInput;
            Thread thread = new Thread(cli);
            thread.start();
            while (true) {
                if ((userInput = cli.getUserInput()) != null) {
                    if(userInput.equals("Invalid command."))
                        System.out.println("Invalid command. Use help to see all valid commands.");
                    else if(!userInput.equals("show"))
                        localClientHandler.submitCommand(userInput);
                    cli.setUserInput(null);
                }
            }
        } else if (type.equals("GUI")) {
            PrinterSingleton.getPrinterSingleton().setLocal(true);
            PrinterSingleton.getPrinterSingleton().setLocalClientHandler(localClientHandler);
            GUI gui = new GUI();
            local = new LocalServerReader(null, gui);
            localClientHandler.setLocalServerReader(local);
            GUI.main(args);
        }
        else{
            System.err.println("Wrong config");
            System.exit(1);
        }
    }

    /**
     * This class represents the "builder" of the login,
     * the method set the standards variable for the player
     * @return the message to send
     */
    private static String buildLogin(){
        String nickname;
        Gson gson = new Gson();
        AddPlayerMessage message = new AddPlayerMessage();
        message.setMessageType("AddPlayer");
        Scanner input = new Scanner(System.in);
        System.out.println("Please insert your nickname for the login");
        nickname= input.nextLine();
        message.setSenderNickname(nickname);
        return gson.toJson(message);
    }
}
