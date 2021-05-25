package it.polimi.ingsw.View.CLINotifiers;

import it.polimi.ingsw.View.ModelPrinter;

public abstract class CLINotifier {
    public CLINotifier(ModelPrinter modelPrinter){
        this.modelPrinter = modelPrinter;
    }

    ModelPrinter modelPrinter;

    public abstract void notifyCLI(String notification);

}
