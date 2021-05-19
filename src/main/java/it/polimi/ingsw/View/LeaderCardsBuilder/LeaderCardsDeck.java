package it.polimi.ingsw.View.LeaderCardsBuilder;

import it.polimi.ingsw.Colour;
import it.polimi.ingsw.ResourceType;

import java.util.ArrayList;


public class LeaderCardsDeck {

    ArrayList<LeaderCardDiscount> discount = new ArrayList<>();
    ArrayList<LeaderCardDeposit> deposit = new ArrayList<>();
    ArrayList<LeaderCardTransformation> transformation = new  ArrayList<>();
    ArrayList<LeaderCardProduction> production = new ArrayList<>();

    public LeaderCardsDeck(){
        buildDiscount();
        buildDeposit();
        buildTransformation();
        buildProduction();
    }

    private void buildDiscount(){
        discount.add(new LeaderCardDiscount(Colour.YELLOW, Colour.GREEN, ResourceType.SERVANTS,1));
        discount.add(new LeaderCardDiscount(Colour.BLUE, Colour.PURPLE, ResourceType.SHIELDS, 2));
        discount.add(new LeaderCardDiscount(Colour.GREEN,Colour.BLUE,ResourceType.STONES,3));
        discount.add(new LeaderCardDiscount(Colour.YELLOW,Colour.PURPLE,ResourceType.COINS,4));
    }
    private void buildDeposit(){
        deposit.add(new LeaderCardDeposit(ResourceType.COINS,ResourceType.STONES,5));
        deposit.add(new LeaderCardDeposit(ResourceType.STONES,ResourceType.SERVANTS,6));
        deposit.add(new LeaderCardDeposit(ResourceType.SERVANTS,ResourceType.SHIELDS,7));
        deposit.add(new LeaderCardDeposit(ResourceType.SHIELDS,ResourceType.COINS,8));
    }
    private void buildTransformation(){
        transformation.add(new LeaderCardTransformation(Colour.YELLOW,Colour.BLUE,ResourceType.SERVANTS,9));
        transformation.add(new LeaderCardTransformation(Colour.GREEN,Colour.PURPLE,ResourceType.SHIELDS,10));
        transformation.add(new LeaderCardTransformation(Colour.BLUE,Colour.YELLOW,ResourceType.STONES,11));
        transformation.add(new LeaderCardTransformation(Colour.PURPLE,Colour.GREEN,ResourceType.COINS,12));
    }
    private void buildProduction(){
        production.add(new LeaderCardProduction(Colour.YELLOW,ResourceType.SHIELDS,13));
        production.add(new LeaderCardProduction(Colour.BLUE,ResourceType.SERVANTS,14));
        production.add(new LeaderCardProduction(Colour.PURPLE,ResourceType.STONES,15));
        production.add(new LeaderCardProduction(Colour.GREEN,ResourceType.COINS,16));
    }

    public ArrayList<LeaderCardDiscount> getDiscount() {
        return discount;
    }

    public ArrayList<LeaderCardDeposit> getDeposit() {
        return deposit;
    }

    public ArrayList<LeaderCardTransformation> getTransformation() {
        return transformation;
    }

    public ArrayList<LeaderCardProduction> getProduction() {
        return production;
    }
}
