package Mule;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Henry on 9/30/2015.
 * The Store class
 */
public class Store implements Serializable {
    /**
     * The types of items the store has
     */
    public enum Item {
        Smithore,
        Energy,
        Food
    }


    private List<Item> inventory = new ArrayList<>();
    private Map<Item, Integer> inventoryAmount = new HashMap<>();
    private int MulePrice;
    private int smithoreMulePrice;
    private int energyMulePrice;
    private int foodMulePrice;
    private int smithorePrice;
    private int energyPrice;
    private int foodPrice;

    /**
     * Gets the price of a mule
     * @return price
     */
    public int getMulePrice() {
        return MulePrice;
    }

    /**
     * Gets the price of a Smithore Mule attachment
     * @return price
     */
    public int getSmithoreMulePrice() {
        return smithoreMulePrice;
    }

    /**
     * Returns the price of a Energy Mule attachment
     * @return price
     */
    public int getEnergyMulePrice() {
        return energyMulePrice;
    }

    /**
     * Returns the price of a Food Mule attachment
     * @return price
     */
    public int getFoodMulePrice() {
        return foodMulePrice;
    }

    /**
     * Returns the price of a specified item
     * @param item item
     * @return price
     */
    public int getPrice(Store.Item item) {
        if (item == Item.Smithore) {
            return smithorePrice;
        } else if (item == Item.Energy) {
            return energyPrice;
        } else if (item == Item.Food) {
            return foodPrice;
        } else {
            return 0;
        }
    }

    /**
     * Generates the prices of the store's items
     */
    public void generatePrices() {
        MulePrice = 69;
        smithoreMulePrice = 69;
        energyMulePrice = 69;
        foodMulePrice = 69;
        smithorePrice = 6;
        foodPrice = 9;
        energyPrice = 6;
        inventoryAmount.put(Item.Smithore, 69);
        inventoryAmount.put(Item.Energy, 69);
        inventoryAmount.put(Item.Food, 69);
        inventory.add(Item.Smithore);
        inventory.add(Item.Energy);
        inventory.add(Item.Food);
    }

    /**
     * Gets the amouunt of an Item that the store has
     * @param item item
     * @return amount
     */
    public int getInventoryAmount(Store.Item item) {
        if (inventory.contains(item)) {
            return inventoryAmount.get(item);
        } else {
            return 0;
        }
    }

    /**
     * Gets the inventory of the store
     * @return inventory
     */
    public List<Store.Item> getInventory() {
        return inventory;
    }

    /**
     * Adjusts the inventory of the store by a specified amount
     * @param item item
     * @param adjustment amount to add (can be negative)
     */
    public void changeInventory(Item item, int adjustment) {
        int amount = inventoryAmount.get(item);
        inventoryAmount.put(item, amount + adjustment);
        if (!inventory.contains(item)) {
            inventory.add(item);
        }
    }
}
