package Mule;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.util.PriorityQueue;

/**
 * Created by Henry on 9/18/2015.
 */
public class LandBuyingMapController {

    private MainController mc;
    private Land[][] landArray = new Land[5][9];
    private int currentPlayerNum = 0;
    private PriorityQueue<Player> playerArray;
    private ObservableList<Land[]> landList;
    private int currentRound;
    private Player currentPlayer;

    @FXML
    private Pane map_pane;

    @FXML
    private Label test_label;

    @FXML
    private void initialize() {
    }

    private Text info;

    @FXML
    public void generateButtons() throws Exception {
        currentRound = mc.getCurrentRound();
        playerArray = new PriorityQueue<Player>(mc.getPlayers());
        landList = mc.getMap();
        currentPlayer = playerArray.poll();
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
                    + "-fx-background-size: stretch, auto;"
                    + "-fx-background-position: right bottom;"
                    + "-fx-background-repeat: stretch stretch;", image));
        }

        for (int i = 0; i < 5; i++) { // column
            for (int j = 0; j < 9; j++) { // row
                Button landButton = new Button();
                landButton.setPrefHeight(144.0);
                landButton.setPrefWidth(120.0);
                landButton.setLayoutX(1 + (j * (120)));
                landButton.setLayoutY(1 + (i * (144)));
                int tempX = (int) landButton.getLayoutX();
                int tempY = (int) landButton.getLayoutY();
                landButton.setStyle("-fx-background-color: transparent");
                if (landList.get(i)[j].getOwner() != null) {
                    int red = (int) (landList.get(i)[j].getOwner().getColor().
                            getRed() * 255);
                    int green = (int) (landList.get(i)[j].getOwner().
                            getColor().getGreen() * 255);
                    int blue = (int) (landList.get(i)[j].getOwner().getColor().
                            getBlue() * 255);
                    String color = toRGBCode(landList.get(i)[j].getOwner().
                            getColor());
                    landButton.setStyle(String.format("-fx-border-color: %s;"
                            + "-fx-border-width: 10px; "
                            + "-fx-background-color: rgba(%s, %s, %s, 0.5);",
                            color, red, green, blue));
                }
                if ((i == 2) && (j == 4)) {
                    // set middle button to be pass button
                    landButton.setStyle("-fx-background-color: whitesmoke;"
                            + "-fx-border-color: black;"
                            + "-fx-border-width: 15px");
                    landButton.setText("PASS");
                    landButton.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            passButtonPressed(event);
                            // test_label.setText(String.
                            // format("%f, %f", landButton.getLayoutX(),
                            // landButton.getLayoutY()));
                        }
                    });
                } else {
                    landButton.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            buttonPressed(event);
                            // test_label.setText(String.
                            // format("%f, %f", landButton.getLayoutX(),
                            // landButton.getLayoutY()));
                        }
                    });

                    landButton.setOnMouseEntered(
                            new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent event) {
                                buttonEntered(event);
                            }
                        });

                    landButton.setOnMouseExited(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            buttonExited(event);
                        }
                    });
                }
                map_pane.getChildren().add(landButton);
            }
        }
        Color color = currentPlayer.getColor();
        info = new Text("test");
        info.setText("Player " + (currentPlayerNum + 1) + "--"
                + currentPlayer.getName() + "-- $"
                + currentPlayer.getMoney());
        TextFlow infoFlow = new TextFlow(info);
        infoFlow.setLayoutX(8);
        infoFlow.setLayoutY(689);
        infoFlow.setStyle("-fx-background-color:" + toRGBCode(color));
        map_pane.getChildren().add(infoFlow);
    }

    public void passButtonPressed(ActionEvent event) {

        try {
            if (currentPlayerNum == 3) {
                mc.startTurn();
            } else {
                currentPlayerNum++;
                currentPlayer = playerArray.poll();
                info.setText("Player " + (currentPlayerNum + 1) + "--"
                        + currentPlayer.getName() + "-- $"
                        + currentPlayer.getMoney());
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void buttonPressed(ActionEvent event) {
        try {
            if (currentPlayerNum < 4) {
                Button sourceButton = (Button) event.getSource();
                double buttonX = sourceButton.getLayoutX();
                double buttonY = sourceButton.getLayoutY();
                int intButtonXIndex = (int) (buttonX - 1) / 120;
                int intButtonYIndex = (int) (buttonY - 1) / 144;

                if (landList.get(intButtonYIndex)[intButtonXIndex].
                        getOwner() == null) {
                    landList.get(intButtonYIndex)[intButtonXIndex].
                            setOwner(currentPlayer);

                    test_label.setText(String.format("%d, %d", (int) buttonX,
                            (int) buttonY));
                    int red = (int) (currentPlayer.getColor().getRed() * 255);
                    int green = (int) (currentPlayer.getColor().
                            getGreen() * 255);
                    int blue = (int) (currentPlayer.getColor().
                            getBlue() * 255);
                    String color = toRGBCode(currentPlayer.getColor());
                    sourceButton.setStyle(String.format("-fx-border-color: %s;"
                            + "-fx-border-width: 10px; "
                            + "-fx-background-color: rgba(%s, %s, %s, 0.3);",
                            color, red, green, blue));
                    sourceButton.setText(currentPlayer.getName());
                    if (currentRound > 2) {
                        currentPlayer.adjustMoney(-6969);
                    }
                    if (currentPlayerNum == 3) {
                        mc.startTurn();
                    } else {
                        currentPlayerNum++;
                        currentPlayer = playerArray.poll();
                    }

                }
                info.setText("Player " + (currentPlayerNum + 1 + "--"
                        + currentPlayer.getName() + "-- $"
                        + currentPlayer.getMoney()));

            }
        } catch (Exception e) {
            System.out.println("Exception at land buying button.");
        }
    }

    public void buttonEntered(MouseEvent event) {
        Button b = (Button) event.getSource();
        double buttonX = b.getLayoutX();
        double buttonY = b.getLayoutY();
        int intButtonXIndex = (int) (buttonX - 1) / 120;
        int intButtonYIndex = (int) (buttonY - 1) / 144;
        if (landList.get(intButtonYIndex)[intButtonXIndex].
                getOwner() == null) {
            if ((currentRound == 1) || (currentRound == 2)) {
                b.setText("$0 \n" + landList.
                        get(intButtonYIndex)[intButtonXIndex].getType().
                        toString());
            } else {
                b.setText("$6969 \n" + landList.
                        get(intButtonYIndex)[intButtonXIndex].getType().
                        toString());
            }
            b.setStyle("-fx-border-color: grey; -fx-border-width: 10px;"
                    + "-fx-background-color: rgba(255,255,255, 0.3)");
        }
    }

    public void buttonExited(MouseEvent event) {
        Button b = (Button) event.getSource();
        double buttonX = b.getLayoutX();
        double buttonY = b.getLayoutY();
        int intButtonXIndex = (int) (buttonX - 1) / 120;
        int intButtonYIndex = (int) (buttonY - 1) / 144;
        if (landList.get(intButtonYIndex)[intButtonXIndex].getOwner() == null) {
            b.setStyle("-fx-background-color: transparent");
        }
        b.setText("");
    }

    public static String toRGBCode(Color color) {
        return String.format("#%02X%02X%02X",
                (int) (color.getRed() * 255),
                (int) (color.getGreen() * 255),
                (int) (color.getBlue() * 255));
    }

    public void setMainController(MainController mc) {
        this.mc = mc;
    }



}