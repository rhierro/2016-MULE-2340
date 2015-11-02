package Mule;

import javafx.scene.control.Alert;

import java.util.Random;

/**
 * Created by Henry on 10/21/2015.
 */
public class RandomEvent {

    public MainController mc;

    public void doRandomEvent(){
        Player currentPlayer = mc.getCurrentPlayer();
        Random rand = new Random();
        int nextInt = rand.nextInt(3);
        if (mc.getCurrentPlayerNumber() < 3){ // bad event
            if (nextInt == 1){ // lose money
                currentPlayer.adjustMoney(-690);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Random Event");
                alert.setHeaderText("Bad Luck");
                alert.setContentText(String.format("You were robbed of $690!"));
                alert.showAndWait();
            } else if (nextInt == 2 ) { // lose energy
                currentPlayer.changeInventory(Store.Item.Energy, -3);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Random Event");
                alert.setHeaderText("Bad Luck");
                alert.setContentText(String.format("Your MULES are sick! You lose 3 Energy."));
                alert.showAndWait();
            } else if (nextInt == 3) { // lose food
                currentPlayer.changeInventory(Store.Item.Food, -3);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Random Event");
                alert.setHeaderText("Bad Luck");
                alert.setContentText(String.format("Wolves ate 3 of your food."));
                alert.showAndWait();

            }
        } else { // good event
            if (nextInt == 1){ // gain money
                currentPlayer.adjustMoney(690);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Random Event");
                alert.setHeaderText("Good Luck");
                alert.setContentText(String.format("$690 dropped from the sky!"));
                alert.showAndWait();
            } else if (nextInt == 2 ) { // gain energy
                currentPlayer.changeInventory(Store.Item.Energy, 3);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Random Event");
                alert.setHeaderText("Good Luck");
                alert.setContentText(String.format("Your MULES drank coffee! You gain 3 energy."));
                alert.showAndWait();
            } else if (nextInt == 3) { // gain  food
                currentPlayer.changeInventory(Store.Item.Food, 3);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Random Event");
                alert.setHeaderText("Good Luck");
                alert.setContentText(String.format("Someone gives you an extra 3 foods."));
                alert.showAndWait();

            }
        }

    }

    public void setMainController(MainController mc) {
        this.mc = mc;
    }
}
