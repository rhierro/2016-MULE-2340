package Mule;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Created by Henry on 9/14/2015.
 */
public class MainController {
    private ObservableList<Player> players = FXCollections.observableArrayList();
    private int difficulty;
    private int map;
    private int numberOfPlayers;

    public void addPlayer(Player p) {
        players.add(p);
    }

    public void clearPlayers() {
        players.clear();
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setMap(int map) {
        this.map = map;
    }

    public int getMap() {
        return map;
    }

    public void setNumberOfPlayers(int numOfPlayers) {
        this.numberOfPlayers = numOfPlayers;
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }
}
