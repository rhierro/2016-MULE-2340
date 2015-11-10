package Mule;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.scene.paint.Color;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Henry on 9/9/2015.
 * The player class
 */
public class Player implements Serializable {
    private String race;
    private String name;
    private double r;
    private double g;
    private double b;
    private int money;
    private Mule mule;
    private ObservableMap<Store.Item, Integer> inventoryAmount = FXCollections.observableHashMap();
    private ObservableList<Store.Item> inventory = FXCollections.observableArrayList();

    private boolean computer = false;

    /**
     * The player constructor
     */
    public Player(String n, String r, Color c) {
        race = r;
        name = n;
        this.r =  (c.getRed() );
        this.g =  (c.getGreen());
        this.b =  (c.getBlue() );
        money = 696969;
        inventory.add(Store.Item.Energy);
        inventoryAmount.put(Store.Item.Energy, 6);
    }

    /**
     * The default (computer) player constructor
     */
    public Player(String n, String r, Color c, boolean comp) {
        this(n, r, c);
        computer = comp;
    }

    /**
     * Gets the race of the player
     * @return race
     */
    public String getRace() {
        return race;
    }

    /**
     * Sets the race of the player
     * @param race race
     */
    public void setRace(String race) {
        this.race = race;
    }

    /**
     * Gets the name of the player
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the player
     * @param name name of player
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the color of the player
     * @return JavaFX Color
     */
    public Color getColor() {
        return new Color(r, g, b, 1);
    }

    /**
     * Sets the color of the player
     * @param color color
     */
    public void setColor(Color color) {
        this.r  = color.getRed();
        this.g = color.getGreen();
        this.b = color.getBlue();
    }

    /**
     * Returns if the player is a cpu or not
     * @return bool
     */
    public boolean isComputer() {
        return computer;
    }

    /**
     * Returns how much money the player has
     * @return money
     */
    public int getMoney() {
        return money;
    }

    /**
     * Adjusts the players money
     * @param difference amount to add to player
     *                   (can be negative)
     */
    public void adjustMoney(int difference) {
        money += difference;
    }

    /**
     * Calculates and gets the player's score
     * @return score
     */
    public int getScore() {
        int score = 0;
        score += money;
        for (Store.Item item : inventoryAmount.keySet()) {
            score += inventoryAmount.get(item) * 1000000;
        }
        return score;
    }


    /**
     * Returns the mule currently in player's possession
     * @return Mule
     */
    public Mule getMule() {
        return mule;
    }

    /**
     * Sets the mule that the player currently possesses
     * @param mule mule
     */
    public void setMule(Mule mule) {
        this.mule = mule;
    }

    /**
     * Takes away the player's mule
     */
    public void removeMule() {
        mule = null;
    }

    /**
     * Returns if the player has a mule or not
     * @return bool
     */
    public boolean hasMule() {
        return (mule != null);
    }

    /**
     * Returns the player's inventory
     * @return inventory
     */
    public List<Store.Item> getInventory() {
        return inventory;
    }

    /**
     * Gets how much of an Item the player has
     * @param item Store.Item
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
     * Sets the amount of the Item in player's inventory
     * to the number specified
     * @param item item
     * @param i amount
     */
    public void setInventoryAmount(Store.Item item, int i) {
        if (inventoryAmount.get(item) != null) {
            inventoryAmount.put(item, i);
        }

    }

    /**
     * Adjusts the amount of the Item in the player's inventory
     * @param item item
     * @param adjustment amount to add (can be negative)
     */
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
