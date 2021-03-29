package it.polimi.ingsw;

public class Level {
    private ResourceType currentResourceType;
    private int maxDimension;
    private int numResources;

    public Level(int maxDimension) {
        this.numResources=0;
        this.maxDimension=maxDimension;
        this.currentResourceType=null;
    }

    boolean addResources(ResourceType type, int quantity){
        if(quantity>maxDimension) return false;
        this.currentResourceType=type;
        this.numResources=quantity;
        return true;
    }

    boolean getResources(int quantity){
        if(quantity>this.numResources || quantity <0) return false;
        this.numResources-=quantity;
        if(this.numResources==0) this.currentResourceType=null;
        return true;
    }

    int getCurrNumResources(){return this.numResources; }

    int getMaxNumResources(){return this.maxDimension;}

    ResourceType getResourceType(){return this.currentResourceType;}
}
