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
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.util.Random;

/**
 * Created by Henry on 9/18/2015.
 */
public class LandBuyingMapController {

    private MainController mc;
    private Land[][] landArray = new Land[5][5];
    private int currentPlayer = 0;
    private ObservableList<Player> playerArray;
    private ObservableList<Land[]> landList;
    private int currentRound;

    @FXML
    private Pane map_pane;

    @FXML
    private Label test_label;

    @FXML
    private void initialize() {
    }

    private Text info;
    private TextFlow infoFlow;

    @FXML
    public void generateButtons() throws Exception {
        playerArray = mc.getPlayers();
        landList = mc.getMap();
        int mapNum = mc.getMapNum();
        if (mapNum == 0) {
            String image = getClass().getResource("resources/Map0.jpg").toExternalForm();
            map_pane.setStyle(String.format("-fx-background-image: url(%s);", image));
        } else if (mapNum == 1) {
            String image = getClass().getResource("resources/Map1.jpg").toExternalForm();
            map_pane.setStyle(String.format("-fx-background-image: url(%s); -fx-background-size: stretch, auto;" +
                    "    -fx-background-position: right bottom;" +
                    "    -fx-background-repeat: stretch stretch;", image));
        }

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                Button landButton = new Button();
                landButton.setPrefHeight(80.0);
                landButton.setPrefWidth(120.0);
                landButton.setLayoutX(1 + (j * (120)));
                landButton.setLayoutY(1 + (i * (80)));
                int tempX = (int) landButton.getLayoutX();
                int tempY = (int) landButton.getLayoutY();//
                landButton.setStyle("-fx-background-color: transparent");
                if (landList.get(i)[j].getOwner() != null) {
                    int red = (int) (landList.get(i)[j].getOwner().getColor().getRed() * 255);
                    int green = (int) (landList.get(i)[j].getOwner().getColor().getGreen() * 255);
                    int blue = (int) (landList.get(i)[j].getOwner().getColor().getBlue() * 255);
                    String color = toRGBCode(landList.get(i)[j].getOwner().getColor());
                    landButton.setStyle(String.format("-fx-border-color: %s; -fx-border-width: 10px; " +
                            "-fx-background-color: rgba(%s, %s, %s, 0.5);", color, red, green, blue));
                }
                if ((i == 2) && (j == 2)) { // set middle button to be pass button
                    landButton.setStyle("-fx-background-color: whitesmoke; -fx-border-color: black; -fx-border-width: 15px");
                    landButton.setText("PASS");
                    landButton.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            passButtonPressed(event);
                            //test_label.setText(String.format("%f, %f", landButton.getLayoutX(), landButton.getLayoutY()));
                        }
                    });
                } else {
                    landButton.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            buttonPressed(event);
                            //test_label.setText(String.format("%f, %f", landButton.getLayoutX(), landButton.getLayoutY()));
                        }
                    });
                }
                map_pane.getChildren().add(landButton);
            }
        }
        info = new Text(3, 391, "test");
        //info.setFill(new Color(0, 0, 0, 0.6));
        infoFlow = new TextFlow(info);
        infoFlow.setLayoutX(3);
        infoFlow.setLayoutY(385);
        infoFlow.setStyle("-fx-background-color: lightgray");
        map_pane.getChildren().add(infoFlow);
    }

    public void passButtonPressed(ActionEvent event) {

        try {
            if (currentPlayer == 3) {
                mc.loadMainMapScreen();
            }
        }
        catch (Exception e) {

        }
    }

    public void buttonPressed(ActionEvent event) {
        try {
            if (currentPlayer < 4) {
                Button sourceButton = (Button) event.getSource();
                double buttonX = sourceButton.getLayoutX();
                double buttonY = sourceButton.getLayoutY();
                int intButtonXIndex = (int) (buttonX - 1) / 120;
                int intButtonYIndex = (int) (buttonY - 1) / 80;

                if (landList.get(intButtonYIndex)[intButtonXIndex].getOwner() == null) {
                    landList.get(intButtonYIndex)[intButtonXIndex].setOwner(playerArray.get(currentPlayer));

                    test_label.setText(String.format("%d, %d", (int) buttonX, (int) buttonY));
                    int red = (int) (playerArray.get(currentPlayer).getColor().getRed() * 255);
                    int green = (int) (playerArray.get(currentPlayer).getColor().getGreen() * 255);
                    int blue = (int) (playerArray.get(currentPlayer).getColor().getBlue() * 255);
                    String color = toRGBCode(playerArray.get(currentPlayer).getColor());
                    sourceButton.setStyle(String.format("-fx-border-color: %s; -fx-border-width: 10px; " +
                            "-fx-background-color: rgba(%s, %s, %s, 0.3);", color, red, green, blue));
                    sourceButton.setText(playerArray.get(currentPlayer).getName());
                    playerArray.get(currentPlayer).adjustMoney(-6969);

                    if (currentPlayer == 3) {
                        mc.loadMainMapScreen();
                    } else {
                        currentPlayer++;
                    }

                }
            }
        } catch (Exception e) {

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



}