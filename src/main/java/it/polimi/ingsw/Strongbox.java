package it.polimi.ingsw;

import java.util.TreeMap;

public class Strongbox {

    TreeMap<ResourceType, Integer> resourcesContained = new TreeMap<>();
    String printTest;
    int faithPoints;

    public void addResources(ResourceType resource, int quantity){
        if(quantity<0)
            quantity=0;
        if( resourcesContained.get(resource)!= null )
            quantity += resourcesContained.get(resource);
        resourcesContained.put(resource, quantity);
    }

    public void viewAllResources(){
        for (ResourceType resource : resourcesContained.keySet()) {
            System.out.println("Risorsa: " + resource + " ,Quantità: " + resourcesContained.get(resource));
        }

         /*   //for testing...
    public String viewAllResources(){
        for (ResourceType resource : resourcesContained.keySet()) {
            printTest = printTest + ("Risorsa: " + resource + " ,Quantità: " + resourcesContained.get(resource));
        }
        return printTest;
    }
        */
    }

    public int getVictoryPoints(){
        for (ResourceType resource : resourcesContained.keySet()) {
            faithPoints += resourcesContained.get(resource);
        }
        faithPoints /= 5;
    return faithPoints;
    }

    public boolean removeResources(ResourceType resource, int quantity){
        if(quantity<0)
            quantity=0;
        quantity = -quantity;
        if( resourcesContained.get(resource)!= null )
            quantity += resourcesContained.get(resource);
        else
            return false;
        if( quantity>=0 ) {
            resourcesContained.put(resource, quantity);
            return true;
        }
        else
            return false;
    }
}
