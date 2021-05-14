package it.polimi.ingsw.View.NotificationReaders;

import it.polimi.ingsw.View.VirtualView;

public abstract class NotificationReader {
    public NotificationReader(VirtualView virtualView){
        this.virtualView = virtualView;
    }

    VirtualView virtualView;

    public abstract void readNotification(String notification);

}
