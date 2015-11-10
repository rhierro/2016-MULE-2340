package Mule;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;
import java.util.*;

/**
 * Created by Henry
 */
public class MainController implements Serializable {
    private PriorityQueue<Player> players ;
    private PriorityQueue<Player> tempPlayers;
    private List<Land[]>  map = new ArrayList<>();

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

    /**
     * Add a player to the game
     * @param p Player
     */
    public void addPlayer(Player p) {
        players.add(p);
    }

    /**
     * Delete all added players
     */
    public void clearPlayers() {
        players.clear();
    }

    /**
     * Set difficulty of game
     * @param difficulty difficulty level
     */
    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    /**
     * Returns the difficulty of the current game
     * @return difficutly
     */
    public int getDifficulty() {
        return difficulty;
    }

    /**
     * Sets the map to use
     * @param map 2x2 array of lands in map
     */
    public void setMap(List<Land[]> map) {
        this.map = map;
    }

    /**
     * Returns the map in use
     * @return 2x2 array of lands in map
     */
    public List<Land[]> getMap() {
        return map;
    }

    /**
     * Sets the map to use within the predefined maps
     * @param i number of map
     */
    public void setMapNum(int i) {
        this.mapNum = i;
    }

    /**
     * Returns the number of the map being used
     * @return number  of map
     */
    public int getMapNum() {
        return mapNum;
    }

    /**
     * Sets the number of human players
     * @param numOfPlayers number of human players
     */
    public void setNumberOfPlayers(int numOfPlayers) {
        this.numberOfPlayers = numOfPlayers;
    }

    /**
     * Returns the number of human players
     * @return number of human players
     */
    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    /**
     * Returns the players and their current order
     * @return players
     */
    public PriorityQueue<Player> getPlayers() {
        return players;
    }

    /**
     * Sets JavaFX to use for displaying
     * @param stage JavaFX stage
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * Returns the JavaFX stage being used
     * @return stage
     */
    public Stage getStage() {
        return stage;
    }

    /**
     * Returns the player who is currently in turn
     * @return current player
     */
    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Returns the current player's position relative
     * to all players
     * @return player's position
     */
    public int getCurrentPlayerNumber() {
        return currentPlayerNum;
    }

    /**
     * Returns the current round number
     * @return current round number
     */
    public int getCurrentRound() {
        return currentRound;
    }

    /**
     * Returns the amount of time left in the round
     * @return amount of time left in round
     */
    public int getRoundTime() {
        return roundTime;
    }

    /**
     * Returns the store in use for the current game
     * @return store
     */
    public Store getStore() {
        return store;
    }



    //endregion

    /**
     * Starts the current game
     * @throws Exception
     */
    public void startGame() throws Exception {
        store = new Store();
        Scene scene = new Scene(configLoader.load());
        stage.setScene(scene);
        stage.show();
        ConfigurationScreenController controller = configLoader.getController();
        controller.setMainController(this);
    }

    /**
     * Loads a new Player configuration screen
     * @throws Exception
     */
    public void loadPlayerConfigurationScreen() throws Exception {
        Scene sceneConfig = new Scene((Parent) playerConfigLoader.load());
        stage.setScene(sceneConfig);
        stage.show();
        PlayerConfigurationScreenController controller = playerConfigLoader.
                getController();
        controller.setMainController(this);
    }

    /**
     * Loads a new Player Overview Screen
     * @throws Exception
     */
    public void loadPlayerOverviewScreen() throws Exception {
        Scene sceneConfig = new Scene(playerOverviewLoader.load());
        stage.setScene(sceneConfig);
        stage.show();
        PlayerOverviewScreenController controller = playerOverviewLoader.
                getController();
        controller.setMainController(this);
        controller.generateOverview();
    }

    /**
     * Loads a new Land Buying Map Screen
     * @throws Exception
     */
    public void loadLandBuyingMapScreen() throws Exception {
        Scene sceneConfig = new Scene(landBuyingMapLoader.load());
        stage.setScene(sceneConfig);
        stage.show();
        LandBuyingMapController controller = landBuyingMapLoader.
                getController();
        controller.setMainController(this);
        controller.generateButtons();
    }

