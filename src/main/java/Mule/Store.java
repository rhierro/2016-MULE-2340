package Mule;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;

import java.io.Serializable;
//import java.util.Map;

/**
 * Created by Henry on 9/30/2015.
 */
public class Store implements Serializable {
    public enum Item {
        Smithore,
        Energy,
        Food
    }


    private ObservableList<Item> inventory = FXCollections.
            observableArrayList();
    private ObservableMap<Item, Integer> inventoryAmount
            = FXCollections.observableHashMap();
    private int MulePrice;
    private int smithoreMulePrice;
    private int energyMulePrice;
    private int foodMulePrice;
    private int smithorePrice;
    private int energyPrice;
    private int foodPrice;

    public int getMulePrice() {
        return MulePrice;
    }
    public int getSmithoreMulePrice() {
        return smithoreMulePrice;
    }
    public int getEnergyMulePrice() {
        return energyMulePrice;
    }
    public int getFoodMulePrice() {
        return foodMulePrice;
    }

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

    public int getInventoryAmount(Store.Item item) {
        if (inventory.contains(item)) {
            return inventoryAmount.get(item);
        } else {
            return 0;
        }
    }

    public ObservableList<Store.Item> getInventory() {
        return inventory;
    }

    public void changeInventory(Item item, int adjustment) {
        int amount = inventoryAmount.get(item);
        inventoryAmount.put(item, amount + adjustment);
        if (!inventory.contains(item)) {
            inventory.add(item);
        }
    }
}
