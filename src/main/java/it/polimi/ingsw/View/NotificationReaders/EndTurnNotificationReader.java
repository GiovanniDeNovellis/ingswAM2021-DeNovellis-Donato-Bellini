package it.polimi.ingsw.View.NotificationReaders;

import com.google.gson.Gson;
import it.polimi.ingsw.Controller.Messages.EndTurnNotificationMessage;
import it.polimi.ingsw.View.ModelPrinter;
import it.polimi.ingsw.View.Printers.PersonalBoardPrinter;

public class EndTurnNotificationReader extends NotificationReader{
    String nickname,winner;
    int oldValue = 0;
    public EndTurnNotificationReader(ModelPrinter modelPrinter) {
        super(modelPrinter);
    }

    @Override
    public void readNotification(String notification) {
        Gson gson = new Gson();
        EndTurnNotificationMessage data = gson.fromJson(notification, EndTurnNotificationMessage.class);
        nickname = data.getActualCurrentPlayer();
        printNotification0();
        if(modelPrinter.getBlackFaithPoints()!=-1)
            modelPrinter.setBlackFaithPoints(data.getBlackFaithPoints());
        for (PersonalBoardPrinter p : modelPrinter.getPersonalBoards()){
            oldValue = p.getFaithPoints();
            p.setFaithPoints(data.getNumResourcesDiscarded()+oldValue);
        }
        modelPrinter.getMarketBoardPrinter().setTemporaryResources(data.getTemporaryResources());
        if(data.isGameEnding()) {
            if ((data.getWinnerPlayerNickname()!=null)) {
                winner = data.getWinnerPlayerNickname();
                printNotification1();
            }else{
                printNotification2();
            }
        }
    }
    public void printNotification0(){
        System.out.println("Player " + nickname + " has finished his turn.");
    }
    public void printNotification1(){
        System.out.println("GAME OVER.\n Player " + winner + " has won the game.");
    }
    public void printNotification2(){
        System.out.println("This is the final turn, at the end game is over.");
    }
}
