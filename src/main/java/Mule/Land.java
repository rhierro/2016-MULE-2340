package Mule;

import java.io.Serializable;

/**
 * Created by Henry on 9/18/2015.
 * The class that represents land
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

    /**
     * The land constructor
     */
    public Land() {

    }
    
    /**
     * Determines whether or not a piece of land has a mule on it
     * @return if a piece of land has a mule or not
     */
    public boolean hasMule() {
        return hasMule;
    }

    /**
     * Sets the hasMule property of land to be true
     */
    public void setHasMule() {
        hasMule = true;
    }

    /**
     * Sets the type of a piece of land
     * @param type the type of land you wish to set the type to
     */
    public void setType(LandType type) {
        this.type = type;
    }

    /**
     * Determines what type a piece of land is
     * @return the type of land
     */
    public LandType getType() {
        return type;
    }

    /**
     * Sets the production type of a piece of land
     * @param productionType the production type you wish to set the production type to
     */
    public void setProductionType(Mule.MuleType productionType) {
        this.productionType = productionType;
    }

    /**
     * Determines the production type of a piece of land
     * @return the production type of a piece of land
     */
    public Mule.MuleType getProductionType() {
        return productionType;
    }

    /**
     * Sets the owner of a piece of land
     * @param owner the owner you wish to set the owner to
     */
    public void setOwner(Player owner) {
        this.owner = owner;
    }

    /**
     * Determines which player is the owner of the piece of land
     * @return the player that is the owner of the piece of land
     */
    public Player getOwner() {
        return owner;
    }
}
