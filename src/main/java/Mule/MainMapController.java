package Mule;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Timer;

/**
 * Created by Henry on 9/22/2015.
 * The main map controller class
 */
public class MainMapController {

    private MainController mc;
    private PriorityQueue<Player> playerArray;
    private List<Land[]> landList;
    private int currentPlayerNum;
    private Player currentPlayer;
    private Timer timer;
    private final ArrayList<Button> buttonList = new ArrayList<>();


    @FXML
    private Pane map_pane;

    private Text time;

    /**
     * Generates the buttons upon initialization
     * @throws Exception
     */
    public void generateButtons() {
        //basically the initiate method
        int currentPlayerNum = mc.getCurrentPlayerNumber();
        currentPlayer = mc.getCurrentPlayer();
        landList = mc.getMap();
        int mapNum = mc.getMapNum();
        if (mapNum == 0) {
            String image = getClass().
                    getResource("resources/MuleMap0-Grid.jpg").
                    toExternalForm();
            map_pane.setStyle(String.format("-fx-background-image: url(%s);",
                    image));
        } else if (mapNum == 1) {
            String image = getClass().getResource("resources/Map1.jpg").
                    toExternalForm();
            map_pane.setStyle(String.format("-fx-background-image: url(%s);"
                    + " -fx-background-size: stretch, auto;"
                    + " -fx-background-position: right bottom;"
                    + " -fx-background-repeat: stretch stretch;", image));
        }

        for (int i = 0; i < 5; i++) { //column
            for (int j = 0; j < 9; j++) { //row
                Button landButton = new Button();
                landButton.setPrefHeight(144.0);
                landButton.setPrefWidth(120.0);
                landButton.setLayoutX(1 + (j * (120)));
                landButton.setLayoutY(1 + (i * (144)));
                landButton.setStyle("-fx-background-color: transparent");
                if (landList.get(i)[j].getOwner() != null) {
                    int red = (int) (landList.get(i)[j].getOwner().
                            getColor().getRed() * 255);
                    int green = (int) (landList.get(i)[j].getOwner().
                            getColor().getGreen() * 255);
                    int blue = (int) (landList.get(i)[j].getOwner().
                            getColor().getBlue() * 255);
                    String color = toRGBCode(landList.get(i)[j].
                            getOwner().getColor());
                    landButton.setStyle(String.format("-fx-border-color: %s; "
                            + "-fx-border-width: 10px; "
                            + "-fx-background-color: rgba(%s, %s, %s, 0.5);",
                            color, red, green, blue));
                }
                if ((i == 2) && (j == 4)) {
                    // set middle button to be town button
                    landButton.setStyle("-fx-background-color: transparent;"
                            + "-fx-border-color: black; -fx-border-width:"
                            + " 25px;");
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
                buttonList.add(landButton);
            }
        }

        for (Button b : buttonList) {
            addTypeIcon(b);
        }
        Color color = currentPlayer.getColor();
        Text info = new Text("");
        info.setText("Player " + (currentPlayerNum + 1) + "--"
                + currentPlayer.getName() + "-- $"
                + (currentPlayer.getMoney()));
        TextFlow infoFlow = new TextFlow(info);
        infoFlow.setLayoutX(8);
        infoFlow.setLayoutY(689);
        infoFlow.setStyle("-fx-background-color:" + toRGBCode(color));
        map_pane.getChildren().add(infoFlow);
        time = new Text("10");
        TextFlow timeFlow = new TextFlow(time);
        timeFlow.setLayoutX(500);
        timeFlow.setLayoutY(689);
        timeFlow.setStyle("-fx-background-color: lightgray");
        map_pane.getChildren().add(timeFlow);


    }

    /**
     * Updates the time displayed in the label
     */
    public void updateTime() {
        time.setText(String.format("%d", mc.getRoundTime()));

    }

    @FXML
    private void townButtonPressed(ActionEvent event) {
        try {
            mc.switchtoTownMapScreen();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
    }

    @FXML
    private void buttonPressed(ActionEvent event) {
        if (currentPlayer.hasMule()) {
            Button b = (Button) event.getSource();
            double buttonX = b.getLayoutX();
            double buttonY = b.getLayoutY();
            int intButtonXIndex = (int) (buttonX - 1) / 120;
            int intButtonYIndex = (int) (buttonY - 1) / 144;
            Land land = landList.get(intButtonYIndex)[intButtonXIndex];
            if (land.getOwner() == currentPlayer) {
                if (!land.hasMule()) {
                    land.setProductionType(currentPlayer.getMule().getType());
                    addTypeIcon(b);
                    land.setHasMule();
                }
            } else {
                currentPlayer.removeMule();
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Mule");
                alert.setHeaderText("Mule Ran Away");
                alert.setContentText("Your Mule has run away! Sucks to suck.");
                alert.showAndWait();
            }
        }
    }

    @FXML
    private void addTypeIcon(Button b) {
        double buttonX = b.getLayoutX();
        double buttonY = b.getLayoutY();
        int intButtonXIndex = (int) (buttonX - 1) / 120;
        int intButtonYIndex = (int) (buttonY - 1) / 144;
        Land land = landList.get(intButtonYIndex)[intButtonXIndex];
        ImageView view = new ImageView();
        view.setX(buttonX);
        view.setY(buttonY);
        Image image;
        Mule.MuleType type = land.getProductionType();
        if (type == Mule.MuleType.SMITHORE) {
            image = new Image(getClass().
                    getResourceAsStream("resources/SmithoreIcon.png"));
            view.setImage(image);
        } else if (type == Mule.MuleType.FOOD) {
            image = new Image(getClass().
                    getResourceAsStream("resources/FoodIcon.png"));
            view.setImage(image);
        } else if (type == Mule.MuleType.ENERGY) {
            image = new Image(getClass().
                    getResourceAsStream("resources/EnergyIcon.png"));
            view.setImage(image);
        }
        map_pane.getChildren().add(view);

    }



    private static String toRGBCode(Color color) {
        return String.format("#%02X%02X%02X",
                (int) (color.getRed() * 255),
                (int) (color.getGreen() * 255),
                (int) (color.getBlue() * 255));
    }

    /**
     * Sets the reference back to the main controller
     * @param mc maincontroller object
     */
    public void setMainController(MainController mc) {
        this.mc = mc;
    }
}
