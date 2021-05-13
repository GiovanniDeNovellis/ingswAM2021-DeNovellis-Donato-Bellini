package it.polimi.ingsw;

import it.polimi.ingsw.Controller.Server;
import it.polimi.ingsw.View.Client;

import java.io.FileNotFoundException;

public class Starter {
    public static void main(String[] args) throws FileNotFoundException {
        if(args==null || args.length==0)
            Server.main(null);
        switch(args[0]){
            case "CLI":
                Client.main(new String[]{"CLI"});
            case "SERVER":
                Server.main(null);
            case "GUI":
                //TODO GUI
                //Client.main(new String[]{"GUI"});
        }
        System.out.println("Wrong start config");
        System.exit(0);
    }
}
