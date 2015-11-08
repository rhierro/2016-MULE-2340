package Mule;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.scene.paint.Color;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Henry on 9/9/2015.
 */
public class Player implements Serializable{
    private String race;
    private String name;
    private Color color;
    private int money;
    private Mule mule;
    private ObservableMap<Store.Item, Integer> inventoryAmount = FXCollections.observableHashMap();
    private ObservableList<Store.Item> inventory = FXCollections.observableArrayList();

    boolean computer = false;


    public Player(String n, String r, Color c) {
        race = r;
        name = n;
        color = c;      money = 696969;
        inventory.add(Store.Item.Energy);
        inventoryAmount.put(Store.Item.Energy, 6);
    }

    public Player(String n, String r, Color c, boolean comp) {
        this(n, r, c);
        computer = comp;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public boolean isComputer() {
        return computer;
    }

    public int getMoney() {
        return money;
    }

    public void adjustMoney(int difference) {
        money += difference;
    }

    public int getScore() {
        int score = 0;
        score += money;
        for (Store.Item item : inventoryAmount.keySet()) {
            score += inventoryAmount.get(item) * 1000000;
        }
        return score;
    }

    //returns mule currently in player's possession
    public Mule getMule() {
        return mule;
    }

    public void setMule(Mule mule) {
    this.mule = mule;
    }

    public void removeMule() {
        mule = null;
    }

    public boolean hasMule() {
        return (mule != null);
    }

    public ObservableList<Store.Item> getInventory() {
        return inventory;
    }

    public int getInventoryAmount(Store.Item item) {
        if (inventory.contains(item)) {
            return inventoryAmount.get(item);
        } else {
            return 0;
        }
    }

    public void changeInventory(Store.Item item, int adjustment) {

        int amount = 0;
        if (inventoryAmount.get(item) != null) {
            amount = inventoryAmount.get(item);
        }
        
        int newAmount = amount + adjustment;
        if (newAmount < 0) {
            newAmount = 0;
        }

        inventoryAmount.put(item, newAmount);

        if (!inventory.contains(item)) {
            inventory.add(item);
        }
    }


}
