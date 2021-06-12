package it.polimi.ingsw.View.CLINotifiers;

import com.google.gson.Gson;
import it.polimi.ingsw.Controller.Messages.EndTurnNotificationMessage;
import it.polimi.ingsw.View.ModelPrinter;
import it.polimi.ingsw.View.Printers.PersonalBoardPrinter;

import java.util.Scanner;

public class EndTurnCLINotifier extends CLINotifier {
    String winner,nextPlayer;
    int oldValue = 0;
    public EndTurnCLINotifier(ModelPrinter modelPrinter) {
        super(modelPrinter);
    }

    @Override
    public void notifyCLI(String notification) {
        Gson gson = new Gson();
        EndTurnNotificationMessage data = gson.fromJson(notification, EndTurnNotificationMessage.class);
        nextPlayer = data.getActualCurrentPlayer();
        modelPrinter.setCurrentPlayerNickname(nextPlayer);
        printNotification0();
        if(modelPrinter.getBlackFaithPoints()!=-1) {
            int diff = data.getBlackFaithPoints()   - modelPrinter.getBlackFaithPoints();
            if(diff>0)
                System.out.println("You discarded resources: Lorenzo gets " + diff + " faith points." );
            modelPrinter.setBlackFaithPoints(data.getBlackFaithPoints());
        }
        else if(data.getNumResourcesDiscarded()>0)
            System.out.println(data.getNumResourcesDiscarded() + " resources have been discarded");
        for (PersonalBoardPrinter p : modelPrinter.getPersonalBoards()){
            oldValue = p.getFaithPoints();
            p.setFaithPoints(data.getNumResourcesDiscarded()+oldValue);
        }
        modelPrinter.getMarketBoardPrinter().setTemporaryResources(data.getTemporaryResources());
        if(data.isGameEnding()) {
            if ((data.getWinnerPlayerNickname()!=null)) {
                winner = data.getWinnerPlayerNickname();
                printNotification1(data.getWinnerPoints());
            }
            else if(data.getWinnerPlayerNumber()==5)
                printNotification3(data.getWinnerPoints());
            else{
                printNotification2();
            }
        }
    }
    public void printNotification0(){
        System.out.println("Turn ended." +
                "\nIt is " + nextPlayer + " turn.");
    }
    public void printNotification1(int points){
        System.out.println("GAME OVER.\n Player " + winner + " has won the game with " + points + " points.");
        forceQuit();
    }
    public void printNotification2(){
        System.out.println("This is the final turn, at the end game is over.");
    }
    public void printNotification3(int points){
        System.out.println("GAME OVER. Lorenzo has won the game. You obtained " + points + " points.");
        forceQuit();
    }

    private void forceQuit(){
        String exit;
        Scanner in = new Scanner(System.in);
        do{
            System.out.println("Game ended. Please write \"QUIT\" to close the game." );
            exit=in.nextLine();
        }while(!exit.equals("QUIT"));
        System.exit(1);
    }
}
