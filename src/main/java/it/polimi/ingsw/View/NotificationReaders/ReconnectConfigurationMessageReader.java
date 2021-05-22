package it.polimi.ingsw.View.NotificationReaders;

import com.google.gson.Gson;
import it.polimi.ingsw.Controller.Messages.ReconnectConfigurationMessage;
import it.polimi.ingsw.View.ModelPrinter;
import it.polimi.ingsw.View.Printers.LeaderCardsPrinter;
import it.polimi.ingsw.View.Printers.PersonalBoardPrinter;

public class ReconnectConfigurationMessageReader extends NotificationReader{
    public ReconnectConfigurationMessageReader(ModelPrinter modelPrinter) {
        super(modelPrinter);
    }

    @Override
    public void readNotification(String notification) {
        Gson gson = new Gson();
        ReconnectConfigurationMessage reconnectConfigurationMessage = gson.fromJson(notification,ReconnectConfigurationMessage.class);
        PersonalBoardPrinter p = new PersonalBoardPrinter();
        p.setOwnerNickname(reconnectConfigurationMessage.getSenderNickname());
        p.setPlayerNumber(reconnectConfigurationMessage.getPlayerNumber());
        p.setWareHouseDepot(reconnectConfigurationMessage.getWareHouseConfiguration());
        p.setStrongbox(reconnectConfigurationMessage.getStrongboxConfiguration().getResourcesContained());
        if(reconnectConfigurationMessage.getExtraDepositConfiguration().get(0)!=null)
            p.setExtraDeposit1(reconnectConfigurationMessage.getExtraDepositConfiguration().get(0));
        if(reconnectConfigurationMessage.getExtraDepositConfiguration().get(1)!=null)
            p.setExtraDeposit2(reconnectConfigurationMessage.getExtraDepositConfiguration().get(1));
        p.setFaithPoints(reconnectConfigurationMessage.getNewFaithPoints());
        p.setDevelopmentCards(reconnectConfigurationMessage.getDevelopmentCardsConfiguration());
        LeaderCardsPrinter l = new LeaderCardsPrinter();
        l.setOwnerNickname(reconnectConfigurationMessage.getSenderNickname());
        if(!reconnectConfigurationMessage.getChoosableLeaderCards().isEmpty()){
            int[] leads = new int[4];
            for(int i=0; i<reconnectConfigurationMessage.getChoosableLeaderCards().size();i++){
                leads[i]=reconnectConfigurationMessage.getChoosableLeaderCards().get(i);
            }
            l.setChoosableLeaderCards(leads);
        }
        if(!reconnectConfigurationMessage.getChoosedLeaderCards().isEmpty()){
            int[] leads = new int[2];
            for(int i=0; i<reconnectConfigurationMessage.getChoosedLeaderCards().size();i++){
                leads[i]=reconnectConfigurationMessage.getChoosedLeaderCards().get(i);
            }
            l.setChosenLeaderCards(leads);
        }
        l.setBuilt(true);
        l.setActivatedLeaderCards(reconnectConfigurationMessage.getActiveLeaderCards());
        modelPrinter.getPersonalBoards().add(p);
        modelPrinter.getLeaderCardsPrinters().add(l);
        modelPrinter.getMarketBoardPrinter().setMarbleGrid(reconnectConfigurationMessage.getMarbleGridConfiguration());
        modelPrinter.getMarketBoardPrinter().setMarbleOut(reconnectConfigurationMessage.getMarbleOut());
        modelPrinter.getDeckGridPrinter().setDeckgrid(reconnectConfigurationMessage.getDeckgridConfiguration());
        System.out.println("You obtained the configuration of " + reconnectConfigurationMessage.getSenderNickname());
    }
}
