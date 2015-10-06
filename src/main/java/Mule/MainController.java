package Mule;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Henry on 9/14/2015.
 */
public class MainController {
    private PriorityQueue<Player> players ;
    private PriorityQueue<Player> tempPlayers;
    private ObservableList<Land[]>  map = FXCollections.observableArrayList();
    private int mapNum;

    private Stage stage;

    private Store store;

    final int ROUNDTIME = 20;
    private int difficulty;
    private int numberOfPlayers;
    private int currentPlayerNum = 0;
    private int currentRound = 1;
    private Player currentPlayer;

    private Timer timer;
    private Timer tickTimer;
    private int roundTime = ROUNDTIME; // in seconds

    FXMLLoader configLoader;
    FXMLLoader playerConfigLoader;
    FXMLLoader playerOverviewLoader;
    FXMLLoader landBuyingMapLoader;
    FXMLLoader mainMapLoader;
    FXMLLoader townMapLoader;
    FXMLLoader storeLoader;
    MainMapController mainMapController;
    TownMapController townMapController;
    StoreController storeController;
    Scene mainMapScene;
    Scene townMapScene;
    Scene storeScene;



    public MainController() {
        try {
            players = new PriorityQueue<>(4, new Comparator<Player>() {
                public int compare(Player p1, Player p2) {
                    if (p1.getScore() > p2.getScore()) {
                        return 1;
                    } else if (p1.getScore() < p2.getScore()) {
                        return -1;
                    } else {
                        return 0;
                    }
                }
            });
            tempPlayers = new PriorityQueue<>(4, new Comparator<Player>() {
                public int compare(Player p1, Player p2) {
                    if (p1.getScore() > p2.getScore()) {
                        return 1;
                    } else if (p1.getScore() < p2.getScore()) {
                        return -1;
                    } else {
                        return 0;
                    }
                }
            });
            configLoader = new FXMLLoader(getClass().getResource("ConfigurationScreen.fxml"));
            playerConfigLoader = new FXMLLoader(getClass().getResource("PlayerConfigurationScreen.fxml"));
            playerOverviewLoader = new FXMLLoader(getClass().getResource("PlayerOverviewScreen.fxml"));
            landBuyingMapLoader = new FXMLLoader(getClass().getResource("LandBuyingMap.fxml"));
            mainMapLoader = new FXMLLoader(getClass().getResource("MainMap.fxml"));
            townMapLoader = new FXMLLoader(getClass().getResource("TownMap.fxml"));
            storeLoader = new FXMLLoader(getClass().getResource("Store.fxml"));
        } catch (Exception e) {

        }
    }

    //region Getters and Setters
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

    public PriorityQueue<Player> getPlayers() {
        return players;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Stage getStage() {
        return stage;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public int getCurrentPlayerNumber() {
        return currentPlayerNum;
    }

    public int getCurrentRound() {
        return currentRound;
    }

    public int getRoundTime() {
        return roundTime;
    }

    public Store getStore() {
        return store;
    }

    public void advancePlayer() {
        currentPlayerNum = (currentPlayerNum + 1) % 4;
    }
    //endregion

    public void startGame() throws Exception{
        store = new Store();
        Scene scene = new Scene(configLoader.load());
        stage.setScene(scene);
        stage.show();
        ConfigurationScreenController controller = configLoader.getController();
        controller.setMainController(this);
    }

    public void loadPlayerConfigurationScreen() throws Exception{
        Scene sceneConfig = new Scene(playerConfigLoader.load());
        stage.setScene(sceneConfig);
        stage.show();
        PlayerConfigurationScreenController controller = playerConfigLoader.getController();
        controller.setMainController(this);


    }

    public void loadPlayerOverviewScreen() throws Exception {
        Scene sceneConfig = new Scene(playerOverviewLoader.load());
        stage.setScene(sceneConfig);
        stage.show();
        PlayerOverviewScreenController controller = playerOverviewLoader.getController();
        controller.setMainController(this);
        controller.generateOverview();
    }

    public void loadLandBuyingMapScreen() throws Exception{
        Scene sceneConfig = new Scene(landBuyingMapLoader.load());
        stage.setScene(sceneConfig);
        stage.show();
        LandBuyingMapController controller = landBuyingMapLoader.getController();
        controller.setMainController(this);
        controller.generateButtons();
    }

    public void loadMainMapScreen() throws Exception {
        mainMapScene = new Scene(mainMapLoader.load());
        mainMapController = mainMapLoader.getController();
        mainMapController.setMainController(this);
        mainMapController.generateButtons();
    }

    public void loadStoreScreen() throws Exception {
        storeScene = new Scene(storeLoader.load());
        storeController = storeLoader.getController();
        storeController.setMainController(this);
    }

    public void switchtoMainMapScreen() {
        stage.setScene(mainMapScene);
        stage.show();
    }

    public void switchtoTownMapScreen() {
        stage.setScene(townMapScene);
        stage.show();
    }

    public void switchtoStoreScreen() {
        stage.setScene(storeScene);
        stage.show();
    }

    public void loadTownMapScreen() throws Exception {
        townMapScene = new Scene(townMapLoader.load());
        townMapController = townMapLoader.getController();
        townMapController.setMainController(this);

    }

    public void initiateRound() throws Exception{
        loadLandBuyingMapScreen();
        store.generatePrices();
    }

    public void startTurn() {
        try {
            roundTime = ROUNDTIME; //+ (4 - currentPlayerNum) * 2;
            currentPlayer = players.poll();
            tempPlayers.add(currentPlayer);
            loadTownMapScreen();
            loadMainMapScreen();
            loadStoreScreen();
            switchtoMainMapScreen();

            timer = new Timer();
            // timer task for timing round
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    Platform.runLater(new Runnable() {
                        public void run() {
                            endTurn();
                        }
                    });
                }
            }, roundTime * 1000);
            tickTimer = new Timer();
            tickTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    tickTimer();
                }
            },0, 1000);



        } catch (Exception e) {
            System.out.println("exception at start turn");
            //System.out.println(e.getMessage());
        }
    }

    private void tickTimer() {
        roundTime--;
        if (mainMapController != null) {
            mainMapController.updateTime();
        }
        if (townMapController != null) {
            townMapController.updateTime();
        }

    }


    public void endTurn() {
        try {

            timer.cancel();
            tickTimer.cancel();
            currentPlayerNum++;
            mainMapLoader =  new FXMLLoader(getClass().getResource("MainMap.fxml"));
            townMapLoader = new FXMLLoader(getClass().getResource("TownMap.fxml"));
            storeLoader = new FXMLLoader(getClass().getResource("Store.fxml"));
            if (currentPlayerNum < 4) { //player nums are (1-4)
                startTurn();
            } else {
                endRound();
            }
        } catch (Exception e) {
            System.out.println("exception at end turn");
            System.out.println(e.getMessage());
        }
    }

    public void endRound() {
        try {
            currentRound++;
            //TEMP: SKIP AUCTION AND RETURN TO MAP
            players = new PriorityQueue<>(tempPlayers);
            tempPlayers.clear();
            currentPlayerNum = 0;
            landBuyingMapLoader = new FXMLLoader(getClass().getResource("LandBuyingMap.fxml"));
            initiateRound();
            //todo: remove temp
            //loadMainMapScreen();
            //todo: show auction
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
