package Mule;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

/**
 * Created by Henry on 9/22/2015.
 */
public class MainMapController {

    private MainController mc;
    private ObservableList<Player> playerArray;
    private ObservableList<Land[]> landList;
    private Player currentPlayer;


    @FXML
    private Pane map_pane;

    public void generateButtons() throws Exception {
        currentPlayer = mc.getCurrentPlayer();
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

        for (int i = 0; i < 5; i++) { //column
            for (int j = 0; j < 5; j++) { //row
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
                if ((i == 2) && (j == 2)) { // set middle button to be town button
                    landButton.setStyle("-fx-background-color: transparent; -fx-border-color: black; -fx-border-width: 25px;");
                    landButton.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            townButtonPressed(event);
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
    }

    private void townButtonPressed(ActionEvent event){
        try {
            mc.loadTownMapScreen();
        }
        catch (Exception e) {

        }
    }

    private void buttonPressed(ActionEvent event) {

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
