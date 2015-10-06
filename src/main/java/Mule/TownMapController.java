package Mule;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Alert;
import javafx.scene.text.Text;

import java.util.Random;

/**
 * Created by Henry on 9/22/2015.
 */
public class TownMapController {

    private MainController mc;

    @FXML
    private Button returnToMap_button;
    @FXML
    private Button pub_button;
    @FXML
    private Button buyMule_Button;
    @FXML
    private Text time;

    private Player currentPlayer;

    @FXML
    private void returntoMapPressed(ActionEvent event) throws Exception {
        mc.switchtoMainMapScreen();
    }

    @FXML
    private void pubPressed(ActionEvent event) throws Exception {
        Random rand = new Random();
        int winnings = rand.nextInt(6969);
        currentPlayer.adjustMoney(winnings);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Pub");
        alert.setHeaderText("Winnings From Gambling");
        alert.setContentText(String.format("You won $%d!", winnings));
        alert.showAndWait();


        mc.endTurn();
    }

    @FXML
    private void buyMulePressed(ActionEvent event) {
        int price = mc.getStore().getMulePrice();
        if (price < currentPlayer.getMoney()) {
            if (!currentPlayer.hasMule()) {
                currentPlayer.setMule(new Mule());
                currentPlayer.adjustMoney(-mc.getStore().getMulePrice());
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Mule Store");
                alert.setHeaderText("Mule Purchased");
                alert.setContentText(String.format("You purchased a Mule! Outfit it at a store."));
                alert.showAndWait();
            }
        }
    }

    @FXML
    private void smithorePressed(ActionEvent event) {
        if (currentPlayer.hasMule()) {
            if (mc.getStore().getSmithoreMulePrice() < currentPlayer.getMoney()) {
                currentPlayer.getMule().setType(Mule.MuleType.SMITHORE);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Mule Store");
                alert.setHeaderText("Mule Outfitted");
                alert.setContentText(String.format("You have outfitted your Mule to produce Smithore!"));
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Mule Store");
            alert.setHeaderText("No Mule");
            alert.setContentText(String.format("Purchase a Mule eto outfit it!"));
            alert.showAndWait();
        }
    }

    @FXML
    private void foodPressed(ActionEvent event) {
        if (currentPlayer.hasMule()) {
            if (mc.getStore().getFoodMulePrice() < currentPlayer.getMoney()) {
                currentPlayer.getMule().setType(Mule.MuleType.FOOD);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Mule Store");
                alert.setHeaderText("Mule Outfitted");
                alert.setContentText(String.format("You have outfitted your Mule to produce Food!"));
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Mule Store");
            alert.setHeaderText("No Mule");
            alert.setContentText(String.format("Purchase a Mule eto outfit it!"));
            alert.showAndWait();
        }
    }

    @FXML
    private void energyPresed(ActionEvent event) {
        if (currentPlayer.hasMule()) {
            if (mc.getStore().getEnergyMulePrice() < currentPlayer.getMoney()) {
                currentPlayer.getMule().setType(Mule.MuleType.ENERGY);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Mule Store");
                alert.setHeaderText("Mule Outfitted");
                alert.setContentText(String.format("You have outfitted your Mule to produce Energy!"));
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Mule Store");
            alert.setHeaderText("No Mule");
            alert.setContentText(String.format("Purchase a Mule eto outfit it!"));
            alert.showAndWait();
        }
    }

    @FXML
    private void storePressed(ActionEvent event) {
        try {
            mc.switchtoStoreScreen();

        } catch (Exception e){
            System.out.println("Exception at loading store");
        }
    }

    public void updateTime() {
        time.setText(String.format("%d", mc.getRoundTime()));
    }
    public void setMainController(MainController mc) {
        this.mc = mc;
        currentPlayer = mc.getCurrentPlayer();
    }
}
