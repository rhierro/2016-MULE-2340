package Mule;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by Henry on 9/14/2015.
 */
public class MainController {
    private ObservableList<Player> players = FXCollections.observableArrayList();
    private ObservableList<Land[]>  map = FXCollections.observableArrayList();

    private int difficulty;
    private int numberOfPlayers;
    private Player currentPlayer;

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

    public void setMap(ObservableList<Land[]> map) {
        this.map = map;
    }

    public ObservableList<Land[]> getMap() {
        return map;
    }

    public void setNumberOfPlayers(int numOfPlayers) {
        this.numberOfPlayers = numOfPlayers;
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public ObservableList<Player> getPlayers() {
        return players;
    }

    public void startGame(Stage stage) throws Exception{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ConfigurationScreen.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        ConfigurationScreenController controller = loader.getController();
        controller.setMainController(this);




    }
}
