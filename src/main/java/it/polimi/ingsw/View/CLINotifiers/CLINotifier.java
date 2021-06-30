package it.polimi.ingsw.View.CLINotifiers;

import it.polimi.ingsw.View.ModelPrinter;

/**
 * This class has a method that update the modelprinter and eventually
 * update the CLI with the new attributes of the objects
 */
public abstract class CLINotifier{

    public CLINotifier(ModelPrinter modelPrinter){
        this.modelPrinter = modelPrinter;
    }

    ModelPrinter modelPrinter;

    public abstract void notifyCLI(String notification);

}
