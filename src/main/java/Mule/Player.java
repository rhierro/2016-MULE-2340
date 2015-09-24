package Mule;
import javafx.scene.paint.Color;

/**
 * Created by Henry on 9/9/2015.
 */
public class Player {
    private String race;
    private String name;
    private Color color;
    private int money;

    boolean computer = false;


    public Player(String n, String r, Color c) {
        race = r;
        name = n;
        color = c;
        money = 696969;
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
}
