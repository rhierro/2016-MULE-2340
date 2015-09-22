/**
 * Created by Henry on 9/9/2015.
 */
package Mule;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.awt.*;
import java.util.*;
import java.util.List;

public class PlayerConfigurationScreenController {



    private int playersAdded = 0;
    private MuleGame mainApp;
    private String name;
    private Color color = Color.WHITE;
    private String race;
    private int players;
    private ObservableList<String> races =
            FXCollections.observableArrayList(
                    "Packer",
                    "Spheroid",
                    "Humanoid",
                    "Leggite",
                    "Flapper",
                    "Bonzoid",
                    "Mechtron",
                    "Gollumer"
            );

    private List<String> compNames = Arrays.asList("Bob", "Joe", "Bill");

    @FXML
    private ComboBox<String> race_combobox;
    @FXML
    private TextField name_textfield;
    @FXML
    private ColorPicker color_colorpicker;
    @FXML
    private Label testLabel;
    @FXML
    private Label error_label;
    @FXML
    private Label playerNum_label;

    private MainController mc;


    @FXML
    void initialize() {
        for (String s: races) {
            race_combobox.getItems().add(s);
        }
        race_combobox.setValue(races.get(0));

    }

    public PlayerConfigurationScreenController() {

    }


    @FXML
    private void continuePressed(ActionEvent event) throws Exception {
        if (name_textfield.getText().trim().equals("")) {
            error_label.setText("Please enter a name");
        } else {
            name = name_textfield.getText();
            race = race_combobox.getValue();
            color = color_colorpicker.getValue();
            Player p = new Player(name, race, color);
            mc.addPlayer(p);
            name_textfield.setText("");
            playersAdded++;
        }

        if (playersAdded == mc.getNumberOfPlayers()) {
            if (playersAdded < 4) {
                // generate computer player
                for (int i = 0; i < 4-playersAdded; i++) {
                    Random rand = new Random();
                    double r = rand.nextDouble() % 255;
                    double g = rand.nextDouble() % 255;
                    double b = rand.nextDouble() % 255;
                    Color color = new Color(r, g, b, 1);
                    Player c = new Player(compNames.get(i), races.get(i), color);
                    mc.addPlayer(c);
                }
            }

            Stage stageN = (Stage) ((Node) event.getSource()).getScene()
                    .getWindow();
            mc.loadPlayerOverviewScreen();



        } else {
            playerNum_label.setText(String.format("Player %d", playersAdded + 1));

        }



    }
    @FXML
    private void inputboxEntered(KeyEvent event) {
        error_label.setText("");
    }

    public void setMainController(MainController mc) {
        this.mc = mc;
    }

}