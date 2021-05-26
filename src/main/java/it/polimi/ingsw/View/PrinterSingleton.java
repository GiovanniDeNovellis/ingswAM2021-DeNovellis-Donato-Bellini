package it.polimi.ingsw.View;


import java.io.PrintWriter;

public class PrinterSingleton {
    private static PrinterSingleton printerSingleton=null;
    private PrintWriter out;

    private PrinterSingleton(){}

    public static PrinterSingleton getPrinterSingleton(){
        if(printerSingleton==null)
            printerSingleton=new PrinterSingleton();
        return printerSingleton;
    }

    public void setPrintWriter(PrintWriter out) {
        this.out = out;
    }

    public void sendMessage(String jsonContent){
        out.println(jsonContent);
    }
}
