package it.polimi.ingsw.View.GUINotifiers;

import com.google.gson.Gson;
import it.polimi.ingsw.Controller.Messages.NotifyDevelopmentCardInsertedOKMessage;
import it.polimi.ingsw.View.GUI;
import it.polimi.ingsw.View.ModelPrinter;
import it.polimi.ingsw.View.Printers.PersonalBoardPrinter;
import javafx.application.Platform;

public class CardBoughtGUINotifier extends GUINotifier{

    public CardBoughtGUINotifier(ModelPrinter modelPrinter) {
        this.modelPrinter = modelPrinter;
    }

    ModelPrinter modelPrinter;


    @Override
    public void notifyGui(String notification) {
        NotifyDevelopmentCardInsertedOKMessage data = parseMessage(notification);
        updateModel(data);
        if(GUI.getStatus()!=null) {
            Platform.runLater(() -> {
                switch (GUI.getStatus()) {
                    case "Market":
                        GUI.getMarketSceneController().printScene(modelPrinter);
                        GUI.getMarketSceneController().notifyChangement("has bought a development card.", data.getNickname());
                        break;
                    case "Deck":
                        GUI.getDeckgridSceneController().printScene(modelPrinter);
                        GUI.getDeckgridSceneController().notifyChangement("has bought a development card.", data.getNickname());
                        break;
                    case "Main":
                        GUI.getMainSceneController().printClientPlayer(modelPrinter);
                        GUI.getMainSceneController().notifyChangement("has bought a development card.", data.getNickname());
                        break;
                }
            });
        }
    }

    public NotifyDevelopmentCardInsertedOKMessage parseMessage(String message){
        Gson gson = new Gson();
        return gson.fromJson(message, NotifyDevelopmentCardInsertedOKMessage.class);
    }

    public void updateModel(NotifyDevelopmentCardInsertedOKMessage data){
        String nickname = data.getNickname();
        for(PersonalBoardPrinter p : modelPrinter.getPersonalBoards()){
            if(p.getOwnerNickname().equals(nickname)){
                p.setDevelopmentCards(data.getDevelopmentCardsConfiguration());
                if(data.getExtraDepositsConfiguration()[0]!=null)
                    p.setExtraDeposit1(data.getExtraDepositsConfiguration()[0]);
                if(data.getExtraDepositsConfiguration()[1]!=null)
                    p.setExtraDeposit2(data.getExtraDepositsConfiguration()[1]);
                p.setWareHouseDepot(data.getWarehouseConfiguration());
                p.setStrongbox(data.getStrongboxConfiguration());
                p.setAllCardsInserted(data.getInsertedDevelopmentCard());
            }
        }
        modelPrinter.getDeckGridPrinter().setDeckgrid(data.getDeckgridConfiguration());
    }
}
