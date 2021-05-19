package it.polimi.ingsw.View.NotificationReaders;

import com.google.gson.Gson;
import it.polimi.ingsw.Controller.Messages.TemporaryResourcesChangedMessage;
import it.polimi.ingsw.View.ModelPrinter;

public class TemporaryResourcesChanged extends NotificationReader{

    public TemporaryResourcesChanged(ModelPrinter modelPrinter){
        super(modelPrinter);
    }

    @Override
    public void readNotification(String notification) {
        Gson gson = new Gson();
        TemporaryResourcesChangedMessage data = gson.fromJson(notification, TemporaryResourcesChangedMessage.class);
        printNotification();
        modelPrinter.getMarketBoardPrinter().setTemporaryResources(data.getTemporaryResourcesConfiguration());
    }

    public void printNotification(){
        System.out.println("Write \"show temporary resources\" to see the remaining resource(s)");
    }
}
