package Mule;
import javafx.scene.paint.Color;

/**
 * Created by Henry on 9/9/2015.
 */
public class Player {
    private String race;
    private String name;
    private Color color;

    public Player(String n, String r, Color c) {
        race = r;
        name = n;
        color = c;
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
}
