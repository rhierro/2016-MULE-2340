/**
 * Created by Henry on 9/14/2015.
 */
package Mule;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Node;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
//import javafx.stage.Stage;

import java.util.ArrayList;

public class PlayerOverviewScreenController {
    private MainController mc;
    private ArrayList<Player> players;

    @FXML
    private ObservableList<VBox> vboxes = FXCollections.observableArrayList();

    @FXML
    private VBox player1_vbox;
    @FXML
    private VBox player2_vbox;
    @FXML
    private VBox player3_vbox;
    @FXML
    private VBox player4_vbox;




    @FXML
    public void initialize() {
        vboxes.add(player1_vbox);
        vboxes.add(player2_vbox);
        vboxes.add(player3_vbox);
        vboxes.add(player4_vbox);
    }

    public void generateOverview() {
        players = new ArrayList<Player>(mc.getPlayers());
        for (int i = 0; i < vboxes.size(); i++) {
            Label player = new Label(String.format("Player %d", i + 1));
            vboxes.get(i).getChildren().add(player);
            Label name = new Label("Name: " + players.get(i).getName());
            vboxes.get(i).getChildren().add(name);
            Label race = new Label("Race: " + players.get(i).getRace());
            String color = toRGBCode(players.get(i).getColor());
            int red = (int) (players.get(i).getColor().getRed() * 255);
            int  green = (int) (players.get(i).getColor().getGreen() * 255);
            int blue = (int) (players.get(i).getColor().getBlue() * 255);
            vboxes.get(i).setStyle(String.format("-fx-border-color: %s; "
                            + "-fx-border-width: 10px; "
                            + "-fx-background-color: rgba(%s, %s, %s, 0.5);",
                    color, red, green, blue));

        }
    }

    @FXML
    private void OKPressed(ActionEvent event) throws Exception {
        mc.initiateRound();
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
