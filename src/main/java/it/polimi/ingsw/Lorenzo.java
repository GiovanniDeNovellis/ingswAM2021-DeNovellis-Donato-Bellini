package it.polimi.ingsw;
public class Lorenzo {
    private int blackFaithPoints;

    public Lorenzo() {
        this.blackFaithPoints = 0;
    }

    public void addFaithPoints(int number) {
        blackFaithPoints += number;
    }

    public int getBlackFaithPoints() {
        return blackFaithPoints;
    }
}

