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
        if(data.isOccurred() ) {
            nickname = data.getNickname();
            for (PersonalBoardPrinter p : modelPrinter.getPersonalBoards()) {
                if (p.getOwnerNickname().equals(nickname)) {
                    p.setFaithPoints(data.getNewFaithPoints());
                    if (data.getWhichOne() == 1) {
                        if(data.getNewFaithPoints()>4)
                            vatReport = "First vatican report occurred. You were inside the vatican space";
                        else
                            vatReport = "First vatican report occurred. You weren't inside the vatican space";
                    } else if (data.getWhichOne() == 2) {
                        if(data.getNewFaithPoints()>11)
                            vatReport = "Second vatican report occurred. You were inside the vatican space";
                        else
                            vatReport = "Second vatican report occurred. You weren't inside the vatican space";
                    } else if (data.getWhichOne() == 3) {
                        if(data.getNewFaithPoints()>18)
                            vatReport = "Third vatican report occurred. You were inside the vatican space";
                        else
                            vatReport = "Third vatican report occurred. You weren't inside the vatican space";
                    }
                }
                printNotification();
            }
        }
    }
    public void printNotification(){
        System.out.println(vatReport);
    }
}
