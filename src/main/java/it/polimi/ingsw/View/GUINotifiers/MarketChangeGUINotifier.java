package it.polimi.ingsw.View.GUINotifiers;

import com.google.gson.Gson;
import it.polimi.ingsw.Controller.Messages.MarketGridChangedMessage;
import it.polimi.ingsw.View.GUI;
import it.polimi.ingsw.View.ModelPrinter;
import javafx.application.Platform;

import java.io.IOException;

public class MarketChangeGUINotifier extends GUINotifier{
    private final ModelPrinter modelPrinter;

    public MarketChangeGUINotifier(ModelPrinter modelPrinter) {
        this.modelPrinter = modelPrinter;
    }

    @Override
    public void notifyGui(String notification) {
        Gson gson = new Gson();
        MarketGridChangedMessage data = gson.fromJson(notification,MarketGridChangedMessage.class);
        updateModel(data);
        Platform.runLater(()->{
            try {
                GUI.setRoot("market_scene");
                GUI.getMarketSceneController().printScene(modelPrinter);
                GUI.getMarketSceneController().getNotificationLabel().setVisible(true);
                GUI.getMarketSceneController().getNotificationLabel().setText("Current player has taken resources from market.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void updateModel(MarketGridChangedMessage data){
        modelPrinter.getMarketBoardPrinter().setMarbleOut(data.getMarbleout());
        modelPrinter.getMarketBoardPrinter().setMarbleGrid(data.getMarketGridConfiguration());
    }
}
