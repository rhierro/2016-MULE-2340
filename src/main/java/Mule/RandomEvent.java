package Mule;

import javafx.scene.control.Alert;

import java.util.Random;

/**
 * Created by Henry on 10/21/2015.
 * The Random Event class
 */
public class RandomEvent {
    
    private MainController mc;

    /**
     * Spawns a Random event
     */
    public void doRandomEvent() {
        Player currentPlayer = mc.getCurrentPlayer();
        Random rand = new Random();
        int nextInt = rand.nextInt(3);
        if (mc.getCurrentPlayerNumber() < 3) { // bad event
            if (nextInt == 1) { // lose money
                currentPlayer.adjustMoney(-690);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Random Event");
                alert.setHeaderText("Bad Luck");
                alert.setContentText("You were robbed of $690!");
                alert.showAndWait();
            } else if (nextInt == 2) { // lose energy
                currentPlayer.changeInventory(Store.Item.Energy, -3);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Random Event");
                alert.setHeaderText("Bad Luck");
                alert.setContentText("Your MULES are sick! You lose 3 Energy.");
                alert.showAndWait();
            } else if (nextInt == 3) { // lose food
                currentPlayer.changeInventory(Store.Item.Food, -3);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Random Event");
                alert.setHeaderText("Bad Luck");
                alert.setContentText("Wolves ate 3 of your food.");
                alert.showAndWait();

            }
        } else { // good event
            if (nextInt == 1) { // gain money
                currentPlayer.adjustMoney(690);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Random Event");
                alert.setHeaderText("Good Luck");
                alert.setContentText("$690 dropped from the sky!");
                alert.showAndWait();
            } else if (nextInt == 2) { // gain energy
                currentPlayer.changeInventory(Store.Item.Energy, 3);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Random Event");
                alert.setHeaderText("Good Luck");
                alert.setContentText("Your MULES drank coffee! You gain 3 energy.");
                alert.showAndWait();
            } else if (nextInt == 3) { // gain  food
                currentPlayer.changeInventory(Store.Item.Food, 3);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Random Event");
                alert.setHeaderText("Good Luck");
                alert.setContentText("Someone gives you an extra 3 foods.");
                alert.showAndWait();

            }
        }

    }

    /**
     * Sets the reference back to the main controller
     * @param mc maincontroller object
     */
    public void setMainController(MainController mc) {
        this.mc = mc;
    }
}
