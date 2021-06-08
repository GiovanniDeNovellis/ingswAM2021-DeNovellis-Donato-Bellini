package it.polimi.ingsw.View;


import it.polimi.ingsw.View.LocalNetwork.LocalClientHandler;

import java.io.PrintWriter;

public class PrinterSingleton {
    private static PrinterSingleton printerSingleton=null;
    private PrintWriter out;
    private boolean local=false;
    private LocalClientHandler localClientHandler;

    private PrinterSingleton(){}

    public static PrinterSingleton getPrinterSingleton(){
        if(printerSingleton==null)
            printerSingleton=new PrinterSingleton();
        return printerSingleton;
    }

    public void setPrintWriter(PrintWriter out) {
        this.out = out;
    }

    public void setLocal(boolean local) {
        this.local = local;
    }

    public void setLocalClientHandler(LocalClientHandler localClientHandler) {
        this.localClientHandler = localClientHandler;
    }

    public void sendMessage(String jsonContent){
        if(local)
            localClientHandler.submitCommand(jsonContent);
        else
            out.println(jsonContent);
    }
}
