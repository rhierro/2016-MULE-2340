package Mule;

/**
 * Created by Henry on 9/18/2015.
 */
public class Land {
    private String type = "plains";
    private Player owner;
    private int numOfMules;
    private int production;
    private int x;
    private int y;

    public Land() {

    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public Player getOwner() {
        return owner;
    }
}
