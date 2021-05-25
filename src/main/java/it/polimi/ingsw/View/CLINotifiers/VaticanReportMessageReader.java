package it.polimi.ingsw.View.CLINotifiers;

import com.google.gson.Gson;
import it.polimi.ingsw.Controller.Messages.VaticanReportMessage;
import it.polimi.ingsw.View.ModelPrinter;
import it.polimi.ingsw.View.Printers.PersonalBoardPrinter;

public class VaticanReportMessageReader extends CLINotifier {
    public VaticanReportMessageReader(ModelPrinter modelPrinter) {
        super(modelPrinter);
    }
    String nickname;
    String vatReport;

    @Override
    public void notifyCLI(String notification) {
        Gson gson = new Gson();
        VaticanReportMessage data = gson.fromJson(notification,VaticanReportMessage.class);
        for(PersonalBoardPrinter p : modelPrinter.getPersonalBoards()){
            if(p.getOwnerNickname().equals(data.getNickname()))
                p.setFaithPoints(data.getNewFaithPoints());
        }
        if(data.isOccurred() ) {
            nickname = data.getNickname();
            for (PersonalBoardPrinter p1 : modelPrinter.getPersonalBoards()) {
                if (p1.getOwnerNickname().equals(nickname)) {
                    if (data.getWhichOne() == 1) {
                        if(data.getNewFaithPoints()>4)
                            vatReport = "First vatican report occurred. " + nickname + " was inside the vatican space";
                        else
                            vatReport = "First vatican report occurred. " + nickname + " wasn't inside the vatican space";
                        p1.getFaithCards()[0]=0;
                    } else if (data.getWhichOne() == 2) {
                        if(data.getNewFaithPoints()>11)
                            vatReport = "Second vatican report occurred. " + nickname + " was inside the vatican space";
                        else
                            vatReport = "Second vatican report occurred. " + nickname + " wasn't inside the vatican space";
                        p1.getFaithCards()[1]=0;
                    } else if (data.getWhichOne() == 3) {
                        if(data.getNewFaithPoints()>18)
                            vatReport = "Third vatican report occurred. " + nickname + " was inside the vatican space";
                        else
                            vatReport = "Third vatican report occurred. " + nickname + " wasn't inside the vatican space";
                        p1.getFaithCards()[2]=0;
                    }
                    printNotification();
                }
            }
        }
    }
    public void printNotification(){
        System.out.println(vatReport);
    }
}
