package Mule;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.util.Random;

/**
 * Created by Henry on 9/18/2015.
 */
public class MapController {

    private MainController mc;
    private Land[][] landArray = new Land[5][5];
    private int currentPlayer = 0;
    ObservableList<Player> playerArray;

    @FXML
    private Pane map_pane;

    @FXML
    private Label test_label;

    @FXML
    private void initialize() {
    }

    @FXML
    public void generateButtons() throws Exception {
        playerArray = mc.getPlayers();
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                Button landButton = new Button();
                landButton.setPrefHeight(80.0);
                landButton.setPrefWidth(120.0);
                landButton.setLayoutX(j + (j * (120)));
                landButton.setLayoutY(i + (i * (80)));
                int tempX = (int)landButton.getLayoutX();
                int tempY = (int)landButton.getLayoutY();
                landButton.setStyle("-fx-background-color: transparent");
                map_pane.getChildren().add(landButton);

                landArray[i][j] = new Land(); //fills in LandArray, i = x values, j = y values

                landButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        buttonPressed(event);
                        //test_label.setText(String.format("%f, %f", landButton.getLayoutX(), landButton.getLayoutY()));
                    }
                });
            }
        }
        initializeLandTypes();
    }

    public void buttonPressed(ActionEvent event) {
        if (currentPlayer < 4) {
            Button sourceButton = (Button) event.getSource();
            double buttonX = sourceButton.getLayoutX();
            double buttonY = sourceButton.getLayoutY();
            int intButtonXIndex = (int) buttonX / 121;
            int intButtonYIndex = (int) buttonY / 81;

            if (landArray[intButtonXIndex][intButtonYIndex].getOwner() == null) {
                landArray[intButtonXIndex][intButtonYIndex].setOwner(playerArray.get(currentPlayer));

                test_label.setText(String.format("%d, %d", (int) buttonX, (int) buttonY));
                int red = (int) (playerArray.get(currentPlayer).getColor().getRed() * 255);
                int green = (int) (playerArray.get(currentPlayer).getColor().getGreen() * 255);
                int blue = (int) (playerArray.get(currentPlayer).getColor().getBlue() * 255);
                String color = toRGBCode(playerArray.get(currentPlayer).getColor());
                sourceButton.setStyle(String.format("-fx-border-color: %s; -fx-border-width: 10px; " +
                        "-fx-background-color: rgba(%s, %s, %s, 0.5);", color, red, green, blue));
                sourceButton.setText(playerArray.get(currentPlayer).getName());
                playerArray.get(currentPlayer).adjustMoney(-6969);

                currentPlayer++;
            }
        }
    }

    public static String toRGBCode(Color color) {
        return String.format( "#%02X%02X%02X",
                (int)( color.getRed() * 255 ),
                (int)( color.getGreen() * 255 ),
                (int)( color.getBlue() * 255 ) );
    }

    public void setMainController(MainController mc) {
        this.mc = mc;
    }

    private void initializeLandTypes() {
        int[][] mountainArray = {{0, 1}, {1, 1}, {1, 2}, {3, 3}, {4, 3}, {3, 4}, {4, 4}};
        int[][] waterArray = {{2, 0}, {3, 0}, {4, 0}, {2, 1}, {2, 2}, {2, 3}, {1, 3}, {1, 4}};

        for (int[] mountain : mountainArray) {

            landArray[mountain[0]][mountain[1]].setType("mountain");
        }

        for (int[] water : waterArray) {
            landArray[water[0]][water[1]].setType("water");
        }
    }


}