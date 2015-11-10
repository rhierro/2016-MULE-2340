package Mule;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;

/**
 * Created by Henry on 9/8/2015.
 * The Mule Game class
 */
public class MuleGame extends Application {

    private ObservableList<Player> players = FXCollections.
            observableArrayList();

    /**
     * The constructor of the Mule Game
     */
    public MuleGame() {
    //Constructor
    }

    /**
     * Starts the game
     * @param stage stage to display the game in
     * @throws Exception
     */
    public void start(Stage stage) throws Exception {
        MainController mc = new MainController();
        mc.setStage(stage);
        mc.startGame();

    }

}
