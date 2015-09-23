package Mule;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by Henry on 9/14/2015.
 */
public class MainController {
    private ObservableList<Player> players = FXCollections.observableArrayList();
    private ObservableList<Land[]>  map = FXCollections.observableArrayList();
    private int mapNum;

    private Stage stage;

    private int difficulty;
    private int numberOfPlayers;
    private int currentPlayer = 0;

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

    public void setMapNum(int i) {
        this.mapNum = i;
    }

    public int getMapNum() {
        return mapNum;
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

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Stage getStage() {
        return stage;
    }

    public Player getCurrentPlayer() {
        return players.get(currentPlayer);
    }

    public int getCurrentPlayerNumber() {
        return currentPlayer;
    }

    public void advancePlayer() {
        currentPlayer = (currentPlayer + 1) % 4;
    }

    public void startGame() throws Exception{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ConfigurationScreen.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        ConfigurationScreenController controller = loader.getController();
        controller.setMainController(this);
    }

    public void loadPlayerConfigurationScreen() throws Exception{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("PlayerConfigurationScreen.fxml"));
        Parent config = loader.load();
        Scene sceneConfig = new Scene(config);
        Stage stageN = stage;
        stageN.setScene(sceneConfig);
        stageN.show();
        PlayerConfigurationScreenController controller = loader.getController();
        controller.setMainController(this);
    }

    public void loadPlayerOverviewScreen() throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("PlayerOverviewScreen.fxml"));
        Parent config = loader.load();
        Scene sceneConfig = new Scene(config);
        Stage stageN = stage;
        stageN.setScene(sceneConfig);
        stageN.show();
        PlayerOverviewScreenController controller = loader.getController();
        controller.setMainController(this);
        controller.generateOverview();
    }

    public void loadLandBuyingMapScreen() throws Exception{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("LandBuyingMap.fxml"));
        Parent config = loader.load();
        Scene sceneConfig = new Scene(config);
        Stage stageN = stage;
        stageN.setScene(sceneConfig);
        stageN.show();
        LandBuyingMapController controller = loader.getController();
        controller.setMainController(this);
        controller.generateButtons();
    }

    public void loadMainMapScreen() throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("MainMap.fxml"));
        Parent config = loader.load();
        Scene sceneConfig = new Scene(config);
        Stage stageN = stage;
        stageN.setScene(sceneConfig);
        stageN.show();
        MainMapController controller = loader.getController();
        controller.setMainController(this);
        controller.generateButtons();
    }

    public void loadTownMapScreen() throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("TownMap.fxml"));
        Parent config = loader.load();
        Scene sceneConfig = new Scene(config);
        Stage stageN = stage;
        stageN.setScene(sceneConfig);
        stageN.show();
        TownMapController controller = loader.getController();
        controller.setMainController(this);

    }

    public void initiateRound() throws Exception{
        loadLandBuyingMapScreen();
    }

    public void startTurn() {

    }
}