    /**
     * Loads a new main map screen
     * @throws Exception
     */
    public void loadMainMapScreen() throws Exception {
        mainMapScene = new Scene(mainMapLoader.load());
        mainMapController = mainMapLoader.getController();
        mainMapController.setMainController(this);
        mainMapController.generateButtons();
    }

    /**
     * Loads a new store screen
     * @throws Exception
     */
    public void loadStoreScreen() throws Exception {
        storeScene = new Scene(storeLoader.load());
        storeController = storeLoader.getController();
        storeController.setMainController(this);
    }

    /**
     * Loads a new player summary screen
     * @throws Exception
     */
    public void loadPlayerSummaryScreen() throws Exception {
        Scene sceneConfig = new Scene(playerSummaryLoader.load());
        stage.setScene(sceneConfig);
        stage.show();
        PlayerSummaryController controller = playerSummaryLoader.
                getController();
        controller.setMainController(this);
    }

    /**
     * Switches to the loaded main map screen
     */
    public void switchtoMainMapScreen() {
        stage.setScene(mainMapScene);
        stage.show();
    }

    /**
     * Switches to the loaded town map screen
     */
    public void switchtoTownMapScreen() {
        stage.setScene(townMapScene);
        stage.show();
    }

    /**
     * Switches to the loaded store screen
     */
    public void switchtoStoreScreen() {
        stage.setScene(storeScene);
        stage.show();
    }

    /**
     * Loads a new town map screen
     * @throws Exception
     */
    public void loadTownMapScreen() throws Exception {
        townMapScene = new Scene(townMapLoader.load());
        townMapController = townMapLoader.getController();
        townMapController.setMainController(this);

    }

    /**
     * Initiates the round
     * @throws Exception
     */
    public void initiateRound() throws Exception {
        loadLandBuyingMapScreen();
        store.generatePrices();
        playerSummaryLoader = new FXMLLoader(getClass().
                getResource("PlayerSummary.fxml"));

    }

    /**
     * Starts a player's turn
     * Starts timer for current turn
     */
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
            e.printStackTrace();
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


    /**
     * End's the current player's turn
     */
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

    /**
     * Ends the current round
     */
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

    /**
     * Calculates the production of the lands in
     * its current status
     */
    public void calculateProduction() {
        for (Land[] landRow : map) {
            for (Land land : landRow) {
                int amount;
                if (land.getOwner() != null) {
                    if (land.getProductionType() != Mule.MuleType.NONE) {
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
                            land.getOwner().changeInventory(Store.Item.Energy, -1);
                        }
                    }
                }
            }
        }
    }

    /**
     * Saves the current game state
     */
    public void saveGame() {
        try {
            FileOutputStream saveFile = new FileOutputStream("SaveObj.sav");
            ObjectOutputStream save = new ObjectOutputStream(saveFile);
            save.writeObject(players.size());
            for (Player p : players) {
                save.writeObject(p);
            }
            save.writeObject(currentPlayer);
            save.writeObject(tempPlayers.size());
            for (Player tp: tempPlayers) {
                save.writeObject(tp);
            }
            save.writeObject(map);
            save.writeObject(mapNum);
            save.writeObject(store);
            save.writeObject(difficulty);
            save.writeObject(numberOfPlayers);
            save.writeObject(currentPlayerNum);
            save.writeObject(currentRound);
            save.writeObject(currentPlayer);
            save.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Loads the last saved game state
     */
    public void loadGame(){
        try {
            FileInputStream saveFile = new FileInputStream("SaveObj.sav");
            ObjectInputStream save = new ObjectInputStream(saveFile);
            int p = (int) save.readObject();
            for (int i = 0; i < p; i++) {
                players.add((Player) save.readObject());
            }
            players.add((Player) save.readObject());
            int tp = (int) save.readObject();
            for (int i = 0; i < tp; i++) {
                tempPlayers.add((Player) save.readObject());
            }
            map = (List<Land[]>) save.readObject();
            mapNum = (int) save.readObject();
            store = (Store) save.readObject();
            difficulty = (int) save.readObject();
            numberOfPlayers = (int) save.readObject();
            currentPlayerNum = (int) save.readObject();
            currentRound = (int) save.readObject();
            currentPlayer = (Player) save.readObject();
            save.close();
            startTurn();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
