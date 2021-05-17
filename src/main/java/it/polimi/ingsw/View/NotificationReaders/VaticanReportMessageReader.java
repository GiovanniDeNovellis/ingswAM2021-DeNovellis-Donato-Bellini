package it.polimi.ingsw.View.NotificationReaders;

import com.google.gson.Gson;
import it.polimi.ingsw.Controller.Messages.VaticanReportMessage;
import it.polimi.ingsw.View.ModelPrinter;
import it.polimi.ingsw.View.Printers.PersonalBoardPrinter;

public class VaticanReportMessageReader extends NotificationReader{
    public VaticanReportMessageReader(ModelPrinter modelPrinter) {
        super(modelPrinter);
    }
    String nickname;
    String vatReport;

    @Override
    public void readNotification(String notification) {
        Gson gson = new Gson();
        VaticanReportMessage data = gson.fromJson(notification,VaticanReportMessage.class);
        nickname = data.getNickname();
        printNotification();
        for(PersonalBoardPrinter p : modelPrinter.getPersonalBoards()){
            if(p.getOwnerNickname().equals(nickname)){
                p.setOwnerNickname(data.getNickname());
                p.setFaithPoints(data.getNewFaithPoints());
                if(data.getWhichOne()==1) {
                    vatReport = "First vatican report occurred.";
                }else if (data.getWhichOne()==2) {
                    vatReport = "Second vatican report occurred.";
                }else if (data.getWhichOne()==3){
                    vatReport = "Third vatican report occurred.";
                }
            }
        }
}
    public void printNotification(){
        System.out.println("Player " + nickname + " has a vatican report." + vatReport);
    }
}
