import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;

public class ConfigurationScreenController{

    @FXML
    private Label difficulty_label;
    @FXML
    private Label map_label;
    @FXML
    private Label players_label;
    @FXML
    private Button increase_difficulty;
    @FXML
    private Button decrease_difficulty;
    @FXML
    private Button increase_map;
    @FXML
    private Button decrease_map;
    @FXML
    private Button increase_players;
    @FXML
    private Button decrease_players;

    private int difficulty = 1;

    @FXML
    private void increaseDifficulty(ActionEvent event) {
        if (difficulty < 3) {
            difficulty++;
        }
        if (difficulty == 1) {
            difficulty_label.setText("Beginner");
        } else if (difficulty == 2) {
            difficulty_label.setText("Medium");
        } else if (difficulty == 3) {
            difficulty_label.setText("Expert");
        }
    }

    @FXML
    private void decreaseDifficulty(ActionEvent event) {
        if (difficulty > 0) {
            difficulty--;
        }
        if (difficulty == 1) {
            difficulty_label.setText("Beginner");
        } else if (difficulty == 2) {
            difficulty_label.setText("Medium");
        } else if (difficulty == 3) {
            difficulty_label.setText("Expert");
        }
    }

    @FXML
    private void increaseMap(ActionEvent event) {

    }

}

