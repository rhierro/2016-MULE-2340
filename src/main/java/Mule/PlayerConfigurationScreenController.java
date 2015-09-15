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

    @FXML
    private void continuePressed(ActionEvent event) throws Exception {
        if (name_textfield.getText().trim().equals("")) {
            error_label.setText("Please enter a name");
        } else if (playersAdded == mc.getNumberOfPlayers()) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("PlayerOverviewScreen.fxml"));
            Parent config = loader.load();
            Scene sceneConfig = new Scene(config);
            Stage stageN = (Stage) ((Node) event.getSource()).getScene()
                    .getWindow();
            stageN.setScene(sceneConfig);
            stageN.show();
            PlayerOverviewScreenController controller = loader.getController();
            controller.setMainController(mc);
        } else {
            name = name_textfield.getText();
            race = race_combobox.getValue();
            color = color_colorpicker.getValue();
            Player p = new Player(name, race, color);
            mc.addPlayer(p);
            playersAdded++;
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
