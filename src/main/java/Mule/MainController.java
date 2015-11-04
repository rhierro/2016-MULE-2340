package Mule;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
//import javafx.scene.layout.Priority;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Timer;
import java.util.PriorityQueue;
import java.util.Comparator;
import java.util.TimerTask;

/**
 * Created by Henry on 9/14/2015.
 */
public class MainController {
    private PriorityQueue<Player> players;
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
    private RandomEvent randomEvent;

    FXMLLoader configLoader;
    FXMLLoader playerConfigLoader;
    FXMLLoader playerOverviewLoader;
    FXMLLoader landBuyingMapLoader;
    FXMLLoader mainMapLoader;
    FXMLLoader townMapLoader;
    FXMLLoader storeLoader;
    FXMLLoader playerSummaryLoader;
    MainMapController mainMapController;
    TownMapController townMapController;
    StoreController storeController;
    Scene mainMapScene;
    Scene townMapScene;
    Scene storeScene;

    public MainController() {
        try {
            players = new PriorityQueue<Player>(4, new Comparator<Player>() {
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
            tempPlayers = new PriorityQueue<Player>(4,
                    new Comparator<Player>() {
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

            configLoader = new FXMLLoader(getClass().
                    getResource("ConfigurationScreen.fxml"));
            playerConfigLoader = new FXMLLoader(getClass().
                    getResource("PlayerConfigurationScreen.fxml"));
            playerOverviewLoader = new FXMLLoader(getClass().
                    getResource("PlayerOverviewScreen.fxml"));
            landBuyingMapLoader = new FXMLLoader(getClass().
                    getResource("LandBuyingMap.fxml"));
            mainMapLoader = new FXMLLoader(getClass().
                    getResource("MainMap.fxml"));
            townMapLoader = new FXMLLoader(getClass().
                    getResource("TownMap.fxml"));
            storeLoader = new FXMLLoader(getClass().
                    getResource("Store.fxml"));
            playerSummaryLoader = new FXMLLoader(getClass().
                    getResource("PlayerSummary.fxml"));
            randomEvent = new RandomEvent();
            randomEvent.setMainController(this);
        } catch (Exception e) {
            System.out.println(e.getMessage());
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

    public void startGame() throws Exception {
        store = new Store();
        Scene scene = new Scene(configLoader.load());
        stage.setScene(scene);
        stage.show();
        ConfigurationScreenController controller = configLoader.getController();
        controller.setMainController(this);
    }

    public void loadPlayerConfigurationScreen() throws Exception {
        Scene sceneConfig = new Scene((Parent) playerConfigLoader.load());
        stage.setScene(sceneConfig);
        stage.show();
        PlayerConfigurationScreenController controller = playerConfigLoader.
                getController();
        controller.setMainController(this);
    }

    public void loadPlayerOverviewScreen() throws Exception {
        Scene sceneConfig = new Scene(playerOverviewLoader.load());
        stage.setScene(sceneConfig);
        stage.show();
        PlayerOverviewScreenController controller = playerOverviewLoader.
                getController();
        controller.setMainController(this);
        controller.generateOverview();
    }

    public void loadLandBuyingMapScreen() throws Exception {
        Scene sceneConfig = new Scene(landBuyingMapLoader.load());
        stage.setScene(sceneConfig);
        stage.show();
        LandBuyingMapController controller = landBuyingMapLoader.
                getController();
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

    public void loadPlayerSummaryScreen() throws Exception {
        Scene sceneConfig = new Scene(playerSummaryLoader.load());
        stage.setScene(sceneConfig);
        stage.show();
        PlayerSummaryController controller = playerSummaryLoader.
                getController();
        controller.setMainController(this);
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

    public void initiateRound() throws Exception {
        loadLandBuyingMapScreen();
        store.generatePrices();
        playerSummaryLoader = new FXMLLoader(getClass().
                getResource("PlayerSummary.fxml"));

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
            new Runnable() {
                public void run() {
                    randomEvent.doRandomEvent();
                }
            }.run();



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
            }, 0, 1000);

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
            mainMapLoader =  new FXMLLoader(getClass().
                    getResource("MainMap.fxml"));
            townMapLoader = new FXMLLoader(getClass().
                    getResource("TownMap.fxml"));
            storeLoader = new FXMLLoader(getClass().getResource("Store.fxml"));
            if (currentPlayerNum < 4) { //player nums are (0-3)
                startTurn();
            } else {
                endRound();
            }
        } catch (Exception e) {
            System.out.println("Exception at end turn.");
            System.out.println(e.getMessage());
        }
    }

    public void endRound() {
        try {
            calculateProduction();
            currentRound++;

            players = new PriorityQueue<Player>(tempPlayers);
            tempPlayers.clear();
            currentPlayerNum = 0;
            landBuyingMapLoader = new FXMLLoader(getClass().
                    getResource("LandBuyingMap.fxml"));
            loadPlayerSummaryScreen();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void calculateProduction() {
        for (Land[] landRow : map) {
            for (Land land : landRow) {
                int amount;
                if (land.getOwner() != null) {
                    if (land.getProductionType() != Mule.MuleType.NONE) {
                        //donothing
                        if (land.getOwner().
                                getInventoryAmount(Store.Item.Energy) > 0) {
                            if (land.getProductionType()
                                    == Mule.MuleType.SMITHORE) {
                                if (land.getType() == Land.LandType.Mountain) {
                                    amount = 2;
                                } else {
                                    amount = 1;
                                }
                                land.getOwner().
                                        changeInventory(Store.Item.Smithore,
                                                amount);
                            } else if (land.getProductionType()
                                    == Mule.MuleType.ENERGY) {
                                if (land.getType() == Land.LandType.Plains) {
                                    amount = 2;
                                } else {
                                    amount = 1;
                                }
                                land.getOwner().
                                        changeInventory(Store.Item.Energy,
                                                amount);
                            } else if (land.getProductionType()
                                    == Mule.MuleType.FOOD) {
                                if (land.getType() == Land.LandType.Water) {
                                    amount = 2;
                                } else {
                                    amount = 1;
                                }
                                land.getOwner().
                                        changeInventory(Store.Item.Food,
                                                amount);
                            }
                            land.getOwner().
                                    changeInventory(Store.Item.Energy, -1);
                        }
                    }
                }
            }

        }
    }

    private void saveGame() throws Exception {
        FileOutputStream saveFile = new FileOutputStream("SaveObj.sav");
        ObjectOutputStream save = new ObjectOutputStream(saveFile);
        save.writeObject(players);
        save.writeObject(map);
        save.writeObject(mapNum);
        save.writeObject(store);
        save.writeObject(difficulty);
        save.writeObject(numberOfPlayers);
        save.writeObject(currentPlayerNum);
        save.writeObject(currentRound);
        save.writeObject(currentPlayer);
        save.close();

    }

    private void loadGame() throws Exception {
        FileInputStream saveFile = new FileInputStream("SaveObj.sav");
        ObjectInputStream save = new ObjectInputStream(saveFile);
        players = (PriorityQueue<Player>) save.readObject();
        map = (ObservableList<Land[]>) save.readObject();
        mapNum = (int) save.readObject();
        store = (Store) save.readObject();
        difficulty = (int) save.readObject();
        numberOfPlayers = (int) save.readObject();
        currentPlayerNum = (int) save.readObject();
        currentRound = (int) save.readObject();
        currentPlayer = (Player) save.readObject();
        save.close();
    }
}
