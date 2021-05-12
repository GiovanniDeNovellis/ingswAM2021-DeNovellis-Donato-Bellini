package it.polimi.ingsw.View.Printers;

import it.polimi.ingsw.DevelopmentCard;
import it.polimi.ingsw.ExtraDeposit;
import it.polimi.ingsw.ResourceType;
import it.polimi.ingsw.WareHouseDepot;

import java.util.HashMap;
import java.util.Map;

public class PersonalBoardPrinter implements Printable{
    private String ownerNickname;
    private DevelopmentCard[] developmentCards = new DevelopmentCard[3]; //RICORDA CONTROLLO A NULL SE UNO SLOT E' VUOTO
    private WareHouseDepot wareHouseDepot = new WareHouseDepot();
    private Map<ResourceType,Integer> strongbox = new HashMap<>();
    private ExtraDeposit extraDeposit1 = new ExtraDeposit(null);
    private ExtraDeposit extraDeposit2 = new ExtraDeposit(null);
    private int faithPoints=0;
    private int[] faithCards = {2,3,4};


    @Override
    public void print() {

    }

    public void setDevelopmentCards(DevelopmentCard[] developmentCards) {
        this.developmentCards = developmentCards;
    }

    public void setWareHouseDepot(WareHouseDepot wareHouseDepot) {
        this.wareHouseDepot = wareHouseDepot;
    }

    public void setStrongbox(Map<ResourceType, Integer> strongbox) {
        this.strongbox = strongbox;
    }

    public void setExtraDeposit1(ExtraDeposit extraDeposit1) {
        this.extraDeposit1 = extraDeposit1;
    }

    public void setExtraDeposit2(ExtraDeposit extraDeposit2) {
        this.extraDeposit2 = extraDeposit2;
    }

    public void setFaithPoints(int faithPoints) {
        this.faithPoints = faithPoints;
    }

    public void setFaithCards(int[] faithCards) {
        this.faithCards = faithCards;
    }

    public void setOwnerNickname(String ownerNickname) {
        this.ownerNickname = ownerNickname;
    }
}