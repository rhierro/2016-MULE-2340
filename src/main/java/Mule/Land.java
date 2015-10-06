package Mule;

/**
 * Created by Henry on 9/18/2015.
 */
public class Land {
    public enum LandType {
        Plains,
        Water,
        Mountain
    }
    private LandType type = LandType.Plains;
    private Player owner;
    private int numOfMules;
    private Mule.MuleType productionType;
    private int x;
    private int y;

    public Land() {

    }

    public void setType(LandType type) {
        this.type = type;
    }

    public LandType getType() {
        return type;
    }

    public void setProductionType(Mule.MuleType productionType) {
        this.productionType = productionType;
    }

    public Mule.MuleType getProductionType() {
        return productionType;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public Player getOwner() {
        return owner;
    }

}