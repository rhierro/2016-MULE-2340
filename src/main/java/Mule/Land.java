package Mule;

import java.io.Serializable;

/**
 * Created by Henry on 9/18/2015.
 */
public class Land implements Serializable {
    public enum LandType {
        Plains,
        Water,
        Mountain
    }
    private LandType type = LandType.Plains;
    private Player owner;
    private boolean hasMule = false;
    private Mule.MuleType productionType = Mule.MuleType.NONE;
    private int x;
    private int y;

    public Land() {

    }

    public boolean hasMule() {
        return hasMule;
    }

    public void setHasMule() {
        hasMule = true;
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
