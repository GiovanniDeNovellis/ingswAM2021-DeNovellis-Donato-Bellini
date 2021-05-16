package it.polimi.ingsw.View.NotificationReaders;

import it.polimi.ingsw.View.ModelPrinter;

public abstract class NotificationReader {
    public NotificationReader(ModelPrinter modelPrinter){
        this.modelPrinter = modelPrinter;
    }

    ModelPrinter modelPrinter;

    public abstract void readNotification(String notification);

}
