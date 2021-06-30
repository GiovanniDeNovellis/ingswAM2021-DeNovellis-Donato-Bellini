package it.polimi.ingsw;

        import it.polimi.ingsw.Controller.Server;
        import it.polimi.ingsw.View.Client;
        import it.polimi.ingsw.View.LocalNetwork.LocalClient;

        import java.io.FileNotFoundException;
        import java.util.Scanner;

public class Starter {
    public static void main(String[] args) throws FileNotFoundException {
        String start;
        do {
            System.out.println("Write \"CLI\" or \"GUI\" to start CLIENT, or \"SERVER\" to start SERVER\n");
            Scanner input = new Scanner(System.in);
            start = input.nextLine();
        }while(!start.equals("CLI") && !start.equals("SERVER") && !start.equals("GUI"));
        String local=null;
        if(start.equals("CLI")||start.equals("GUI")){
            do {
                System.out.println("Do you want to play a local single player game? Y N");
                Scanner input = new Scanner(System.in);
                local = input.nextLine();
            }while(!local.equals("Y") && !local.equals("N"));
        }
        switch(start){
            case "CLI":
                if(local.equals("Y"))
                    LocalClient.main(new String[]{"CLI"});
                else
                    Client.main(new String[]{"CLI"});

                System.out.println("Client closed.");
                System.exit(0);
                break;
            case "SERVER":
                Server.main(null);
                System.out.println("Server closed.");
                System.exit(0);
                break;
            default:
                if(local.equals("Y"))
                    LocalClient.main(new String[]{"GUI"});
                else
                    Client.main(new String[]{"GUI"});

                System.out.println("Client closed.");
                System.exit(0);
                break;
        }
        System.out.println("Wrong start config");
        System.exit(0);
    }
}