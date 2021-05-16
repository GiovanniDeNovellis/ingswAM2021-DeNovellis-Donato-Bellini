package it.polimi.ingsw.View.NotificationReaders;

import com.google.gson.Gson;
import it.polimi.ingsw.Controller.Messages.TemporaryResourcesChangedMessage;
import it.polimi.ingsw.View.Printers.PersonalBoardPrinter;
import it.polimi.ingsw.View.VirtualView;

public class TemporaryResourcesChanged extends NotificationReader{

    public TemporaryResourcesChanged(VirtualView virtualView){
        super(virtualView);
    }

    @Override
    public void readNotification(String notification) {
        Gson gson = new Gson();
        TemporaryResourcesChangedMessage data = gson.fromJson(notification, TemporaryResourcesChangedMessage.class);
        printNotification();
        for(PersonalBoardPrinter p : virtualView.getPersonalBoards()){
            if( p.getOwnerNickname().equals(data.getNickname()))
                p.setTemporaryResources(data.getTemporaryResourcesConfiguration());
        }
    }

    public void printNotification(){
        System.out.println("Write \"ShowInsertableResources\" tho see the remaining resource(s)" +
                " you could insert(if there are other insertable resources).");
    }
}
