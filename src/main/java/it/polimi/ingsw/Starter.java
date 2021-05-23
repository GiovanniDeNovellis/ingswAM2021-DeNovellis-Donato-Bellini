package it.polimi.ingsw;

        import it.polimi.ingsw.Controller.Server;
        import it.polimi.ingsw.View.Client;

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
        // if(start==null || args.length==0)
        // Server.main(null);
        switch(start){
            case "CLI":
                Client.main(new String[]{"CLI"});
            case "SERVER":
                Server.main(null);
                case "GUI":
                App.main(args);
                //Client.main(new String[]{"GUI"});
        }
        System.out.println("Wrong start config");
        System.exit(0);
    }
}