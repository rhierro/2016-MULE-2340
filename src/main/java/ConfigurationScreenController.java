import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.Parent;
import sun.applet.Main;

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
    @FXML
    private Button next_button;

    private int difficulty = 1;
    private int map = 1;
    private int players = 1;
    private MainController mc;

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
        if (map < 3) {
            map++;
        }
        if (map == 1) {
            map_label.setText("Map1");
        } else if (map == 2) {
            map_label.setText("Map2");
        } else if (map == 3) {
            map_label.setText("Map3");
        }
    }

    @FXML
    private void decreaseMap(ActionEvent event) {
        if (map > 0) {
            map--;
        }
        if (map == 1) {
            map_label.setText("Map1");
        } else if (map == 2) {
            map_label.setText("Map2");
        } else if (map == 3) {
            map_label.setText("Map3");
        }
    }

    @FXML
    private void increasePlayers(ActionEvent event) {
        if (players < 4) {
            players++;
        }
        players_label.setText(String.valueOf(players));
    }

    @FXML
    private void decreasePlayers(ActionEvent event) {
        if (players > 1) {
            players--;
        }
        players_label.setText(String.valueOf(players));
    }

    @FXML
    private void next(ActionEvent event) throws Exception{
        mc.setDifficulty(difficulty);
        mc.setMap(map);
        mc.setNumberOfPlayers(players);
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("PlayerConfigurationScreen.fxml"));
        Parent config = loader.load();
        Scene sceneConfig = new Scene(config);
        Stage stageN = (Stage) ((Node) event.getSource()).getScene()
                .getWindow();
        stageN.setScene(sceneConfig);
        stageN.show();
        PlayerConfigurationScreenController controller = loader.getController();


    }

    public void setMainController(MainController mc) {
        this.mc = mc;
    }


}

