package Mule;

import java.io.Serializable;

/**
 * Created by Henry on 9/30/2015.
 * The Mule class
 */
public class Mule implements Serializable {
    
    /**
     * The types of Mules
     */
    public enum MuleType {
        NONE,
        FOOD,
        ENERGY,
        SMITHORE
    }

    private MuleType type;

    /**
     * Sets the type of the mule
     * @param type MuleType
     */
    public void setType(MuleType type) {
        this.type = type;
    }

    /**
     * Gets the type of the mule
     * @return MuleType
     */
    public MuleType getType() {
        return type;
    }
}
